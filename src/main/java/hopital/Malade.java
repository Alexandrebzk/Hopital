package hopital;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "malade")
public class Malade implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "malade_id", nullable = false)
    private Integer maladeId;

    @Column(name = "nom", nullable = false)
    private String nom;

    @Column(name = "prenom", nullable = false)
    private String prenom;

    @Column(name = "rue")
    private String rue;

    @Column(name = "numero_de_rue")
    private Integer numeroDeRue;

    @Column(name = "ville")
    private String ville;

    public Malade() {
    }

    public Malade setMaladeId(Integer maladeId) {
        this.maladeId = maladeId;
        return this;
    }

    public Integer getMaladeId() {
        return maladeId;
    }

    public Malade setNom(String nom) {
        this.nom = nom;
        return this;
    }

    public String getNom() {
        return nom;
    }

    public Malade setPrenom(String prenom) {
        this.prenom = prenom;
        return this;
    }

    public String getPrenom() {
        return prenom;
    }

    public Malade setRue(String rue) {
        this.rue = rue;
        return this;
    }

    public String getRue() {
        return rue;
    }

    public Malade setNumeroDeRue(Integer numeroDeRue) {
        this.numeroDeRue = numeroDeRue;
        return this;
    }

    public Integer getNumeroDeRue() {
        return numeroDeRue;
    }

    public Malade setVille(String ville) {
        this.ville = ville;
        return this;
    }

    public String getVille() {
        return ville;
    }

    @Override
    public String toString() {
        return "Malade{" +
                "maladeId=" + maladeId + '\'' +
                "nom=" + nom + '\'' +
                "prenom=" + prenom + '\'' +
                "rue=" + rue + '\'' +
                "numeroDeRue=" + numeroDeRue + '\'' +
                "ville=" + ville + '\'' +
                '}';
    }
}
