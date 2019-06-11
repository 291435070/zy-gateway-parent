package org.zy.gateway.admin.client.web;

import java.time.LocalDateTime;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.zy.gateway.admin.client.model.UserInfo;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("demo")
@Api(tags = "基本演示")
public class DemoController {

	private final Logger logger = LoggerFactory.getLogger(getClass());

	@GetMapping("get")
	@ApiOperation(value = "获取时间信息", notes = "通过条件查询时间信息")
	public Object get(@RequestParam(value = "id", defaultValue = "1") Long id) {
		logger.info("id : {}", id);
		return LocalDateTime.now();
	}

	@GetMapping("list")
	@ApiOperation(value = "获取分页信息", notes = "通过条件查询分页信息")
	@ApiImplicitParams({ 
		@ApiImplicitParam(name = "page", required = true, dataType = "Long", paramType = "query"),
		@ApiImplicitParam(name = "rows", required = false, dataType = "Long", paramType = "form")
	})
	public Object list(@RequestParam(value = "id", defaultValue = "1") Long id) {
		logger.info("id : {}", id);
		return String.format("%s:%s", LocalDateTime.now(), id);
	}
	
	@PostMapping("model")
	@ApiOperation(value = "获取用户信息", notes = "通过条件查询用户信息")
	@ApiResponses({
		@ApiResponse(code = 200, message = "请求成功"),
		@ApiResponse(code = 500, message = "请求失败，参数格式错误", response = Exception.class),
	})
	public UserInfo model(UserInfo userInfo) {
		logger.info("user : {}", userInfo);
		return userInfo;
	}

}