package org.kpmp.stateManager;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;

import org.bson.Document;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;

public class StateDisplayTest {

	private StateDisplay stateDisplay;

	@BeforeEach
	public void setUp() throws Exception {
		MockitoAnnotations.openMocks(this);
		stateDisplay = new StateDisplay();
	}

	@AfterEach
	public void tearDown() throws Exception {
        MockitoAnnotations.openMocks(this).close();
		stateDisplay = null;
	}

	@Test
	public void testSetState() {
		stateDisplay.setState("state");
		assertEquals("state", stateDisplay.getState());
	}

	@Test
	public void testSetId() {
		stateDisplay.setId("displayId");
		assertEquals("displayId", stateDisplay.getId());
	}

	@Test
	public void testSetApps() throws Exception {
		Document apps = mock(Document.class);
		stateDisplay.setApps(apps);

		assertEquals(apps, stateDisplay.getApps());
	}

}
