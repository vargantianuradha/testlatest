package ca.cityofkingston.payments;

import java.io.IOException;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.EventRequest;
import javax.portlet.EventResponse;
import javax.portlet.PortletException;
import javax.portlet.PortletPreferences;
import javax.portlet.PortletRequest;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;
import javax.portlet.ResourceRequest;
import javax.portlet.ResourceResponse;
import javax.portlet.filter.ActionFilter;
import javax.portlet.filter.EventFilter;
import javax.portlet.filter.FilterChain;
import javax.portlet.filter.FilterConfig;
import javax.portlet.filter.RenderFilter;
import javax.portlet.filter.ResourceFilter;

import org.apache.log4j.Logger;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.portlet.PortletPreferencesFactoryUtil;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.util.WebKeys;

import ca.cityofkingston.configuration.PaymentConfiguration;

public class ServiceAccessFilter implements EventFilter, ActionFilter, RenderFilter, ResourceFilter {

	private static final Logger LOGGER = Logger.getLogger(ServiceAccessFilter.class);
	
	static final String PRODUCT_ID = "ProductId";
	
	@Override
	public void init(FilterConfig filterConfig) throws PortletException {
		LOGGER.info("Initialized");
	}

	@Override
	public void destroy() {
		LOGGER.info("Destroyed");
	}

	@Override
	public void doFilter(EventRequest request, EventResponse response, FilterChain chain) throws IOException, PortletException {	
		LOGGER.info("Filtering event request");
	}

	@Override
	public void doFilter(ActionRequest request, ActionResponse response, FilterChain chain) throws IOException, PortletException {
		
		LOGGER.info("Filtering action request");
		
		enforceServiceAccess(request);
		chain.doFilter(request, response);
	}

	@Override
	public void doFilter(RenderRequest request, RenderResponse response, FilterChain chain) throws IOException, PortletException {
		
		LOGGER.debug("Filtering render request");
		
		enforceServiceAccess(request);
		chain.doFilter(request, response);
	}

	@Override
	public void doFilter(ResourceRequest request, ResourceResponse response, FilterChain chain) throws IOException, PortletException {
		LOGGER.info("Filtering resource request");
		chain.doFilter(request, response);		
	}

	private void enforceServiceAccess(PortletRequest request) throws PortletException {
		try {

			String portletID = (String) request.getAttribute(WebKeys.PORTLET_ID);
			String productId = request.getParameter(PRODUCT_ID);
			
			LOGGER.debug("Enforcing service access for product id " + productId);
			
			PortletPreferences preferences = PortletPreferencesFactoryUtil.getPortletSetup(request, portletID);
			PaymentConfiguration configuration = PaymentConfiguration.make(PortalUtil.getHttpServletRequest(request), preferences);
			
			if(!configuration.isServiceEnabled(productId)) {
				LOGGER.error("Service for product id " + portletID + " is currently disabled");
				throw new IllegalArgumentException("Service for product id " + portletID + " is currently disabled");
			}
			
		} catch (SystemException e) {
			LOGGER.error("Render filter", e);
			throw new PortletException(e);
		} catch (PortalException e) {
			LOGGER.error("Render filter", e);
			throw new PortletException(e);
		}
	}


}
