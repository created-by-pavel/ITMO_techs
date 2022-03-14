package service;

import bl.SessionUtil;
import dao.OwnerDAO;
import entity.Cat;
import entity.Owner;
import org.hibernate.Session;
import org.hibernate.query.Query;
import java.sql.*;
import java.util.List;

public class OwnerService extends SessionUtil implements OwnerDAO {

    public void add(Owner owner) throws SQLException {
        openTransactionSession();
        Session session = getSession();
        session.save(owner);
        closeTransactionSesstion();
    }

    public List<Owner> getAll() throws SQLException {
        openTransactionSession();

        String sql = "SELECT * FROM owner";

        Session session = getSession();
        Query query = session.createNativeQuery(sql).addEntity(Owner.class);
        List<Owner> ownerList = query.list();
        closeTransactionSesstion();
        return ownerList;
    }

    public Owner getById(Long id) throws SQLException {
        openTransactionSession();

        String sql = "SELECT * FROM owner WHERE id = :id";

        Session session = getSession();
        Query query = session.createNativeQuery(sql).addEntity(Owner.class);
        query.setParameter("id", id);
        Owner owner = (Owner)query.getSingleResult();
        closeTransactionSesstion();
        return owner;
    }

    public List<Cat> getCatsByOwnerId(Long ownerId) throws SQLException {
        openTransactionSession();

        String sql = "SELECT * FROM cat WHERE cat.owner_id = :owner_id";

        Session session = getSession();
        Query query = session.createNativeQuery(sql).addEntity(Cat.class);
        query.setParameter("owner_id", ownerId);
        List<Cat> catList = query.list();
        closeTransactionSesstion();
        return catList;
    }

    public void update(Owner owner) throws SQLException {
        openTransactionSession();
        Session session = getSession();
        session.update(owner);
        closeTransactionSesstion();
    }

    public void remove(Owner owner) throws SQLException {
        openTransactionSession();
        Session session = getSession();
        session.remove(owner);
        closeTransactionSesstion();
    }
}
