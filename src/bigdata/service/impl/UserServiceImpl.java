package bigdata.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import bigdata.dao.BaseDao;
import bigdata.model.User;
import bigdata.service.UserService;
@Service("userService")
@Transactional(isolation=Isolation.DEFAULT,propagation=Propagation.REQUIRED)
public class UserServiceImpl extends BaseServiceImpl<User> implements UserService {
	
	@Resource(name="userDao")
	public void setDao(BaseDao dao) {
		super.setDao(dao);
	}

	public User validateLoginInfo(String username, String password) {
		String hql="from User u where u.name=? and u.password=?";
		List<User> list=this.findByHQL(hql, username,password);
		if(list!=null&&!list.isEmpty()){
			return list.get(0);
		}else{
			return null;
		}
	}

}
