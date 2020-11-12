package com.itmuch.msuser.repository;

import com.itmuch.msuser.domain.entity.User;
import com.itmuch.msuser.domain.entity.UserAccountEventLog;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * @program: ms-user
 * @description:
 * @author: Long Ao Tian
 * @create: 2020-09-15 21:19
 **/
@Repository
public interface UserAccountEventLogRepository extends CrudRepository<UserAccountEventLog,Integer> {

}

