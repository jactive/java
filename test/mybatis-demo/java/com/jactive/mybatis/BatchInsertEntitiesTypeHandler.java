package com.jactive.mybatis;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;

public class BatchInsertEntitiesTypeHandler<T> extends BaseTypeHandler<BatchInsertEntityPrimaryKeys> {

    public void setNonNullParameter(PreparedStatement ps, int i, BatchInsertEntities<T> parameter, JdbcType jdbcType)
            throws SQLException {
        // TODO Auto-generated method stub
        System.out.println(" BatchInsertEntitiesTypeHandler#setNonNullParameter got called. ");

    }

    public BatchInsertEntityPrimaryKeys getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
        // Read the primary key values from result set. It is believed that
        // there is 1 primary key column.
        List<String> pks = new LinkedList<>();
        do {
            // rs.next is called before.
            pks.add(rs.getString(columnIndex) );
        } while (rs.next());

        return new BatchInsertEntityPrimaryKeys(pks);
    }

    @Override
    public void setNonNullParameter(PreparedStatement ps, int i, BatchInsertEntityPrimaryKeys parameter,
            JdbcType jdbcType) throws SQLException {
        // TODO Auto-generated method stub
        System.out.println(" BatchInsertEntitiesTypeHandler#setNonNullParameter got called. ");

    }

    @Override
    public BatchInsertEntityPrimaryKeys getNullableResult(ResultSet rs, String columnName) throws SQLException {
        // TODO Auto-generated method stub
        System.out.println(" BatchInsertEntitiesTypeHandler#getNullableResult got called. ");
        return null;
    }

    @Override
    public BatchInsertEntityPrimaryKeys getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
        // TODO Auto-generated method stub
        System.out.println(" BatchInsertEntitiesTypeHandler#getNullableResult got called. ");
        return null;
    }


}
