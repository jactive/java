package com.jactive.mock;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.context.support.GenericApplicationContext;
import org.springframework.test.context.MergedContextConfiguration;
import org.springframework.test.context.support.GenericXmlContextLoader;

import com.jactive.spring.runtimebean.RuntimeBeanContextLoader.DefaultDummyDao;

/**
 * Populate the mock object into the application context if it is a bean in order to to avoid
 * declare a the mock object in configuration file as below. If such a bean is already
 * registered, the existing bean will be replaced.
 * 
 * <p>
 * <code>
 *      &lt;bean id="dummyDao" class="org.mockito.Mockito" factory-method="mock"&gt;<br>
 *          &nbsp; &lt;constructor-arg value="com.jactive.fakebusiness.DummyDao" /&gt;<br>
 *      &lt;/bean&gt;<br>
 * </code>
 */
public class SpringMockitoContextLoader extends GenericXmlContextLoader {

    private static final Log LOG = LogFactory.getLog(SpringMockitoContextLoader.class);

    /**
     * Register all the mock objects, which are spring beans as well, into bean factory.
     * 
     * <p>It is supposed to override the {@link #customizeBeanFactory(DefaultListableBeanFactory)}.
     * However, there is no {@code mergedConfig} in the method signature. So we register the mock objects
     * before calling the {@link #loadBeanDefinitions(GenericApplicationContext, MergedContextConfiguration)}.
     */
    protected void loadBeanDefinitions(GenericApplicationContext context, MergedContextConfiguration mergedConfig) {
        customizeBeanFactory(context.getDefaultListableBeanFactory(), mergedConfig);

        super.loadBeanDefinitions(context, mergedConfig);
    }


    private void customizeBeanFactory(DefaultListableBeanFactory beanFactory, MergedContextConfiguration mergedConfig) {
        BeanDefinitionBuilder dummyDaoBuilder = BeanDefinitionBuilder.rootBeanDefinition(DefaultDummyDao.class);
        beanFactory.registerBeanDefinition("dummyDao", dummyDaoBuilder.getBeanDefinition());
    }
}
