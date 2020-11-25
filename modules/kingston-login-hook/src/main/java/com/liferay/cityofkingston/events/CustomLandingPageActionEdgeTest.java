package com.liferay.cityofkingston.events;

import com.liferay.portal.kernel.events.LifecycleEvent;
import com.liferay.portal.kernel.model.Role;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.util.Portal;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.util.PropsUtil;
import com.liferay.portal.kernel.util.WebKeys;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

@RunWith(PowerMockRunner.class)
@PrepareForTest({
	PortalUtil.class,
	PropsUtil.class
})
public class CustomLandingPageActionEdgeTest {

	private HttpServletRequest request;
	private CustomLandingPageAction fixture;
	private User user;
	private LifecycleEvent lifecycleEvent;
	private HttpServletResponse response;
	private String staffLandingPage;
	private String userLandingPage;
	private String staffRoles;

	@Before
	public void setUp() throws  Exception{
		staffLandingPage = null;
		userLandingPage = null;
		staffRoles = null;

		// Static Mocks
		PowerMockito.mockStatic(PortalUtil.class);
		PowerMockito.mockStatic(PropsUtil.class);

		// Mocks
		request = Mockito.mock(HttpServletRequest.class);
		response = Mockito.mock(HttpServletResponse.class);
		user = Mockito.mock(User.class);

		// When
		Mockito.when(PortalUtil.getUser(request)).thenReturn(user);
		Mockito.when(PropsUtil.get("login.staff.landing.page")).thenReturn(staffLandingPage);
		Mockito.when(PropsUtil.get("login.citizen.group.landing.page")).thenReturn(userLandingPage);
		Mockito.when(PropsUtil.get("login.staff.roles")).thenReturn(staffRoles);

		// Fixture
		fixture = new CustomLandingPageAction();
	}

	@Test
	public void tesRunDoesNotChangeResponseIfLandingPageIsNull() throws Exception {
		// Set Up
		withRedirect(true);

		// Execute
		fixture.run(request, response);

		// Verify
		Mockito.verifyZeroInteractions(response);
	}


	@Test
	public void testIsCitizen() throws Exception {
		// Set Up
		withRoles("fdsa", "dsa");

		// Execute
		boolean result = fixture.isCitizen(request);

		// Verify
		Assert.assertEquals(true, result);
	}


	@Test
	public void testIsStaff() throws Exception {
		// Set Up
		withRoles("dsa", "staff1");

		// Execute
		boolean result = fixture.isCitizen(request);

		// Verify
		Assert.assertEquals(true, result);
	}


	private void withRoles(String... userRoles) throws Exception {
		List<Role> roleList = new ArrayList<Role>();

		for(String roleName: userRoles) {
			Role role = Mockito.mock(Role.class);
			Mockito.when(role.getName()).thenReturn(roleName);
			roleList.add(role);
		}

		Mockito.when(user.getRoles()).thenReturn(roleList);
	}

	private void withRedirect(boolean isRedirect) {
		Mockito.when(request.getAttribute(WebKeys.CURRENT_URL)).thenReturn( isRedirect ? "dsaddsa" : Portal.PATH_MAIN);
	}

}
