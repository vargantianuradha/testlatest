package ca.cityofkingston.payments;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.http.Cookie;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;

import com.liferay.portal.kernel.util.SortedArrayList;

public class ServiceClientDelegate {

	static final String HOSTED_CHECKOUT_RESPONSE_ASPX = "HostedCheckoutResponse.aspx";
	//FIXME: RV we need to determine this value at runtime
	/**
	 * @deprecated Use {@link RequestContext#PORTLET_ID} instead
	 */
	private static final String PORTLET_ID = RequestContext.PORTLET_ID;
	private static final String NEXT_PAGE_PARAM_NAME 	= "wNext";

	private RequestContext requestContext;
	
	private static final Logger LOGGER = Logger.getLogger(ServiceClientDelegate.class);

	
	public ServiceClientDelegate(RequestContext requestContext) {
		this.requestContext = requestContext;
	}
	
	public String getURL() {
		
		StringBuilder url = new StringBuilder(requestContext.getServerURL());
		Map<String, String> queryString = getQueryString();
		appendPath(queryString, url);
		
		appendQueryStringParams(queryString, url);
		return url.toString(); 
	}
	
	public List<FormParameter> getPostParameters() {
		
		ArrayList<FormParameter> postParams = new ArrayList<FormParameter>();
		
		if(!isHostedCheckoutRequest(getQueryString())) {
			
			makePostParams(postParams);		
		
		} else {
			
			makeHostedCheckoutPostParams(postParams);				

		}
		
		return postParams;
	}

	public String getParameter(String name) {
		return requestContext.getParameter(name);
	}
	
	public Cookie getCookie() {
		return requestContext.getGbizSessionCookie();
	}
	
	public String getUserAgent() {
		return requestContext.getHeader("user-agent");
	}
	
	public void handle(ServiceError serviceError, String responseBody) {
		
		LOGGER.debug("Handling service error " + serviceError);
		
		if(requestContext.isMailEnabled()) {
			LOGGER.debug("Sending error email for " + serviceError);
			String template = serviceError.getTemplate(requestContext.getSourceIPAddress(), new Date(), responseBody);
			getMessenger().send(template, serviceError.getSubject());
		} else {
			LOGGER.info("Not sending error email because it's disabled");
		}
	}
	
	private void makeHostedCheckoutPostParams(ArrayList<FormParameter> postParams) {
		
		postParams.add(new FormParameter("GrossAmount").add(requestContext.getParameter("GrossAmount")));
		postParams.add(new FormParameter("CustomerFirstName").add(requestContext.getParameter("CustomerFirstName")));
		postParams.add(new FormParameter("CustomerLastName").add(requestContext.getParameter("CustomerLastName")));
		postParams.add(new FormParameter("CustomerEmailAddress").add(requestContext.getParameter("CustomerEmailAddress")));
		postParams.add(new FormParameter("CustomerPhoneNumber").add(requestContext.getParameter("CustomerPhoneNumber")));
		postParams.add(new FormParameter("Reference1").add(requestContext.getParameter("Reference1")));
		postParams.add(new FormParameter("Reference2").add(requestContext.getParameter("Reference2")));
		postParams.add(new FormParameter("TxnTimestamp").add(requestContext.getParameter("TxnTimestamp")));	
		postParams.add(new FormParameter("AuthCode").add(requestContext.getParameter("AuthCode")));	
		postParams.add(new FormParameter("InteracName").add(requestContext.getParameter("InteracName")));	
		postParams.add(new FormParameter("InteracConf").add(requestContext.getParameter("InteracConf")));	
		postParams.add(new FormParameter("TxnStatusCodeId").add(requestContext.getParameter("TxnStatusCodeId")));	
		postParams.add(new FormParameter("MessageText").add(requestContext.getParameter("MessageText")));
		postParams.add(new FormParameter("PaymentType").add(requestContext.getParameter("PaymentType")));
		postParams.add(new FormParameter("PaymentTypeCode").add(requestContext.getParameter("PaymentTypeCode")));
		postParams.add(new FormParameter("TenderType").add(requestContext.getParameter("TenderType")));
		postParams.add(new FormParameter("StatusCode").add(requestContext.getParameter("StatusCode")));
		postParams.add(new FormParameter("PaymentProcessorOrderNumber").add(requestContext.getParameter("PaymentProcessorOrderNumber")));
		postParams.add(new FormParameter("PaymentProcessorTransactionID").add(requestContext.getParameter("PaymentProcessorTransactionID")));
		postParams.add(new FormParameter("IsSilentPost").add(requestContext.getParameter("IsSilentPost")));
		postParams.add(new FormParameter("HashValue").add(requestContext.getParameter("HashValue")));
	}

