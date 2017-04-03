package bigdata.config;

import java.util.Properties;
import javax.sql.DataSource;
import org.hibernate.SessionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.ClassPathResource;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.stereotype.Component;

import com.mchange.v2.c3p0.ComboPooledDataSource;

@Component
public class MyBeans {
	/**
	 * 属性
	 */
	@Bean("p")
	public Properties getProp(){
		try {
			Properties prop=new Properties();
			ClassPathResource r=new ClassPathResource("jdbc.properties");
			prop.load(r.getInputStream());
			return prop;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * 数据源
	 */
	@Bean
	public DataSource getDataResource(Properties p){
		ComboPooledDataSource ds=null;
		try {
			ds=new ComboPooledDataSource();
			ds.setDriverClass(p.getProperty("jdbc.driverclass"));
			ds.setJdbcUrl(p.getProperty("jdbc.jdbcUrl"));
			ds.setUser(p.getProperty("jdbc.user"));
			ds.setPassword(p.getProperty("jdbc.password"));
			
			ds.setMaxPoolSize(Integer.parseInt(p.getProperty("c3p0.pool.size.max")));
			ds.setMinPoolSize(Integer.parseInt(p.getProperty("c3p0.pool.size.min")));
			ds.setInitialPoolSize(Integer.parseInt(p.getProperty("c3p0.pool.size.ini")));
			ds.setAcquireIncrement(Integer.parseInt(p.getProperty("c3p0.pool.size.increment")));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ds;
	}
	
	@Bean("sessionFactory")
	public LocalSessionFactoryBean getSessionFactory(DataSource ds,Properties p){
		LocalSessionFactoryBean sf=new LocalSessionFactoryBean();
		sf.setDataSource(ds);
		sf.setHibernateProperties(p);
		sf.setMappingDirectoryLocations(new ClassPathResource("bigdata/model"));
		return sf;
	}
	
	@Bean("transactionManager")
	public HibernateTransactionManager getTransactionManager(SessionFactory sessionFactory){
		HibernateTransactionManager htm=new HibernateTransactionManager();
		htm.setSessionFactory(sessionFactory);
		return htm;
	}
	
}
