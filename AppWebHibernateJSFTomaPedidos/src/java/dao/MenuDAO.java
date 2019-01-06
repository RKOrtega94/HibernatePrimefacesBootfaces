/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.util.List;
import model.Menu;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import util.HibernateUtil;

/**
 *
 * @author RKOrtega
 */
public class MenuDAO {

    private static final SessionFactory SESSION_FACTORY = HibernateUtil.getSessionFactory();

    @SuppressWarnings({"rawtypes", "unchecked"})
    public List<Menu> findAll() {
        List<Menu> menus = null;
        Session session = SESSION_FACTORY.openSession();
        try {
            session.beginTransaction();
            Query query = session.createQuery("select m from Menu m");
            menus = query.getResultList();
            session.getTransaction().commit();
        } catch (Exception e) {
            menus = null;
            session.getTransaction().rollback();
        } finally {
            session.close();
        }
        return menus;
    }

    @SuppressWarnings({"rawtypes", "unchecked"})
    public boolean save(Menu menu) {
        boolean result = true;
        Session session = SESSION_FACTORY.openSession();
        try {
            session.beginTransaction();
            session.save(menu);
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
    public boolean update(Menu menu) {
        boolean result = true;
        Session session = SESSION_FACTORY.openSession();
        try {
            session.beginTransaction();
            session.update(menu);
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
    public boolean delete(Menu menu) {
        boolean result = true;
        Session session = SESSION_FACTORY.openSession();
        try {
            session.beginTransaction();
            session.delete(menu);
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
    public Menu findById(int id) {
        Menu menu = null;
        Session session = SESSION_FACTORY.openSession();
        try {
            session.beginTransaction();
            Query query = session.createQuery("select m from Menu m where m.menuId = :id");
            query.setParameter("id", id);
            menu = (Menu) query.uniqueResult();
            session.getTransaction().commit();
        } catch (Exception e) {
            menu = null;
            session.getTransaction().rollback();
        } finally {
            session.close();
        }
        return menu;
    }

    @SuppressWarnings({"rawtypes", "unchecked"})
    public List<Menu> findEntradas() {
        List<Menu> menus = null;
        Session session = SESSION_FACTORY.openSession();
        try {
            session.beginTransaction();
            Query query = session.createQuery("select m from Menu m where m.menuTipo = 'E'");
            menus = query.getResultList();
            session.getTransaction().commit();
        } catch (Exception e) {
            menus = null;
            session.getTransaction().rollback();
        } finally {
            session.close();
        }
        return menus;
    }
}
