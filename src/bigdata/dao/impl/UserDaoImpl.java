package bigdata.dao.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

import bigdata.model.User;
@Repository("userDao")
public class UserDaoImpl extends BaseDaoImpl<User> {

}
