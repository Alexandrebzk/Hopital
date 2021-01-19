package hopital;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "equipe")
public class Equipe implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "equipe_id", nullable = false)
    private Integer equipeId;

    @Column(name = "nom", nullable = false)
    private String nom;

    public Equipe() {
    }

    public Equipe(String name) {
        this.nom = name;
    }

    public Equipe setEquipeId(Integer equipeId) {
        this.equipeId = equipeId;
        return this;
    }

    public Integer getEquipeId() {
        return equipeId;
    }

    public Equipe setNom(String nom) {
        this.nom = nom;
        return this;
    }

    public String getNom() {
        return nom;
    }

    @Override
    public String toString() {
        return "Equipe{" +
                "equipeId=" + equipeId + '\'' +
                "nom=" + nom + '\'' +
                '}';
    }
}
