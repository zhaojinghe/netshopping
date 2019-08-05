package com.school.service.user;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.dao.support.DataAccessUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.school.common.exception.BizException;
import com.school.common.service.impl.BaseServiceImpl;
import com.school.common.utils.FastJsonUtil;
import com.school.dao.UserMapper;
import com.school.pojo.User;
import com.school.pojo.UserExample;

@Service("userService")
@Transactional(rollbackFor=Exception.class)
public class UserServiceImpl extends BaseServiceImpl  implements UserService {

	
	 @Resource
	 private UserMapper userMapper;
	 
	@Override
	public User login(String loginName, String password) throws BizException {
		// TODO Auto-generated method stub
		
		UserExample example=new UserExample();
		example.createCriteria().andLoginnameEqualTo(loginName).andPasswordEqualTo(password);
		User user=DataAccessUtils.uniqueResult(userMapper.selectByExample(example));
		
		if(user==null) {
			
			throw new BizException(FastJsonUtil.getResponseJson(2000, "用户名和密码错误",null));
			
		}
		return user;
	}

	@Override
	public int addUser(User user) throws BizException {
		// TODO Auto-generated method stub
		
       if(user==null) {
			
			throw new BizException(FastJsonUtil.getResponseJson(2000, "获取用户信息错误！",null));
			
		}
       if(user.getLoginname()==null) {
			
			throw new BizException(FastJsonUtil.getResponseJson(2000, "用户名不能为空！",null));
			
		}
       if(user.getUsername()==null) {
			
			throw new BizException(FastJsonUtil.getResponseJson(2000, "真实姓名不能为空！",null));
			
		}
		return userMapper.insertSelective(user);
	}

	@Override
	public List<User> findUsersInfo() throws BizException {
		// TODO Auto-generated method stub
		return userMapper.selectByExample(null);
	}

	@Override
	public int modifyUser(User user) throws BizException {
		// TODO Auto-generated method stub
		return userMapper.updateByPrimaryKeySelective(user);
	}

	@Override
	public int delUser(int id) throws BizException {
		// TODO Auto-generated method stub
		return userMapper.deleteByPrimaryKey(id);
	}

	@Override
	public User findUserById(int id) throws BizException {
		// TODO Auto-generated method stub
		return userMapper.selectByPrimaryKey(id);
	}
	
	

}
