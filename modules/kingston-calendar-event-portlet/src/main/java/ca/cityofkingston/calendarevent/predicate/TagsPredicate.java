package ca.cityofkingston.calendarevent.predicate;


import ca.cityofkingston.calendarevent.event.CalendarEvent;

import com.google.common.base.Predicate;
import com.liferay.asset.kernel.model.AssetTag;

import java.util.List;

import org.apache.commons.lang3.Validate;

public class TagsPredicate implements Predicate<CalendarEvent> {
	public static TagsPredicate make(List<AssetTag> tagsFilter) {
		Validate.notNull(tagsFilter);
		TagsPredicate result = new TagsPredicate();
		result.tagsFilter = tagsFilter;
		return result;
	}
	
	private List<AssetTag> tagsFilter;
	
	@Override
	public boolean apply(CalendarEvent calendarEvent) {
		List<AssetTag> assetTags = calendarEvent.getAssetTags();
		return assetTags.containsAll(tagsFilter);
	}
	
}
