package bigdata.web.controller;

import java.net.InetAddress;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import bigdata.model.User;
import bigdata.service.UserService;

@Controller
public class UserController {
	@Resource
	private UserService us;

	@RequestMapping(value = "/user/viewone", method = RequestMethod.GET)
	public String viewOne(@RequestParam("id") Integer id, Model m) {
		User u = us.getEntity(id);
		m.addAttribute(u);
		return "userInfo";
	}

	@RequestMapping(value = "/user/findall", method = RequestMethod.GET)
	public String findAll(Model m) {
		try {
			List<User> list = us.findAll();
			m.addAttribute("userList", list);
			m.addAttribute("server_addr", InetAddress.getLocalHost().getHostAddress());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "userList";
	}

	@RequestMapping(value = "/user/deleteone", method = RequestMethod.GET)
	public String deleteOne(@RequestParam("id") Integer id, Model m) {
		us.deletedById(id);
		return "redirect:/user/findall"; // 请求转发
	}

	// 组册新用户
	@RequestMapping(value = "/user/register", method = RequestMethod.GET)
	public String register() {
		return "userReg";
	}

	// 组册
	@RequestMapping(value = "/user/doreg", method = RequestMethod.POST)
	public String doReg(User u) {
		us.saveEntity(u);
		return "redirect:/user/findall"; // 请求转发
	}

	// login
	@RequestMapping(value = "/user/login", method = RequestMethod.GET)
	public String login() {
		return "login";
	}

	// dologin
	@RequestMapping(value = "/user/dologin", method = RequestMethod.POST)
	public String doLogin(User u, HttpSession s, Model m) {
		User u0 = us.validateLoginInfo(u.getName(), u.getPassword());
		if (u0 == null) {
			m.addAttribute("error", "name or password is no vaild!");
			return "login";
		} else {
			s.setAttribute("user", u0);// 将用户保存到Session中
			return "welcome";
		}

	}

}
