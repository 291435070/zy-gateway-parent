package org.zy.gateway.admin.client.webs;

import java.time.LocalDateTime;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.zy.gateway.admin.client.model.News;
import org.zy.gateway.admin.client.model.UserInfo;
import org.zy.gateway.admin.client.service.NewsService;
import org.zy.gateway.admin.client.service.UserInfoService;

@RestController
@RequestMapping("example")
public class ExampleController {

	private final Logger logger = LoggerFactory.getLogger(getClass());

	@Autowired
	private UserInfoService userInfoService;

	@Autowired
	private NewsService newsService;

	@GetMapping("get")
	public Object get(@RequestParam(value = "id", defaultValue = "1") Long id) {
		logger.info("id : {}", id);
		return LocalDateTime.now();
	}

	@PostMapping("save")
	public Object save(UserInfo userInfo) {
		News n = new News();
		n.setTitle("标题" + userInfo.getUsername());
		n.setContent("内容" + userInfo.getPassword());
		newsService.insert(n);
		return userInfoService.insert(userInfo);
	}

	@PostMapping("delete")
	public Object delete() {
		newsService.delete(null);
		return userInfoService.delete(null);
	}

	@PostMapping("update")
	public Object update() {
		newsService.update(null);
		return userInfoService.update(null);
	}

	@GetMapping("vo/userinfo")
	public Object userinfo() {
		return userInfoService.select(null);
	}

	@GetMapping("vo/news")
	public Object news() {
		return newsService.select(null);
	}

	@GetMapping("list/userinfo")
	public Object listUserinfo() {
		return userInfoService.list(null);
	}

	@GetMapping("list/news")
	public Object listNews() {
		return newsService.list(null);
	}

}