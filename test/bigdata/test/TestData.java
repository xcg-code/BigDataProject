package bigdata.test;

import java.sql.Connection;

import javax.sql.DataSource;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import bigdata.model.User;
import bigdata.service.UserService;


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
		ApplicationContext ac=new ClassPathXmlApplicationContext("beans.xml");
		UserService us=(UserService) ac.getBean("userService");
		User u=new User();
		u.setName("lili");
		u.setAge(13);
		us.saveEntity(u);
	}
}
