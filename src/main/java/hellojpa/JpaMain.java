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
         *  
         *  영속성 컨텍스트를 플러시하는 방법
         *  - em.flush() - 직접 호출
         *  - 트랜잭션 커밋 - 플러시 자동 호출
         *  - JPQL 쿼리 실행 - 플러시 자동 호출
         */
        try{
            // 영속 엔티티 조회
            Member member = new Member(200L, "member200");
            em.persist(member);

            em.flush();

            System.out.println("=======================");
            tx.commit();
        }catch (Exception e){
            tx.rollback();
        }finally {
            em.close();
        }
        emf.close();
    }
}
