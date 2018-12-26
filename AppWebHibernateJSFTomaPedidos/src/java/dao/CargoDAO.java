package dao;

import java.util.List;
import model.Cargo;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import util.HibernateUtil;

/**
 *
 * @author RKOrtega
 */
public class CargoDAO {
    private static final SessionFactory SESSION_FACTORY = HibernateUtil.getSessionFactory();
    
    @SuppressWarnings({"rawtypes", "unchecked"})
    public List<Cargo> findAll(){
        List<Cargo> cargos = null;
        Session session = SESSION_FACTORY.openSession();
        try {
            session.beginTransaction();
            Query query = session.createQuery("select c from Cargo c");
            cargos = query.getResultList();
            session.getTransaction().commit();
        } catch (Exception e) {
            cargos = null;
            session.getTransaction().rollback();
        } finally {
            session.close();
        }
        return cargos;
    }
    
    @SuppressWarnings({"rawtypes", "unchecked"})
    public boolean save(Cargo cargo){
        boolean result = true;
        Session session = SESSION_FACTORY.openSession();
        try {
            session.beginTransaction();
            session.save(cargo);
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
    public boolean update(Cargo cargo){
        boolean result = true;
        Session session = SESSION_FACTORY.openSession();
        try {
            session.beginTransaction();
            session.update(cargo);
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
    public boolean delete(Cargo cargo){
        boolean result = true;
        Session session = SESSION_FACTORY.openSession();
        try {
            session.beginTransaction();
            session.delete(cargo);
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
