package com.itmuch.msclass.domain.dto;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @program: ms-user
 * @description:
 * @author: Long Ao Tian
 * @create: 2020-09-15 21:09
 **/

public class UserDTO {

    /*
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    与数据库 id 对应起来，id生成策略
    @Column 表示这是一个数据库字段
    @Table(name = "user")  表示对应哪个数据库
    @Entity 表示这是一个实体
    */
    private Integer id;
    private String username;
    private String password;
    private BigDecimal money;
    private String role;
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

