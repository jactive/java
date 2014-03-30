package com.jactive.mybatis;

import java.util.Iterator;
import java.util.List;

import org.apache.ibatis.reflection.MetaObject;
import org.apache.ibatis.reflection.factory.ObjectFactory;
import org.apache.ibatis.reflection.property.PropertyTokenizer;
import org.apache.ibatis.reflection.wrapper.ObjectWrapper;

import com.google.common.base.Preconditions;

/**
 * Wrap the collection object for batch insert.
 */
public class BatchInsertObjectWrapper implements ObjectWrapper {
    private final MetaObject metaObject;
    private final BatchInsertEntities<?> entities;

    public BatchInsertObjectWrapper(MetaObject metaObject, Object object) {
        // TODO what is metaobject ?
        this.metaObject = metaObject;
        entities = (BatchInsertEntities<?>) object;
    }


    @Override
    public void set(PropertyTokenizer prop, Object value) {
        // check the primary key type existed or not when setting PK by reflection.
        BatchInsertEntityPrimaryKeys pks = (BatchInsertEntityPrimaryKeys) value;
        Preconditions.checkState(pks.getPrimaryKeys().size() == entities.getEntities().size());
        Iterator<String> iterPks = pks.getPrimaryKeys().iterator();
        Iterator<?> iterEntities = entities.getEntities().iterator();

        while (iterPks.hasNext() ) {
            String pk = iterPks.next();
            Object entity = iterEntities.next();


            // TODO
            ((MyEntity) entity).setEntityId(Integer.parseInt(pk));
        }
    }

    @Override
    public Object get(PropertyTokenizer prop) {
        // Only the entities or parameters property of BatchInsertEntities
        // can be accessed by mapper.
        Preconditions.checkState("entities".equals(prop.getName()) ||
                "parameters".equals(prop.getName()));

        return entities.getEntities();
    }
    @Override
    public String findProperty(String name, boolean useCamelCaseMapping) {
        // TODO Auto-generated method stub
        System.out.println(" BatchInsertObjectWrapper#findProperty got called. ");
        return null;
    }
    @Override
    public String[] getGetterNames() {
        // TODO Auto-generated method stub
        System.out.println(" BatchInsertObjectWrapper#getGetterNames got called. ");
        return null;
    }
    @Override
    public String[] getSetterNames() {
        // TODO Auto-generated method stub
        System.out.println(" BatchInsertObjectWrapper#getSetterNames got called. ");
        return null;
    }
    @Override
    public Class<?> getSetterType(String name) {
        // Return the primary key setter type.
        // Here, we return the BatchInsertEntityPrimaryKeys because
        // there are several primary keys  in the result set of
        // INSERT statement.
        return BatchInsertEntityPrimaryKeys.class;
    }
    @Override
    public Class<?> getGetterType(String name) {
        // TODO Auto-generated method stub
        System.out.println(" BatchInsertObjectWrapper#getGetterType got called. ");
        return null;
    }
    @Override
    public boolean hasSetter(String name) {
        // In BatchInsertObjectWrapper, name is the primary key property name.
        // Always return true here without checking if there is such property
        // in BatchInsertEntities#getEntities().get(0) . The verification be
        // postphone until setting the PK value at this.set method.
        return true;
    }
    @Override
    public boolean hasGetter(String name) {
        // TODO Auto-generated method stub
        System.out.println(" BatchInsertObjectWrapper#hasGetter got called. ");
        return false;
    }
    @Override
    public MetaObject instantiatePropertyValue(String name, PropertyTokenizer prop, ObjectFactory objectFactory) {
        // TODO Auto-generated method stub
        System.out.println(" BatchInsertObjectWrapper#instantiatePropertyValue got called. ");
        return null;
    }
    @Override
    public boolean isCollection() {
        // TODO Auto-generated method stub
        System.out.println(" BatchInsertObjectWrapper#isCollection got called. ");
        return false;
    }
    @Override
    public void add(Object element) {
        // TODO Auto-generated method stub
        System.out.println(" BatchInsertObjectWrapper#add got called. ");

    }
    @Override
    public <E> void addAll(List<E> element) {
        // TODO Auto-generated method stub
        System.out.println(" BatchInsertObjectWrapper#addAll got called. ");

    }
}