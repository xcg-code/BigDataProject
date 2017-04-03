package bigdata.web.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.persistence.MappedSuperclass;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import bigdata.model.User;
import bigdata.service.UserService;

@Controller
public class HomeController {
	@Resource
	private UserService us;
	
	/**
	 * 响应GET请求
	 */
	@RequestMapping(value="/home",method=RequestMethod.GET)
	public String home(){
		List<User> list=us.findAll();
		for(User u:list){
			System.out.println(u.getId()+","+u.getName()+","+u.getAge());
		}
		return "home";
	}

}
