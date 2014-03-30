package com.jactive.mybatis;

import static junit.framework.Assert.assertSame;

import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.Reader;
import java.sql.Connection;
import java.util.Arrays;

import javax.annotation.Resource;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.jdbc.ScriptRunner;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.GenericXmlContextLoader;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "/mybatis/applicationContext.xml" }, loader = GenericXmlContextLoader.class)
public class DaoTest {

    @Resource(name = "dao")
    private Dao dao;

    @Resource(name = "sqlSessionFactory")
    private SqlSessionFactory sqlSessionFactory;

    @Before
    public void setUp() throws Exception {
        // populate in-memory database
        SqlSession session = sqlSessionFactory.openSession();
        Connection conn = session.getConnection();
        Reader reader = Resources.getResourceAsReader("mybatis/createdb.sql");
        ScriptRunner runner = new ScriptRunner(conn);
        runner.setLogWriter(new PrintWriter(new OutputStreamWriter(System.out)));
        runner.runScript(reader);
        reader.close();
        session.close();
    }

    @Test
    public void testBatchInsertVO() {
        MyEntity e1 = new MyEntity("e1");
        MyEntity e2 = new MyEntity("e2");
        MyEntity e3 = new MyEntity("e3");
        MyEntity e4 = new MyEntity("e4");
        MyEntity e5 = new MyEntity("e5");
        dao.batchInsert(new BatchInsertEntities<MyEntity>(Arrays.asList(e1, e2, e3, e4, e5)));

        assertSame(0, e1.getEntityId());
        assertSame(1, e2.getEntityId());
        assertSame(2, e3.getEntityId());
        assertSame(3, e4.getEntityId());
        assertSame(4, e5.getEntityId());

        assertSame("e1", e1.getProperty1());
        assertSame("e2", e2.getProperty1());
        assertSame("e3", e3.getProperty1());
        assertSame("e4", e4.getProperty1());
        assertSame("e5", e5.getProperty1());

        // mybatis mysql batch insert
        // http://www.iteye.com/problems/66512
        // http://stackoverflow.com/questions/21038350/ibatis-can-not-rereurn-primary-key-for-batch-insert
    }
}
