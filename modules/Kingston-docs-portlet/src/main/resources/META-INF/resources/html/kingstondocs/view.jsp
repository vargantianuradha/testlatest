<%@ page import="java.util.List"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="java.util.Collections"%>
<%@ page import="java.util.Comparator"%>
<%@ page import="java.util.ListIterator" %>
<%@ page import="java.text.DateFormat" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@page import="com.liferay.portal.kernel.util.HtmlUtil" %>
<%@page import="com.liferay.portal.kernel.util.HttpUtil" %>
<%@page import="com.liferay.portal.kernel.util.ArrayUtil" %>
<%@page import="com.liferay.document.library.kernel.service.DLFileEntryLocalServiceUtil" %>
<%@page import="com.liferay.document.library.kernel.service.DLFolderLocalServiceUtil" %>
<%@page import="com.liferay.document.library.kernel.model.DLFolderConstants" %>
<%@page import="com.liferay.document.library.kernel.model.DLFolder" %>
<%@page import="com.liferay.document.library.kernel.model.DLFileEntry" %>
<%@page import="com.liferay.portal.kernel.theme.ThemeDisplay" %>

<%@ taglib uri="http://liferay.com/tld/aui" prefix="aui" %>
<%@ taglib uri="http://liferay.com/tld/portlet" prefix="liferay-portlet" %>
<%@ taglib uri="http://liferay.com/tld/security" prefix="liferay-security" %>
<%@ taglib uri="http://liferay.com/tld/theme" prefix="liferay-theme" %>
<%@ taglib uri="http://liferay.com/tld/ui" prefix="liferay-ui" %>
<%@ taglib uri="http://liferay.com/tld/util" prefix="liferay-util" %>

<%@ taglib uri="http://java.sun.com/portlet_2_0" prefix="portlet" %>
<portlet:defineObjects />
<liferay-theme:defineObjects />

<%
Long parentFolderId = DLFolderConstants.DEFAULT_PARENT_FOLDER_ID;  
//DLFolder folder = DLFolderLocalServiceUtil.getFolder(themeDisplay.getCompanyGroupId(), parentFolderId, dirName);  

String includeExtensions[] = { ".pdf", ".docx", ".doc", ".xls", ".xlsx", ".ppt", ".pptx" };

List<DLFileEntry> fileEntryList = null;  
ArrayList<DLFileEntry> sortedList = new ArrayList<DLFileEntry>();

try {  
	fileEntryList = DLFileEntryLocalServiceUtil.getGroupFileEntries(themeDisplay.getScopeGroupId(),-1, -1); //12345 is folderId  
	for (DLFileEntry fileEntry: fileEntryList ) {
		sortedList.add(fileEntry);
	}
	
    Collections.sort(sortedList, new Comparator<DLFileEntry>() {
          @Override
          public int compare(DLFileEntry c1, DLFileEntry c2) {
              //You should ensure that list doesn't contain null values!
              //return c1.getTitle().compareTo(c2.getTitle());
			  return c2.getCreateDate().compareTo(c1.getCreateDate());
          }
      });
} catch (Exception e) {  
	System.out.println("Error: " + e);
}
%>
<h2>File Listing (<%=themeDisplay.getScopeGroupId()%>)</h2>
<ul>
<%
DateFormat df = new SimpleDateFormat("yyyy-MM-dd");

for(DLFileEntry fileEntryObj : sortedList) {
	//-- IF file is not an image AND
	//--    the user has permission
	//-- THEN
	//--    Show entry
	if ( ArrayUtil.contains(includeExtensions, "." + fileEntryObj.getExtension()) &&
		 permissionChecker.hasPermission(themeDisplay.getScopeGroupId(), DLFileEntry.class.getName(), fileEntryObj.getPrimaryKey(), "VIEW") ) {
		
		String fileUrl = themeDisplay.getPortalURL() + themeDisplay.getPathContext() + "/documents/" + fileEntryObj.getRepositoryId() + "/" + fileEntryObj.getFolderId() +"/" + HttpUtil.encodeURL(HtmlUtil.unescape(fileEntryObj.getTitle()), true);
		
		String created = df.format(fileEntryObj.getCreateDate());
		
%>
     <li><a href="<%= fileUrl %>"><%=fileEntryObj.getTitle() %></a>:<%=created %>:<%=fileEntryObj.getReadCount()%></li>
<%   
	}
}  
%>
</ul>
