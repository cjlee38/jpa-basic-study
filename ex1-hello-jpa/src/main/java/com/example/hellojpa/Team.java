package com.example.hellojpa;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
//@ToString
public class Team {

    @Id @GeneratedValue

    @Column(name = "TEAM_ID")
    private Long id;
    private String name;


    @OneToMany(mappedBy = "team")
    private List<Member> members = new ArrayList<>(); // new ArrayList<>()는 관례

}
