package bigdata.service;

import bigdata.model.User;

public interface UserService extends BaseService<User>{
	public User validateLoginInfo(String username,String password);
}
