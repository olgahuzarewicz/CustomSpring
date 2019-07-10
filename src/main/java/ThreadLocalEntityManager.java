import javax.persistence.EntityManager;

public class ThreadLocalEntityManager {
    private static final ThreadLocal<EntityManager> THREAD_LOCAL_VALUE = new ThreadLocal<>();

    public static EntityManager getThreadLocalValue() {
        return THREAD_LOCAL_VALUE.get();
    }

    public static void setThreatLocalValue(EntityManager em) {
        THREAD_LOCAL_VALUE.set(em);
    }

    public static void removeThreadLocalValue() {
        THREAD_LOCAL_VALUE.remove();
    }
}
