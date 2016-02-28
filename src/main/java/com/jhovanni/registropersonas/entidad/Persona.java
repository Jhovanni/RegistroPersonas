package com.jhovanni.registropersonas.entidad;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import org.hibernate.validator.constraints.Range;

/**
 *
 * @author Jhovanni
 */
@Entity
@Table
@JsonIgnoreProperties({})
public class Persona implements Serializable {

    @Id
    @GeneratedValue
    private Integer id;
    @NotNull
    @Size(min = 3, max = 30)
    private String nombre;
    @Range(min = 15, max = 70)
    private Integer edad;
    @NotNull
    @Enumerated(EnumType.STRING)
    private Genero genero;
    @JoinColumn(name = "ID_FOTO", referencedColumnName = "ID")
    @OneToOne
    private Foto foto;
    @JoinColumn(name = "NOMBRE_USUARIO", referencedColumnName = "NOMBRE")
    @OneToOne
    private Usuario usuario;
    @JoinColumn(name = "ID_CIUDAD", referencedColumnName = "ID")
    @ManyToOne
    @NotNull
    private Ciudad ciudad;

    public Persona() {
    }

    public Persona(String nombre, Integer edad, Genero genero) {
        this.nombre = nombre;
        this.edad = edad;
        this.genero = genero;
    }

    public Persona(Integer id, String nombre, Integer edad, Genero genero, Ciudad ciudad, Integer fotoId) {
        this.id = id;
        this.nombre = nombre;
        this.edad = edad;
        this.genero = genero;
        this.ciudad = ciudad;
        if (fotoId == null) {
        } else {
            Foto f = new Foto();
            f.setId(fotoId);
            this.foto = f;
        }
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

    public Integer getEdad() {
        return edad;
    }

    public void setEdad(Integer edad) {
        this.edad = edad;
    }

    public Genero getGenero() {
        return genero;
    }

    public void setGenero(Genero genero) {
        this.genero = genero;
    }

    public Foto getFoto() {
        return foto;
    }

    public void setFoto(Foto foto) {
        this.foto = foto;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Ciudad getCiudad() {
        return ciudad;
    }

    public void setCiudad(Ciudad ciudad) {
        this.ciudad = ciudad;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 83 * hash + Objects.hashCode(this.id);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Persona other = (Persona) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Persona{" + "id=" + id + ", nombre=" + nombre + ", edad=" + edad + ", genero=" + genero + ", foto=" + foto + ", usuario=" + usuario + ", ciudad=" + ciudad + '}';
    }

}
