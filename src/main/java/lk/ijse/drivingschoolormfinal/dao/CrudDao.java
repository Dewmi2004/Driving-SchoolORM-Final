package lk.ijse.drivingschoolormfinal.dao;

import java.io.Serializable;
import java.util.List;


public interface CrudDao<T, ID extends Serializable> extends SuperDao {
    boolean save(T entity) throws Exception;
    boolean update(T entity) throws Exception;
    boolean delete(ID id) throws Exception;
    T findById(ID id) throws Exception;
    List<T> findAll() throws Exception;

}
