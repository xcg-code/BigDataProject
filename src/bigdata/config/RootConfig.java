package bigdata.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;

/**
 * spring容器配置类
 */
@Component
@ComponentScan(basePackages={"bigdata.config","bigdata.dao.impl","bigdata.service.impl"})
public class RootConfig {

}
