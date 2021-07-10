package com.example.hellojpa;

import jpabook.jpashop.domain.Member;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.transaction.Transactional;
import java.util.List;

public class JpaMain {
    public static void main(String[] args) {

        // == try catch 로 묶는게 좋음 == //

        EntityManagerFactory emf =
                Persistence.createEntityManagerFactory("hello");
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();

        tx.begin();

        // == custom code starts == //

        // == Create == //
        Member member = new Member();
        member.setName("hello");

//
        em.persist(member); // 여기서 영속 but db에 업데이트 되지 않음.


        tx.commit();
        em.close();
        emf.close();
    }
}
