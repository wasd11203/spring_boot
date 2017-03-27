package com.telincn.service;

import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.telincn.entity.Demo;
import com.telincn.mapper.DemoDao;

@Service
public class DemoService {
	
	@Autowired
	private DemoDao userDao;
	
	public int countAll(){
		int count = userDao.countAll();
		return count;
	}
	
	public List<Demo> findByPage(Map<String, Integer> pageInfo){
		
		List<Demo> list = userDao.findByPage(pageInfo);
		System.out.println("service:"+list);
		return list;
	}
	
	public void save(Demo demo){
		String id = UUID.randomUUID().toString();
		demo.setId(id);
		userDao.save(demo);
		System.out.println("+1");
	}
	
}
