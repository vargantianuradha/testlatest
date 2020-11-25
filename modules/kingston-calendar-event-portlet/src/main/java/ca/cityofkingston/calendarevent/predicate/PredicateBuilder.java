package ca.cityofkingston.calendarevent.predicate;


import ca.cityofkingston.calendarevent.Configuration;
import ca.cityofkingston.calendarevent.event.CalendarEvent;

import com.google.common.base.Predicate;
import com.google.common.base.Predicates;
import com.liferay.asset.kernel.model.AssetCategory;
import com.liferay.asset.kernel.model.AssetTag;
import com.liferay.portal.kernel.exception.SystemException;

import java.util.List;

import org.apache.commons.lang3.Validate;

public class PredicateBuilder {

	public static PredicateBuilder make(long groupId, Configuration configuration) {
		Validate.notNull(configuration);
		PredicateBuilder result = new PredicateBuilder();
		result.groupId = groupId;
		result.configuration = configuration;
		return result;
	}

	private long groupId;
	private Configuration configuration;

	private PredicateBuilder() {}
	
	public Predicate<CalendarEvent> getPredicate() throws SystemException {
		List<AssetCategory> categoriesFilter = configuration.getAssetCategories(groupId);
		List<AssetTag> tagsFilter = configuration.getAssetTags(groupId);
		return makePredicate(categoriesFilter, tagsFilter);
	}

	@SuppressWarnings("unchecked")
	private Predicate<CalendarEvent> makePredicate(List<AssetCategory> categoriesFilter, List<AssetTag> tagsFilter) {
		return Predicates.and(CategoriesPredicate.make(categoriesFilter), TagsPredicate.make(tagsFilter), DailyRecurrencePredicate.make());
	}
}
