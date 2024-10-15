package org.kpmp.stateManager;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;

import jakarta.servlet.http.HttpServletRequest;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

public class StateControllerTest {

	@Mock
	private StateService stateService;
	private StateController controller;

	@BeforeEach
	public void setUp() throws Exception {
		MockitoAnnotations.openMocks(this).close();
		controller = new StateController(stateService);
	}

	@AfterEach
	public void tearDown() throws Exception {
		controller = null;
	}

	@Test
	public void testSetState() {
		State state = mock(State.class);
		when(stateService.setState(state, "origin")).thenReturn("id");
		String stateId = controller.setState(state, "origin", mock(HttpServletRequest.class));

		assertEquals("id", stateId);
		verify(stateService).setState(state, "origin");
	}

	@Test
	public void testGetState() throws Exception {
		HttpServletRequest request = mock(HttpServletRequest.class);
		State expectedState = mock(State.class);
		when(stateService.getState("packageId")).thenReturn(expectedState);

		State state = controller.getState("packageId", request);

		assertEquals(expectedState, state);
		verify(stateService).getState("packageId");
	}

	@Test
	public void testGetStates() throws Exception {
		HttpServletRequest request = mock(HttpServletRequest.class);
		List<State> expectedResults = Arrays.asList(mock(State.class));
		when(stateService.getAllCurrentStates()).thenReturn(expectedResults);

		List<State> states = controller.getStates(request);

		assertEquals(expectedResults, states);
		verify(stateService).getAllCurrentStates();
	}

	@Test
	public void testGetStateDisplays() throws Exception {
		List<StateDisplay> stateDisplays = Arrays.asList(mock(StateDisplay.class));
		when(stateService.getAllStateDisplays()).thenReturn(stateDisplays);

		assertEquals(stateDisplays, controller.getStateDisplays(mock(HttpServletRequest.class)));
	}

}
