package com.hwua.test;

import java.util.List;

import org.junit.Test;

import com.hwua.entity.News;
import com.hwua.service.NewServiceImpl;
import com.hwua.service.I.NewService;

public class NewsTest {
	@Test
    public void testQueryNews() throws Exception{
		NewService newsService = new NewServiceImpl();
		List<News> newsList = newsService.queryNews();
		for(News news : newsList) {
			System.out.println(news);
		}
		
    }
}
