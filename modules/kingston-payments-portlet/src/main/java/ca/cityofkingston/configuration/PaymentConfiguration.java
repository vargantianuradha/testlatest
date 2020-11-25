package ca.cityofkingston.configuration;

import java.io.IOException;

import javax.portlet.PortletPreferences;
import javax.portlet.ReadOnlyException;
import javax.portlet.ValidatorException;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;

public class PaymentConfiguration implements DemoConfiguration{

	private static final Log LOGGER = LogFactoryUtil.getLog(PaymentConfiguration.class);
	
	public static final String POA_COLLECTIONS_NOTICES_SVC_PARAM = "poaCollectionsNoticeSVC";
	public static final String POA_NOTICE_OF_FINE_AND_DUE_DATE_SVC_PARAM = "poaNoticeOfFineAndDueDateSVC";
	public static final String POA_NOTICE_OF_TRIAL_SVC_PARAM = "poaNoticeOFTrialSVC";
	public static final String POA_OFFENCE_NOTICES_SVC_PARAM = "poaOffenceNoticesSVC";
	public static final String GARBAGE_BAG_TAG_SVC_PARAM = "garbageBagTagSVC";
	public static final String RENEW_PET_TAG_SVC_PARAM = "renewPetTagSVC";
	public static final String LICENSE_PET_TAG_SVC_PARAM = "licensePetTagSVC";
	public static final String PARKING_PERMIT_SVC_PARAM = "parkingPermitSVC";
	public static final String PARKING_TICKET_SVC_PARAM = "parkingTicketSVC";
	public static final String TRANSIT_PASS_SVC_PARAM = "transitPassSVC";
	public static final String RECREATION_SVC_PARAM = "recreationSVC";
	
	public static final String IS_MAIL_ENABLED_REQ_PARAM = "isMailEnabled";
	public static final String GBIZ_URL_REQ_PARAM = "gbiz-url";
	public static final String HELP_URL_PARAM = "help-url";
	public static final String REC_URL_PARAM = "rec-url";
	public static final String REC_URL_DEFAULT = "https://register.cityofkingston.ca";

	public static final String IS_MAIL_ENABLED_PREF_KEY = "isGbizMailEnabled";
	public static final String GBIZ_URL_PREF_KEY = "gbiz-server-url";
	public static final String HELP_URL_PREF_KEY = "help-url";
	public static final String REC_URL_PREF_KEY = "rec-url";
	
	public static final String TRANSIT_PASS_SVC_PREF_KEY = "transitSVCStatus";
	public static final String PARKING_TICKET_SVC_PREF_KEY = "parkingTicketSVCStatus";
	
	private PortletPreferences preferences;
	private HttpServletRequest request;


	public static final String PORTLET_RESOURCE = "portletResource";

	public static final String PARKING_PERMIT_SVC_PREF_KEY = "parkingPermitSVC";

	public static final String RENEW_PET_TAG_SVC_PREF_KEY = "renewPetTagSVC";
	public static final String LICENSE_PET_TAG_SVC_PREF_KEY = "licensePetTagSVC";

	public static final String GARBAGE_BAG_TAG_SVC_PREF_KEY = "garbageBagTagSVC";

	public static final String POA_OFFENCE_NOTICES_PREF_KEY = "poaOffenceNoticeSVC";

	public static final String POA_NOTICE_OF_FINE_AND_DUE_DATE_PREF_KEY = "poaNoticeOfFineAndDueDateSVC";

	public static final String POA_NOTICE_OF_TRIAL_PREF_KEY = "poaNoticeOfTrialSVC";

	public static final String POA_COLLECTIONS_NOTICES_PREF_KEY = "poaNoticeOfCollectionsSVC";

	public static final String TRANSIT_PASS_PRODUCT_ID = "transitsearch";

	public static final String PARKING_TICKET_PRODUCT_ID = "ptp2001";

	public static final String PARKING_PERMIT_PRODUCT_ID = "ppr2001";

	public static final String RENEW_PET_TAG_PRODUCT_ID = "PLR2001";

	public static final String GARBAGE_BAG_TAG_PRODUCT_ID = "gbt1001";

	public static final String POA_OFFENCE_NOTICES_PRODUCT_ID = "poa2101";

	public static final String POA_NOTICE_OF_FINE_AND_DUE_DATE_PRODUCT_ID = "poa2103";

	public static final String POA_NOTICE_OF_TRIAL_PRODUCT_ID = "poa2105";

	public static final String POA_COLLECTIONS_NOTICES_PRODUCT_ID = "poa2107";

	private static final String RECREATION_SVC_PREF_KEY = "recreationSVC";


	public static PaymentConfiguration make(HttpServletRequest request, PortletPreferences preferences) throws PortalException, SystemException {
		
		return new PaymentConfiguration(request, preferences);
	}
	
