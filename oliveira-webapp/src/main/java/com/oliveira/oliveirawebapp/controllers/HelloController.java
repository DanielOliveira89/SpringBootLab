package com.oliveira.oliveirawebapp.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HelloController {

	private Logger logger = LoggerFactory.getLogger(getClass());
	
	@RequestMapping("say-hello-old")
	@ResponseBody
	public String sayHello() {
		return "Hello!";
	}
	
	@RequestMapping("say-hello-html")
	@ResponseBody
	public String sayHelloHtml() {
		StringBuffer sb = new StringBuffer();
			sb.append("<html>");
			sb.append("<head>");
			sb.append("<title>");
			sb.append("Oliveira's HTML Page");
			sb.append("</title>");
			sb.append("</head>");
			sb.append("<body>");
			sb.append("Oliveira's HTML Page");
			sb.append("</body>");
			sb.append("</html>");
		
		return sb.toString();
	} 
	
	@RequestMapping("say-hello")
	public String sayHelloJsp(@RequestParam String name, ModelMap model) {
		
		model.put("name", name);
		System.out.println("Request param is " + name);
		logger.info("Request param is {}", name);
		
		return "sayHello";
	}
	
	
	
}
