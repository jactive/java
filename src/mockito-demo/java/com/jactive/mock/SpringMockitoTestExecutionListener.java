package com.jactive.mock;

import org.springframework.test.context.TestContext;
import org.springframework.test.context.support.AbstractTestExecutionListener;


public class SpringMockitoTestExecutionListener extends AbstractTestExecutionListener {

	@Override
	public void prepareTestInstance(TestContext testContext) throws Exception {
    // The test instance is holded in testContext. We could iterate all the
    // @Mock objects in it later.
    // When mockito integrated with spring, some of the beans cannot be initialized
    // by Spring while running test cases due to that some API calls fail in devo
    // environment. So I need to mock the the API bean by mockito and use a seperate
    // spring configuration file to run the test case. I want to find a way to avoid
    // maintaining the seperate spring conf file.
    //    1. Wrap Spring application context, when it cannot find the API bean, hold
    //       the excpetion. After mockito completes creating the mock objects,
    //       rigisters the mock objects into Spring.
    //    2. Before Spring refreshes the context, add all the mock objects into the
    //       context. Then refreshing the context.
    //
    // 1 is not neat. It is a kind of hacking spring. Fails to implement 2 because
    //    a. Test case instance is invisiable in SpringMockitoContextLoader because
    //       ... does not pass it in. Although current class can see the test case
    //       instance in testContext, I cannot pass it to SpringMockitoContextLoader.
    //    b. Mockito does not notify outside that which mock object is created.
		super.prepareTestInstance(testContext);
	}
}
