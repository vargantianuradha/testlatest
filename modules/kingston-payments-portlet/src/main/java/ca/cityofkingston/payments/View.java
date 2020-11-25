package ca.cityofkingston.payments;

import java.io.Serializable;

import javax.servlet.http.Cookie;

import org.apache.commons.lang3.StringUtils;

import ca.cityofkingston.configuration.PaymentConfiguration;

public class View implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private String path;

	private String html;

	private Cookie cookie;

	private boolean error;

	private boolean displayProvincialOffenceContent;
	
	private boolean displayCommunicationErrorContent;
	
	private PaymentConfiguration paymentConfiguration;

	private boolean propertyLookup;

	public static final String LANG_FR = "fr";
	public static final String LANG_EN = "en";

	private String language = LANG_EN;

	public View(PaymentConfiguration paymentConfiguration) {
		this.paymentConfiguration = paymentConfiguration;
	}
	
	public View() {}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}
	
	public String getHTML() {
		return html;
	}

	public void setHTML(String html) {
		this.html = html;
	}

	public Cookie getCookie() {
		return cookie;
	}

	public void setCookie(Cookie cookie2) {
		this.cookie = cookie2;
	}

	public boolean isShoppingCartViewable() {
		return !propertyLookup && cookie != null;
	}

	public boolean hasPath() {
		return StringUtils.isNotEmpty(path);
	}

	public void setError(boolean isError) {
		this.error = isError;
		
	}

	public boolean isError() {
		return error;
	}

	public boolean isProvincialOffenceContentDisplayable() {
		return displayProvincialOffenceContent;
	}

	public void setProvincialOffenceContentDisplayable(boolean displayProvincialOffenceContent) {
		this.displayProvincialOffenceContent = displayProvincialOffenceContent;
		
	}
	
	public boolean isDisplayCommunicationErrorContent() {
		return displayCommunicationErrorContent;
	}

	public void setDisplayCommunicationErrorContent(boolean displayCommunicationErrorContent) {
		this.displayCommunicationErrorContent = displayCommunicationErrorContent;
	}

	public boolean isTransitPassOnline() {
		return paymentConfiguration.isTransitPassOnline();
	}
	
	public boolean isRecreationProgramsOnline() {
		return paymentConfiguration.isRecreationOnline();
	}

	public boolean isParkingTicketOnline() {
		return paymentConfiguration.isParkingTicketOnline();
	}

	public boolean isParkingPermitOnline() {
		return paymentConfiguration.isParkingPermitOnline();
	}

	public boolean isRenewPetTagsOnline() {
		return paymentConfiguration.isPetTagRenewalOnline();
	}

	public boolean isLicensePetTagsOnline() {
		return paymentConfiguration.isPetTagLicensingOnline();
	}

	public boolean isGarbageBagTagsOnline() {
		return paymentConfiguration.isGarbageTagOnline();
	}

	public boolean isPoaOffencNoticeOnline() {
		return paymentConfiguration.isPOAOffenceNoticesOnline();
	}

	public boolean isPoaNoticeOfTrialOnline() {
		return paymentConfiguration.isPOANoticeOfTrialOnline();
	}

	public boolean isPoaNoticeOfFineAndDueDateOnline() {
		return paymentConfiguration.isPOANoticeOfFineAndDueDateOnline();
	}

	public boolean isPoaCollectionsNoticeService() {
		return paymentConfiguration.isPOACollectionsNoticesOnline();
	}

	public PaymentConfiguration getPaymentConfiguration() {
		return paymentConfiguration;
	}

	public void setPropertyLookup(boolean propertyLookup) {	
		this.propertyLookup = propertyLookup;
	}
	
	public void setLang(String inLang)
	{
		this.language = inLang;
	}

	public String getLang()
	{
		return this.language;
	}

	public boolean isFrench()
	{
		return (this.language.equalsIgnoreCase(LANG_FR));	
	}

	public boolean isEnglish()
	{
		return (this.language.equalsIgnoreCase(LANG_EN));	
	}

}