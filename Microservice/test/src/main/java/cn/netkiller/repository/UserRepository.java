package cn.netkiller.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import cn.netkiller.domain.User;

public interface UserRepository extends MongoRepository<User, String> {

}
