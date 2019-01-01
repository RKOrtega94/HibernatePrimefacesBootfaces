/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.util.List;
import model.Usuario;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import util.HibernateUtil;

/**
 *
 * @author RKOrtega
 */
public class UsuarioDAO {

    private static final SessionFactory SESSION_FACTORY = HibernateUtil.getSessionFactory();

    @SuppressWarnings({"rawtypes", "unchecked"})
    public List<Usuario> findAll() {
        List<Usuario> usuarios = null;
        Session session = SESSION_FACTORY.openSession();
        try {
            session.beginTransaction();
            Query query = session.createQuery("select u from Usuario u");
            usuarios = query.getResultList();
            session.getTransaction().commit();
        } catch (Exception e) {
            usuarios = null;
            session.getTransaction().rollback();
        } finally {
            session.close();
        }
        return usuarios;
    }

    @SuppressWarnings({"rawtypes", "unchecked"})
    public boolean save(Usuario usuario) {
        boolean result = true;
        Session session = SESSION_FACTORY.openSession();
        try {
            session.beginTransaction();
            session.save(usuario);
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
    public boolean update(Usuario usuario) {
        boolean result = true;
        Session session = SESSION_FACTORY.openSession();
        try {
            session.beginTransaction();
            session.update(usuario);
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
    public boolean delete(Usuario usuario) {
        boolean result = true;
        Session session = SESSION_FACTORY.openSession();
        try {
            session.beginTransaction();
            session.delete(usuario);
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
