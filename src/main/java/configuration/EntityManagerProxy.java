package configuration;

import javax.persistence.*;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaDelete;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.CriteriaUpdate;
import javax.persistence.metamodel.Metamodel;
import java.util.List;
import java.util.Map;

public class EntityManagerProxy implements EntityManager {
    @Override
    public void persist(Object o) {
        ThreadLocalEntityManager.getThreadLocalValue().persist(o);
    }

    @Override
    public <T> T merge(T t) {
        return ThreadLocalEntityManager.getThreadLocalValue().merge(t);
    }

    @Override
    public void remove(Object o) {
        ThreadLocalEntityManager.getThreadLocalValue().remove(o);
    }

    @Override
    public <T> T find(Class<T> aClass, Object o) {
        return ThreadLocalEntityManager.getThreadLocalValue().find(aClass, o);
    }

    @Override
    public <T> T find(Class<T> aClass, Object o, Map<String, Object> map) {
        return ThreadLocalEntityManager.getThreadLocalValue().find(aClass, o, map);
    }

    @Override
    public <T> T find(Class<T> aClass, Object o, LockModeType lockModeType) {
        return ThreadLocalEntityManager.getThreadLocalValue().find(aClass, o, lockModeType);
    }

    @Override
    public <T> T find(Class<T> aClass, Object o, LockModeType lockModeType, Map<String, Object> map) {
        return ThreadLocalEntityManager.getThreadLocalValue().find(aClass, o, lockModeType, map);
    }

    @Override
    public <T> T getReference(Class<T> aClass, Object o) {
        return ThreadLocalEntityManager.getThreadLocalValue().getReference(aClass, o);
    }

    @Override
    public void flush() {
        ThreadLocalEntityManager.getThreadLocalValue().flush();
    }

    @Override
    public FlushModeType getFlushMode() {
        return ThreadLocalEntityManager.getThreadLocalValue().getFlushMode();
    }

    @Override
    public void setFlushMode(FlushModeType flushModeType) {
        ThreadLocalEntityManager.getThreadLocalValue().setFlushMode(flushModeType);
    }

    @Override
    public void lock(Object o, LockModeType lockModeType) {
        ThreadLocalEntityManager.getThreadLocalValue().lock(o, lockModeType);
    }

    @Override
    public void lock(Object o, LockModeType lockModeType, Map<String, Object> map) {
        ThreadLocalEntityManager.getThreadLocalValue().lock(o, lockModeType, map);
    }

    @Override
    public void refresh(Object o) {
        ThreadLocalEntityManager.getThreadLocalValue().refresh(o);
    }

    @Override
    public void refresh(Object o, Map<String, Object> map) {
        ThreadLocalEntityManager.getThreadLocalValue().refresh(o, map);
    }

    @Override
    public void refresh(Object o, LockModeType lockModeType) {
        ThreadLocalEntityManager.getThreadLocalValue().refresh(o, lockModeType);
    }

    @Override
    public void refresh(Object o, LockModeType lockModeType, Map<String, Object> map) {
        ThreadLocalEntityManager.getThreadLocalValue().refresh(o, lockModeType, map);
    }

    @Override
    public void clear() {
        ThreadLocalEntityManager.getThreadLocalValue().clear();
    }

    @Override
    public void detach(Object o) {
        ThreadLocalEntityManager.getThreadLocalValue().detach(o);
    }

    @Override
    public boolean contains(Object o) {
        return ThreadLocalEntityManager.getThreadLocalValue().contains(o);
    }

    @Override
    public LockModeType getLockMode(Object o) {
        return ThreadLocalEntityManager.getThreadLocalValue().getLockMode(o);
    }

    @Override
    public void setProperty(String s, Object o) {
        ThreadLocalEntityManager.getThreadLocalValue().setProperty(s, o);
    }

    @Override
    public Map<String, Object> getProperties() {
        return ThreadLocalEntityManager.getThreadLocalValue().getProperties();
    }

    @Override
    public Query createQuery(String s) {
        return ThreadLocalEntityManager.getThreadLocalValue().createQuery(s);
    }

    @Override
    public <T> TypedQuery<T> createQuery(CriteriaQuery<T> criteriaQuery) {
        return ThreadLocalEntityManager.getThreadLocalValue().createQuery(criteriaQuery);
    }

