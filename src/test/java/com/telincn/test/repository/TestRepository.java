package com.telincn.test.repository;

import java.util.ArrayList;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.telincn.SpringBootApplicationMain;
import com.telincn.entity.ExEntity;
import com.telincn.repository.DAO;

@RunWith(SpringJUnit4ClassRunner.class) // SpringJUnit支持，由此引入Spring-Test框架支持！ 
@SpringApplicationConfiguration(classes = SpringBootApplicationMain.class) // 指定我们SpringBoot工程的Application启动类
@WebAppConfiguration //如果是 web项目则添加该注解,否则的话,不需要添加
public class TestRepository {
	
	@Autowired
	private DAO dao;
	
	@Test
	public void test(){
		System.out.println(dao.findById(Integer.parseInt("1")));
	}
	
	@Test
	public void test2(){
		
		ArrayList<ExEntity> list = (ArrayList<ExEntity>)dao.findAll();
		System.out.println(dao.findAll());
		System.out.println(list.get(0).getPassword().trim().equals(""));
	}
}
