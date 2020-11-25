package ca.cityofkingston.model;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class MediaItem {
	
	private String thumbnailPath;
	private String mediaPath;

	private String title;
	private String description;

	private String postBy;

	public MediaItem(){}

	private Date postDate;

	private Map<String, MediaMetadata> metadata = new HashMap<String, MediaMetadata>();
	
	private boolean approved;
	private boolean pending;
	private boolean expired;

	public String getDescription() {
		return description;
	}

	public String getMediaPath() {
		return mediaPath;
	}

	public Map<String, MediaMetadata> getMetadata() {
		return metadata;
	}

	public String getPostBy() {
		return postBy;
	}

	public Date getPostDate() {
		return postDate;
	}

	public String getThumbnailPath() {
		return thumbnailPath;
	}

	public String getTitle() {
		return title;
	}

	public boolean isApproved() {
		return approved;
	}

	public boolean isPending() {
		return pending;
	}
	
	public boolean isExpired() {
		return expired;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}

	public void setMediaPath(String mediaPath) {
		this.mediaPath = mediaPath;
	}

	public void setMetadata(Map<String, MediaMetadata> metadata) {
		this.metadata = metadata;
	}

	public void setPostBy(String postBy) {
		this.postBy = postBy;
	}

	public void setPostDate(Date postDate) {
		this.postDate = postDate;
	}

	public void setThumbnailPath(String thumbnailPath) {
		this.thumbnailPath = thumbnailPath;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public void setIsApproved(boolean approved) {
		this.approved = approved;
	}

	public void setIsPending(boolean pending) {
		this.pending = pending;
	}

	public void setIsExpired(boolean expired) {
		this.expired = expired;
	}
}
