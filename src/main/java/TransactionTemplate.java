import service.TransactionCallback;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class TransactionTemplate {


    public static <T> T runInTransaction(TransactionCallback transactionCallback) {

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("customspring");
        EntityManager em = emf.createEntityManager();
        T emp = null;
        try {
            ThreadLocalEntityManager.setThreatLocalValue(em);
            em.getTransaction().begin();
            emp = (T) transactionCallback.execute(em);
            em.getTransaction().commit();

        } catch (Throwable throwable) {
            throwable.printStackTrace();
        } finally {
            em.close();
            ThreadLocalEntityManager.removeThreadLocalValue();
        }
        return emp;
    }
}