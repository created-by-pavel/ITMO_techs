package service;

import bl.SessionUtil;
import dao.CatDAO;
import entity.Cat;
import org.hibernate.Session;
import org.hibernate.query.Query;
import java.sql.*;
import java.util.List;

public class CatService extends SessionUtil implements CatDAO {

    public void add(Cat cat) throws SQLException {
        openTransactionSession();
        Session session = getSession();
        session.save(cat);
        closeTransactionSesstion();
    }

    public List<Cat> getAll() throws SQLException {
        openTransactionSession();

        String sql = "SELECT * FROM cat";

        Session session = getSession();
        Query query = session.createNativeQuery(sql).addEntity(Cat.class);
        List<Cat> catList = query.list();
        closeTransactionSesstion();
        return catList;
    }

    public Cat getById(Long id) throws SQLException {
        openTransactionSession();

        String sql = "SELECT * FROM cat WHERE id = :id";

        Session session = getSession();
        Query query = session.createNativeQuery(sql).addEntity(Cat.class);
        query.setParameter("id", id);
        Cat cat = (Cat) query.getSingleResult();
        closeTransactionSesstion();
        return cat;
    }

    public void update(Cat cat) throws SQLException {
        openTransactionSession();
        Session session = getSession();
        session.update(cat);
        closeTransactionSesstion();
    }

    public void remove(Cat cat) throws SQLException {
        openTransactionSession();
        Session session = getSession();
        session.remove(cat);
        closeTransactionSesstion();
    }
}

