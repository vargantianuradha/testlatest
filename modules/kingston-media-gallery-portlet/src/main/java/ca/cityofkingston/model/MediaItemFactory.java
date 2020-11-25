package ca.cityofkingston.model;

import java.util.Map;

import com.liferay.document.library.kernel.util.DLUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.repository.model.FileEntry;
import com.liferay.portal.kernel.repository.model.FileVersion;
import com.liferay.portal.kernel.theme.ThemeDisplay;

public class MediaItemFactory {
	
	public static final String IMAGE_THUMBNAIL_QUERY = "&imageThumbnail=1";
	public static final String IMAGE_PREVIEW_QUERY = "&imagePreview=1&type=.png";

	public MediaItem makeMediaItem(FileEntry fileEntry, ThemeDisplay themeDisplay) throws SystemException, PortalException {

		MediaItem item = new MediaItem();
		FileVersion fileVersion = fileEntry.getFileVersion();

		item.setTitle(fileVersion.getTitle());
		item.setDescription(fileVersion.getDescription());
		item.setPostBy(fileEntry.getUserName());
		item.setPostDate(fileVersion.getCreateDate());
		
		// Image Status
		item.setIsApproved(fileVersion.isApproved());
		item.setIsPending(fileVersion.isPending());
		item.setIsExpired(fileVersion.isExpired());

		// Image Preview path
		String mediaPath = getPath(fileEntry, themeDisplay, item, fileVersion, IMAGE_PREVIEW_QUERY);
		item.setMediaPath(mediaPath);

		// Image Thumbnail path
		String thumbnailPath = getPath(fileEntry, themeDisplay, item, fileVersion, IMAGE_THUMBNAIL_QUERY); 
		item.setThumbnailPath(thumbnailPath);

		// Metadata
		MediaMetadataExtractor metadataExtractor = getMediadataExtractor(fileEntry);
		Map<String, MediaMetadata> metadata = metadataExtractor.extractAllMetadata();
		//metadataExtractor.extractAllMetadata();
		item.setMetadata(metadata);

		return item;
	}

	/* For Testing */
	MediaMetadataExtractor getMediadataExtractor(FileEntry fileEntry) throws SystemException, PortalException {
		return new MediaMetadataExtractor(fileEntry);
	}

	/* For Testing */
	String getPath(FileEntry fileEntry, ThemeDisplay themeDisplay, MediaItem item, FileVersion fileVersion, String query) throws SystemException, PortalException {
		return DLUtil.getPreviewURL(fileEntry, fileVersion, themeDisplay, query);
	}

}
