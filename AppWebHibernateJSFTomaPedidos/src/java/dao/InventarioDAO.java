/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.util.List;
import model.Inventario;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import util.HibernateUtil;

/**
 *
 * @author RKOrtega
 */
public class InventarioDAO {

    private static final SessionFactory SESSION_FACTORY = HibernateUtil.getSessionFactory();

    @SuppressWarnings({"rawtypes", "unchecked"})
    public List<Inventario> findAll() {
        List<Inventario> inventarios = null;
        Session session = SESSION_FACTORY.openSession();
        try {
            session.beginTransaction();
            Query query = session.createQuery("select i from Inventario i");
            inventarios = query.getResultList();
            session.getTransaction().commit();
        } catch (Exception e) {
            inventarios = null;
            session.getTransaction().rollback();
        } finally {
            session.close();
        }
        return inventarios;
    }

    @SuppressWarnings({"rawtypes", "unchecked"})
    public boolean save(Inventario inventario) {
        boolean result = true;
        Session session = SESSION_FACTORY.openSession();
        try {
            session.beginTransaction();
            session.save(inventario);
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
    public boolean update(Inventario inventario) {
        boolean result = true;
        Session session = SESSION_FACTORY.openSession();
        try {
            session.beginTransaction();
            session.update(inventario);
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
    public boolean delete(Inventario inventario) {
        boolean result = true;
        Session session = SESSION_FACTORY.openSession();
        try {
            session.beginTransaction();
            session.delete(inventario);
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
