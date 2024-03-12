//package com.blog;
//
//import com.example.myweb.mapper.*;
//import com.example.myweb.pojo.ArticleInfo;
//import org.apache.ibatis.io.Resources;
//import org.apache.ibatis.session.SqlSession;
//import org.apache.ibatis.session.SqlSessionFactory;
//import org.apache.ibatis.session.SqlSessionFactoryBuilder;
//import org.junit.jupiter.api.Test;
//import org.springframework.boot.test.context.SpringBootTest;
//
//import java.io.IOException;
//import java.io.InputStream;
//import java.io.Reader;
//
//@SpringBootTest
//class MywebApplicationTests {
//
//	@Test
//	void Connect() throws IOException {
//		// 核心配置文件classpath路径
//		String resource = "mybatis-config.xml";
//		// 加载配置文件
//		Reader reader = Resources.getResourceAsReader(resource);
//		// 构建会话工厂
//		SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);
//		// 从SqlSessionFactory对象中获取SqlSession
//		SqlSession sqlSession = sqlSessionFactory.openSession();
//		System.out.println(sqlSession.getConnection());
//		// 归还连接给数据源
//		sqlSession.close();
//	}
//
//	@Test
//	void ArticleContent() throws IOException{
//		String resource = "mybatis-config.xml";
//		InputStream inputStream = Resources.getResourceAsStream(resource);
//		SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
//		SqlSession sqlSession=sqlSessionFactory.openSession();
//
//		ArticleContentMapper articleContentMapper = sqlSession.getMapper(ArticleContentMapper.class);
//		System.out.println(articleContentMapper.select(1));
//
//		sqlSession.close();
//	}
//
//	@Test
//	void ArticleInfo() throws IOException{
//		String resource = "mybatis-config.xml";
//		InputStream inputStream = Resources.getResourceAsStream(resource);
//		SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
//		SqlSession sqlSession=sqlSessionFactory.openSession();
//
//		ArticleInfoMapper articleInfoMapper = sqlSession.getMapper(ArticleInfoMapper.class);
//		System.out.println(articleInfoMapper.select(1));
//
//		sqlSession.close();
//	}
//
//	@Test
//	void ArticleLanguage() throws IOException{
//		String resource = "mybatis-config.xml";
//		InputStream inputStream = Resources.getResourceAsStream(resource);
//		SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
//		SqlSession sqlSession=sqlSessionFactory.openSession();
//
//		ArticleLanguageMapper articleLanguageMapper = sqlSession.getMapper(ArticleLanguageMapper.class);
//		System.out.println(articleLanguageMapper.select(2));
//
//		sqlSession.close();
//	}
//
//	@Test
//	void MyInfo() throws IOException{
//		String resource = "mybatis-config.xml";
//		InputStream inputStream = Resources.getResourceAsStream(resource);
//		SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
//		SqlSession sqlSession=sqlSessionFactory.openSession();
//
//		MyInfoMapper mapper = sqlSession.getMapper(MyInfoMapper.class);
//		System.out.println(mapper.select());
//
//		sqlSession.close();
//	}
//
//	@Test
//	void User() throws IOException{
//		String resource = "mybatis-config.xml";
//		InputStream inputStream = Resources.getResourceAsStream(resource);
//		SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
//		SqlSession sqlSession=sqlSessionFactory.openSession();
//
//		UserMapper mapper = sqlSession.getMapper(UserMapper.class);
//		System.out.println(mapper.select(0));
//
//		sqlSession.close();
//	}
//
//	//@Test
//	//void UserToken() throws IOException{
//	//	String resource = "mybatis-config.xml";
//	//	InputStream inputStream = Resources.getResourceAsStream(resource);
//	//	SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
//	//	SqlSession sqlSession=sqlSessionFactory.openSession();
//	//
//	//	UserTokenMapper mapper = sqlSession.getMapper(UserTokenMapper.class);
//	//	System.out.println(mapper.select(0));
//	//
//	//	sqlSession.close();
//	//}
//}
