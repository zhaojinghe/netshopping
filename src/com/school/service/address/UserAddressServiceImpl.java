package com.school.service.address;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.school.dao.UserAddressMapper;
import com.school.pojo.UserAddress;
import com.school.pojo.UserAddressExample;

@Service("userAddressService")
public class UserAddressServiceImpl  implements  UserAddressService {
	
	@Resource
	private UserAddressMapper userAddressMapper;

	@Override
	public List<UserAddress> findAddressInfos(int userid,int id) {
		// TODO Auto-generated method stub
		
		UserAddressExample example=new UserAddressExample();
		if(userid!=0) {
		example.createCriteria().andUseridEqualTo(userid);
		}
		if(id!=0) {
			example.createCriteria().andIdEqualTo(id);
			}
		return userAddressMapper.selectByExample(example);
	}

	@Override
	public int addUserAddress(UserAddress userAddress) {
		// TODO Auto-generated method stub
		return userAddressMapper.insertSelective(userAddress);
	}
	
	

}
