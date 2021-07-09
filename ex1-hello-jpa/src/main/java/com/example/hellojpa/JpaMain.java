package com.example.hellojpa;

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
//        Member member = new Member();
//        member.setId(1L);
//        member.setName("first");
//
//        em.persist(member);

        // == read & update == //
//        Member member = em.find(Member.class, 1L);
//        System.out.println("member = " + member);
//        member.setName("updated name");
////        em.persist(); // 필요 없음

        // == JPQL == //
        List<Member> resultList = em.createQuery("select m from Member as m", Member.class)
                .setFirstResult(5) // 5번부터 시작해서
                .setMaxResults(100) // 100 개를 가져와라.
                .getResultList();
        // 주의해야 할 점 ? =>
        // 테이블이 아니고 객체를 대상으로 쿼리를 날린다고 생각해라. (왜 중요한건지는 모르겠네..)


        // == custom code starts == //

        tx.commit();
        em.close();
        emf.close();
    }
}
