import org.hibernate.Session;
import org.hibernate.Transaction;
import service.TransactionCallback;

public class TransactionTemplate {

    public static <T> T runInTransaction(TransactionCallback transactionCallback) {

        Session session = null;
        Transaction transaction = null;
        T employee = null;

        try {
            session = HibernateUtil.getSession();
            transaction = session.beginTransaction();
            employee = (T) transactionCallback.execute(session);

            System.out.println("Committing transaction");
            transaction.commit();

        } catch (Exception ex) {
            ex.printStackTrace();
            // handle exception here
            if (transaction != null) transaction.rollback();
        } finally {
            try {
                if (session != null) session.close();
            } catch (Exception ex) {
            }
        }
        return employee;
    }
}