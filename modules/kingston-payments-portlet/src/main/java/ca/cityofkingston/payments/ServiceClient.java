package ca.cityofkingston.payments;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import javax.servlet.http.Cookie;

import org.apache.log4j.Logger;
import org.restlet.data.ClientInfo;
import org.restlet.data.CookieSetting;
import org.restlet.data.Form;
import org.restlet.data.MediaType;
import org.restlet.data.Parameter;
import org.restlet.data.Preference;
import org.restlet.data.Status;
import org.restlet.engine.header.Header;
import org.restlet.representation.Representation;
import org.restlet.resource.ClientResource;
import org.restlet.resource.ResourceException;
import org.restlet.util.Series;

import ca.cityofkingston.payments.ServiceClientDelegate.FormParameter;

public class ServiceClient {
	private static final String G_BIZ_ERROR_MSG = "gBiz Error";
	private static final String TIMED_OUT_MSG = "timed out!";
	private static final String CONNECT_FAILED_MSG = "Connect failed";
	private static final String THE_RESOURCE_CANNOT_BE_FOUND_MSG = "The resource cannot be found";
	private static final String AN_UNHANDLED_EXCEPTION_HAS_OCCURRED_MSG = "An unhandled exception has occurred";
	private static final String OPERATION_TIMED_OUT_MSG = "Operation timed out";
	private static final Logger LOGGER = Logger.getLogger(ServiceClient.class);
	
	private ServiceClientDelegate delegate;
	private ClientResponse response;

	public ServiceClient(ServiceClientDelegate delegate) {
		this.delegate = delegate;
		this.response = new ClientResponse();
	}
	
	public ClientResponse post() {  
		
		return service(new ResourceRunner(){
			@Override
			public Representation run(ClientResource resource) {
				resource.setRequestEntityBuffering(true);
				Map<String, Object> mapData = resource.getRequestAttributes();
				 
				return resource.post(makeForm());
			}
		}, delegate.getURL());
	}
	
	public ClientResponse get() { 
		
		return service(new ResourceRunner() {
			@Override
			public Representation run(ClientResource resource) {
				
				resource.setRequestEntityBuffering(true);
				Map<String, Object> mapData = resource.getRequestAttributes();
				return resource.get();
			}
		}, delegate.getURL());
	}	
	
	private ClientResponse service(ResourceRunner runner, final String url) {
		
		ClientResource resource = makeClientResource(delegate.getCookie(), url);
		resource.setRetryAttempts(0);
		Representation representation = null;
		try {
			representation = runner.run(resource);
			if(isRedirecting(resource)) {
				//POST requests don't follow redirects, so this is a work around.
				LOGGER.info("The gBiz request has been redirected to " + resource.getLocationRef() + " after a post");
				resource = makeClientResource(delegate.getCookie(), resource.getLocationRef().toString());
				
				representation = resource.get();
			}

			fillResponse(resource, representation);
			
		} catch (IOException e) {
			LOGGER.fatal("Could not recover from gBiz error");
			throw new RuntimeException(e);
		} catch (ResourceException e) {
			LOGGER.error("We received an error from gBiz! HTTP status " + e.getStatus());
			e.printStackTrace();
			response.setError(true);
			response.setCommunicationError(true);
		} finally {
			cleanUp(representation);
		}		
		
		return response;	
	}

	private void cleanUp(Representation representation) {
		if(representation != null) {
			try {
				representation.exhaust();
			} catch (IOException e) {
				LOGGER.error(e);
			}
			representation.release();
		}
	}

	private void fillResponse(ClientResource resource, Representation representation) throws IOException {
		String body = representation.getText();
		
		LOGGER.debug("Received this gBiz response: " + body);
		
		response.setBody(body);
		
		handleErrors(body);
		
		applyCookies(resource);
		
	}

	private void applyCookies(ClientResource resource) {
		
		Series<CookieSetting> cookieSettings = resource.getResponse().getCookieSettings();
		
		LOGGER.debug("Response has " + cookieSettings.size() + " cookie(s)");
		
		if(LOGGER.isDebugEnabled()) {
			for (CookieSetting cookieSetting : cookieSettings) {
				LOGGER.debug("Cookie from response :" + cookieSetting.getName() + "=" + cookieSetting.getValue());
			}
		}
		
		response.setCookies(cookieSettings);
		
	}

	private boolean isRedirecting(ClientResource resource) {
		return resource.getStatus().getCode() == Status.REDIRECTION_FOUND.getCode();
	}
	
	private void handleErrors(String body) {
		
		if(body != null) {
			
			response.setError(body.contains(OPERATION_TIMED_OUT_MSG)
					|| body.contains(AN_UNHANDLED_EXCEPTION_HAS_OCCURRED_MSG)
					|| body.contains(THE_RESOURCE_CANNOT_BE_FOUND_MSG)
					|| body.contains(CONNECT_FAILED_MSG)
					|| body.contains(TIMED_OUT_MSG)
					|| body.contains(G_BIZ_ERROR_MSG));

			notifyErrors(body);
		}
		
	}

