package com.example.hellojpa;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.Date;

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
//@SequenceGenerator(
//        name = "MEMBER_SEQ_GENERATOR",
//        sequenceName = "MEMBER_SEQ",
//        initialValue = 1,
//        allocationSize = 1
//) // 시퀀스를 별도로 적용하고 싶을 경우.
public class Member {

    /*
    @Id @GeneratedValue
    private Long    id;

//    @Column(name = "username") // 만약 db column 이름이 username이라면 이렇게 하면 됨.
    // JPA 실행에는 영향을 주지 않고, DDL 생성에만 영향을 준다.
    // unique 속성, length 속성 등을 줄 수 있음.
    // unique -> PK처럼 unique한 값을 가져야 함.
    // length -> 길이가 10자 제한이어야 함.
    private String  name;
     */

    /*
    @Id
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "MEMBER_SEQ_GENERATOR"
    )
    private Long id;

    @Column(name = "name")
    private String username;

    private Integer age;

    @Enumerated(EnumType.STRING) // enum 지원
    private RoleType roleType;

    @Temporal(TemporalType.TIMESTAMP) // DATE = 날짜, TIME = 시간, TIMESTAMP = 날짜+시간
    private Date createdDate;

    @Temporal(TemporalType.TIMESTAMP) // 사실 근데 Date가 아니고, LocalDate, LocalDateTime을 쓰면 Temporal도 필요없음.
    private Date lastModifiedDate;

    @Lob // 제한이 없는 경우.
    private String description;
     */

    // == //
    @Id @GeneratedValue
    @Column(name = "MEMBER_ID")
    private Long id;

//    @Column
    private String username;

//    @Column(name = "TEAM_ID")
//    private Long teamId;


    @ManyToOne
    @JoinColumn(name = "TEAM_ID")
    private Team team;

}
