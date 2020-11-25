package com.liferay.cityofkingston.webform;

import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.ResourceBundle;

import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;

import com.liferay.dynamic.data.mapping.model.DDMForm;
import com.liferay.dynamic.data.mapping.model.DDMFormInstance;
import com.liferay.dynamic.data.mapping.model.DDMFormInstanceRecord;
import com.liferay.dynamic.data.mapping.model.DDMStructure;
import com.liferay.dynamic.data.mapping.storage.DDMFormFieldValue;
import com.liferay.dynamic.data.mapping.storage.DDMFormValues;
import com.liferay.mail.kernel.model.MailMessage;
import com.liferay.mail.kernel.service.MailServiceUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.language.LanguageUtil;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.PrefsPropsUtil;
import com.liferay.portal.kernel.util.PropsKeys;
import com.liferay.portal.kernel.util.ResourceBundleUtil;
import com.liferay.util.ContentUtil;


public class SendEmailNotification {
	public void sendEmail(DDMFormInstanceRecord ddmFormInstanceRecord, String toEmailAddress, String firstName) throws AddressException, PortalException, UnsupportedEncodingException {
		DDMFormInstance ddmFormInstance = ddmFormInstanceRecord.getFormInstance();
		String defaultEmailFromAddress = PrefsPropsUtil.getString(ddmFormInstance.getCompanyId(), PropsKeys.ADMIN_EMAIL_FROM_ADDRESS);
		InternetAddress fromInternetAddress = new InternetAddress(defaultEmailFromAddress, defaultEmailFromAddress);
		String subject = getEmailSubject(ddmFormInstance);
		String body = getEmailBody(ddmFormInstance, ddmFormInstanceRecord, firstName);
		MailMessage mailMessage = new MailMessage(fromInternetAddress, subject, body, true);
		InternetAddress[] toAddresses = InternetAddress.parse(toEmailAddress);
		mailMessage.setTo(toAddresses);
		MailServiceUtil.sendEmail(mailMessage);
	}

	protected String getEmailSubject(DDMFormInstance ddmFormInstance) throws PortalException {
		DDMStructure ddmStructure = ddmFormInstance.getStructure();
		DDMForm ddmForm = ddmStructure.getDDMForm();
		Locale locale = ddmForm.getDefaultLocale();
		ResourceBundle resourceBundle = ResourceBundleUtil.getBundle("content.Language", Locale.CANADA, getClass());
		String defaultEmailSubject = LanguageUtil.format(resourceBundle, "new-x-form-submitted", ddmFormInstance.getName(locale), false);
		_log.info("Acknowledgement Email Subject" + defaultEmailSubject);
		return defaultEmailSubject;
	}

	protected String getEmailBody(DDMFormInstance ddmFormInstance, DDMFormInstanceRecord ddmFormInstanceRecord, String firstName) throws PortalException {
		String emailBody = populateValues(_TEMPLATE_PATH, ddmFormInstance, ddmFormInstanceRecord, firstName);
		return emailBody;
	}

	protected String populateValues(String template, DDMFormInstance ddmFormInstance, DDMFormInstanceRecord ddmFormInstanceRecord, String firstName) throws PortalException {
		String content =  ContentUtil.get(SendEmailNotification.class.getClassLoader(), template);
		Locale locale = getLocale(ddmFormInstance);
		content = content.replace("[$firstName$]", ddmFormInstance.getName(locale));
		content = content.replace("[$formName$]", ddmFormInstance.getName(locale));
		content = content.replace("[$formValues$]", renderformValues(ddmFormInstanceRecord));
		return content;
	}
	
	protected String renderformValues(DDMFormInstanceRecord ddmFormInstanceRecord) {
		StringBuilder fieldData  = new StringBuilder();
		try {
			DDMFormValues ddmFormValues = ddmFormInstanceRecord.getDDMFormValues();
			Map<String, List<DDMFormFieldValue>>  map = ddmFormValues.getDDMFormFieldValuesMap();
			for (Map.Entry<String, List<DDMFormFieldValue>> entry : map.entrySet()) {
				List<DDMFormFieldValue> ddmformFieldValueList = entry.getValue();
				for(DDMFormFieldValue ddmformFieldValue:ddmformFieldValueList) {
					_log.info(entry.getKey()+" ====== "+ddmformFieldValue.getValue().getString(Locale.US));
					fieldData.append("<div style=\"background-color: #fff; border-radius: 4px; margin: 0 auto 24px auto; padding: 40px;\">\r\n" + 
							"<h4 style=\"color: #9aa2a6; font-size: 21px; font-weight: 500; margin: 0;\">"+entry.getKey()+"</h4>\r\n" +  
							ddmformFieldValue.getValue().getString(Locale.US)+"\r\n" + 
							"</div>");
					
				}
			}
			} catch (PortalException e) {
				_log.error("Error while retriveing the data :"+e.getMessage());
			}
		return fieldData.toString();
	}

	protected Locale getLocale(DDMFormInstance ddmFormInstance) throws PortalException {
		DDMForm ddmForm = getDDMForm(ddmFormInstance);
		return ddmForm.getDefaultLocale();
	}
	
	protected DDMForm getDDMForm(DDMFormInstance ddmFormInstance) throws PortalException {
		DDMStructure ddmStructure = ddmFormInstance.getStructure();
		return ddmStructure.getDDMForm();
	}
	
	private static final String _TEMPLATE_PATH = "/META-INF/resources/email.ftl";
	private static final Log _log = LogFactoryUtil.getLog(SendEmailNotification.class);
}
