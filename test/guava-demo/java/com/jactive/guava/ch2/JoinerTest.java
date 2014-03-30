package com.jactive.guava.ch2;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

import java.util.LinkedHashMap;
import java.util.Map;

import org.junit.Test;

import com.google.common.base.Joiner;
public class JoinerTest {

    @Test public void firstTouch() {
        String s = Joiner.on(", ").join(1, 2, 3, 4);
        assertThat("1, 2, 3, 4", is(s) );
    }

    @Test public void testBlankString() {
        String s = Joiner.on(',').join(1, 2, 3, "", 4);
        assertThat("1,2,3,,4", is(s));

        s = Joiner.on(',').join(1, 2, 3, "");
        assertThat("1,2,3,", is(s));

    }

    @Test(expected = NullPointerException.class)
    public void testNull() {
        Joiner.on(',').join(1, null);
    }

    @Test public void dealWithNull() {
        String s = Joiner.on(',').skipNulls().join(new Integer[] {1, 2, 3, null, 4, 5 });
        assertThat("1,2,3,4,5", is(s));

        s = Joiner.on(',').useForNull("nUlL").join(1, null, 2, 3);
        assertThat("1,nUlL,2,3", is(s) );
    }

    @Test public void joinMap() {
        Map<String, String> map = new LinkedHashMap<>();
        map.put("k1", "v1");
        map.put("k2", "v2");

        String s = Joiner.on(',').withKeyValueSeparator(":").join(map);
        assertThat("k1:v1,k2:v2", is(s));
    }

    // Joiner cann not be extended because its constructor is private.
}
