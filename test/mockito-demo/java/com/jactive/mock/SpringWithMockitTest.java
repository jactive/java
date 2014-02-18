package com.jactive.mock;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.only;
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
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.jactive.fakebusiness.DummyDao;
import com.jactive.fakebusiness.DummyService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "/unit-test.xml" })
public class SpringWithMockitTest {

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
    public void testDummyDao() {
        String firstMessage = "Message 1st";
        when(dummyDao.getMessageById(1L)).thenReturn(firstMessage);

        assertEquals(firstMessage, dummyService.getMessage(1));

        verify(dummyDao, only()).getMessageById(1L);

        System.out.println(System.identityHashCode(dummyDao) + "@" + dummyDao.getClass());
    }
}
