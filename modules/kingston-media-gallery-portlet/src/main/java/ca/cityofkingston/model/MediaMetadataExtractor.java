package ca.cityofkingston.model;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.osgi.service.component.annotations.Reference;

import com.liferay.document.library.kernel.model.DLFileEntryMetadata;
import com.liferay.document.library.kernel.model.DLFileEntryType;
import com.liferay.document.library.kernel.model.DLFileVersion;
import com.liferay.document.library.kernel.service.DLFileEntryMetadataLocalServiceUtil;
import com.liferay.document.library.kernel.service.DLFileEntryTypeServiceUtil;
import com.liferay.dynamic.data.mapping.exception.StorageException;
import com.liferay.dynamic.data.mapping.kernel.DDMFormField;
import com.liferay.dynamic.data.mapping.kernel.DDMFormFieldValue;
import com.liferay.dynamic.data.mapping.kernel.DDMFormValues;
import com.liferay.dynamic.data.mapping.kernel.StorageEngineManagerUtil;
import com.liferay.dynamic.data.mapping.model.DDMStructure;
import com.liferay.dynamic.data.mapping.service.DDMStructureLocalService;
import com.liferay.dynamic.data.mapping.service.DDMStructureLocalServiceUtil;
import com.liferay.dynamic.data.mapping.storage.StorageEngine;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.repository.model.FileEntry;
import com.liferay.portal.kernel.repository.model.FileVersion;
import com.liferay.portal.kernel.util.LocaleUtil;


public class MediaMetadataExtractor {
	
	
	private FileVersion fileVersion;
	private long fileEntryTypeId;
	private long fileVersionId;

	public MediaMetadataExtractor(FileEntry fileEntry) throws SystemException, PortalException{

		this.fileVersion = fileEntry.getFileVersion();
		this.fileVersionId = fileVersion.getFileVersionId();
        
		if (fileVersion.getModel() instanceof DLFileVersion) {
			DLFileVersion dlFileVersion = (DLFileVersion)fileVersion.getModel();
			this.fileEntryTypeId = dlFileVersion.getFileEntryTypeId();
		} else throw new SystemException("Only 'DLFileVersion' are supported by the MediaMetadataExtractor");
	}
    
	
	
	public Map<String, MediaMetadata> extractAllMetadata() throws SystemException {

		Map<String, MediaMetadata> metadataMap = extractEmbeddedMetadata();
		metadataMap.putAll(extractDocumentTypeMetadata());

		return metadataMap;
	}


	public Map<String, MediaMetadata> extractDocumentTypeMetadata() throws SystemException {

		Map<String, MediaMetadata> metadataMap = new HashMap<String, MediaMetadata>();

		if (fileEntryTypeId > 0) {
			try {
				populateWithDocumentTypeMetadata(metadataMap);
			}catch (PortalException e) {
				//getLog().debug(e.getMessage());
			}
		}
		return metadataMap;
	}


	public Map<String, MediaMetadata> extractEmbeddedMetadata() throws SystemException {

		Map<String, MediaMetadata> metadataMap = new HashMap<String, MediaMetadata>();
		List<DDMStructure> ddmStructures = getDDMStructures();
		//List<DDMStructure> ddmStructures = DDMStructureLocalServiceUtil.getDDMStructures(0, DDMStructureLocalServiceUtil.getDDMStructuresCount()-1); 
		for (DDMStructure ddmStructure : ddmStructures) {
			try {
				//DLFileEntryMetadata fileEntryMetadata = getFiledEntryMetadata(ddmStructure, fileVersionId);
				//Fields fields = getFields(fileEntryMetadata.getDDMStorageId());
				DLFileEntryMetadata fileEntryMetadata = DLFileEntryMetadataLocalServiceUtil.getFileEntryMetadata(ddmStructure.getStructureId(), fileVersionId);
				Long storageId = fileEntryMetadata.getDDMStorageId();
				DDMFormValues ddmFormValues=  StorageEngineManagerUtil.getDDMFormValues(fileEntryMetadata.getDDMStorageId());
				List<DDMFormFieldValue> fieldValueList = ddmFormValues.getDDMFormFieldValues(); 
				metadataMap.putAll(createMetadataMap(fieldValueList));
			}catch (PortalException e) {
				//getLog().debug(e.getMessage());
			}
		}

		return metadataMap;
	}

	protected Map<String, MediaMetadata> createMetadataMap(List<DDMFormFieldValue> fieldValueList) throws PortalException, SystemException {

		Map<String, MediaMetadata> metadataMap = new HashMap<String, MediaMetadata>();

		for(DDMFormFieldValue fieldVal : fieldValueList){
			DDMFormField ddmFormField = fieldVal.getDDMFormField();
			MediaMetadata metadata = new MediaMetadata(fieldVal);
			metadataMap.put(ddmFormField.getName(), metadata);
		}		return metadataMap;
	}

	void populateWithDocumentTypeMetadata(Map<String, MediaMetadata> metadataMap) throws PortalException, SystemException, StorageException {
		DLFileEntryType fileEntryType = DLFileEntryTypeServiceUtil.getFileEntryType(fileEntryTypeId);
		List<com.liferay.dynamic.data.mapping.kernel.DDMStructure> ddmStructures = fileEntryType.getDDMStructures();

		for (com.liferay.dynamic.data.mapping.kernel.DDMStructure ddmStructure : ddmStructures) {
			DLFileEntryMetadata fileEntryMetadata = getFiledEntryMetadata(ddmStructure, fileVersion.getFileVersionId());
			//Fields fields = getFields(fileEntryMetadata.getDDMStorageId());
			Long storageId = fileEntryMetadata.getDDMStorageId();
			DDMFormValues ddmFormValues=  StorageEngineManagerUtil.getDDMFormValues(fileEntryMetadata.getDDMStorageId());
			List<DDMFormFieldValue> fieldValueList = ddmFormValues.getDDMFormFieldValues(); 
			metadataMap.putAll(createMetadataMap(fieldValueList));
		}
	}


	/* For Testing */
	DLFileEntryMetadata getFiledEntryMetadata(com.liferay.dynamic.data.mapping.kernel.DDMStructure ddmStructure, long fvId) throws PortalException, SystemException {
		return DLFileEntryMetadataLocalServiceUtil.getFileEntryMetadata(ddmStructure.getStructureId(), fvId);
	}

	/* For Testing */
	List<DDMStructure> getDDMStructures() throws SystemException {
		return DDMStructureLocalServiceUtil.getDDMStructures(0, DDMStructureLocalServiceUtil.getDDMStructuresCount()-1); 
	}

	/* For Testing */
//	Fields getFields(long storeageId) throws StorageException {
//		return StorageEngineUtil.getFields(storeageId);
//	}

	
	
	
	// new
	
	@Reference
	private DDMStructureLocalService _ddmStructureLocalService;
	
	@Reference(unbind = "-")
    public void setStorageEngine(StorageEngine storageEngine) {
        _storageEngine = storageEngine;
    }
	
	private StorageEngine _storageEngine;
}
