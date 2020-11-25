package ca.cityofkingston.kingstonUtils.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.liferay.expando.kernel.exception.DuplicateColumnNameException;
import com.liferay.expando.kernel.exception.NoSuchTableException;
import com.liferay.expando.kernel.model.ExpandoColumn;
import com.liferay.expando.kernel.model.ExpandoColumnConstants;
import com.liferay.expando.kernel.model.ExpandoTable;
import com.liferay.expando.kernel.model.ExpandoValue;
import com.liferay.expando.kernel.service.ExpandoColumnLocalServiceUtil;
import com.liferay.expando.kernel.service.ExpandoTableLocalServiceUtil;
import com.liferay.expando.kernel.service.ExpandoValueLocalServiceUtil;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.BaseModel;
import com.liferay.portal.kernel.util.UnicodeProperties;

public class ExpandoUtil {

	public static final String EXPANDO_CATEGORY = "category";
	public static final String EXPANDO_CATEGORY_DEFAULT = 	CludoUtil.CATEGORY_DEFAULT +"," + 
															CludoUtil.CATEGORY_COMMITTEE +"," +
															CludoUtil.CATEGORY_COUNCIL + "," +
															CludoUtil.CATEGORY_EVENTS + "," +
															CludoUtil.CATEGORY_NEWS	+"," +
															CludoUtil.CATEGORY_PROJECTS +"," +
															CludoUtil.CATEGORY_SERVICES;
	

	public static void checkDefaultValueStartsWithEmpty( long companyId, String className, String expandoName)
	{
		_log.debug("checkDefaultValueStartsWithEmpty: " + className + "("+expandoName+"): companyId=" + companyId );
		
		//-- Get expando basic data
		ExpandoTable expandoTable 	= null;
		ExpandoColumn column 		= null;
				
		try {
			expandoTable 	= ExpandoTableLocalServiceUtil.getDefaultTable(companyId, className);
			column 			= ExpandoColumnLocalServiceUtil.getColumn(expandoTable.getTableId(), expandoName);
			if (column != null) {
				_log.debug("Class "+className+" expando values=" + column.getDefaultData());
			}
		}
		catch (Exception e)
		{
			_log.error("Couldn't find "+className+" expando=" + expandoName + " reason:" + e.getMessage());
			return;
		}
		
		//-- To update expando default values
		// 1. Generate default Expando values from the column definition
		// 2. Walk through the array and look for the "empty" string
		// 3. If it doesn't exist -> prepend to array
		// 4. Return array string form and apply to column definition
		
		List<String> origDefaultList = new ArrayList<String> (Arrays.asList(column.getDefaultData().split(",")));
		int index = origDefaultList.indexOf("");
		
		if (index < 0 )
		{		
			_log.debug("Setting "+className+" expando=" + expandoName + " to defaultVals=" + origDefaultList);
		 
			ArrayList <String> newDefaultList = new ArrayList<String>();
			newDefaultList.add("");
			newDefaultList.addAll(origDefaultList);
			
			column.setDefaultData(join(newDefaultList.toArray(),","));
		
			try{
				ExpandoColumnLocalServiceUtil.updateExpandoColumn(column);
			}catch (Exception e) {
				_log.debug("Couldn't add an empty entry to "+className+" expando=" + expandoName + " reason:" + e.getMessage());
			}
			
			
		}
				
	}
	
	public static void createExpando(long companyId, String className, String expandoFieldName, String displayType, String defaultVal) {
		try {
            _log.debug("createExpando("+companyId+",'"+className+"','"+expandoFieldName+"')");

			// Get a reference to the ExpandoTable (User class)  
			ExpandoTable table = null;     
		  
			try {  
			    table = ExpandoTableLocalServiceUtil.getDefaultTable(companyId, className); 
			}  
			catch(NoSuchTableException nste) {  
			    table = ExpandoTableLocalServiceUtil.addDefaultTable(companyId, className);  
			}  
  
            // Add the ExpandoColumn (expandoFieldName)    
            ExpandoColumn column = null;  
            long tableId = table.getTableId();  
            try {  
                column = ExpandoColumnLocalServiceUtil.addColumn( tableId, expandoFieldName, ExpandoColumnConstants.STRING_ARRAY);  
  
                // Add Unicode Properties  
                UnicodeProperties properties = new UnicodeProperties();  
  
                properties.setProperty( ExpandoColumnConstants.INDEX_TYPE, Boolean.FALSE.toString());  
                properties.setProperty( ExpandoColumnConstants.PROPERTY_DISPLAY_TYPE, displayType);  
                properties.setProperty( ExpandoColumnConstants.PROPERTY_HIDDEN, Boolean.FALSE.toString());  
                properties.setProperty( ExpandoColumnConstants.PROPERTY_VISIBLE_WITH_UPDATE_PERMISSION, Boolean.FALSE.toString());  
 
                column.setTypeSettingsProperties(properties);
                column.setDefaultData(defaultVal);
                
        		try {
        			ExpandoColumnLocalServiceUtil.updateExpandoColumn(column);
        		} catch (Exception e) {
        			_log.debug("Couldn't update expando=" + expandoFieldName + " reason:" + e.getMessage());
        		}
 
        		// Set Guest view permission for the custom field
        		/* Not quite working so commenting out for now
        			Role guestRole = RoleLocalServiceUtil.getRole(companyId, RoleConstants.GUEST);
        			String[] actionsRW = new String[] { ActionKeys.VIEW }; 
        			ResourcePermissionServiceUtil.setIndividualResourcePermissions(groupId, companyId, ExpandoColumn.class.getName(), String.valueOf(column.getColumnId()), guestRole.getRoleId(),actionsRW);
        		*/
        		
            }  
            catch(DuplicateColumnNameException dcne) {  
  
                _log.debug("createExpando: Expando field '"+expandoFieldName+"' already exists for " + className);
            } 
		}
		catch (Exception e)
		{
			_log.error("createExpando: Unable to create expando field '"+expandoFieldName+"' for " + className );
		}
	}
	
