package com.telincn.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.telincn.entity.Demo;
import com.telincn.service.DemoService;

@RestController
public class DemoController {

	@Autowired
	private DemoService demoService;

	
	@RequestMapping("/insert.do")
	public String save() {
		for(int i = 0 ;i<1000;i++){
			Demo de = new Demo();
			de.setName("A"+i);
			demoService.save(de);
		}
		
		return null;
	}
	
	@RequestMapping("/list.do")
	public String getCount() {
		int count = demoService.countAll();
		System.out.println(count);
		String res = "{\"counts\":"+count+"}";
		
		return res;
	}

	@RequestMapping("/list_onepage.do")
	public List<Demo> findAll(Integer pageNow, Integer pageSize) {

		int startIndex = (pageNow - 1) * pageSize;
		Map<String, Integer> pageInfo = new HashMap<String, Integer>();
		pageInfo.put("startIndex", startIndex);
		pageInfo.put("pageSize", pageSize);
		List<Demo> list = demoService.findByPage(pageInfo);
		System.out.println("controller");

		return list;
	}

}