	private void notifyErrors(String body) {
		ServiceError serviceError = null;
		
		if(response.isError()) {
			if(body.contains(OPERATION_TIMED_OUT_MSG)) {
				serviceError = ServiceError.OPERATION_TIMED_OUT;
			} else if(body.contains(AN_UNHANDLED_EXCEPTION_HAS_OCCURRED_MSG)) {
				serviceError = ServiceError.UNHANDLED_EXCEPTION;
			} else if(body.contains(THE_RESOURCE_CANNOT_BE_FOUND_MSG)) {
				serviceError = ServiceError.RESOURCE_CANNOT_BE_FOUND;
			} else if(body.contains(CONNECT_FAILED_MSG)) {
				serviceError = ServiceError.CONNECT_FAILED;
			} else if(body.contains(TIMED_OUT_MSG)) {
				serviceError = ServiceError.TIMED_OUT;
			}
			
			if(serviceError != null) {
				
				if (serviceError.equals(ServiceError.OPERATION_TIMED_OUT)
						|| serviceError.equals(ServiceError.CONNECT_FAILED)
						|| serviceError.equals(ServiceError.TIMED_OUT)) {
					
					this.response.setCommunicationError(true);
				}
				this.delegate.handle(serviceError, body);
			}
		}
	}
	
	private Form makeForm() {  
		
		Form form = new Form();
		
		for (FormParameter formParameter : delegate.getPostParameters()) { 
			
			String paramName = formParameter.getName();
			//gBiz doesn't seem to support multiple values for a given parameter
//			String value = !formParameter.getValues().isEmpty() ? formParameter.getValues().get(0) : "";
			
			for(String value: formParameter.getValues()) {
				LOGGER.debug("Adding form parameter " + paramName + " with value " + value);				
				form.add(new Parameter(paramName, value));
			}

//			form.add(new Parameter(paramName, formParameter.getValues().get(0)));
			
//			LOGGER.debug("Adding form parameter " + paramName + " with value " + value);				
			
		}
		LOGGER.debug("We are sending " + form.size() + " post parameters to gBiz");
		
		return form;
	}

	private ClientResource makeClientResource(Cookie cookie, String url) {
		
		ClientResource resource = new ClientResource(url);
		resource.setCookies(makeCookieForRequest(cookie));
		resource.setFollowingRedirects(true);
		addHeaders(resource);
		
		ClientInfo info = new ClientInfo();
		info.setAgent(delegate.getUserAgent());
		info.setAcceptedMediaTypes(makeAcceptedMediaTypes());

		resource.setClientInfo(info);
		
		LOGGER.info("Making a request to gBiz using....: " + url + " from " + info.getAgent());
		return resource;
	}

	@SuppressWarnings("unchecked")
	private void addHeaders(ClientResource resource) {
		Series<Header> headers = (Series<Header>) resource.getRequestAttributes().get("org.restlet.engine.header.Header");
		if (headers == null) {  
		    headers = new Series<Header>(Header.class);
		    resource.getRequestAttributes().put("org.restlet.engine.header.Header", headers);
		}
		
		headers.add("Cache-Control", "no-cache");
		headers.add("Connection", "Keep-Alive");
		headers.add("Content-Length", "14668");
		if(LOGGER.isDebugEnabled()) {
			for (Header header : headers) {
				LOGGER.debug("Sending header " + header.getName() + " with value " + header.getValue());
			}
		}
	}

	private List<Preference<MediaType>> makeAcceptedMediaTypes() {
		List<Preference<MediaType>> acceptedMedia = new ArrayList<Preference<MediaType>>();
		acceptedMedia.add(new Preference<MediaType>(new MediaType("image/gif")));
		acceptedMedia.add(new Preference<MediaType>(new MediaType("image/x-bitmap")));
		acceptedMedia.add(new Preference<MediaType>(new MediaType("image/jpeg")));
		acceptedMedia.add(new Preference<MediaType>(new MediaType("image/pjpeg")));
		acceptedMedia.add(new Preference<MediaType>(new MediaType("*/*")));
		
		if(LOGGER.isDebugEnabled()) {
			for (Preference<MediaType> preference : acceptedMedia) {
				LOGGER.debug("Accepting media type: " + preference.toString());
			}
		}
		
		return acceptedMedia;
	}

	private Series<org.restlet.data.Cookie> makeCookieForRequest(Cookie cookie) {
		
		Series<org.restlet.data.Cookie> cookieSeries = null;
		if(cookie != null) {
			org.restlet.data.Cookie cookieItem = new org.restlet.data.Cookie(cookie.getName(), cookie.getValue());
			cookieSeries = new Series<org.restlet.data.Cookie>(org.restlet.data.Cookie.class, Arrays.asList(cookieItem));
			LOGGER.debug("Adding cookie " + cookie.getName() + " " + cookie.getValue());
		} else {
			LOGGER.debug("Didn't find a cookie to send to gBiz");
		}
		
		return cookieSeries;
	}
	
	private interface ResourceRunner {
		Representation run(ClientResource resource); 
	}

}
