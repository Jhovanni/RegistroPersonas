package com.jhovanni.registropersonas.hibernate;

import com.jhovanni.registropersonas.entidad.Usuario;
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
public class UsuarioRepositorio implements Repositorio<Usuario> {
    private static final Logger log = LogManager.getLogger();

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public List<Usuario> get() {
        log.entry();
        Session session = sessionFactory.getCurrentSession();
        return log.exit(session.createQuery("from Usuario").list());
    }

    @Override
    public List<Usuario> buscar(String busqueda) {
        log.entry(busqueda);
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Usuario get(Serializable id) {
        log.entry(id);
        Session session = sessionFactory.getCurrentSession();
        return log.exit((Usuario) session.get(Usuario.class, id));
    }

    @Override
    public Serializable crear(Usuario usuario) {
        log.entry(usuario);
        Session session = sessionFactory.getCurrentSession();
        return log.exit(session.save(usuario));
    }

    @Override
    public void guardar(Usuario usuario) {
        log.entry(usuario);
        Session session = sessionFactory.getCurrentSession();
        session.update(usuario);
        log.exit();
    }

    @Override
    public void borrar(Usuario usuario) {
        log.entry(usuario);
        Session session = sessionFactory.getCurrentSession();
        session.delete(usuario);
        log.exit();
    }

    @Override
    public Usuario get(String nombreUsuario) {
        log.entry(nombreUsuario);
        Session session = sessionFactory.getCurrentSession();
        return log.exit((Usuario) session.get(Usuario.class, nombreUsuario));
    }

}
