package com.jhovanni.registropersonas.hibernate;

import com.jhovanni.registropersonas.entidad.Ciudad;
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
public class CiudadRepositorio implements Repositorio<Ciudad> {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public List<Ciudad> get() {
        Session session = sessionFactory.getCurrentSession();
        return session.createQuery("from Ciudad").list();
    }

    @Override
    public List<Ciudad> buscar(String busqueda) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Ciudad get(Serializable id) {
        Session session = sessionFactory.getCurrentSession();
        return (Ciudad) session.get(Ciudad.class, id);
    }

    @Override
    public Serializable crear(Ciudad ciudad) {
        Session session = sessionFactory.getCurrentSession();
        return session.save(ciudad);
    }

    @Override
    public void guardar(Ciudad ciudad) {
        Session session = sessionFactory.getCurrentSession();
        session.update(ciudad);
    }

    @Override
    public void borrar(Ciudad ciudad) {
        Session session = sessionFactory.getCurrentSession();
        session.delete(ciudad);
    }

}
