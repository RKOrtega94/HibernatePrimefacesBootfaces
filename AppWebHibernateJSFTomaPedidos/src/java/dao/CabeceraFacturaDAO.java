/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.util.List;
import model.Cabecerafactura;
import model.Cliente;
import model.Usuario;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import util.HibernateUtil;

/**
 *
 * @author RKOrtega
 */
public class CabeceraFacturaDAO {

    private static final SessionFactory SESSION_FACTORY = HibernateUtil.getSessionFactory();

    @SuppressWarnings({"rawtypes", "unchecked"})
    public boolean save(Cliente cliente, Usuario usuario, Double descuento) {
        boolean result = true;
        Cabecerafactura cabecerafactura = new Cabecerafactura();
        Session session = SESSION_FACTORY.openSession();
        try {
            session.beginTransaction();
            cabecerafactura.setCliente(cliente);
            cabecerafactura.setUsuario(usuario);
            cabecerafactura.setCabecerafacturaValor(descuento);
            session.save(cabecerafactura);
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
    public Cabecerafactura findFactura(Usuario usuario, Cliente cliente) {
        Cabecerafactura cabecerafactura = null;
        Session session = SESSION_FACTORY.openSession();
        try {
            session.beginTransaction();
            Query query = session.createQuery("select c from Cabecerafactura c where c.cliente = :cliente and c.usuario = :usuario and c.cabecerafacturaEstado = 'A'");
            query.setParameter("cliente", cliente);
            query.setParameter("usuario", usuario);
            cabecerafactura = (Cabecerafactura) query.uniqueResult();
            session.getTransaction().commit();
        } catch (Exception e) {
            cabecerafactura = null;
            session.getTransaction().rollback();
        } finally {
            session.close();
        }
        return cabecerafactura;
    }

    @SuppressWarnings({"rawtypes", "unchecked"})
    public boolean update(Cabecerafactura cabecerafactura) {
        boolean result = true;
        Session session = SESSION_FACTORY.openSession();
        try {
            session.beginTransaction();
            session.update(cabecerafactura);
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
    public List<String> count() {
        List<String> result = null;
        Session session = SESSION_FACTORY.openSession();
        try {
            session.beginTransaction();
            Query query = session.createQuery("select count(c.cabecerafacturaValor) from Cabecerafactura c");
            result = query.getResultList();
            session.getTransaction().commit();
        } catch (Exception e) {
            result = null;
            session.getTransaction().rollback();
        } finally {
            session.close();
        }
        return result;
    }

    @SuppressWarnings({"rawtypes", "unchecked"})
    public List<String> countPendientes() {
        List<String> result = null;
        Session session = SESSION_FACTORY.openSession();
        try {
            session.beginTransaction();
            Query query = session.createQuery("select count(c) from Cabecerafactura c where c.cabecerafacturaEstado = 'P'");
            result = query.getResultList();
            session.getTransaction().commit();
        } catch (Exception e) {
            result = null;
            session.getTransaction().rollback();
        } finally {
            session.close();
        }
        return result;
    }

    @SuppressWarnings({"rawtypes", "unchecked"})
    public List<Cabecerafactura> findPendiente() {
        List<Cabecerafactura> result = null;
        Session session = SESSION_FACTORY.openSession();
        try {
            session.beginTransaction();
            Query query = session.createQuery("select c from Cabecerafactura c where c.cabecerafacturaEstado = 'P' order by c.cabecerafacturaId desc");
            result = query.getResultList();
            session.getTransaction().commit();
        } catch (Exception e) {
            result = null;
            session.getTransaction().rollback();
        } finally {
            session.close();
        }
        return result;
    }
    
    @SuppressWarnings({"rawtypes", "unchecked"})
    public List<Cabecerafactura> ultimaFactura(){
        List<Cabecerafactura> result = null;
        Session session = SESSION_FACTORY.openSession();
        try {
            session.beginTransaction();
            Query query = session.createQuery("select c.cabecerafacturaId from Cabecerafactura c order by c.cabecerafacturaId desc");
            query.setMaxResults(1);
            result = query.getResultList();
            session.getTransaction().commit();
        } catch (Exception e) {
            result = null;
            session.getTransaction().rollback();
        } finally {
            session.close();
        }
        return result;
    }
}
