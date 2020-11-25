package ca.cityofkingston.model;

import java.util.Comparator;

public class PostDateComparator implements Comparator<MediaItem> {

	public int compare(MediaItem item1, MediaItem item2) {
		return item1.getPostDate().compareTo(item2.getPostDate());
	}

}
