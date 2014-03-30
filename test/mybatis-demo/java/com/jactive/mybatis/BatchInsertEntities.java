package com.jactive.mybatis;

import java.util.List;


public class BatchInsertEntities<T> {
    private final List<T> entities;

    public BatchInsertEntities(List<T> entities) {
        this.entities = entities;
    }

    /**
     * <p>The entities will be batch inserted into DB. The entities are also the
     * parameters of the {@link org.apache.ibatis.binding.MapperMethod.SqlCommand}.
     */
    public List<T> getEntities() {
        return entities;
    }
}
