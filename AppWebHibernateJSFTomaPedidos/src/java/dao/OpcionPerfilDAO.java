/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.util.List;
import model.OpcionXperfil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import util.HibernateUtil;

/**
 *
 * @author RKOrtega
 */
public class OpcionPerfilDAO {
    
    private static final SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
    
    @SuppressWarnings({"rawtypes", "unchecked"})
    public List<OpcionXperfil> findAll(){
        List<OpcionXperfil> permisos = null;
        Session session = sessionFactory.openSession();
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
}
