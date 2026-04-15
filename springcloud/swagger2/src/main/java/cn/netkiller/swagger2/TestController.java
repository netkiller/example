package cn.netkiller.swagger2;

import java.io.Serializable;
import java.util.List;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@Api(value = "测试", tags = "test")
@RestController
@RequestMapping("/api/test")
public class TestController {
	@ApiOperation(value = "接口说明", notes = "接口说明")
	@ApiImplicitParams({ @ApiImplicitParam(name = "id", value = "唯一ID", dataType = "Integer"), @ApiImplicitParam(name = "name", value = "名字") })
	@RequestMapping(value = "/name", method = { RequestMethod.GET, RequestMethod.POST })
	public String test(@RequestParam(value = "id", required = true) String id, @RequestParam(value = "name", required = true) String name) {
		return String.format("%s:%s", id, name);
	}

	@ApiOperation("更改用户信息")
	@PostMapping("/user/info")
	public User userInfo(@RequestBody @ApiParam(name = "用户对象", value = "传入json格式", required = true) User user) {

		return user;
	}
}
