package com.java.dao.impl;

import bean.store;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class bookDaoImpl {
    private SqlSession session=null;

    @Before
    public void init() throws IOException {
        SqlSessionFactoryBuilder ssfb=new SqlSessionFactoryBuilder();
        InputStream inputStream = Resources.getResourceAsStream("mybatis.xml");
        SqlSessionFactory ssf = ssfb.build(inputStream);
        session=ssf.openSession();
    }

    @Test
    public void selectBookId(){
        Map<String,Object> paraMap=new HashMap<>();
        paraMap.put("id",1);
        Map<String,Object> resultMap=session.selectOne("com.java.dao.impl.bookDaoImpl.selectBookId",paraMap);
        System.out.println(resultMap);
    }

    @Test
    public void selectBookIdByBean(){
        Map<String,Object> paraMap=new HashMap<>();
        paraMap.put("id",1);
        store store=session.selectOne("com.java.dao.impl.bookDaoImpl.selectBookIdByBean",1);
        System.out.println(store);
    }

    @Test
    public void selectBookIdForBean(){
        store store=new store();
        store.setId(1);
        store st=session.selectOne("com.java.dao.impl.bookDaoImpl.selectBookIdForBean",store);
        System.out.println(st);
    }

    /*
    *查询所有,List<Map<String,Object>>
    */
    @Test
    public void selectAll(){
        List<Map<String,Object>> list = session.selectList("com.java.dao.impl.bookDaoImpl.selectAll");
        list.forEach(temList->{
            System.out.println(temList);
        });
    }

    @Test
    public void selectAll2(){
        List<store> list =session.selectList("com.java.dao.impl.bookDaoImpl.selectAll2");
        list.forEach(temList->{
            System.out.println(temList);
        });
    }

    @Test
    public void selectLike(){
        Map<String,Object> paramMap=new HashMap<>();
        paramMap.put("keyWord","boy");
        List<Map<String,Object>> obj = session.selectList("com.java.dao.impl.bookDaoImpl.selectLike", paramMap);
        obj.forEach(temp-> System.out.println(temp));

    }

    @Test
    public void selectDynamic(){
        Map<String,Object> paramMap=new HashMap<>();
        paramMap.put("name","little_boy");
        paramMap.put("price","21");

        List<Map<String,Object>> obj = session.selectList("com.java.dao.impl.bookDaoImpl.selectDynamic", paramMap);
        obj.forEach(temp-> System.out.println(temp));
    }



    @After
    public void end(){
        session.commit();
    }

}
