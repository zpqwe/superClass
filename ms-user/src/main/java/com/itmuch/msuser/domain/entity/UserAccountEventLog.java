package com.itmuch.msuser.domain.entity;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @program: ms-user
 * @description:
 * @author: Long Ao Tian
 * @create: 2020-09-15 21:09
 **/
@Table(name = "user_account_event_log")
@Entity
public class UserAccountEventLog {

    /*
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    与数据库 id 对应起来，id生成策略
    @Column 表示这是一个数据库字段
    @Table(name = "user")  表示对应哪个数据库
    @Entity 表示这是一个实体
    */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column
    private Integer userId;
    @Column
    private BigDecimal money;
    @Column
    private String event;
    @Column
    private String description;
    @Column
    private Date createTime;

    public UserAccountEventLog() {
    }

    public UserAccountEventLog(Integer userId, BigDecimal money, String event, String description, Date createTime) {
        this.userId = userId;
        this.money = money;
        this.event = event;
        this.description = description;
        this.createTime = createTime;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public BigDecimal getMoney() {
        return money;
    }

    public void setMoney(BigDecimal money) {
        this.money = money;
    }

    public String getEvent() {
        return event;
    }

    public void setEvent(String event) {
        this.event = event;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}

