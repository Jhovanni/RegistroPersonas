package com.jhovanni.registropersonas.control;

import com.jhovanni.registropersonas.entidad.Ciudad;
import com.jhovanni.registropersonas.entidad.Genero;
import com.jhovanni.registropersonas.entidad.Persona;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import org.hibernate.validator.constraints.Range;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author Jhovanni
 */
public class PersonaForm {

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
    private String nombreUsuario;
    @NotNull
    @Size(min = 7)
    private String clave;
    @NotNull
    private String clave2;

    public PersonaForm() {
    }

    PersonaForm(Persona persona) {
        this.nombre = persona.getNombre();
        this.edad = persona.getEdad();
        this.genero = persona.getGenero();
        this.ciudad = persona.getCiudad();
        this.nombreUsuario = persona.getUsuario().getNombre();
        this.clave = this.clave2 = persona.getUsuario().getClave();
    }

    public Persona toPersona() {
        Persona persona = new Persona();
        persona.setNombre(nombre);
        persona.setEdad(edad);
        persona.setGenero(genero);
        persona.setCiudad(ciudad);
        try {
            persona.setFoto(foto.getBytes());
        } catch (IOException ex) {
            Logger.getLogger(PersonaForm.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NullPointerException ne) {
            Logger.getLogger(PersonaForm.class.getName()).log(Level.WARNING, null, ne);
        }
        return persona;
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

}
