package com.example.myweb;

import com.example.myweb.mapper.VisitCountMapper;
import com.example.myweb.pojo.VisitCount;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;

public class Test {
    public static void main(String[] args) throws IOException {
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);

        SqlSession sqlSession=sqlSessionFactory.openSession();

        //List<VisitCount> visitCountList = sqlSession.selectList("VisitCountMapper.select");
        //System.out.println(((VisitCount)sqlSession.selectOne("VisitCountMapper.select")).getCount());
        VisitCountMapper visitCountMapper = sqlSession.getMapper(VisitCountMapper.class);
        VisitCount visitCount = visitCountMapper.select();

        System.out.println(visitCount);

        sqlSession.close();
    }
}
