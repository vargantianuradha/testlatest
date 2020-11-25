package ca.cityofkingston.payments;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.portlet.PortletURL;
import javax.servlet.http.Cookie;

import org.apache.log4j.Logger;
import org.restlet.data.CookieSetting;



public abstract class PaymentRequest {

	static final Logger LOGGER = Logger.getLogger(PaymentRequest.class);

	//find provincial offence content on any line, case insenstive, and words only
	private static final String FIND_PROVINCIAL_OFFENCE_REGEX = "(?si).*\\bPOA\\b.*";
	
	static final String BEGIN_G_BIZ_CONTENT = "<!--Begin gBiz content-->";
	static final String END_G_BIZ_CONTENT = "<!--End gBiz content-->";
	
	//everything between the comments (includes comments)
	static final String GBIZ_CONTENT_SECTION_REGEX = "((?si)" + BEGIN_G_BIZ_CONTENT + ".*" +  END_G_BIZ_CONTENT + ")";
	
	//everything from the form to the div (includes the div) case insensitive on any line
	static final String FORM_REGEX = "((?si)<form.*<div id=\"TemplatePanel\">)";
	
	//-- look for first lang="fr-CA" or lang="en-CA"
	static final String LANG_REGEX = " lang=\"(\\w\\w)-(\\w\\w)\"";
	
	private Pattern contentPattern;
	private Pattern formPattern;
	private Pattern langPattern;
	
	public static final String DEFAULT_VIEW = "view-jsp";
	public static final String ACTION_URL = "actionURL";

	static final String GBIZ_HOME_PAGE_MESSAGE = "gBiz 5.2 home page";
	static final String GBIZ_HOME_TEMP_HOME_PAGE_MESSAGE = "gBiz 4.4 temporary home page";

	static final String SECURE_URL_TEXT = "PROTECTED URL PART HERE";

	public static final String MAINTENANCE_PATH = "/maintenance.jsp";

	public static final String DOWN_FOR_MAINTENANCE_MESSAGE = "Kingnet Down for Maintenance";
	
	private String[] urlSearchCriteria;
	private ServiceClient serviceClient;

	private RequestContext requestContext;

	

	PaymentRequest(RequestContext requestContext) {
		this.requestContext = requestContext;
		makeRegExPatterns();
		makeURLSearchCriteria();
	}
	
	public static PaymentRequest makeGetRequest(RequestContext requestContext) {
		return new PaymentGetRequest(requestContext);
	}
	
	public static PaymentRequest makePostRequest(RequestContext requestContext) {
		return new PaymentPostRequest(requestContext);
	}	
	
	public View send() {

		View view = requestContext.getViewFromRequest();
		if(view == null) {
			
			view = getNewView();
//			LOGGER.info("Requesting a new view -----"+view.getHTML());
		} else {
			LOGGER.debug("View found in request");
			updateExistingView(view);
		}
		
		LOGGER.info("Next view: " + view.getPath());
		return view;
	}		

	/*For testing*/
	void setServiceClient(ServiceClient serviceClient) {
		this.serviceClient = serviceClient;
	}
	
	protected ServiceClient getServiceClient() {
		/*For testing*/
		if(serviceClient != null) {
			return this.serviceClient;
		}
		
		return new ServiceClient(makeServiceClientDelegate());
	}
	
	private String updateURLs(String html) {
		String scrubbedFormURL = updateFormActionURL(html);
		return updateJavaScriptURLs(scrubbedFormURL);
	}

	private String updateJavaScriptURLs(String html) {
		Pattern scriptSrcPattern = Pattern.compile("(src=\")(.*\")", Pattern.CASE_INSENSITIVE);
		Matcher matcher = scriptSrcPattern.matcher(html);
		
		if(matcher.find()) {
			html = matcher.replaceAll("$1" + requestContext.getScriptDocRoot() +"$2");
			LOGGER.debug("Replaced script URL with " + requestContext.getScriptDocRoot());
		}
		return html;
	}

	private Cookie findCookieInResponse(ClientResponse response) {
		
		for (CookieSetting cookieSetting : response.getCookies()) {
			
			if(RequestContext.GBIZ_COOKIE_NAME.equals(cookieSetting.getName())) {
				LOGGER.debug("Found gBiz cookie " + cookieSetting.getName() + "=" + cookieSetting.getValue() + "  in response");
				return new Cookie(cookieSetting.getName(), cookieSetting.getValue());
			}
		}
		LOGGER.debug("Didn't find gBiz cookie in response");
		return null;
	}
	
	private String getViewPathFromRequestOrConfig() {

		String page = requestContext.getParameter("render");
		if(page == null) {
			LOGGER.debug("Render page parameter is not found. Catalogue will be the next page");
			page = getCataloguePath();
		} else {
			LOGGER.debug("Render page parameter is found. " + page + " + will be the next page");
			page = "/" + page + ".jsp";
		}
		return page;
	}

