package ca.cityofkingston.model;

import java.util.Locale;

import com.liferay.dynamic.data.mapping.kernel.DDMFormField;
import com.liferay.dynamic.data.mapping.kernel.DDMFormFieldValue;
import com.liferay.dynamic.data.mapping.storage.Field;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.LocaleUtil;
import com.liferay.portal.kernel.util.Validator;


public class MediaMetadata {

	public static final String EQUIPMENT_MODEL = "TIFF_EQUIPMENT_MODEL";
	public static final String ORIGINAL_DATE = "TIFF_ORIGINAL_DATE";

	public static final String IMAGE_WIDTH = "TIFF_IMAGE_WIDTH";
	public static final String IMAGE_LENGTH = "TIFF_IMAGE_LENGTH";

	public static final String LOCATION = "location";
	public static final String LATITUDE = "Geographic_LATITUDE";
	public static final String LONGITUDE = "Geographic_LONGITUDE";

	public static final String BY = "by";

	private String key;
	private String value; 


	public MediaMetadata(DDMFormFieldValue field) throws SystemException, PortalException {
		this.key = field.getName();
		this.value = Validator.isNotNull(field.getValue()) ? field.getValue().getString(LocaleUtil.getDefault()) : null;

		if(ORIGINAL_DATE.equals(this.key) && this.value != null) {
			value = value.substring(0, 10);
		}
	}

	public String getKey() {
		return key;
	}
	public void setKey(String key) {
		this.key = key;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	
}
