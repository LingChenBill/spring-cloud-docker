package com.lc.cloud.entity;

import lombok.Data;

import java.math.BigDecimal;

/**
 * User entity.
 *
 * @author zyz.
 */
@Data
public class User {

    private Long id;

    private String username;

    private String name;

    private Integer age;

    private BigDecimal balance;
}
