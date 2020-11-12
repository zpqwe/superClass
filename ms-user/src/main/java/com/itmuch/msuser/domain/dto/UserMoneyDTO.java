package com.itmuch.msuser.domain.dto;

import java.math.BigDecimal;

/**
 * @program: ms-user
 * @description:
 * @author: Long Ao Tian
 * @create: 2020-11-04 16:35
 **/
public class UserMoneyDTO {

    private Integer userId;
    private BigDecimal money;
    private String event;
    private String description;

    public UserMoneyDTO() {
    }

    public UserMoneyDTO(Integer userId, BigDecimal money, String event, String description) {
        this.userId = userId;
        this.money = money;
        this.event = event;
        this.description = description;
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
}