	public static void updateExpandoDefaultValues(String className, long companyId, String expandoName, String oldVal, String newVal) throws SystemException {
		_log.debug("Updating expando default values for " + className + "("+expandoName+"): companyId=" + companyId + " old=" + oldVal + " new=" +newVal);
		//-- Get expando basic data
		ExpandoTable expandoTable 	= null;
		ExpandoColumn column 		= null;
				
		try {
			expandoTable 	= ExpandoTableLocalServiceUtil.getDefaultTable(companyId, className);
			column 			= ExpandoColumnLocalServiceUtil.getColumn(expandoTable.getTableId(), expandoName);
			if (column != null) {
				_log.debug("Class "+className+" expando values=" + column.getDefaultData());
			}
		}
		catch (Exception e)
		{
			_log.error("Couldn't find "+className+" expando=" + expandoName + " reason:" + e.getMessage());
			return;
		}
		
		//-- To update expando default values
		// 1. Generate default Expando values from the column definition
		// 2. Walk through the array and update the old val with the new val
		// 3. If the old val doesn't exist -> add
		// 4. Return array string form and apply to column definition
		
		List<String> origDefaultList = new ArrayList<String> (Arrays.asList(column.getDefaultData().split(",")));

		int index = origDefaultList.indexOf(oldVal);
		
		if (index < 0 )
			origDefaultList.add(newVal);
		else
			origDefaultList.set(index, newVal);		
		
		_log.debug("Setting "+className+" expando=" + expandoName + " to defaultVals=" + origDefaultList);
		 
		column.setDefaultData(join(origDefaultList.toArray(),","));
		
		try {
			ExpandoColumnLocalServiceUtil.updateExpandoColumn(column);
		} catch (Exception e) {
			_log.debug("Couldn't find "+className+" expando=" + expandoName + " reason:" + e.getMessage());
		}
		
		//-- Find all expandos that had the old owner value AND Update to new value.	
		if (oldVal != null && !newVal.equals(oldVal))
		{
			_log.debug("Updating all "+className+ " with expando=" + expandoName + " and current value='" + oldVal+"' to new='" + newVal + "'");
			updateExpandoEntryValues(className, expandoTable, column, oldVal, newVal  );			
		}
		
	}

	public static void removeExpandoDefaultValues(String className, long companyId, String expandoName, String oldVal) throws SystemException {
		_log.debug("Removing value from expando " + className + ": " + oldVal + " companyId=" + companyId);

		//-- Get expando basic data
		ExpandoTable expandoTable 	= null;
		ExpandoColumn column 		= null;
				
		try {
			expandoTable 	= ExpandoTableLocalServiceUtil.getDefaultTable(companyId, className);
			column 			= ExpandoColumnLocalServiceUtil.getColumn(expandoTable.getTableId(), expandoName);
			if (column != null) {
				_log.debug("Class "+className+" expando values=" + column.getDefaultData());
			}
		}
		catch (Exception e)
		{
			_log.debug("Couldn't find "+className+" expando=" + expandoName + " reason:" + e.getMessage());
			return;
		}
		
		//-- To update expando default values
		// 1. Generate default Expando values from the column definition
		// 2. Walk through the array and remove the old val
		// 3. Return array string form and apply to column definition
		
		ArrayList<String> origDefaultList = new ArrayList<String> (Arrays.asList(column.getDefaultData().split(",")));
		origDefaultList.remove(oldVal);
		column.setDefaultData(join(origDefaultList.toArray(),","));
				
		try {
			ExpandoColumnLocalServiceUtil.updateExpandoColumn(column);
		} catch (Exception e) {
			_log.debug("Couldn't find "+className+" expando=" + expandoName + " reason:" + e.getMessage());
		}
		
		
		//-- Find all expandos that had the old owner value AND Update to new value.
		_log.debug("Removing all "+className+ " with expando=" + expandoName + " and current value='" + oldVal +"'");
		try {
			removeExpandoValues(className, expandoTable, column, oldVal  );
		}
		catch (SystemException e)
		{
			e.printStackTrace();
			throw e;
		}

	
	}

