/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.util.List;
import org.hibernate.SessionFactory;
import util.HibernateUtil;
import model.TempInventario;
import org.hibernate.Session;
import org.hibernate.query.Query;

/**
 *
 * @author RKOrtega
 */
public class TempInventarioDAO {

    private static final SessionFactory SESSION_FACTORY = HibernateUtil.getSessionFactory();

    @SuppressWarnings({"rawtypes", "unchecked"})
    public List<TempInventario> findAll() {
        List<TempInventario> lista = null;
        Session session = SESSION_FACTORY.openSession();
        try {
            session.beginTransaction();
            Query query = session.createQuery("select i from TempInventario i");
            lista = query.getResultList();
            session.getTransaction().commit();
        } catch (Exception e) {
            lista = null;
            session.getTransaction().rollback();
        } finally {
            session.close();
        }
        return lista;
    }
}