	private String getCataloguePath() {  
		return requestContext.getInitParameter("view-jsp");
	}	
	
	private String getForm(String page) {
		
		String form = "";
		
		Matcher formMatcher = formPattern.matcher(page);
		
		if(formMatcher.find()) {
			
			LOGGER.debug("Form found in the response");
			
			form = formMatcher.group(0);
			form = form.replaceAll("(?i)enctype=\"multipart/form-data\"", "");
			form = form.replaceAll("(?i)<div id=\"TemplatePanel\">", "");
			LOGGER.debug("form string:\r\n"+form);
			
			Matcher contentPatternMatcher = contentPattern.matcher(page);
			if(contentPatternMatcher.find()) {
				LOGGER.debug("content:\r\n"+contentPatternMatcher.group(0));
				form += contentPatternMatcher.group(0);
			}
	
			form += "</form>";
		} else {
			LOGGER.debug("Didn't find a form in the response");
		}
		
		return form;
	}
	
	private String updateGetRequestURLs(String body) {
		PortletURL renderURL = requestContext.createGetURL();
		
		final String url = renderURL.toString();
		
		LOGGER.info("Render URL " + renderURL);
		
		return rewriteURL(body, new ReWriteStrategy() {
			private String current;

			@Override
			public boolean isReWritable(String body, String current) {
				this.current = current;
				return true;
			}
			
			@Override
			public String rewriteWithParams(String body, String params) {
				return body.replaceAll("(?si)" + current, url + params);
			}
			
		});
	}
	
	private String updateFormActionURL(String page) {
		
		PortletURL portletURL = requestContext.createActionURL();

		if(portletURL != null) {
			final String actionURL = portletURL.toString();
			
			LOGGER.debug("Action request found " + actionURL);

			return rewriteURL(page, new ReWriteStrategy() {
				
				private Pattern queryStringPattern;
				private Matcher queryStringMatcher;

				@Override
				public boolean isReWritable(String body, String current) {
					queryStringPattern = makeFormActionPattern(current);
					queryStringMatcher = queryStringPattern.matcher(body);
					
					return queryStringMatcher.find();
				}
				
				@Override
				public String rewriteWithParams(String body, String params) {
					return queryStringMatcher.replaceAll("$1" + actionURL + "\\" + params + "$4");
				}
				
			});
		} else {
			LOGGER.debug("Doesn't look like an Action request");
		}
		return page;
	}	
	
	private String rewriteURL(String body, ReWriteStrategy strategy) {
		
		String newBody = body;
		
		for (String urlSearchCriterion : urlSearchCriteria) {
			
			if(strategy.isReWritable(newBody, urlSearchCriterion)) {
				
				String params = null;
				
				if("Basket\\.aspx".equals(urlSearchCriterion)) {
					params = "&wNext=Basket&render=payments";
				} else if ("ShippingNoJS\\.aspx".equals(urlSearchCriterion)) {
					params = "&wNext=ShippingNoJS&render=payments";
				} else if ("CheckoutPayment\\.aspx".equals(urlSearchCriterion)) {
					params = "&wNext=CheckoutPayment&render=payments";
				} else if ("CheckoutPayment\\.aspx\\?show".equals(urlSearchCriterion)) {
					params = "&wNext=CheckoutPayment&show&render=payments";
				} else if ("CheckoutConfirmation\\.aspx".equals(urlSearchCriterion)) {
					params = "&wNext=CheckoutConfirmation&render=payments";
				} else {
					params = "&render=payments&";
				}
				newBody = strategy.rewriteWithParams(newBody, params);
			}
		}
		
		return newBody;
	}

	private String stripInlineStyleAttribute(String page) {
		
		Pattern queryStringPattern = Pattern.compile("style=\".*\"");
		Matcher queryStringMatcher = queryStringPattern.matcher(page);
		
		if(queryStringMatcher.find()) {
			page = queryStringMatcher.replaceAll("style=\"\"");
			LOGGER.debug("Strip the inline style attribute");
			return page;
		}
		
		return page;
	}

	private Pattern makeFormActionPattern(String searchName) {
		
		return Pattern.compile("(action=\")(\\.\\/)?(" + searchName + ")(.*\")", Pattern.CASE_INSENSITIVE);
	}

	private void makeRegExPatterns() {
		
		this.contentPattern = Pattern.compile(PaymentRequest.GBIZ_CONTENT_SECTION_REGEX);
		this.formPattern = Pattern.compile(PaymentRequest.FORM_REGEX);
		this.langPattern = Pattern.compile(PaymentRequest.LANG_REGEX);
	}
	
	private void makeURLSearchCriteria() {
		
		this.urlSearchCriteria = new String[] { 
				"Product\\.aspx\\?",
				"Basket\\.aspx\\?", 
				"Basket\\.aspx", 
				"ShippingNoJS\\.aspx",
				"CheckoutPayment\\.aspx\\?show", 
				"CheckoutPayment\\.aspx\\?",
				"CheckoutPayment\\.aspx",
				"CheckoutPayment\\.aspx\\?",
				"CheckoutConfirmation\\.aspx\\?",
				"CheckoutConfirmation\\.aspx"};
	}
	
