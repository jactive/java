package com.jactive.mock;

import javax.annotation.Resource;

public class DummyServiceImpl implements DummyService {

	@Resource(name = "dummyDao")
	private DummyDao dummyDao;

	public String getMessage(long id) {
		return dummyDao.getMessageById(id);
	}

	
}