	public static void updateExpandoEntryValues(String className, ExpandoTable table, ExpandoColumn column, String oldValue, String newValue) throws SystemException {
		// For expando values that support multivalue fields, we need to:
		// -- 1. Get all the columns and itereate over the data field to see if the oldvalue exists in the list
		// -- 2. Replace that old value with new value in the list
		// -- 3. Update the expando with the new list
		List<ExpandoValue> expVals = ExpandoValueLocalServiceUtil.getColumnValues(table.getCompanyId(), className, table.getName(), column.getName(), -1, -1);
		
		for (ExpandoValue expVal: expVals)
		{
			
			String values[] = expVal.getData().split(",");
			
			for (int i = 0; i < values.length; i++)
			{
				//-- IF value is in list
				//-- THEN
				//--   1. Replace the value in the list
				//--   2. Apply to db
				//--   3. End loop
				if (values[i].equals(oldValue))
				{
					values[i] = newValue;
					String newData = join(values,",");
					expVal.setData(newData);
					ExpandoValueLocalServiceUtil.updateExpandoValue(expVal);
					break;
				}
			}
		}

	}

	public static void removeExpandoValues(String className, ExpandoTable table, ExpandoColumn column, String oldValue) throws SystemException {
	//	List<ExpandoValue> expVals = ExpandoValueLocalServiceUtil.getColumnValues(table.getCompanyId(), className, table.getName(), column.getName(),oldValue, -1, -1);
		
		// For expando values that support multivalue fields, we need to:
		// -- 1. Get all the columns and itereate over the data field to see if the oldvalue exists in the list
		// -- 2. Replace that old value with new value in the list
		// -- 3. Update the expando with the new list
		List<ExpandoValue> expVals = ExpandoValueLocalServiceUtil.getColumnValues(table.getCompanyId(), className, table.getName(), column.getName(), -1, -1);
		
		for (ExpandoValue expVal: expVals)
		{			
			String values[] = expVal.getData().split(",");
			
			boolean found = false;
			ArrayList<String> newValues = new ArrayList<String>();
			
			for (int i = 0; i < values.length; i++)
			{
				//-- IF value is in list
				//-- THEN
				//--   Do Not add 
				//-- ELSE
				//--   Add
				if (values[i].equals(oldValue))
				{
					found = true;
				}
				else
					newValues.add(values[i]);
			}
			
			if (found)
			{
				String newData = join( newValues.toArray(new String[newValues.size()]),",");
				expVal.setData(newData);
				_log.debug("Removing "+oldValue+"from expando class " + className + " "+ expVal.getClassPK() + " newvalue=" + newData);
				ExpandoValueLocalServiceUtil.updateExpandoValue(expVal);				
			}
		}

	}

	public static boolean doesExpandoValBelongToFieldName(ExpandoValue expVal, String expandoFieldName) {
		try {

			// Check the column's name in to see if it matches  
            ExpandoColumn column = ExpandoColumnLocalServiceUtil.getColumn(expVal.getColumnId());  
            if (expandoFieldName.equals(column.getName()))
            {
                _log.debug("doesExpandoValMatchFieldName("+expVal.getCompanyId()+" "+expVal.getClassName()+"','"+expandoFieldName+"') returns true");
            	return true;
            }
                 
		}
		catch (Exception e)
		{
			// Ignore
		}
		
        _log.debug("doesExpandoValMatchFieldName("+expVal.getCompanyId()+" "+expVal.getClassName()+"','"+expandoFieldName+"') returns false");
		return false;

	}

	@SuppressWarnings("rawtypes")
	public static String getSingleton(BaseModel obj, String fieldName)
	{
		String result = null;
		
		try {
			String vals[] = (String []) (obj).getExpandoBridge().getAttribute( fieldName );

			if (vals == null) {
				_log.error("ExpandoUtil.getSingleton("+obj+","+fieldName+") returns NULL - please check to ensure it exists and permissions are correct");
			}
			else if (vals.length > 0) {
				result = vals[0];
			}
			
		}
		catch (Exception e)
		{
			//-- ignore
			_log.debug("ExpandoUtil.getSingleton("+obj+","+fieldName+") returns NULL: " +e);
		}
		return result;
	}
	
	private static String join(Object[] input, String delimiter)
	{
	    StringBuilder sb = new StringBuilder();
	    for(Object value : input)
	    {
	        sb.append(value);
	        sb.append(delimiter);
	    }
	    int length = sb.length();
	    if(length > 0)
	    {
	        // Remove the extra delimiter
	        sb.setLength(length - delimiter.length());
	    }
	    return sb.toString();
	}

	private static Log _log = LogFactoryUtil.getLog(ExpandoUtil.class);

	
}
