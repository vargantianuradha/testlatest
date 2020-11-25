<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%@ taglib uri="http://java.sun.com/portlet_2_0" prefix="portlet" %>

<%@ taglib uri="http://liferay.com/tld/aui" prefix="aui" %><%@
taglib uri="http://liferay.com/tld/portlet" prefix="liferay-portlet" %><%@
taglib uri="http://liferay.com/tld/theme" prefix="liferay-theme" %><%@
taglib uri="http://liferay.com/tld/ui" prefix="liferay-ui" %>

<liferay-theme:defineObjects />

<portlet:defineObjects />

<jsp:useBean id="view" scope="request" class="ca.cityofkingston.payments.View" />


<h2>Online Payment Support &amp; Inquiries</h2>
<p>If you are attempting to make an online payment and  require assistance,  please <a class="skip-external-icon" href="<%=view.getPaymentConfiguration().getHelpURL() %>">submit a request or inquiry</a>.  A customer service representative will aim to respond within two business days. If your request or inquiry is time-sensitive, call <a href="tel:16135460000">613-546-0000</a> Monday to Friday, 8 a.m. to 5 p.m.</p>

<h2>Aide et Demande de Renseignements au Sujet du Paiement en Ligne</h2>
<p>Si vous essayez d'effectuer un paiement en ligne et que vous avez besoin d’aide, veuillez nous faire parvenir <a class="skip-external-icon" href="<%=view.getPaymentConfiguration().getHelpURL() %>">votre demande de renseignements</a>.  Un préposé au service à la clientèle s’efforcera de communiquer avec vous dans les deux prochains jours ouvrables.  Si votre demande exige une réponse plus rapide, veuillez composer le <a href="tel:16135460000">613-546-0000</a> (tous les jours de la semaine, de 8 h à 17 h).
</p>

