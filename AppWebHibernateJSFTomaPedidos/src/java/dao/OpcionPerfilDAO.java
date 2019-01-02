/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.util.List;
import model.OpcionXperfil;
import model.Perfil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import util.HibernateUtil;

/**
 *
 * @author RKOrtega
 */
public class OpcionPerfilDAO {

    private static final SessionFactory SESSION_FACTORY = HibernateUtil.getSessionFactory();

    @SuppressWarnings({"rawtypes", "unchecked"})
    public List<OpcionXperfil> findAll() {
        List<OpcionXperfil> permisos = null;
        Session session = SESSION_FACTORY.openSession();
        try {
            session.beginTransaction();
            Query query = session.createQuery("select o from OpcionXperfil o");
            permisos = query.getResultList();
            session.getTransaction().commit();
        } catch (Exception e) {
            permisos = null;
            session.getTransaction().rollback();
        } finally {
            session.close();
        }
        return permisos;
    }

    @SuppressWarnings({"rawtypes", "unchecked"})
    public boolean save(OpcionXperfil opcionXperfil) {
        boolean result = true;
        Session session = SESSION_FACTORY.openSession();
        try {
            session.beginTransaction();
            session.save(opcionXperfil);
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
    public boolean update(OpcionXperfil opcionXperfil) {
        boolean result = true;
        Session session = SESSION_FACTORY.openSession();
        try {
            session.beginTransaction();
            session.update(opcionXperfil);
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
    public boolean delete(OpcionXperfil opcionXperfil) {
        boolean result = true;
        Session session = SESSION_FACTORY.openSession();
        try {
            session.beginTransaction();
            session.delete(opcionXperfil);
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
    public List<OpcionXperfil> findByPerfil(Perfil perfil) {
        List<OpcionXperfil> opcionXperfil = null;
        Session session = SESSION_FACTORY.openSession();
        try {
            session.beginTransaction();
            Query query = session.createQuery("select op from OpcionXperfil op where op.perfil = :perfil");
            query.setParameter("perfil", perfil);
            opcionXperfil = query.getResultList();
            session.getTransaction().commit();
        } catch (Exception e) {
            opcionXperfil = null;
            session.getTransaction().rollback();
        } finally {
            session.close();
        }
        return opcionXperfil;
    }
}
