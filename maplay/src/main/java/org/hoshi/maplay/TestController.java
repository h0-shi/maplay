package org.hoshi.maplay;

import org.hoshi.maplay.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {
	
	@Autowired
	private TestService ts;
	
	@GetMapping("/")
	public String index() {
		return "!";
	}
	
	@GetMapping("/test")
	public String test() {
		int no = ts.test(1);
		return no+"";
	}

}
