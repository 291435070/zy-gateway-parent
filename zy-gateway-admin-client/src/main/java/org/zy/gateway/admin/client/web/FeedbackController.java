package org.zy.gateway.admin.client.web;

import java.time.LocalDateTime;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.zy.gateway.admin.client.model.Feedback;

@RestController
@RequestMapping("feedback")
public class FeedbackController {
	
	private final Logger logger = LoggerFactory.getLogger(getClass());

	@GetMapping("verify")
	public Object verify(@Validated Feedback feedback, BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			for (ObjectError e : bindingResult.getAllErrors()) {
				logger.info("ERROR : {}", e.getDefaultMessage());
				return e.getDefaultMessage();
			}
		}
		return LocalDateTime.now();
	}

}