	public void store() throws ValidatorException, IOException, ReadOnlyException {
		
		applyGbizURLToPreferences();
		applyHelpURLToPreferences();
		applyRecURLToPreferences();
		
		applyGbizMailEnabledStateToPreferences();
		
		applyServiceStatusToPreferences();		
		
		preferences.store();
	}

	public String getGbizURL() {

		return preferences.getValue(PaymentConfiguration.GBIZ_URL_PREF_KEY, "");
	}

	public String getHelpURL() {

		return preferences.getValue(PaymentConfiguration.HELP_URL_PREF_KEY, "");
	}

	public String getRecURL() {

		return preferences.getValue(PaymentConfiguration.REC_URL_PREF_KEY, PaymentConfiguration.REC_URL_DEFAULT);
	}

	public boolean isEmailEnabled() {
		
		return getBooleanPreferenceValue(PaymentConfiguration.IS_MAIL_ENABLED_PREF_KEY);
	}

	public boolean isTransitPassOnline() {
		return getBooleanPreferenceValue(PaymentConfiguration.TRANSIT_PASS_SVC_PREF_KEY);
	}

	public boolean isRecreationOnline() {
		return getBooleanPreferenceValue(PaymentConfiguration.RECREATION_SVC_PREF_KEY);
	}

	public boolean isParkingTicketOnline() {
		
		return getBooleanPreferenceValue(PaymentConfiguration.PARKING_TICKET_SVC_PREF_KEY);
	}
	
	public boolean isParkingPermitOnline() {
		
		return getBooleanPreferenceValue(PaymentConfiguration.PARKING_PERMIT_SVC_PREF_KEY);
	}
	
	public boolean isPetTagRenewalOnline() {
		
		return getBooleanPreferenceValue(PaymentConfiguration.RENEW_PET_TAG_SVC_PREF_KEY);
	}

	public boolean isPetTagLicensingOnline() {
		
		return getBooleanPreferenceValue(PaymentConfiguration.LICENSE_PET_TAG_SVC_PREF_KEY);
	}

	public boolean isGarbageTagOnline() {
		
		return getBooleanPreferenceValue(PaymentConfiguration.GARBAGE_BAG_TAG_SVC_PREF_KEY);
	}
	
	public boolean isPOAOffenceNoticesOnline() {
		
		return getBooleanPreferenceValue(PaymentConfiguration.POA_OFFENCE_NOTICES_PREF_KEY);
	}
	
	public boolean isPOANoticeOfFineAndDueDateOnline() {
		
		return getBooleanPreferenceValue(PaymentConfiguration.POA_NOTICE_OF_FINE_AND_DUE_DATE_PREF_KEY);
	}
	
	public boolean isPOANoticeOfTrialOnline() {
		
		return getBooleanPreferenceValue(PaymentConfiguration.POA_NOTICE_OF_TRIAL_PREF_KEY);
	}
	
	public boolean isPOACollectionsNoticesOnline() {
		
		return getBooleanPreferenceValue(PaymentConfiguration.POA_COLLECTIONS_NOTICES_PREF_KEY);
	}
	
	private PaymentConfiguration(HttpServletRequest request, PortletPreferences preferences) throws PortalException, SystemException {
		this.request = request;
		this.preferences = preferences;
	}	

	private void applyGbizMailEnabledStateToPreferences() throws ReadOnlyException {
		
		String enabled = request.getParameter(PaymentConfiguration.IS_MAIL_ENABLED_REQ_PARAM);
		
		PaymentConfiguration.LOGGER.debug("Is gbiz email enabled? " + enabled);
		
		preferences.setValue(PaymentConfiguration.IS_MAIL_ENABLED_PREF_KEY, enabled);
	}

	private void applyGbizURLToPreferences() throws ReadOnlyException {
		
		String url = request.getParameter(PaymentConfiguration.GBIZ_URL_REQ_PARAM);
		
		if (StringUtils.isEmpty(url)) {
			PaymentConfiguration.LOGGER.debug("Removing gbiz url from preferences");
			preferences.reset(PaymentConfiguration.GBIZ_URL_PREF_KEY);
		} else {
			PaymentConfiguration.LOGGER.debug("Setting gbiz url in preferences: " + url);
			preferences.setValue(PaymentConfiguration.GBIZ_URL_PREF_KEY, url);
		}
	}

	private void applyHelpURLToPreferences() throws ReadOnlyException {
		
		String url = request.getParameter(PaymentConfiguration.HELP_URL_PARAM);
		
		if (StringUtils.isEmpty(url)) {
			PaymentConfiguration.LOGGER.debug("Removing help url from preferences");
			preferences.reset(PaymentConfiguration.HELP_URL_PREF_KEY);
		} else {
			PaymentConfiguration.LOGGER.debug("Setting help url in preferences: " + url);
			preferences.setValue(PaymentConfiguration.HELP_URL_PREF_KEY, url);
		}
	}

