package dao;

import entity.Cat;
import entity.Owner;

import java.sql.SQLException;
import java.util.List;

public interface OwnerDAO {

    void add(Owner owner) throws SQLException;

    List<Owner> getAll() throws SQLException;

    Owner getById(Long id) throws SQLException;

    List<Cat> getCatsByOwnerId(Long id) throws SQLException;

    void update(Owner owner) throws SQLException;

    void remove(Owner owner) throws SQLException;
}
