import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.kpmp.FailedPackageChecker;
import org.kpmp.dataManager.DataManagerRepository;
import org.kpmp.dataManager.DluPackageInventory;
import org.kpmp.stateManager.State;
import org.kpmp.stateManager.StateService;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.client.RestTemplate;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;

public class FailedPackageCheckerTest {


    @Mock
    private StateService service;
    @Mock
    RestTemplate restTemplate;

    @Mock
    DataManagerRepository dataManagerRepository;

    private FailedPackageChecker packageChecker;
    @Value("${package.state.checker.timeout}")
    private long timeout;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        packageChecker = new FailedPackageChecker(service, restTemplate, dataManagerRepository);
    }

    @After
    public void tearDown() throws Exception {
        packageChecker = null;
    }

    @Test
    public void testGetTimeSinceLastModified() throws Exception {
        assertEquals(42, packageChecker.getTimeSinceLastModified(System.currentTimeMillis() - 42));
    }

    @Test
    public void testPackageDidFail() throws Exception {
        State state = new State();

        // To Do: Mock enough of this out so we can test the "run" method
//        state.setPackageId("package_id");
//        DluPackageInventory packageInventory = new DluPackageInventory();
//        packageInventory.setDluError(false);
//        packageInventory.setDluPackageId("package_id");
//        when(dataManagerRepository.findByDluPackageId("package_id")).thenReturn(packageInventory);

        long timeSinceLastModified = System.currentTimeMillis() - timeout + 1;
        assertFalse(packageChecker.packageDidFail(state, timeSinceLastModified));

        timeSinceLastModified = System.currentTimeMillis() - timeout - 1;
        assertTrue(packageChecker.packageDidFail(state, timeSinceLastModified));
    }
}
