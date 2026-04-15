package cn.netkiller.test;

import org.springframework.stereotype.Service;

import cn.netkiller.core.annotation.Log;
import cn.netkiller.core.annotation.ParamLog;
import cn.netkiller.core.annotation.ResultLog;
import cn.netkiller.core.annotation.ThrowingLog;

@Service
public class TestService {

	public TestService() {
		// TODO Auto-generated constructor stub
	}

	@ParamLog("test1")
	public void test1() {
	}

	@ResultLog("test2")
	public void test2(String name) {
	}

	@ThrowingLog("test3")
	public void test3(String name, int id) {
	}

	@Log("test4")
	public void test4(String name, int... id) {
	}
}
