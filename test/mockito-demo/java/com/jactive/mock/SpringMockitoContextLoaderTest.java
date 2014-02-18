package com.jactive.mock;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.reset;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import javax.annotation.Resource;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.annotation.Repeat;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;

import com.jactive.fakebusiness.DummyDao;
import com.jactive.fakebusiness.DummyService;

/**
 * <b>Failed</b> to implement it. See {@link SpringMockitoTestExecutionListener} for the reason.
 * Fall back to the default solution, i.e. seperate the prod Spring application context
 * configuration file and devo file.
 *
 * <p>{@link SpringMockitoTestExecutionListener} should be the first one in the listener list of
 * {@link TestExecutionListeners}.
 * 
 * See {@link org.springframework.test.context.TestContextManager#DEFAULT_TEST_EXECUTION_LISTENER_CLASS_NAMES}
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "/unit-test.xml" }, loader = SpringMockitoContextLoader.class)
@TestExecutionListeners(listeners = { SpringMockitoTestExecutionListener.class, DependencyInjectionTestExecutionListener.class })
public class SpringMockitoContextLoaderTest {

    @Mock
    private DummyDao dummyDao;

    @InjectMocks
    @Resource(name = "dummyService")
    private DummyService dummyService;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);

    }

    @After
    public void tearDown() {
        reset(dummyDao);
    }

    @Test
    @Repeat(3)
    public void testDummyDao() {
        String firstMessage = "Message 1st";
        when(dummyDao.getMessageById(1L)).thenReturn(firstMessage);

        assertEquals(firstMessage, dummyService.getMessage(1));

        verify(dummyDao).getMessageById(1L);

        System.out.println(System.identityHashCode(dummyDao) + "@"
                + dummyDao.getClass());
    }
}
