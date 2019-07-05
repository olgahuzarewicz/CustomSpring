import service.TransactionCallback;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class TransactionTemplate {

    public static <T> T runInTransaction(TransactionCallback transactionCallback) {

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("customspring");
        EntityManager em = emf.createEntityManager();

        em.getTransaction().begin();
        T employee = (T) transactionCallback.execute(em);
        System.out.println("Committing transaction");
        em.getTransaction().commit();


        em.close();
        return employee;
    }
}