	private void makePostParams(ArrayList<FormParameter> postParams) {
		
		Iterator<Entry<String, String[]>> iterator = requestContext.getRequestBody().entrySet().iterator();
		
		while(iterator.hasNext()) {
			
			Entry<String, String[]> entry = iterator.next();
			
			String paramName = entry.getKey();
			if(!isLiferayParameter(paramName)) {
				
				FormParameter param = new FormParameter(paramName);
				
				postParams.add(param);
				
				String[] value = entry.getValue();
				
				if(!isMultipleValuesSupported(paramName)) {
					value = value.length > 1 ? new String[] {value[0]} : value;
				}
				
				param.getValues().addAll(Arrays.asList(value));
				
				LOGGER.debug("Including " + paramName + " as a form parameter");
			} else {
				
				LOGGER.debug("Not including " + paramName + " as a form paramter because it's a Liferay parameter");
			}
		}
	}

	private boolean isMultipleValuesSupported(String paramName) {
		return "PetRenewals".equalsIgnoreCase(paramName) || "OLTPProducts".equalsIgnoreCase(paramName);
	}		
	
	private Map<String, String> getQueryString() {
		
		Map<String, String> queryParams = new Hashtable<String, String>();
		
		String[] query = requestContext.getRequestedURL().split("\\?");
		String[] params = query.length == 2 ? query[1].split("\\&") : new String[]{};
		for(int i = 0; i < params.length; i++) {
			String[] paramNameAndValue = params[i].split("=");
			String paramName = removePortletIdPrefixFromParameterName(paramNameAndValue[0]);
			if(!isLiferayParameter(paramName)) {
			
				String paramValue;
				
				if(paramNameAndValue.length > 1) {
					paramValue = paramNameAndValue[1];
				} else {
					paramValue = "";
				}
				
				LOGGER.debug("URL querystring param added " + paramName + " with value " + paramValue);
//				if(paramName.startsWith("_")){
//					paramName = paramName.replace("_","");
//				} 
				queryParams.put(paramName, paramValue);
				
			} else {
				LOGGER.debug("Not including param " + paramName + " in URL querystring because it's a Liferay parameter");
			}
		}
		return queryParams;
	}
	
	private void appendQueryStringParams(Map<String, String> queryStringParams, StringBuilder url) {
		
		StringBuilder params = new StringBuilder();

		int currentQueryStringParamPosition = 0;

		for(Iterator<String> iterator = new SortedArrayList<String>(queryStringParams.keySet()).iterator(); iterator.hasNext(); ) {
			
			String name = iterator.next();
			
			if(isParamAppendable(queryStringParams, name)) {
			
				appendParameter(params, name, queryStringParams.get(name), currentQueryStringParamPosition != 0);
				
				currentQueryStringParamPosition++;
				
			}
			
		}
	
		if(StringUtils.isNotEmpty(params.toString())) {
			url.append("?").append(params);
		}
	}

	private void appendParameter(StringBuilder url, String name, String value, boolean shouldAppendAmpersand) {
		
		if(shouldAppendAmpersand) {
			url.append("&");
		}
		
		url.append(name);
		
		if(StringUtils.isNotEmpty(value)) {
			url.append("=").append(value);
		}
	}	
	
	private void appendPath(Map<String, String> queryStringParams, StringBuilder url) {
		
		if(!isHostedCheckoutRequest(queryStringParams)) {
			
			if(queryStringParams.containsKey(NEXT_PAGE_PARAM_NAME)) {
				
				url.append("/").append(queryStringParams.get(NEXT_PAGE_PARAM_NAME)).append(".aspx");
				return;
			}
			
			url.append("Product.aspx");
		} else {
			
			url.append("/HostedCheckoutResponse.aspx");
			
		}
	}
	
	private boolean isParamAppendable(Map<String, String> queryStringParams, String name) {
		return StringUtils.isNotBlank(queryStringParams.get(name)) && !isHostedCheckoutParamValue(queryStringParams, name);
	}	

	public boolean isHostedCheckoutRequest(Map<String, String> queryStringParams) {
		if(queryStringParams == null) {
			queryStringParams = getQueryString();
		}
		
		return queryStringParams.containsKey(HOSTED_CHECKOUT_RESPONSE_ASPX) || queryStringParams.containsValue(HOSTED_CHECKOUT_RESPONSE_ASPX);
	}	
	
	private boolean isHostedCheckoutParamValue(Map<String, String> queryStringParams, String name) {
		return HOSTED_CHECKOUT_RESPONSE_ASPX.equals(queryStringParams.get(name));
	}	
	
	private String removePortletIdPrefixFromParameterName(String param) {
		
		String cleanedParam = param.replace(RequestContext.PORTLET_ID, "");
		
		PaymentRequest.LOGGER.debug("Cleaned param " + param + " to " + cleanedParam);
		return cleanedParam;
	}	

	private boolean isLiferayParameter(String param) {
		return param.startsWith("p_p");
	}

	/*For testing*/
	Messenger getMessenger() {
		return new Messenger();
	}

	static class FormParameter {
		private String name;
		private List<String> values = new ArrayList<String>();
		
		FormParameter(String name) {
			this.name = name;
		}
		
		public String getName() {
			return name;
		}
		public List<String> getValues() {
			return values;
		}

		public FormParameter add(String string) {
			values.add(string);			
			return this;
		}
		
		
	}

}
