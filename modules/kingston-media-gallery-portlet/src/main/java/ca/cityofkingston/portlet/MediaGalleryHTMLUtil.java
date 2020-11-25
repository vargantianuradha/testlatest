package ca.cityofkingston.portlet;

import java.text.SimpleDateFormat;
import java.util.Date;

import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.Validator;

import ca.cityofkingston.model.MediaItem;
import ca.cityofkingston.model.MediaMetadata;


public class MediaGalleryHTMLUtil {

	public static final String GOOGLE_MAPS_PLACEHOLDER = "<a target=\"_blank\" href=\"http://maps.google.ca/maps?q=%s,%s\">(%s, %s)</a>";
	public static SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd");
	public static final String LABEL_VALUE_SEPARATOR = ": ";
	public static final String HTML_SUFFIX = "<br/>";
	public static final String NON_AVAILABLE_VALUE = "n/a";

	public static final String THUMBNAIL_BY = "By";
	public static final String THUMBNAIL_DATE = "Date";
	public static final String LABEL_SHOT_LOCATION = "Shot Location";
	public static final String LABEL_SHOT_DATE = "Shot Date";
	public static final String LABEL_SHOT_BY = "Shot By";
	public static final String LABEL_POST_DATE = "Post Date";
	public static final String LABEL_POST_BY = "Post By";


	public static String getHTMLProperty(String label, Date date) {
		if(date == null)
			return getHTMLProperty(label, NON_AVAILABLE_VALUE);

		return getHTMLProperty(label, DATE_FORMAT.format(date));
	}

	public static String getHTMLProperty(String label, String value) {

		StringBuffer sb = new StringBuffer();
		if(label != null) {
			sb.append(label);
			sb.append(LABEL_VALUE_SEPARATOR);
		}
		sb.append(value == null ? NON_AVAILABLE_VALUE : value == "" ? NON_AVAILABLE_VALUE : value);
		sb.append(HTML_SUFFIX);

		return sb.toString();
	}

	public static String getHTMLProperty(String value) {
		return getHTMLProperty(null, value);
	}

	public static String getHTMLLocation(MediaItem mediaItem) {

		String locationValue = null;
		MediaMetadata location = mediaItem.getMetadata().get(MediaMetadata.LOCATION);
		if(location != null) {
			locationValue = location.getValue();
		}
		if(Validator.isNull(locationValue)) {
			MediaMetadata longitude = mediaItem.getMetadata().get(MediaMetadata.LONGITUDE);
			MediaMetadata latitude = mediaItem.getMetadata().get(MediaMetadata.LATITUDE);
			if(longitude != null && latitude != null){
				locationValue = String.format(GOOGLE_MAPS_PLACEHOLDER, latitude.getValue(), longitude.getValue(), latitude.getValue(), longitude.getValue());
			}
		}

		return getHTMLProperty(LABEL_SHOT_LOCATION, locationValue);
	}

	public static String getHTMLMetadata(MediaItem mediaItem, String label, String metadataKey) {

		if(metadataKey == null)
			return StringPool.BLANK;
		
		MediaMetadata metadata = mediaItem.getMetadata().get(metadataKey);

		if(metadata == null && label == THUMBNAIL_DATE && metadataKey == MediaMetadata.ORIGINAL_DATE){
			return getHTMLProperty(label, mediaItem.getPostDate());
		}
		
		if(metadata == null && label == THUMBNAIL_DATE && metadataKey == LABEL_POST_DATE){
			return getHTMLProperty(label, StringPool.BLANK);
		}
		
		if(metadata != null) {
			
			
			return getHTMLProperty(label, metadata.getValue());
		}

		return StringPool.BLANK;
	}

	public static String getHTMLDetails(MediaItem mediaItem) {

		StringBuffer sb = new StringBuffer();

		sb.append(getHTMLProperty(mediaItem.getTitle()));
		sb.append(getHTMLProperty(mediaItem.getDescription()));
		sb.append(getHTMLProperty(LABEL_POST_BY, mediaItem.getPostBy()));
		sb.append(getHTMLProperty(LABEL_POST_DATE, mediaItem.getPostDate()));
		sb.append(getHTMLMetadata(mediaItem, LABEL_SHOT_BY, MediaMetadata.BY));
		sb.append(getHTMLMetadata(mediaItem, LABEL_SHOT_DATE, MediaMetadata.ORIGINAL_DATE));
		sb.append(getHTMLLocation(mediaItem));

		return sb.toString();
	}
}
