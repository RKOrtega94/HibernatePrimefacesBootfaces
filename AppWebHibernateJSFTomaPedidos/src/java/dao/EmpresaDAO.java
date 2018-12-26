/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.util.List;
import model.Empresa;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import util.HibernateUtil;

/**
 *
 * @author RKOrtega
 */
public class EmpresaDAO {

    private static final SessionFactory SESSION_FACTORY = HibernateUtil.getSessionFactory();

    @SuppressWarnings({"rawtypes", "unchecked"})
    public List<Empresa> findAll() {
        List<Empresa> empresas = null;
        Session session = SESSION_FACTORY.openSession();
        try {
            session.beginTransaction();
            Query query = session.createQuery("select e from Empresa e");
            empresas = query.getResultList();
            session.getTransaction().commit();
        } catch (Exception e) {
            empresas = null;
            session.getTransaction().rollback();
        } finally {
            session.close();
        }
        return empresas;
    }
    
    @SuppressWarnings({"rawtypes", "unchecked"})
    public boolean save(Empresa empresa){
        boolean result = true;
        Session session = SESSION_FACTORY.openSession();
        try {
            session.beginTransaction();
            session.save(empresa);
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
    public boolean update(Empresa empresa){
        boolean result = true;
        Session session = SESSION_FACTORY.openSession();
        try {
            session.beginTransaction();
            session.update(empresa);
            session.getTransaction().commit();
        } catch (Exception e) {
            result = false;
            session.getTransaction().rollback();
        } finally {
            session.close();
        }
        return result;
    }
    
    @SuppressWarnings({"unchecked", "rawtypes"})
    public boolean delete(Empresa empresa){
        boolean result = true;
        Session session = SESSION_FACTORY.openSession();
        try {
            session.beginTransaction();
            session.delete(empresa);
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
    public Empresa findById(int id){
        Empresa empresa = null;
        Session session = SESSION_FACTORY.openSession();
        try {
            session.beginTransaction();
            Query query = session.createQuery("select e from Empresa e where e.empresaId = :id");
            query.setParameter("id", id);
            empresa = (Empresa) query.uniqueResult();
            session.getTransaction().commit();
        } catch (Exception e) {
            empresa = null;
            session.getTransaction().rollback();
        } finally {
            session.close();
        }
        return empresa;
    }
}
