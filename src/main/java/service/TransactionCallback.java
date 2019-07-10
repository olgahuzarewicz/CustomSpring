package service;

import javax.persistence.EntityManager;

public interface TransactionCallback<T> {
    T execute(EntityManager em) throws Throwable;
}