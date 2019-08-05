package com.school.service.news;


import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.school.dao.NewsMapper;
import com.school.pojo.News;


/**
 *新闻类的实现类
 */
@Service("newsService")
@Transactional
public class NewsServiceImpl implements NewsService {

	@Resource
	private  NewsMapper newsMapper;
	
	@Override
	public int deleteNews(Integer id) {// 删除新闻
		int num=0;
		try {
		
			
			 num=newsMapper.deleteByPrimaryKey(id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return num;
	}
	@Override
	public News findNewsById(Integer id) {// 根据ID获取新闻
		News news = null;
		
		try {
			
			news = newsMapper.selectByPrimaryKey(id);
		} catch (Exception e) {
			e.printStackTrace();
		} 
		return news;
	}
	@Override
	public List<News> getAllNews() {// 获取当页新闻
		List<News> rtn = new ArrayList<News>();
		
		try {
		
		
			rtn=newsMapper.selectByExample(null);
		} catch (Exception e) {
			e.printStackTrace();
		} 
		return rtn;
	}
	
	@Override
	public int saveNews(News news) {// 保存新闻
		int num=0;
		try {
		
			num=newsMapper.insertSelective(news);
		} catch (Exception e) {
			e.printStackTrace();
		} 
		return num;
	}


	
	@Override
	public List<News> queryNewsList() {
		List<News> newsList=new ArrayList<News>();
		
		try {
			
			newsList=newsMapper.selectByExample(null);
		} catch (Exception e) {
			e.printStackTrace();
		} 
		return newsList;
	}



}
