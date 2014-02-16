package com.jactive.spring.runtimebean;

import junit.framework.Assert;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Repeat;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.jactive.fakebusiness.DummyService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"/create-dao-at-runtime.xml"}, loader = RuntimeBeanContextLoader.class)
public class RuntimeBeanContextLoaderTest {

    @Autowired
    private DummyService dummyService;


    @Test
    @Repeat(3)
    public void testDummyService() {
        Assert.assertEquals(RuntimeBeanContextLoader.DEFAULT_MESSAGE, dummyService.getMessage(1L));
    }
}