    @Override
    public Query createQuery(CriteriaUpdate criteriaUpdate) {
        return ThreadLocalEntityManager.getThreadLocalValue().createQuery(criteriaUpdate);
    }

    @Override
    public Query createQuery(CriteriaDelete criteriaDelete) {
        return ThreadLocalEntityManager.getThreadLocalValue().createQuery(criteriaDelete);
    }

    @Override
    public <T> TypedQuery<T> createQuery(String s, Class<T> aClass) {
        return ThreadLocalEntityManager.getThreadLocalValue().createQuery(s, aClass);
    }

    @Override
    public Query createNamedQuery(String s) {
        return ThreadLocalEntityManager.getThreadLocalValue().createNamedQuery(s);
    }

    @Override
    public <T> TypedQuery<T> createNamedQuery(String s, Class<T> aClass) {
        return ThreadLocalEntityManager.getThreadLocalValue().createNamedQuery(s, aClass);
    }

    @Override
    public Query createNativeQuery(String s) {
        return ThreadLocalEntityManager.getThreadLocalValue().createNativeQuery(s);
    }

    @Override
    public Query createNativeQuery(String s, Class aClass) {
        return ThreadLocalEntityManager.getThreadLocalValue().createNativeQuery(s, aClass);
    }

    @Override
    public Query createNativeQuery(String s, String s1) {
        return ThreadLocalEntityManager.getThreadLocalValue().createNativeQuery(s, s1);
    }

    @Override
    public StoredProcedureQuery createNamedStoredProcedureQuery(String s) {
        return ThreadLocalEntityManager.getThreadLocalValue().createNamedStoredProcedureQuery(s);
    }

    @Override
    public StoredProcedureQuery createStoredProcedureQuery(String s) {
        return ThreadLocalEntityManager.getThreadLocalValue().createStoredProcedureQuery(s);
    }

    @Override
    public StoredProcedureQuery createStoredProcedureQuery(String s, Class... classes) {
        return null;
    }

    @Override
    public StoredProcedureQuery createStoredProcedureQuery(String s, String... strings) {
        return null;
    }

    @Override
    public void joinTransaction() {
        ThreadLocalEntityManager.getThreadLocalValue().joinTransaction();
    }

    @Override
    public boolean isJoinedToTransaction() {
        return ThreadLocalEntityManager.getThreadLocalValue().isJoinedToTransaction();
    }

    @Override
    public <T> T unwrap(Class<T> aClass) {
        return ThreadLocalEntityManager.getThreadLocalValue().unwrap(aClass);
    }

    @Override
    public Object getDelegate() {
        return ThreadLocalEntityManager.getThreadLocalValue().getDelegate();
    }

    @Override
    public void close() {
        ThreadLocalEntityManager.getThreadLocalValue().close();
    }

    @Override
    public boolean isOpen() {
        return ThreadLocalEntityManager.getThreadLocalValue().isOpen();
    }

    @Override
    public EntityTransaction getTransaction() {
        return ThreadLocalEntityManager.getThreadLocalValue().getTransaction();
    }

    @Override
    public EntityManagerFactory getEntityManagerFactory() {
        return ThreadLocalEntityManager.getThreadLocalValue().getEntityManagerFactory();
    }

    @Override
    public CriteriaBuilder getCriteriaBuilder() {
        return ThreadLocalEntityManager.getThreadLocalValue().getCriteriaBuilder();
    }

    @Override
    public Metamodel getMetamodel() {
        return ThreadLocalEntityManager.getThreadLocalValue().getMetamodel();
    }

    @Override
    public <T> EntityGraph<T> createEntityGraph(Class<T> aClass) {
        return ThreadLocalEntityManager.getThreadLocalValue().createEntityGraph(aClass);
    }

    @Override
    public EntityGraph<?> createEntityGraph(String s) {
        return ThreadLocalEntityManager.getThreadLocalValue().createEntityGraph(s);
    }

    @Override
    public EntityGraph<?> getEntityGraph(String s) {
        return ThreadLocalEntityManager.getThreadLocalValue().getEntityGraph(s);
    }

    @Override
    public <T> List<EntityGraph<? super T>> getEntityGraphs(Class<T> aClass) {
        return ThreadLocalEntityManager.getThreadLocalValue().getEntityGraphs(aClass);
    }
}
