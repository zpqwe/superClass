package com.itmuch.msclass.repository;

import com.itmuch.msclass.domain.entity.LessonUser;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * @program: ms-user
 * @description:
 * @author: Long Ao Tian
 * @create: 2020-09-15 21:19
 **/
@Repository
public interface LessonUserRepository extends CrudRepository<LessonUser,Integer> {

    LessonUser findByLessonId(Integer id);
}

