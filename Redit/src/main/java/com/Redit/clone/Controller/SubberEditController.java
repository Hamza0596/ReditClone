package com.Redit.clone.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.Redit.clone.Dto.SuberedditDto;
import com.Redit.clone.Service.SuberEdditService;


@RestController
@RequestMapping("api/suberedit")
public class SubberEditController  {

	@Autowired
	SuberEdditService suberEdditService;
	@PostMapping()
	public void creatSubberedit(@RequestBody SuberedditDto suberedditDto ) {
		suberEdditService.saveSubbeEredit(suberedditDto);
		
	}
}
