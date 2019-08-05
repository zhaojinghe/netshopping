package com.school.service.address;

import java.util.List;

import com.school.pojo.UserAddress;

public interface UserAddressService {
	
	
	List<UserAddress>  findAddressInfos(int userid,int id);
	
	int addUserAddress(UserAddress  userAddress);

}
