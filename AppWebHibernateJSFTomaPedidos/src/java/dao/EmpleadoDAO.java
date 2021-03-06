/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.util.List;
import model.Empleado;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import util.HibernateUtil;

/**
 *
 * @author RKOrtega
 */
public class EmpleadoDAO {

    private static final SessionFactory SESSION_FACTORY = HibernateUtil.getSessionFactory();

    @SuppressWarnings({"rawtypes", "unchecked"})
    public List<Empleado> findAll() {
        List<Empleado> empleados = null;
        Session session = SESSION_FACTORY.openSession();
        try {
            session.beginTransaction();
            Query query = session.createQuery("select e from Empleado e");
            empleados = query.getResultList();
            session.getTransaction().commit();
        } catch (Exception e) {
            empleados = null;
            session.getTransaction().rollback();
        } finally {
            session.close();
        }
        return empleados;
    }

    @SuppressWarnings({"rawtypes", "unchecked"})
    public boolean save(Empleado empleado) {
        boolean result = true;
        Session session = SESSION_FACTORY.openSession();
        try {
            session.beginTransaction();
            session.save(empleado);
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
    public boolean update(Empleado empleado) {
        boolean result = true;
        Session session = SESSION_FACTORY.openSession();
        try {
            session.beginTransaction();
            session.update(empleado);
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
    public boolean delete(Empleado empleado) {
        boolean result = true;
        Session session = SESSION_FACTORY.openSession();
        try {
            session.beginTransaction();
            session.delete(empleado);
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
    public Empleado findById(int id) {
        Empleado empleado = null;
        Session session = SESSION_FACTORY.openSession();
        try {
            session.beginTransaction();
            Query query = session.createQuery("select e from Empleado e where e.empleadoNumeroDocumento = :id");
            query.setParameter("id", id);
            empleado = (Empleado) query.uniqueResult();
            session.getTransaction().commit();
        } catch (Exception e) {
            empleado = null;
            session.getTransaction().rollback();
        } finally {
            session.close();
        }
        return empleado;
    }

    @SuppressWarnings({"rawtypes", "unchecked"})
    public Empleado findByDni(String dni) {
        Empleado empleado = null;
        Session session = SESSION_FACTORY.openSession();
        try {
            session.beginTransaction();
            Query query = session.createQuery("select e from Empleado e where e.empleadoNumeroDocumento = :dni");
            query.setParameter("dni", dni);
            empleado = (Empleado) query.uniqueResult();
            session.getTransaction().commit();
        } catch (Exception e) {
            empleado = null;
            session.getTransaction().rollback();
        } finally {
            session.close();
        }
        return empleado;
    }
}
