package com.jactive.mock;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;
import static org.mockito.Mockito.only;
import static org.mockito.Mockito.reset;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.runners.MockitoJUnitRunner;

import com.jactive.fakebusiness.DummyDao;
import com.jactive.mock.RepeatRule.Repeat;

/**
 * See the <a href="http://goo.gl/X1vqa">Java Doc</a>
 * <ol>
 * <li>Full mock, {@link Mock} and {@link org.mockito.ArgumentMatcher} {@link org.mockito.Matchers} </li>
 * <li>Partial mock, {@link Spy}</li>
 * <li>Verify arguments, {@link Captor}</li>
 */
@RunWith(MockitoJUnitRunner.class)
public class MockitoDemo {
    @Rule
    public RepeatRule repeatRule = new RepeatRule();

    @Mock
    private DummyDao dummyDao;

    public void setup() {
        // With @MockitoJUnitRunner modifier, initMocks does NOT
        // needs to be called somewhere in the base class
        // MockitoAnnotations.initMocks(this);
    }

    @Test
    @Repeat(30)
    public void testMock() {
        String message = "hello mockito.mock";

        when(dummyDao.getMessageById(1L)).thenReturn(message);
        assertSame(message, dummyDao.getMessageById(1L));

        verify(dummyDao, only()).getMessageById(1L);

        // reset the mock because RepeatRule.Repeat loop the test method and
        // doesn't call @After method to tear down, which is not like
        // org.springframework.test.annotation.Repeat
        reset(dummyDao);
    }

    // See javadoc of org.mockito.Spy to learn partial

    /**
     * @see {@link org.mockito.Matchers} and <a href="http://goo.gl/upF0h">Matcher Java Doc</a>
     * @see {@link org.mockito.ArgumentCaptor#capture}
     */
    @Captor
    ArgumentCaptor<Long> messageIdCaptor;

    public void testCaptor() {
        String message = "hello mockito.captor";

        when(dummyDao.getMessageById(1L)).thenReturn(message);
        assertSame(message, dummyDao.getMessageById(1L));

        verify(dummyDao, only()).getMessageById(messageIdCaptor.capture());
        assertEquals(1L, messageIdCaptor.getValue().longValue());
    }
}
