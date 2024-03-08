package com.example.myweb;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;
import java.io.Reader;

@SpringBootTest
class MywebApplicationTests {

	@Test
	void contextLoads() throws IOException {
		// 核心配置文件classpath路径
		String resource = "mybatis-config.xml";
		// 加载配置文件
		Reader reader = Resources.getResourceAsReader(resource);
		// 构建会话工厂
		SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);
		// 从SqlSessionFactory对象中获取SqlSession
		SqlSession sqlSession = sqlSessionFactory.openSession();
		System.out.println(sqlSession.getConnection());
		// 归还连接给数据源
		sqlSession.close();
	}

}
