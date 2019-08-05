package com.school.service.news;

import java.util.List;

import com.school.pojo.News;



public interface NewsService{
	/**
	 * 保存新闻
	 * @param news
	 */
	int saveNews(News news);//保存新闻
	/**
	 * 根据id查询新闻
	 * @param parameter
	 * @return
	 */
	News findNewsById(Integer id);//根据ID获取新闻
	/**
	 * 查询所有的新闻
	 * @param pager
	 * @return
	 */
	List<News> getAllNews();//获取当页新闻
	/***
	 * 删除新闻
	 * @param parameter
	 */
	int deleteNews(Integer id);//删除新闻
	/***
	 * 查询新闻列表
	 * @param param
	 * @return
	 */
	List<News> queryNewsList();


}
