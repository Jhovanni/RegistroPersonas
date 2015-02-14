package com.jhovanni.registropersonas.hibernate;

import com.jhovanni.registropersonas.entidad.Ciudad;
import com.jhovanni.registropersonas.entidad.Persona;
import com.jhovanni.registropersonas.entidad.Usuario;
import java.util.List;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Jhovanni
 */
@Transactional
public interface Servicio {

    /**
     * Obtiene el usuario con el nombre de usuario especificado
     *
     * @param nombre
     * @return
     */
    public Usuario getUsuario(String nombre);

    /**
     * Obtiene la ciudad con el id espedificado
     *
     * @param id
     * @return
     */
    public Ciudad getCiudad(int id);

    /**
     * Obtiene la lista completa de ciudades
     *
     * @return
     */
    public List<Ciudad> getCiudades();

    /**
     * Obtiene la lista de personas encontradas en el sistema
     *
     * @return
     */
    public List<Persona> getPersonas();

    /**
     * Agrega una persona al sistema
     *
     * @param persona
     * @return id generado
     */
    public int registrarPersona(Persona persona);

    /**
     * Crea un nuevo registro de una persona en el sistema, con el nombre de
     * usuario y contraseña
     *
     * @param persona
     * @param nombreUsuario
     * @param clave
     * @return id generado
     */
    public int registrarPersona(Persona persona, String nombreUsuario, String clave);

    /**
     * Busca el registro Persona en base a un idenfiticador
     *
     * @param id
     * @return
     */
    public Persona getPersona(int id);

    /**
     * Elimina un registro persona del sistema. Esta operación no puede
     * deshacerse
     *
     * @param persona
     */
    public void borrarPersona(Persona persona);

    /**
     * Elimina un registro persona del sistema en base a su identificador
     *
     * @param id
     */
    public void borrarPersona(int id);

    /**
     * Actualiza los datos de una persona
     *
     * @param persona
     */
    public void editarPersona(Persona persona);
}
