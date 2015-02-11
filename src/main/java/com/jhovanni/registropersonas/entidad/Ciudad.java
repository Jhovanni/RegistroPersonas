package com.jhovanni.registropersonas.entidad;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author Jhovanni
 */
@Entity
@Table(name = "CIUDADES")
public class Ciudad implements Serializable {

    @Id
    @GeneratedValue
    //la secuencia que genera el id es "HIBERNATE_SEQUENCE", por lo tanto no es
    //necesario especificarla en @GeneratedValue
    private int id;
    private String nombre;
    @OneToMany(mappedBy = "ciudad")
    private List<Persona> personas;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public List<Persona> getPersonas() {
        return personas;
    }

    public void setPersonas(List<Persona> personas) {
        this.personas = personas;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 97 * hash + this.id;
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
        final Ciudad other = (Ciudad) obj;
        return this.id == other.id;
    }

    @Override
    public String toString() {
        return "Ciudad{" + "id=" + id + ", nombre=" + nombre + '}';
    }

}
