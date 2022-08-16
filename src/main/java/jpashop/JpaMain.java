package jpashop;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class JpaMain {
    public static void main(String[] args) {
        // 엔티티매니저팩토리는 웹서버가 올라오는 시점에 하나만 생성이 된다.
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");

        // 요청이 올떄마다 팩토리에서 만들어진다.
        // 엔티티매니저는 쓰레드간에 공유하면 안되고 사용후 버려야한다.
        EntityManager em = emf.createEntityManager();

        // jpa의 모든 데이터 변화는 트랜잭션 안에서 이뤄진다.
        EntityTransaction transaction = em.getTransaction();
        transaction.begin();

        try {
            transaction.commit();
        } catch (Exception e) {
            transaction.rollback();
        } finally {
            em.close();
        }
        emf.close();
    }

}
