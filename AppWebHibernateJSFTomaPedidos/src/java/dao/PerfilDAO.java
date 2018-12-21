/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.util.List;
import model.Perfil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import util.HibernateUtil;

/**
 *
 * @author RKOrtega
 */
public class PerfilDAO {

    private static final SessionFactory sessionFactory = HibernateUtil.getSessionFactory();

    @SuppressWarnings({"rawtypes", "unchecked"})
    public List<Perfil> findAll() {
        List<Perfil> perfiles = null;
        Session session = sessionFactory.openSession();
        try {
            session.beginTransaction();
            Query query = session.createQuery("select p from Perfil p");
            perfiles = query.getResultList();
            session.getTransaction().commit();
        } catch (Exception e) {
            perfiles = null;
            session.getTransaction().rollback();
        } finally {
            session.close();
        }
        return perfiles;
    }

    @SuppressWarnings({"rawtypes", "unchecked"})
    public boolean save(Perfil perfil) {
        boolean result = true;
        Session session = sessionFactory.openSession();
        try {
            session.beginTransaction();
            session.save(perfil);
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
    public boolean update(Perfil perfil) {
        boolean result = true;
        Session session = sessionFactory.openSession();
        try {
            session.beginTransaction();
            session.update(perfil);
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
    public boolean delete(Perfil perfil) {
        boolean result = true;
        Session session = sessionFactory.openSession();
        try {
            session.beginTransaction();
            session.delete(perfil);
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
    public Perfil findById(int id){
        Perfil perfil = null;
        Session session = sessionFactory.openSession();
        try {
            session.beginTransaction();
            Query query = session.createQuery("select p from Perfil p where p.perfilId = :id");
            query.setParameter("id", id);
            perfil = (Perfil) query.uniqueResult();
            session.getTransaction().commit();
        } catch (Exception e) {
            perfil = null;
            session.getTransaction().rollback();
        } finally {
            session.close();
        }
        return perfil;
    }
}
