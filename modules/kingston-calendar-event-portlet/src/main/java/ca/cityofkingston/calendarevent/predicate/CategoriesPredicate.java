package ca.cityofkingston.calendarevent.predicate;

import java.util.List;

import org.apache.commons.lang3.Validate;

import com.google.common.base.Predicate;
import com.liferay.asset.kernel.model.AssetCategory;

import ca.cityofkingston.calendarevent.event.CalendarEvent;

public class CategoriesPredicate implements Predicate<CalendarEvent> {
	public static CategoriesPredicate make(List<AssetCategory> categoriesFilter) {
		Validate.notNull(categoriesFilter);
		CategoriesPredicate result = new CategoriesPredicate();

		result.categoriesFilter = categoriesFilter;
		return result;
	}

	private List<AssetCategory> categoriesFilter;

	@Override
	public boolean apply(CalendarEvent calendarEvent) {
		List<AssetCategory> assetCategories = calendarEvent.getAssetCategories();
		return assetCategories.containsAll(categoriesFilter);
	}

}
