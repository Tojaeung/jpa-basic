package hellojpa;

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

            /*
               Insert(삽입)
             */
            // Member member = new Member();
            // member.setId(2L);
            // member.setName("helloB");
            // em.persist(member);

            /*
                Read(읽기)
             */
            // Member findMember = em.find(Member.class, 1L);
            // System.out.println("아이디 = " + findMember.getId());
            // System.out.println("이름 = " + findMember.getName());

            /*
                Update(수정)
             */
            Member findMember = em.find(Member.class, 1L);  // Jpa로 불러온 데이터는 Jpa에서 관리된다.
            findMember.setName("HelloJ");   // 그래서, Jpa에서 불러온 데이터 객체에 변화가 생긴다면 자동으로 업데이트를 해준다.
            // em.persist(member);     즉, 이 명령이 없어도 된다.

            transaction.commit();
        } catch (Exception e) {
            transaction.rollback();
        } finally {
            em.close();
        }
        emf.close();
    }
}
