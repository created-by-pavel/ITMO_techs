package ru.itmo.kotiki.repository;

import org.hibernate.Session;
import org.hibernate.query.Query;
import ru.itmo.kotiki.dao.OwnerDAO;
import ru.itmo.kotiki.db.SessionUtil;
import ru.itmo.kotiki.entity.Cat;
import ru.itmo.kotiki.entity.Owner;

import java.util.List;

public class OwnerRepository implements OwnerDAO {
    private final SessionUtil util;

    public OwnerRepository() {
        util = new SessionUtil();
    }

    public void add(Owner owner) {
        util.openTransactionSession();
        Session session = util.getSession();
        session.save(owner);
        util.closeTransactionSesstion();
    }

    public List<Owner> getAll() {
        util.openTransactionSession();

        String sql = "SELECT * FROM owner";

        Session session = util.getSession();
        Query query = session.createNativeQuery(sql).addEntity(Owner.class);
        List<Owner> ownerList = query.list();
        util.closeTransactionSesstion();
        return ownerList;
    }

    public Owner getById(Long id) {
        util.openTransactionSession();

        String sql = "SELECT * FROM owner WHERE id = :id";

        Session session = util.getSession();
        Query query = session.createNativeQuery(sql).addEntity(Owner.class);
        query.setParameter("id", id);
        Owner owner = (Owner)query.getSingleResult();
        util.closeTransactionSesstion();
        return owner;
    }

    public List<Cat> getCatsByOwnerId(Long ownerId) {
        util.openTransactionSession();

        String sql = "SELECT * FROM cat WHERE cat.owner_id = :owner_id";

        Session session = util.getSession();
        Query query = session.createNativeQuery(sql).addEntity(Cat.class);
        query.setParameter("owner_id", ownerId);
        List<Cat> catList = query.list();
        util.closeTransactionSesstion();
        return catList;
    }

    public void update(Owner owner) {
        util.openTransactionSession();
        Session session = util.getSession();
        session.update(owner);
        util.closeTransactionSesstion();
    }

    public void remove(Owner owner) {
        util.openTransactionSession();
        Session session = util.getSession();
        session.remove(owner);
        util.closeTransactionSesstion();
    }
}
