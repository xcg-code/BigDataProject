package bigdata.web;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.EnableMBeanExport;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

@Component
@EnableWebMvc
@ComponentScan(basePackages={"bigdata.web.controller"})
public class WebConfig extends WebMvcConfigurerAdapter{

	/**
	 * 视图解析
	 */
	@Bean
	public ViewResolver viewResolver(){
		InternalResourceViewResolver resolve=new InternalResourceViewResolver("/jsps/",".jsp");
		resolve.setExposeContextBeansAsAttributes(true);
		return resolve;
	}
	
	//配置默认处理器
	public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer){
		configurer.enable();
		
	}
}
