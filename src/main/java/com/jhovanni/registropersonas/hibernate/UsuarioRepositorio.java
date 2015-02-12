package com.jhovanni.registropersonas.hibernate;

import com.jhovanni.registropersonas.entidad.Usuario;
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
public class UsuarioRepositorio implements Repositorio<Usuario> {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public List<Usuario> get() {
        Session session = sessionFactory.getCurrentSession();
        return session.createQuery("from Usuario").list();
    }

    @Override
    public List<Usuario> buscar(String busqueda) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Usuario get(Serializable id) {
        Session session = sessionFactory.getCurrentSession();
        return (Usuario) session.get(Usuario.class, id);
    }

    @Override
    public Serializable crear(Usuario usuario) {
        Session session = sessionFactory.getCurrentSession();
        return session.save(usuario);
    }

    @Override
    public void guardar(Usuario usuario) {
        Session session = sessionFactory.getCurrentSession();
        session.update(usuario);
    }

    @Override
    public void borrar(Usuario usuario) {
        Session session = sessionFactory.getCurrentSession();
        session.delete(usuario);
    }

}
