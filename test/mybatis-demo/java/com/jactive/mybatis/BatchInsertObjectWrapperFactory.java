package com.jactive.mybatis;

import org.apache.ibatis.reflection.MetaObject;
import org.apache.ibatis.reflection.wrapper.ObjectWrapper;
import org.apache.ibatis.reflection.wrapper.ObjectWrapperFactory;

public class BatchInsertObjectWrapperFactory implements ObjectWrapperFactory {
    public boolean hasWrapperFor(Object object) {
        return null != object && BatchInsertEntities.class.isAssignableFrom(object.getClass());
    }

    public ObjectWrapper getWrapperFor(MetaObject metaObject, Object object) {
        return new BatchInsertObjectWrapper(metaObject, object);
    }

}
