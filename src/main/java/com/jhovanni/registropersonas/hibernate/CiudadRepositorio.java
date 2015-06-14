package com.jhovanni.registropersonas.hibernate;

import com.jhovanni.registropersonas.entidad.Ciudad;
import java.io.Serializable;
import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Jhovanni
 */
@Repository
public class CiudadRepositorio implements Repositorio<Ciudad> {
    Logger log = LogManager.getLogger();

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public List<Ciudad> get() {
        log.entry();
        Session session = sessionFactory.getCurrentSession();
        return log.exit(session.createQuery("from Ciudad").list());
    }

    @Override
    public List<Ciudad> buscar(String busqueda) {
        log.entry(busqueda);
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Ciudad get(Serializable id) {
        log.entry(id);
        Session session = sessionFactory.getCurrentSession();
        return log.exit((Ciudad) session.get(Ciudad.class, id));
    }

    @Override
    public Serializable crear(Ciudad ciudad) {
        Session session = sessionFactory.getCurrentSession();
        return session.save(ciudad);
    }

    @Override
    public void guardar(Ciudad ciudad) {
        log.entry(ciudad);
        Session session = sessionFactory.getCurrentSession();
        session.update(ciudad);
        log.exit();
    }

    @Override
    public void borrar(Ciudad ciudad) {
        log.entry(ciudad);
        Session session = sessionFactory.getCurrentSession();
        session.delete(ciudad);
        log.exit();
    }

    @Override
    public Ciudad get(String cadena) {
        log.entry(cadena);
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
