package ca.cityofkingston.payments;

import org.restlet.data.CookieSetting;
import org.restlet.util.Series;

public class ClientResponse {

	private String cookie;
	private String body;
	private Series<CookieSetting> cookies = new Series<CookieSetting>(CookieSetting.class);
	private boolean error;
	private boolean communicationError;

	public void setCookie(String cookie) {
		this.cookie = cookie;
	}

	public String getCookie() {
		return cookie;
	}

	public void setBody(String body) {
		this.body = body;
	}

	public String getBody() {
		return body;
	}

	public void setCookieSettings(Series<CookieSetting> cookies) {	
		this.cookies = cookies;
	}

	public Series<CookieSetting> getCookies() {
		return cookies;
	}

	public void setCookies(Series<CookieSetting> cookies) {
		this.cookies = cookies;
	}

	public void setError(boolean isError) {
		this.error = isError;
	}

	public boolean isError() {
		return error;
	}

	public boolean isCommunicationError() {
		return communicationError;
	}

	public void setCommunicationError(boolean isCommunicationError) {
		this.communicationError = isCommunicationError;
	}

}
