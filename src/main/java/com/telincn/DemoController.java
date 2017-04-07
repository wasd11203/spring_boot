package com.telincn;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class DemoController {

	@RequestMapping("/a")
	public String index(ModelMap model){
		model.put("title", "index");
		model.put("users", new int[]{1,2});
		return "index";
	}
	
}
