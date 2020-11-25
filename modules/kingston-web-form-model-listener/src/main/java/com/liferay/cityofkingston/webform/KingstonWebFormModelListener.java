package com.liferay.cityofkingston.webform;

import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.mail.internet.AddressException;

import org.osgi.service.component.annotations.Component;
import com.liferay.cityofkingston.webform.constants.KingstonWebFormModelListenerKeys;
import com.liferay.dynamic.data.mapping.model.DDMFormInstanceRecord;
import com.liferay.dynamic.data.mapping.storage.DDMFormFieldValue;
import com.liferay.dynamic.data.mapping.storage.DDMFormValues;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.exception.ModelListenerException;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.BaseModelListener;
import com.liferay.portal.kernel.model.ModelListener;
import com.liferay.portal.kernel.util.Validator;
 
/**
 * @author RST028
 */
@Component(
		immediate = true,
		service = ModelListener.class
)
public class KingstonWebFormModelListener extends BaseModelListener<DDMFormInstanceRecord> {

	@Override
	public void onAfterUpdate(DDMFormInstanceRecord ddmFormInstanceRecord) throws ModelListenerException {
		_log.info("Inside the Form Model Listener");
		String toEmailAddress = StringPool.BLANK;
		String firstName = StringPool.BLANK;
		try {
			DDMFormValues ddmFormValues = ddmFormInstanceRecord.getDDMFormValues();
			Map<String, List<DDMFormFieldValue>>  map = ddmFormValues.getDDMFormFieldValuesMap();
			for (Map.Entry<String, List<DDMFormFieldValue>> entry : map.entrySet()) {
				List<DDMFormFieldValue> ddmformFieldValueList = entry.getValue();
				for(DDMFormFieldValue ddmformFieldValue:ddmformFieldValueList) {
					if( Validator.isNotNull(entry.getKey()) && Validator.isNotNull(ddmformFieldValue) && Validator.isNotNull(ddmformFieldValue.getValue())) {
						if(entry.getKey().equals(KingstonWebFormModelListenerKeys.FORMSRENDERFIRSTNAMEKEY)){
							firstName = ddmformFieldValue.getValue().getString(Locale.US);
						}
						if(entry.getKey().equals(KingstonWebFormModelListenerKeys.FORMSRENDEREMAILKEY)){
							toEmailAddress = ddmformFieldValue.getValue().getString(Locale.US);
						}
					}
					
					
				}
			}
			if(!toEmailAddress.equals(StringPool.BLANK)) {
				SendEmailNotification sendEmail = new SendEmailNotification();
				sendEmail.sendEmail(ddmFormInstanceRecord, toEmailAddress, firstName);
				_log.info(" MAIL SENT SUCCESSFULLY ...");
			}
		} catch (PortalException e) {
			_log.error("PortalException : Error while retriveing the data :"+e.getMessage());
		} catch (AddressException e) {
			_log.error("AddressException : Error while sending the data :"+e.getMessage());
		} catch (UnsupportedEncodingException e) {
			_log.error("UnsupportedEncodingException : Error while sending the data :"+e.getMessage());
		}
	}
	private static final Log _log = LogFactoryUtil.getLog(KingstonWebFormModelListener.class);}