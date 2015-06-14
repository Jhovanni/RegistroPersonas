package com.jhovanni.registropersonas.hibernate;

import com.jhovanni.registropersonas.entidad.Foto;
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
public class FotoRepositorio implements Repositorio<Foto> {
    Logger log = LogManager.getLogger();

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public List<Foto> get() {
        log.entry();
        Session session = sessionFactory.getCurrentSession();
        return log.exit(session.createQuery("from Foto").list());
    }

    @Override
    public List<Foto> buscar(String busqueda) {
        log.entry(busqueda);
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Foto get(Serializable id) {
        log.entry(id);
        Session session = sessionFactory.getCurrentSession();
        return log.exit((Foto) session.get(Foto.class, id));
    }

    @Override
    public Serializable crear(Foto foto) {
        Session session = sessionFactory.getCurrentSession();
        return session.save(foto);
    }

    @Override
    public void guardar(Foto foto) {
        log.entry(foto);
        Session session = sessionFactory.getCurrentSession();
        session.update(foto);
        log.exit();
    }

    @Override
    public void borrar(Foto foto) {
        log.entry(foto);
        Session session = sessionFactory.getCurrentSession();
        session.delete(foto);
        log.exit();
    }

    @Override
    public Foto get(String cadena) {
        log.entry(cadena);
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
