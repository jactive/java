package com.jactive.mybatis;


public interface Dao {

    int batchInsert(BatchInsertEntities<MyEntity> batchInsertEntities);
}
