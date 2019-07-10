import service.TransactionCallback;

import javax.persistence.EntityManager;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

class Handler implements InvocationHandler {
    private final EmployeesDaoInterface employeesDao;

    public Handler(EmployeesDaoInterface employeesDao) {
        this.employeesDao = employeesDao;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args)
            throws IllegalArgumentException {

        TransactionCallback<Object> transaction = new TransactionCallback<>() {

            @Override
            public Object execute(EntityManager em) throws InvocationTargetException, IllegalAccessException {
                return method.invoke(employeesDao, args);
            }
        };

        return TransactionTemplate.runInTransaction(transaction);
    }
}
