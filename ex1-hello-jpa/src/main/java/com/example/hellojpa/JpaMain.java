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

        Team team2 = new Team();
        team.setName("teamB");
        em.persist(team2);

        Member member = new Member();
        member.setUsername("member1");
        member.setTeam(team);
        em.persist(member);

        em.flush();
        em.clear(); // 이게 없으면 members가 null이네 ...
        // 왜? Team을 persist할 때 찍은 스냅샷에는 member가 없기 때문

        Member findMember = em.find(Member.class, member.getId());
        Team findTeam = findMember.getTeam();

//        System.out.println("findMember = " + findMember);
//        System.out.println("findTeam = " + findTeam);

//        List<Member> members = findMember.getTeam().getMembers();
//        System.out.println("members = " + members.size());
//        for (Member m : members) {
//            System.out.println("m = " + m);
//        }

        findMember.setTeam(team2);
        em.flush();
        em.clear();
        Team team1 = em.find(Team.class, team.getId());
        System.out.println("team1.getMembers().size() = " + team1.getMembers().size());
        // 만약 list 에 add 만 했으면 기대한 결과가 나오지 않음



        tx.commit();
        em.close();
        emf.close();
    }
}
