package com.jactive.mybatis;

import org.apache.commons.lang3.builder.StandardToStringStyle;
import org.apache.commons.lang3.builder.ToStringBuilder;

public class MyEntity {
    private int entityId;
    private String property1;

    public MyEntity() {
        super();
    }
    public MyEntity(String p1) {
        property1 = p1;
    }

    public int getEntityId() {
        return entityId;
    }

    public void setEntityId(int entityId) {
        this.entityId = entityId;
    }

    public String getProperty1() {
        return property1;
    }

    public void setProperty1(String property1) {
        this.property1 = property1;
    }
    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, new StandardToStringStyle());
    }


}
