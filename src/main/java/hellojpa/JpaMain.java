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
            // 영속 엔티티 조회
            Member member = em.find(Member.class, 150L);
            member.setName("ZZZZZ"); // 객체를 컬렉션처럼 다루기 위해서 jpa를 사용한다는 점을 인지하자

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
