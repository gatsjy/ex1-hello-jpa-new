package hellojpa;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.List;

/**
 * Created by Gatsjy on 2020-10-18
 * Blog : https://blog.naver.com/gkswndks123
 * Github : https://github.com/gatsjy
 */
public class JpaMain {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");

        EntityManager em = emf.createEntityManager();

        EntityTransaction tx = em.getTransaction();
        tx.begin();

        /**
         * 주의 할 점
         *  - 엔티티 매니저 팩토리는 하나만 생성해서 애플리케이션 전체에서 공유
         *  - 엔티티 매니저는 쓰레드간 공유X(사용하고 버려야 한다.)
         *  - JPA의 모든 데이터 변경은 트랜잭션 안에서 실행해야 한다.
         */
        try{
            //Member findMember = em.find(Member.class, 1L);
            // 자바 컬렉션을 다루는 것처럼 설계되어서 .setName() 만 해도 업데이트 쿼리가 나가서 업데이트를 진행할 수 있다.
            //findMember.setName("kekey");
//            List<Member> result = em.createQuery("select m from Member as m", Member.class)
//                    .setFirstResult(5)
//                    .setMaxResults(8)
//                    .getResultList();
//
//            for (Member member : result) {
//                System.out.println("member.getName = " + member.getName());
//            }

            // 비영속
            Member member = new Member();
            member.setId(100L);
            member.setName("HelloJPA");

            // 영속 상태가 되었다고 쿼리가 날라가는게 아니라
            // 커밋하는 시점에 영속성 컨텍스트에서 쿼리가 날라간다.
            System.out.println("==== BEFORE ====");
            em.persist(member);
            // 회원 엔티티를 영속성 컨텍스트에서 분리
            em.detach(member);
            System.out.println("==== AFTER ====");

            tx.commit();
        }catch (Exception e){
            tx.rollback();
        }finally {
            em.close();
        }
        emf.close();
    }
}
