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
//        member.setUsername("first");
////        member.setName("first"); // 여기까지는 비영속
////
//        em.persist(member); // 여기서 영속 but db에 업데이트 되지 않음.

        // == read & update == //
//        Member member = em.find(Member.class, 1L);
//        System.out.println("member = " + member);
//        member.setName("updated name");
////        em.persist(); // 필요 없음

        // == JPQL == //
//        List<Member> resultList = em.createQuery("select m from Member as m", Member.class)
//                .setFirstResult(5) // 5번부터 시작해서
//                .setMaxResults(100) // 100 개를 가져와라.
//                .getResultList();
        // 주의해야 할 점 ? =>
        // 테이블이 아니고 객체를 대상으로 쿼리를 날린다고 생각해라. (왜 중요한건지는 모르겠네..)


        // == custom code ends == //

        Team team = new Team();
        team.setName("teamA");
        em.persist(team);

//        Team team2 = new Team();
//        team.setName("teamB");
//        em.persist(team2);

        Member member = new Member();
        member.setUsername("member1");
        member.setTeam(team);
        em.persist(member);

        Member member2 = new Member();
        member2.setUsername("member2");
        member2.setTeam(team);
        em.persist(member2);

        em.flush();
        em.clear(); // 이게 없으면 members가 null이네 ...
        // 왜? Team을 persist할 때 찍은 스냅샷에는 member가 없기 때문
        // em.flush() -> 실제 쿼리를 날린다 (insert)
        // em.clear() -> 캐시를 지운다.

//        Member findMember = em.find(Member.class, member.getId());
//        Team findTeam = findMember.getTeam();
//        System.out.println("findMember = " + findMember);
//        System.out.println("findTeam = " + findTeam);

//        findMember.setTeam(team2);
//        em.flush();
//        em.clear();

//        Team team1 = em.find(Team.class, team.getId());
//        System.out.println("team1.getMembers().size() = " + team1.getMembers().size());
        // 만약 list 에 add 만 했으면 기대한 결과가 나오지 않음


        List<Member> members = em.createQuery("select m from Member m", Member.class).getResultList();
        // member1 -> teamA && member2 -> teamB 라면,
        // 처음에 members를 가져올때 쿼리 한번 (1)
        // 그리고 teamA, teamB를 "각각" 가져올때 또 한번"씩" (n)
        // 따라서 n + 1 의 쿼리가 나가는 문제 발생 -> n + 1 Problem

        // 해결법 1. fetch join -> 하나씩 각각 가져오는게 아니고, join해서 한방에 가져옴.

        // == Cascade == //
        Child child1 = new Child();
        Child child2 = new Child();

        Parent parent = new Parent();
        parent.addChild(child1);
        parent.addChild(child2);

        em.persist(parent);
        em.persist(child1);
        em.persist(child2);

        // 아니, 귀찮게 persist를 세번이나 해야 해??
        // -> cascade type을 변경히면, em.persist(parent) 만 실행해도 된다 !
        // 그럼 cascade를 언제 쓰느냐 ? -> 소유자가 하나일 때(=단일소유자). 즉, child가 parent에 의해서만 관리될 때 !

        // == 고아 객체 == //
        em.flush();
        em.clear();

        Parent findParent = em.find(Parent.class, parent.getId());
        findParent.getChildren().remove(0);
        // orphanRemoval 을 true로 주면, 여기서 delete 쿼리가 나감.



        tx.commit();
        em.close();
        emf.close();
    }
}
