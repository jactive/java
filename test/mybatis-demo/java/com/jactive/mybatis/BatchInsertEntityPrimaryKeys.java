package com.jactive.mybatis;

import java.util.List;

public class BatchInsertEntityPrimaryKeys {
    private final List<String> primaryKeys;

    public BatchInsertEntityPrimaryKeys(List<String> pks) {
        this.primaryKeys = pks;
    }

    public List<String> getPrimaryKeys() {
        return primaryKeys;
    }
}
