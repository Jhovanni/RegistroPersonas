package com.jhovanni.registropersonas.hibernate;

import com.jhovanni.registropersonas.entidad.Persona;
import java.io.Serializable;
import java.util.List;
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

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public List<Persona> get() {
        Session session = sessionFactory.getCurrentSession();
        return session.createQuery("from Persona").list();
    }

    @Override
    public List<Persona> buscar(String busqueda) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Persona get(Serializable id) {
        Session session = sessionFactory.getCurrentSession();
        return (Persona) session.get(Persona.class, id);
    }

    @Override
    public Serializable crear(Persona persona) {
        Session session = sessionFactory.getCurrentSession();
        return session.save(persona);
    }

    @Override
    public void guardar(Persona persona) {
        Session session = sessionFactory.getCurrentSession();
        session.update(persona);
    }

    @Override
    public void borrar(Persona persona) {
        Session session = sessionFactory.getCurrentSession();
        session.delete(persona);
    }

}
