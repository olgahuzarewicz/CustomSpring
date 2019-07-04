package service;

import org.hibernate.Session;

import java.sql.SQLException;

public interface TransactionCallback<T> {
    T execute(Session session) throws SQLException;
}