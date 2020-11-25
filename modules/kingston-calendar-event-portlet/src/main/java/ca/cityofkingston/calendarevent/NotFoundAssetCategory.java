package ca.cityofkingston.calendarevent;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import com.liferay.asset.kernel.model.AssetCategory;
import com.liferay.expando.kernel.model.ExpandoBridge;
import com.liferay.exportimport.kernel.lar.StagedModelType;
import com.liferay.portal.kernel.exception.LocaleException;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.model.BaseModel;
import com.liferay.portal.kernel.model.CacheModel;
import com.liferay.portal.kernel.service.ServiceContext;

class NotFoundAssetCategory implements AssetCategory {

	private static final long serialVersionUID = 1L;
	
	@Override
	public boolean equals(Object obj) {
		return false;
	}
	
	@Override
	public Object clone() {
		try {
			return super.clone();
		} catch (CloneNotSupportedException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public long getPrimaryKey() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void setPrimaryKey(long primaryKey) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String getUuid() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setUuid(String uuid) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public long getCategoryId() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void setCategoryId(long categoryId) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public long getGroupId() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void setGroupId(long groupId) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public long getCompanyId() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void setCompanyId(long companyId) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public long getUserId() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void setUserId(long userId) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String getUserUuid() throws SystemException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setUserUuid(String userUuid) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String getUserName() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setUserName(String userName) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Date getCreateDate() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setCreateDate(Date createDate) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Date getModifiedDate() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setModifiedDate(Date modifiedDate) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public long getParentCategoryId() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void setParentCategoryId(long parentCategoryId) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public long getLeftCategoryId() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void setLeftCategoryId(long leftCategoryId) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public long getRightCategoryId() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void setRightCategoryId(long rightCategoryId) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setName(String name) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String getTitle() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getTitle(Locale locale) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getTitle(Locale locale, boolean useDefault) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getTitleCurrentLanguageId() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getTitleCurrentValue() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Map<Locale, String> getTitleMap() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setTitle(String title) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setTitle(String title, Locale locale) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setTitle(String title, Locale locale, Locale defaultLocale) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setTitleCurrentLanguageId(String languageId) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setTitleMap(Map<Locale, String> titleMap) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setTitleMap(Map<Locale, String> titleMap, Locale defaultLocale) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String getDescription() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getDescription(Locale locale) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getDescription(Locale locale, boolean useDefault) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getDescription(String languageId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getDescription(String languageId, boolean useDefault) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getDescriptionCurrentLanguageId() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getDescriptionCurrentValue() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Map<Locale, String> getDescriptionMap() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setDescription(String description) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setDescription(String description, Locale locale) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setDescription(String description, Locale locale,
			Locale defaultLocale) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setDescriptionCurrentLanguageId(String languageId) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setDescriptionMap(Map<Locale, String> descriptionMap) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setDescriptionMap(Map<Locale, String> descriptionMap,
			Locale defaultLocale) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public long getVocabularyId() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void setVocabularyId(long vocabularyId) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean isNew() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void setNew(boolean n) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean isCachedModel() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void setCachedModel(boolean cachedModel) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean isEscapedModel() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void prepareLocalizedFieldsForImport(Locale defaultImportLocale)
			throws LocaleException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public int compareTo(AssetCategory assetCategory) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public CacheModel<AssetCategory> toCacheModel() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public AssetCategory toEscapedModel() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String toXmlString() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void resetOriginalValues() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Class<?> getModelClass() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getModelClassName() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void persist() throws SystemException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<AssetCategory> getAncestors() throws PortalException,
			SystemException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getTitle(String languageId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getTitle(String languageId, boolean useDefault) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean isRootCategory() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Date getLastPublishDate() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setLastPublishDate(Date lastPublishDate) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setExpandoBridgeAttributes(BaseModel<?> baseModel) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setExpandoBridgeAttributes(ExpandoBridge expandoBridge) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String[] getAvailableLanguageIds() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getDefaultLanguageId() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void prepareLocalizedFieldsForImport() throws LocaleException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public AssetCategory toUnescapedModel() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean isEntityCacheEnabled() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isFinderCacheEnabled() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public StagedModelType getStagedModelType() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public long getNestedSetsTreeNodeLeft() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public long getNestedSetsTreeNodeRight() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public long getNestedSetsTreeNodeScopeId() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void setNestedSetsTreeNodeLeft(long nestedSetsTreeNodeLeft) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setNestedSetsTreeNodeRight(long nestedSetsTreeNodeRight) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public AssetCategory getParentCategory() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getPath(Locale locale) throws PortalException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getExternalReferenceCode() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setExternalReferenceCode(String externalReferenceCode) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String getPath(Locale locale, boolean reverse) throws PortalException {
		// TODO Auto-generated method stub
		return null;
	}
	
}