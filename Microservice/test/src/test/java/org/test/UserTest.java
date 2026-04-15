package org.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import cn.netkiller.domain.User;
import cn.netkiller.service.UserService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserTest {

	public UserTest() {
		// TODO Auto-generated constructor stub
	}

	@Autowired
	private UserService userService;

	@Test
	public void testSaveUser() throws Exception {
		User user = new User();
		user.setUseruame("netkiller");
		user.setPassword("123456");
		userService.save(user);
	}

//	@Test
//	public void findUserByUserName() {
//		UserEntity user = userDao.findUserByUserName("小明");
//		System.out.println("user is " + user);
//	}
//
//	@Test
//	public void updateUser() {
//		UserEntity user = new UserEntity();
//		user.setId(2l);
//		user.setUserName("天空");
//		user.setPassWord("fffxxxx");
//		userDao.updateUser(user);
//	}
//
//	@Test
//	public void deleteUserById() {
//		userDao.deleteUserById(1l);
//	}

}