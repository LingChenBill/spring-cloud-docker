package com.lc.cloud.entity;

import lombok.Data;

import javax.persistence.*;
import java.math.BigDecimal;

/**
 * User entity.
 *
 * @author zyz.
 */
@Data
@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column
    private String username;

    @Column
    private String name;

    @Column
    private Integer age;

    @Column
    private BigDecimal balance;
}
