package com.example.hellojpa;

import javax.persistence.*;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorColumn
public class Item {

    @Id @GeneratedValue
    @Column(name = "ITEM_ID")
    private Long id;

    private String name;
    private int price;

    // 아무것도 없이 extends만 하면 single table 전략
    // @Inheritance(joined) => 조인 전략
    // @Inheritance(single_table) => single table 전략
    // @Inheritance(table_per_class) => item 이라는 상위 테이블이 없음.
    // @DiscriniminatorColumn => 있는게 좋음. Item에서 DTYPE이라는 칼럼이 나타남. -> Movie insert시 DTYPE은 Movie
    // @DiscriniminatorValue => Movie Entity에서 설정할 때, DTYPE이 Movie로 나타나던걸 커스터마이징 가능. "a" 라고 하면 DTYPE은 a
    // 싱글테이블전략에서는 Discriminator가 필수(그래서 설정을 안해도 들어감)
}
