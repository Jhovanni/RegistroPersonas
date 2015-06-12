package com.jhovanni.registropersonas.hibernate;

import com.jhovanni.registropersonas.entidad.Permiso;
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
public class PermisoRepositorio implements Repositorio<Permiso> {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public List<Permiso> get() {
        Session session = sessionFactory.getCurrentSession();
        return session.createQuery("from Permiso").list();
    }

    @Override
    public List<Permiso> buscar(String busqueda) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Permiso get(Serializable id) {
        Session session = sessionFactory.getCurrentSession();
        return (Permiso) session.get(Permiso.class, id);
    }

    @Override
    public Serializable crear(Permiso permiso) {
        Session session = sessionFactory.getCurrentSession();
        return session.save(permiso);
    }

    @Override
    public void guardar(Permiso permiso) {
        Session session = sessionFactory.getCurrentSession();
        session.update(permiso);
    }

    @Override
    public void borrar(Permiso permiso) {
        Session session = sessionFactory.getCurrentSession();
        session.delete(permiso);
    }

    @Override
    public Permiso get(String cadena) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
