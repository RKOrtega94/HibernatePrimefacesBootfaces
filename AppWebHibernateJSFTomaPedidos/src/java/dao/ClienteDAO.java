/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.util.List;
import model.Cliente;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import util.HibernateUtil;

/**
 *
 * @author RKOrtega
 */
public class ClienteDAO {

    public static final SessionFactory sessionFactory = HibernateUtil.getSessionFactory();

    @SuppressWarnings({"rawtypes", "unchecked"})
    public List<Cliente> findAll() {
        List<Cliente> clientes = null;
        Session session = sessionFactory.openSession();
        try {
            session.beginTransaction();
            Query query = session.createQuery("select c from Cliente c");
            clientes = query.getResultList();
            session.getTransaction().commit();
        } catch (Exception e) {
            clientes = null;
            session.getTransaction().rollback();
        } finally {
            session.close();
        }
        return clientes;
    }

    @SuppressWarnings({"rawtypes", "unchecked"})
    public boolean save(Cliente cliente) {
        boolean result = true;
        Session session = sessionFactory.openSession();
        try {
            session.beginTransaction();
            session.save(cliente);
            session.getTransaction().commit();
        } catch (Exception e) {
            result = false;
            session.getTransaction().rollback();
        } finally {
            session.close();
        }
        return result;
    }

    @SuppressWarnings({"rawtypes", "unchecked"})
    public boolean update(Cliente cliente) {
        boolean result = true;
        Session session = sessionFactory.openSession();
        try {
            session.beginTransaction();
            session.update(cliente);
            session.getTransaction().commit();
        } catch (Exception e) {
            result = false;
            session.getTransaction().rollback();
        } finally {
            session.close();
        }
        return result;
    }

    @SuppressWarnings({"rawtypes", "unchecked"})
    public boolean delete(Cliente cliente) {
        boolean result = true;
        Session session = sessionFactory.openSession();
        try {
            session.beginTransaction();
            session.delete(cliente);
            session.getTransaction().commit();
        } catch (Exception e) {
            result = false;
            session.getTransaction().rollback();
        } finally {
            session.close();
        }
        return result;
    }
}
