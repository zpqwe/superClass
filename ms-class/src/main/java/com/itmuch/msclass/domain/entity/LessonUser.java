package com.itmuch.msclass.domain.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @program: ms-class
 * @description:
 * @author: Long Ao Tian
 * @create: 2020-09-16 11:44
 **/
@Table(name = "lesson_user")
@Entity
public class LessonUser {
    @Column
    @Id
    private Integer lessonId;
    @Column
    private Integer userId;

    public Integer getLessonId() {
        return lessonId;
    }

    public void setLessonId(Integer lessonId) {
        this.lessonId = lessonId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }
}

