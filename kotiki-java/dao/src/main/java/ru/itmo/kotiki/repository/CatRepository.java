package ru.itmo.kotiki.repository;

import org.hibernate.Session;
import org.hibernate.query.Query;
import ru.itmo.kotiki.dao.CatDAO;
import ru.itmo.kotiki.db.SessionUtil;
import ru.itmo.kotiki.entity.Cat;

import java.util.List;

public class CatRepository implements CatDAO {
    private final SessionUtil util;

    public CatRepository() {
        util = new SessionUtil();
    }

    public void add(Cat cat) {
        util.openTransactionSession();
        Session session = util.getSession();
        session.save(cat);
        util.closeTransactionSesstion();
    }

    public List<Cat> getAll() {
        util.openTransactionSession();

        String sql = "SELECT * FROM cat";

        Session session = util.getSession();
        Query query = session.createNativeQuery(sql).addEntity(Cat.class);
        List<Cat> catList = query.list();
        util.closeTransactionSesstion();
        return catList;
    }

    public Cat getById(Long id) {
        util.openTransactionSession();

        String sql = "SELECT * FROM cat WHERE id = :id";

        Session session = util.getSession();
        Query query = session.createNativeQuery(sql).addEntity(Cat.class);
        query.setParameter("id", id);
        Cat cat = (Cat) query.getSingleResult();
        util.closeTransactionSesstion();
        return cat;
    }

    public void update(Cat cat) {
        util.openTransactionSession();
        Session session = util.getSession();
        session.update(cat);
        util.closeTransactionSesstion();
    }

    public void remove(Cat cat) {
        util.openTransactionSession();
        Session session = util.getSession();
        session.remove(cat);
        util.closeTransactionSesstion();
    }
}
