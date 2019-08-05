package com.school.service.user;

import java.util.List;

import com.school.common.exception.BizException;
import com.school.pojo.User;

public interface UserService {
	
	User  login(String loginName, String password) throws BizException;
	
	int addUser(User user) throws BizException;
	
	List<User>  findUsersInfo() throws BizException;
	
	User  findUserById(int id) throws BizException;
	
	int modifyUser(User user) throws BizException;
	
	int delUser(int id) throws BizException;
	
	


}
