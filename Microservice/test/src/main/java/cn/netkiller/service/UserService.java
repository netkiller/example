package cn.netkiller.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Component;

import cn.netkiller.domain.User;

@Component
public class UserService {

	@Autowired
	private MongoTemplate mongoTemplate;

	public UserService() {
		// TODO Auto-generated constructor stub
	}

	public void save(User user) {
		mongoTemplate.save(user);
	}

	//
	// @Override
	// public User findUserByUserName(String userName) {
	// Query query = new Query(Criteria.where("userName").is(userName));
	// User user = mongoTemplate.findOne(query, User.class);
	// return user;
	// }
	//
	// /**
	// * 更新对象
	// *
	// * @param user
	// */
	// @Override
	// public long updateUser(User user) {
	// Query query = new Query(Criteria.where("id").is(user.getId()));
	// Update update = new Update().set("userName", user.getUserName()).set("passWord", user.getPassWord());
	// // 更新查询返回结果集的第一条
	// UpdateResult result = mongoTemplate.updateFirst(query, update, User.class);
	// // 更新查询返回结果集的所有
	// // mongoTemplate.updateMulti(query,update,UserEntity.class);
	// if (result != null)
	// return result.getMatchedCount();
	// else
	// return 0;
	// }
	//
	// /**
	// * 删除对象
	// *
	// * @param id
	// */
	// @Override
	// public void deleteUserById(Long id) {
	// Query query = new Query(Criteria.where("id").is(id));
	// mongoTemplate.remove(query, User.class);
	// }

}
