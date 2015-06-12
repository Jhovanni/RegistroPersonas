package com.jhovanni.registropersonas.hibernate;

import com.jhovanni.registropersonas.entidad.Persona;
import java.io.Serializable;
import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Jhovanni
 */
@Repository
public class PersonaRepositorio implements Repositorio<Persona> {
    private static final Logger log = LogManager.getLogger();
    
    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public List<Persona> get() {
        log.entry();
        Session session = sessionFactory.getCurrentSession();
        return log.exit(session.createQuery("from Persona").list());
    }

    @Override
    public List<Persona> buscar(String busqueda) {
        log.entry(busqueda);
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Persona get(Serializable id) {
        log.entry(id);
        Session session = sessionFactory.getCurrentSession();
        return log.exit((Persona) session.get(Persona.class, id));
    }
    
    @Override
    public Persona get(String nombreUsuario) {
        log.entry(nombreUsuario);
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("from Persona p where usuario.nombre=:nombreUsuario").setString("nombreUsuario", nombreUsuario);
        return log.exit((Persona) query.uniqueResult());
    }

    @Override
    public Serializable crear(Persona persona) {
        log.entry(persona);
        Session session = sessionFactory.getCurrentSession();
        return log.exit(session.save(persona));
    }

    @Override
    public void guardar(Persona persona) {
        log.entry(persona);
        Session session = sessionFactory.getCurrentSession();
        session.update(persona);
        log.exit();
    }

    @Override
    public void borrar(Persona persona) {
        log.entry(persona);
        Session session = sessionFactory.getCurrentSession();
        session.delete(persona);
        log.exit();
    }

}
