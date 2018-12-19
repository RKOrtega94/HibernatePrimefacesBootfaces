/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.util.List;
import model.Opcion;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import util.HibernateUtil;

/**
 *
 * @author RKOrtega
 */
public class OpcionDAO {

    private static final SessionFactory sessionFactory = HibernateUtil.getSessionFactory();

    @SuppressWarnings({"rawtypes", "unchecked"})
    public List<Opcion> findAll() {
        List<Opcion> opciones = null;
        Session session = sessionFactory.openSession();
        try {
            session.beginTransaction();
            Query query = session.createQuery("select o from Opcion o");
            opciones = query.getResultList();
            session.getTransaction().commit();
        } catch (Exception e) {
            opciones = null;
            session.getTransaction().rollback();
        } finally {
            session.close();
        }
        return opciones;
    }
    
    @SuppressWarnings({"rawtypes", "unchecked"})
    public boolean save(Opcion opcion){
        boolean result = true;
        Session session = sessionFactory.openSession();
        try {
            session.beginTransaction();
            session.save(opcion);
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
    public boolean update(Opcion opcion){
        boolean result = true;
        Session session = sessionFactory.openSession();
        try {
            session.beginTransaction();
            session.update(opcion);
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
    public boolean delete(Opcion opcion){
        boolean result = true;
        Session session = sessionFactory.openSession();
        try {
            session.beginTransaction();
            session.delete(opcion);
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
    public Opcion findById(int id){
        Opcion opcion = null;
        Session session = sessionFactory.openSession();
        try {
            session.beginTransaction();
            Query query = session.createQuery("select o from Opcion o where opcionId = :id");
            query.setParameter("id", id);
            opcion = (Opcion) query.uniqueResult();
            session.getTransaction().commit();
        } catch (Exception e) {
            opcion = null;
            session.getTransaction().rollback();
        } finally {
            session.close();
        }
        return opcion;
    }
}
