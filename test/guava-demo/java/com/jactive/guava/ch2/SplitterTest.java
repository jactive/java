package com.jactive.guava.ch2;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

import java.util.List;

import org.junit.Test;

import com.google.common.base.Splitter;

public class SplitterTest {
    @Test public void firstTouch() {
        List<String> l = Splitter.on(',').splitToList("1,2,3");
        assertThat(new String[] { "1", "2", "3" }, is(l.toArray()));
    }

    @Test public void testBlankString() {
        List<String> l = Splitter.on(',').splitToList("1,2,,3");
        assertThat(new String[] { "1", "2", "", "3" }, is(l.toArray()) );


        l = Splitter.on(',').splitToList("1,2,");
        assertThat(new String[] { "1", "2", "" }, is(l.toArray()) );
    }

    @Test public void dealWithBlankString() {
        List<String> l = Splitter.on(',').omitEmptyStrings().splitToList("1,2,,,,3,,");
        assertThat(new String[] { "1", "2", "3" }, is(l.toArray()) );
    }

    @Test public void testPattern() {
        List<String> l = Splitter.on("\\d+").splitToList("a1231b2131c890d");
        assertThat(new String[] { "a", "b", "c", "d"}, is(l.toArray()) );
    }
}
