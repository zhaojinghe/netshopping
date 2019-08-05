package com.school.common.spring.cache;

import java.lang.reflect.Method;

import org.springframework.cache.interceptor.KeyGenerator;

import com.school.common.utils.FastJsonUtil;



public class CustomKeyGenerator implements KeyGenerator {

	@Override
	public Object generate(Object o, Method method, Object... objects) {
		StringBuilder sb = new StringBuilder();
		sb.append("springCache:");
		sb.append(o.getClass().getName());
		sb.append(method.getName());
		for (Object obj : objects) {
			sb.append(FastJsonUtil.toJsonString(obj));
		}
		return sb.toString();
	}

}
