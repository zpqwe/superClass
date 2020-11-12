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
@Table(name = "user")
@Entity
public class User {

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
    private String username;
    @Column
    private String password;
    @Column
    private BigDecimal money;
    @Column
    private String role;
    @Column
    private Date regTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public BigDecimal getMoney() {
        return money;
    }

    public void setMoney(BigDecimal money) {
        this.money = money;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public Date getRegTime() {
        return regTime;
    }

    public void setRegTime(Date regTime) {
        this.regTime = regTime;
    }
}

