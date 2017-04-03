package bigdata.web;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

import bigdata.config.RootConfig;
/**
 * 配置分发器Servlet初始化类
 * @author 14501_000
 *
 */
public class MyConfigDispatcherInitialized extends
		AbstractAnnotationConfigDispatcherServletInitializer {

	protected Class<?>[] getRootConfigClasses() {
		return new Class[]{RootConfig.class};
	}

	@Override
	protected Class<?>[] getServletConfigClasses() {
		return new Class[]{WebConfig.class};
	}

	@Override
	protected String[] getServletMappings() {
		return new String[]{"/"};
	}

}
