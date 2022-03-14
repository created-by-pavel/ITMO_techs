package dao;

import entity.Cat;

import java.sql.SQLException;
import java.util.List;

public interface CatDAO {

    void add(Cat cat) throws SQLException;

    List<Cat> getAll() throws SQLException;

    Cat getById(Long id) throws SQLException;

    void update(Cat cat) throws SQLException;

    void remove(Cat cat) throws SQLException;
}
