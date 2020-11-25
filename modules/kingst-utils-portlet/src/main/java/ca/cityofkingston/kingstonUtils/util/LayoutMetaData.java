package ca.cityofkingston.kingstonUtils.util;

import java.util.Date;

public class LayoutMetaData {

	public static final String TYPE_ARTICLE = "Article";
	public static final String TYPE_DEFAULT = "WebPage";

	private String 	author;
	private String	category;
	private Date 	dateModified;
	private Date 	datePublished;
	private String 	canonicalUrl;
	private String 	headline;
	private long 	height;
	private String 	imageURL;
	private String 	publisher;
	private String 	type;
	private long   	width;
	
	LayoutMetaData() {
		author			= "City of Kingston";
		dateModified 	= null;
		datePublished 	= null;
		canonicalUrl 	= null;
		category		= "";
		type		 	= TYPE_DEFAULT;
		headline		= null;
		imageURL		= "";
		publisher		= "City of Kingston";
		height			= 0;
		width			= 0;
	}
		
	public Date getLastModifiedDate() {
		return dateModified;
	}
	
	public void setLastModifiedDate( Date date) {
		dateModified = date;
	}
	
	public Date getPublishedDate() {
		return datePublished;
	}
	
	public void setPublishedDate( Date date) {
		datePublished = date;
	}

	public String getCanonicalURL() {
		return canonicalUrl;
	}
	
	public void setCanonicalURL( String url) {
		canonicalUrl = url;
	}

	public String getCategory() {
		return category;
	}
	
	public void setCategory( String category) {
		this.category = category;
	}
	
	public String getType() {
		return type;
	}
	
	public void setType( String type) {
		this.type = type;
	}

	public String getHeadline() {
		return headline;
	}
	public void setHeadline(String headline) {
		this.headline = headline;
		
	}

	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
		
	}

	public String getPublisher() {
		return publisher;
	}
	public void setPublisher(String publisher) {
		this.publisher = publisher;
		
	}

	public String getImageURL() {
		return imageURL;
	}
	public void setImageURL(String imageURL) {
		this.imageURL = imageURL;
		
	}

	public long getImageHeight() {
		return height;
	}
	public void setImageHeight(long height) {
		this.height = height;
		
	}

	public long getImageWidth() {
		return width;
	}
	public void setImageWidth(long width) {
		this.width = width;
		
	}

}
