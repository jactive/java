package com.jactive.spring.runtimebean;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.test.context.support.GenericXmlContextLoader;

import com.jactive.fakebusiness.DummyDao;

public class RuntimeBeanContextLoader extends GenericXmlContextLoader {

    private static final Log LOG = LogFactory.getLog(RuntimeBeanContextLoader.class);

    public static final String DEFAULT_MESSAGE = "default message";

    protected void customizeBeanFactory(DefaultListableBeanFactory beanFactory) {
        BeanDefinitionBuilder dummyDaoBuilder = BeanDefinitionBuilder.rootBeanDefinition(DefaultDummyDao.class);
        // Add dependency to other bean and set property value
        // builder.addPropertyReference("propertyName", "someBean");
        // builder.addPropertyValue("propertyName", someValue);

        beanFactory.registerBeanDefinition("dummyDao", dummyDaoBuilder.getBeanDefinition());
    }

    /**
     * A default DummyDao implementation. It could be a meaningful implementation
     * which is decided at runtime by choice.
     */
    public static class DefaultDummyDao implements DummyDao {

        public String getMessageById(long id) {
            LOG.debug("The InnerDummyDao is called with ID " + id);
            return DEFAULT_MESSAGE;
        }
    }
}
