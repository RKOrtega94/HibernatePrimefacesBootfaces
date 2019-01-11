/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import model.Cabecerafactura;
import model.Detallefactura;
import model.Menu;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
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
}
