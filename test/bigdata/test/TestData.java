package bigdata.test;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import javax.sql.DataSource;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import bigdata.config.RootConfig;
import bigdata.model.User;
import bigdata.service.UserService;
import bigdata.util.Util;


public class TestData {
	@Test
	public void getConnMysql() throws Exception {
		ApplicationContext ac=new ClassPathXmlApplicationContext("beans.xml");
		DataSource ds=(DataSource) ac.getBean("dataSource");
		Connection conn=ds.getConnection();
		System.out.println(conn);
	}
	@Test
	public void getService() throws Exception {
//		ApplicationContext context=new ClassPathXmlApplicationContext("beans.xml");
		ApplicationContext ac=new AnnotationConfigApplicationContext(RootConfig.class);
		UserService us=(UserService) ac.getBean("userService");
		User u=null;
		for(int i=1;i<40;i++){
			u=new User();
			u.setName("Mark"+i);
			u.setAge(5+i);
			us.saveEntity(u);
		}
	}
	@Test 
	public void findAll(){
		ApplicationContext context=new ClassPathXmlApplicationContext("beans.xml");
		UserService us=(UserService) context.getBean("userService");
		List<User> list=us.findAll();
		for(User u:list){
			System.out.println(u.getId()+","+u.getName()+","+u.getAge());
		}
	}
	
	/**
	 * 基于注解的spring配置方式
	 */
	@Test
	public void testAnnotationSpring(){
		ApplicationContext ctx=new AnnotationConfigApplicationContext(RootConfig.class);
		UserService us=(UserService) ctx.getBean("userService");
		List<User> list=us.findAll();
		for(User u:list){
			System.out.println(u.getId()+","+u.getName()+","+u.getAge());
		}
		
	}
	
	@Test
	public void readLog() throws Exception{
		InputStream is=TestData.class.getClassLoader().getResourceAsStream("test.txt");
		BufferedReader isr=new BufferedReader(new InputStreamReader(is));
		String line;
		while((line=isr.readLine())!=null){
			System.out.println(Util.getTime(line));
		}
	}
	
}
