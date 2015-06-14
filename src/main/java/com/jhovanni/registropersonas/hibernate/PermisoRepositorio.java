package com.jhovanni.registropersonas.hibernate;

import com.jhovanni.registropersonas.entidad.Permiso;
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
public class PermisoRepositorio implements Repositorio<Permiso> {
    Logger log = LogManager.getLogger();

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public List<Permiso> get() {
        log.entry();
        Session session = sessionFactory.getCurrentSession();
        return log.exit(session.createQuery("from Permiso").list());
    }

    @Override
    public List<Permiso> buscar(String busqueda) {
        log.entry(busqueda);
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Permiso get(Serializable id) {
        log.entry(id);
        Session session = sessionFactory.getCurrentSession();
        return log.exit((Permiso) session.get(Permiso.class, id));
    }

    @Override
    public Serializable crear(Permiso permiso) {
        log.entry(permiso);
        Session session = sessionFactory.getCurrentSession();
        return log.exit(session.save(permiso));
    }

    @Override
    public void guardar(Permiso permiso) {
        log.entry(permiso);
        Session session = sessionFactory.getCurrentSession();
        session.update(permiso);
        log.exit();
    }

    @Override
    public void borrar(Permiso permiso) {
        log.entry(permiso);
        Session session = sessionFactory.getCurrentSession();
        session.delete(permiso);
        log.exit();
    }

    @Override
    public Permiso get(String cadena) {
        log.entry(cadena);
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
