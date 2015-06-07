package com.jhovanni.registropersonas.hibernate;

import java.io.Serializable;
import java.util.List;
import javax.transaction.Transactional;

/**
 * Interface para todos los repositorios de la apliación
 * @author jhovanni
 * @param <E> Entidad sobre la que trabaja el repositorio
 */
@Transactional
public interface Repositorio<E> {

    /**
     * Obtiene la lista completa de registros
     *
     * @return
     */
    List<E> get();

    /**
     * Busca los registros en base a una cadena
     *
     * @param busqueda
     * @return
     */
    List<E> buscar(String busqueda);

    /**
     * Busca un registro en base a su identificador
     *
     * @param id
     * @return
     */
    E get(Serializable id);

    /**
     * Agrega un registro al sistea
     *
     * @param entidad
     * @return
     */
    Serializable crear(E entidad);

    /**
     * Actualiza los datos de un registro
     *
     * @param entidad
     */
    void guardar(E entidad);

    /**
     * Elimina un registro del sistema
     *
     * @param entidad
     */
    void borrar(E entidad);

}
