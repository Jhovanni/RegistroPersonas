package com.jhovanni.registropersonas.control;

import com.jhovanni.registropersona.validacion.Tipologia;
import com.jhovanni.registropersonas.entidad.Ciudad;
import com.jhovanni.registropersonas.entidad.Foto;
import com.jhovanni.registropersonas.entidad.Genero;
import com.jhovanni.registropersonas.entidad.Persona;
import java.io.IOException;
import java.io.Serializable;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.validator.constraints.Range;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author Jhovanni
 */
public class PersonaForm implements Serializable {

    private static final Logger log = LogManager.getLogger();

    private Integer id;
    @NotNull
    @Size(min = 3, max = 20)
    private String nombre;
    @Range(min = 15, max = 70)
    private int edad;
    @NotNull
    private Genero genero;
    @NotNull
    private Ciudad ciudad;
    private MultipartFile foto;
    @NotNull
    @Size(min = 3, max = 20)
    @Tipologia(tipo = Tipologia.Tipo.MINUSCULA)
    private String nombreUsuario;
    @NotNull
    @Size(min = 7)
    private String clave;
    @NotNull
    private String clave2;

    public PersonaForm() {
    }

    PersonaForm(Persona persona) {
        this.id = persona.getId();
        this.nombre = persona.getNombre();
        this.edad = persona.getEdad();
        this.genero = persona.getGenero();
        this.ciudad = persona.getCiudad();
        this.nombreUsuario = persona.getUsuario().getNombre();
        this.clave = persona.getUsuario().getClave();
        this.clave2 = persona.getUsuario().getClave();
    }

    public Persona toPersona() {
        Persona persona = new Persona();
        persona.setId(id);
        persona.setNombre(nombre);
        persona.setEdad(edad);
        persona.setGenero(genero);
        persona.setCiudad(ciudad);
        if (foto != null && foto.getSize() > 0) {
            try {
                Foto f = new Foto();
                f.setContenido(this.foto.getBytes());

                if (this.foto.getOriginalFilename().length() <= Foto.NOMBRE_SIZE) {
                    f.setNombre(this.foto.getOriginalFilename());
                } else {
                    f.setNombre(this.foto.getOriginalFilename().substring(0, Foto.NOMBRE_SIZE));
                }
                persona.setFotoPerfil(f);
            } catch (IOException ex) {
                log.error("IOException PersonaForm.toPersona: " + ex);
            } catch (NullPointerException ne) {
                log.error("NullPointerException PersonaForm.toPersona: " + ne);
            }
        }
        return persona;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public Genero getGenero() {
        return genero;
    }

    public void setGenero(Genero genero) {
        this.genero = genero;
    }

    public Ciudad getCiudad() {
        return ciudad;
    }

    public void setCiudad(Ciudad ciudad) {
        this.ciudad = ciudad;
    }

    public MultipartFile getFoto() {
        return foto;
    }

    public void setFoto(MultipartFile foto) {
        this.foto = foto;
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }

    public String getClave2() {
        return clave2;
    }

    public void setClave2(String clave2) {
        this.clave2 = clave2;
    }

    @Override
    public String toString() {
        return "PersonaForm{" + "id=" + id + ", nombre=" + nombre + ", edad=" + edad + ", genero=" + genero + ", ciudad=" + ciudad + ", nombreUsuario=" + nombreUsuario + '}';
    }

}