	ServiceClientDelegate makeServiceClientDelegate() {
		return new ServiceClientDelegate(this.requestContext);
	}	
	
	private View getNewView() {
		
		View view = new View(this.requestContext.getPaymentConfiguration());
		
		if(!isCatalogueRequest()) {  
			//-- Send request to gBiz and get response
			ClientResponse resp = doSend();
			//-- Process the response AND apply to the View object
			applyResponseToView(view, resp);
		} else {  
			view.setPath(getCataloguePath());
		}
		view.setPropertyLookup(requestContext.isPropertyLookup()); 
		return view;
	}
	
	private void updateExistingView(View existingView) {
		
		if(existingView.getHTML() != null) {
			String formActionUpdates = updateFormActionURL(existingView.getHTML());
			existingView.setHTML(updateGetRequestURLs(formActionUpdates));
		}
	}	

	private void applyResponseToView(View view, ClientResponse response) {
		
		applyCookieToView(view, response);

		if(!response.isError()) {   
			String body = response.getBody();
			view.setLang(extractLangFromBody(body));
			view.setHTML(updateURLs(getForm(body)));
			view.setHTML(stripInlineStyleAttribute(view.getHTML()));
			view.setHTML(view.getHTML().replace("language=\"javascript\"", "type=\"text/javascript\""));
			view.setProvincialOffenceContentDisplayable(isProvincialContentDisplayable(body));
			
			if(isCurrentURLAccessDenied() ) {    
				view.setPath("/accessdenied.jsp");
			} else { 
				applyPathFromResponse(view, body);
			}
		} else {   
			view.setError(true);
			view.setDisplayCommunicationErrorContent(response.isCommunicationError());
			view.setPath("/error.jsp");
		}
		
		LOGGER.info("View lang: " + view.getLang() + " isFrench=" + view.isFrench() );
	}

	private String extractLangFromBody(String html) {

		String lang = View.LANG_EN;

		if (html != null)
		{
			Matcher langMatcher = langPattern.matcher(html);
		
			if(langMatcher.find()) {
				lang = langMatcher.group(1);
				LOGGER.debug("found lang in the response:" + lang);
			}
		}
		LOGGER.debug("returning lang in the response:" + lang);
		return lang;
	}

	private boolean isCurrentURLAccessDenied()  {
		boolean isURLDenied = true;
		try {
			isURLDenied = URLDecoder.decode(requestContext.getRequestedURL(), "UTF-8").contains(PaymentRequest.SECURE_URL_TEXT);
		} catch (UnsupportedEncodingException e) {
			LOGGER.error("Something went wrong when trying to decode " + requestContext.getRequestedURL(), e);
		}
		return isURLDenied;
	}

	private void applyPathFromResponse(View view, String responseBody) {
		if(isShowCatalogueResponse(responseBody)) {   
			view.setPath(getCataloguePath());
		} else if (isDownForMaintenanceResponse(responseBody)) {  
			view.setPath(MAINTENANCE_PATH);
		} else { 
			view.setPath(getViewPathFromRequestOrConfig());
		}
	}

	private boolean isDownForMaintenanceResponse(String responseBody) {
		return responseBody.contains(DOWN_FOR_MAINTENANCE_MESSAGE);
	}

	private boolean isShowCatalogueResponse(String body) {
		return body.contains(PaymentRequest.GBIZ_HOME_PAGE_MESSAGE) || body.contains(PaymentRequest.GBIZ_HOME_TEMP_HOME_PAGE_MESSAGE);
	}

	private void applyCookieToView(View view, ClientResponse response) {
		Cookie responseCookie = findCookieInResponse(response);
		if(responseCookie != null) { 
			LOGGER.info("Updated view cookie " + responseCookie.getName() + " " + responseCookie.getValue() + " cookie from gBiz response");
			view.setCookie(responseCookie);
		} else {  
			Cookie sessionCookie = requestContext.getGbizSessionCookie();
			LOGGER.info("Updating view cookie held in session with " + sessionCookie);  
			view.setCookie(sessionCookie);
		}
	}
	
	private boolean isCatalogueRequest() {
		return !requestContext.getRequestedURL().contains("?")
				|| getCataloguePath().equals("/" + requestContext.getParameter("render"));
	}
	
	private boolean isProvincialContentDisplayable(String body) {
		return body.matches(FIND_PROVINCIAL_OFFENCE_REGEX)
				&& body.contains("Please enter the following information as it appears on your");
	}
	
	private interface ReWriteStrategy {
		boolean isReWritable(String body, String current);
		String rewriteWithParams(String body, String params);
	}	
	
	abstract ClientResponse doSend();
}