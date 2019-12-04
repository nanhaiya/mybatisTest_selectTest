package com.java.dao.impl;

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
import java.util.Map;

public class little_botImpl {
    SqlSession session=null;

    @Before
    public void getSession() throws IOException {
        SqlSessionFactoryBuilder ssfb=new SqlSessionFactoryBuilder();
        InputStream inputStream = Resources.getResourceAsStream("mybatis.xml");
        SqlSessionFactory ssf = ssfb.build(inputStream);
        session=ssf.openSession();
    }

    @Test
    public void updData(){
        Map<String,Object> paramMap=new HashMap<>();
        paramMap.put("id","4");
        paramMap.put("name","haha");

        int update = session.update("com.java.dao.impl.little_botImpl.updData", paramMap);
        System.out.println(update);
    }

    @Test
    public void insertDynamic(){
        Map<String,Object> paramMap=new HashMap<>();
        paramMap.put("name","yaya");
        paramMap.put("token","战神");

        int flag = session.insert("com.java.dao.impl.little_botImpl.insertDynamic", paramMap);
        System.out.println("flag="+flag);
    }

    @After
    public void end(){
        session.commit();
    }
}
