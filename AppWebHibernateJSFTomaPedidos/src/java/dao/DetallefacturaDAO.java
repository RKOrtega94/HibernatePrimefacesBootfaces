/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.util.List;
import model.Cabecerafactura;
import model.Detallefactura;
import model.Menu;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import util.HibernateUtil;

/**
 *
 * @author RKOrtega
 */
public class DetallefacturaDAO {

    private static final SessionFactory SESSION_FACTORY = HibernateUtil.getSessionFactory();

    @SuppressWarnings({"rawtypes", "unchecked"})
    public boolean save(Cabecerafactura cabecerafactura, Menu menu, int cantidad) {
        boolean result = true;
        Detallefactura detallefactura = new Detallefactura();
        Session session = SESSION_FACTORY.openSession();
        try {
            session.beginTransaction();
            detallefactura.setCabecerafactura(cabecerafactura);
            detallefactura.setMenu(menu);
            detallefactura.setDetallefacturaCantidad(cantidad);
            session.save(detallefactura);
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
    public List<Detallefactura> findDetalle(Cabecerafactura cabecera) {
        List<Detallefactura> detalle = null;
        Session session = SESSION_FACTORY.openSession();
        try {
            session.beginTransaction();
            Query query = session.createQuery("select d from Detallefactura d where d.cabecerafactura = :cabecera");
            query.setParameter("cabecera", cabecera);
            detalle = query.getResultList();
            session.getTransaction().commit();
        } catch (Exception e) {
            detalle = null;
            session.getTransaction().rollback();
        } finally {
            session.close();
        }
        return detalle;
    }
}
