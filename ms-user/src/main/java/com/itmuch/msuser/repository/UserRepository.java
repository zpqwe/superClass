package com.itmuch.msuser.repository;

import com.itmuch.msuser.domain.entity.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * @program: ms-user
 * @description:
 * @author: Long Ao Tian
 * @create: 2020-09-15 21:19
 **/
@Repository
public interface UserRepository extends CrudRepository<User,Integer> {

    Optional<User> findByUsernameAndPassword(String username, String password);
}

