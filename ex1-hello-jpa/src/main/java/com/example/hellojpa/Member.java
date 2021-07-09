package com.example.hellojpa;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

//create table member (
//        id bigint not null,
//        name varchar(255),
//        primary key(id)
//        );

@Entity
@Getter
@Setter
@ToString
//@Table(name = "user") // 만약 db table 이름이 user라면 이렇게 하면 됨.
public class Member {
    
    @Id @GeneratedValue
    private Long    id;

//    @Column(name = "username") // 만약 db column 이름이 username이라면 이렇게 하면 됨.
    private String  name;
}