	private void applyRecURLToPreferences() throws ReadOnlyException {
		
		String url = request.getParameter(PaymentConfiguration.REC_URL_PARAM);
		
		if (StringUtils.isEmpty(url)) {
			PaymentConfiguration.LOGGER.debug("Removing rec url from preferences");
			preferences.reset(PaymentConfiguration.REC_URL_PREF_KEY);
		} else {
			PaymentConfiguration.LOGGER.debug("Setting help url in preferences: " + url);
			preferences.setValue(PaymentConfiguration.REC_URL_PREF_KEY, url);
		}
	}

	private void applyServiceStatusToPreferences() throws ReadOnlyException {
		preferences.setValue(RECREATION_SVC_PREF_KEY, getServiceStatusRequestParameter(RECREATION_SVC_PARAM));
		preferences.setValue(TRANSIT_PASS_SVC_PREF_KEY, getServiceStatusRequestParameter(TRANSIT_PASS_SVC_PARAM));
		preferences.setValue(PARKING_TICKET_SVC_PREF_KEY, getServiceStatusRequestParameter(PARKING_TICKET_SVC_PARAM));
		preferences.setValue(PARKING_PERMIT_SVC_PREF_KEY, getServiceStatusRequestParameter(PARKING_PERMIT_SVC_PARAM));
		preferences.setValue(RENEW_PET_TAG_SVC_PREF_KEY, getServiceStatusRequestParameter(RENEW_PET_TAG_SVC_PARAM));		
		preferences.setValue(LICENSE_PET_TAG_SVC_PREF_KEY, getServiceStatusRequestParameter(LICENSE_PET_TAG_SVC_PARAM));		
		preferences.setValue(GARBAGE_BAG_TAG_SVC_PREF_KEY, getServiceStatusRequestParameter(GARBAGE_BAG_TAG_SVC_PARAM));
		preferences.setValue(POA_OFFENCE_NOTICES_PREF_KEY, getServiceStatusRequestParameter(POA_OFFENCE_NOTICES_SVC_PARAM));
		preferences.setValue(POA_NOTICE_OF_FINE_AND_DUE_DATE_PREF_KEY, getServiceStatusRequestParameter(POA_NOTICE_OF_FINE_AND_DUE_DATE_SVC_PARAM));
		preferences.setValue(POA_NOTICE_OF_TRIAL_PREF_KEY, getServiceStatusRequestParameter(POA_NOTICE_OF_TRIAL_SVC_PARAM));		
		preferences.setValue(POA_COLLECTIONS_NOTICES_PREF_KEY, getServiceStatusRequestParameter(POA_COLLECTIONS_NOTICES_SVC_PARAM));
	}
	
	private boolean getBooleanPreferenceValue(String prefKey) {
		return Boolean.parseBoolean(preferences.getValue(prefKey, "false"));
	}	

	private String getServiceStatusRequestParameter(String svcParamName) {
		
		String parameter = request.getParameter(svcParamName);
		return StringUtils.isEmpty(parameter) ? "false" : parameter;
	}

	public boolean isServiceEnabled(String productId) {
		if(TRANSIT_PASS_PRODUCT_ID.equals(productId)) {
			return getBooleanPreferenceValue(TRANSIT_PASS_SVC_PREF_KEY);
		}
		if(PARKING_TICKET_PRODUCT_ID.equals(productId)) {
			return getBooleanPreferenceValue(PARKING_TICKET_SVC_PREF_KEY);
		}
		if(PARKING_PERMIT_PRODUCT_ID.equals(productId)) {
			return getBooleanPreferenceValue(PARKING_PERMIT_SVC_PREF_KEY);
		}
		if(RENEW_PET_TAG_PRODUCT_ID.equals(productId)) {
			return getBooleanPreferenceValue(RENEW_PET_TAG_SVC_PREF_KEY);
		}
		if(GARBAGE_BAG_TAG_PRODUCT_ID.equals(productId)) {
			return getBooleanPreferenceValue(GARBAGE_BAG_TAG_SVC_PREF_KEY);
		}
		if(POA_OFFENCE_NOTICES_PRODUCT_ID.equals(productId)) {
			return getBooleanPreferenceValue(POA_OFFENCE_NOTICES_PREF_KEY);
		}
		if(POA_NOTICE_OF_FINE_AND_DUE_DATE_PRODUCT_ID.equals(productId)) {
			return getBooleanPreferenceValue(POA_NOTICE_OF_FINE_AND_DUE_DATE_PREF_KEY);
		}
		if(POA_NOTICE_OF_TRIAL_PRODUCT_ID.equals(productId)) {
			return getBooleanPreferenceValue(POA_NOTICE_OF_TRIAL_PREF_KEY);
		}
		if(POA_COLLECTIONS_NOTICES_PRODUCT_ID.equals(productId)) {
			return getBooleanPreferenceValue(POA_COLLECTIONS_NOTICES_PREF_KEY);
		}
		return true;
	}	

}
