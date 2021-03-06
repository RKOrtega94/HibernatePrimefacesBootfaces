/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.util.List;
import model.Producto;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import util.HibernateUtil;

/**
 *
 * @author RKOrtega
 */
public class ProductoDAO {

    private static final SessionFactory SESSION_FACTORY = HibernateUtil.getSessionFactory();

    @SuppressWarnings({"rawtypes", "unchecked"})
    public List<Producto> findAll() {
        List<Producto> productos = null;
        Session session = SESSION_FACTORY.openSession();
        try {
            session.beginTransaction();
            Query query = session.createQuery("select p from Producto p");
            productos = query.getResultList();
            session.getTransaction().commit();
        } catch (Exception e) {
            productos = null;
            session.getTransaction().rollback();
        } finally {
            session.close();
        }
        return productos;
    }

    @SuppressWarnings({"rawtypes", "unchecked"})
    public Producto findById(int id) {
        Producto producto = null;
        Session session = SESSION_FACTORY.openSession();
        try {
            session.beginTransaction();
            Query query = session.createQuery("select p from Producto p where p.productoId = :id");
            query.setParameter("id", id);
            producto = (Producto) query.uniqueResult();
            session.getTransaction().commit();
        } catch (Exception e) {
            producto = null;
            session.getTransaction().rollback();
        } finally {
            session.close();
        }
        return producto;
    }

    @SuppressWarnings({"rawtypes", "unchecked"})
    public boolean save(Producto producto) {
        boolean result = true;
        Session session = SESSION_FACTORY.openSession();
        try {
            session.beginTransaction();
            session.save(producto);
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
    public boolean update(Producto producto) {
        boolean result = true;
        Session session = SESSION_FACTORY.openSession();
        try {
            session.beginTransaction();
            session.update(producto);
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
    public boolean delete(Producto producto) {
        boolean result = true;
        Session session = SESSION_FACTORY.openSession();
        try {
            session.beginTransaction();
            session.delete(producto);
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
