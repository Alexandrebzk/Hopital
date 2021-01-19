package hopital;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "medecin")
public class Medecin implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "medecin_id", nullable = false)
    private Integer medecinId;

    @Column(name = "nom", nullable = false)
    private String nom;

    @Column(name = "prenom", nullable = false)
    private String prenom;

    @Column(name = "salaire")
    private Double salaire;

    /* Liste des services que le médecin dirige */
    @OneToMany(mappedBy = "chef")
    private List<Service> serviceList;

    /* Service auquel le médecin appartient */
    @ManyToOne(cascade = CascadeType.DETACH)
    @JoinColumn(name = "service_id")
    private Service service;


    /* Chef du médecin */
    @OneToOne
    @JoinColumn(name = "id_chef")
    private Medecin chef;

    public Medecin() {
        this.serviceList = new ArrayList<>();
    }

    public Medecin(String lastName, String firstName, double randomDouble) {
        this.salaire = randomDouble;
        this.nom = lastName;
        this.prenom = firstName;
        this.serviceList = new ArrayList<>();
    }

    public Service getService() {
        return service;
    }

    public void setService(Service service) {
        this.service = service;
    }

    public Medecin setMedecinId(Integer medecinId) {
        this.medecinId = medecinId;
        return this;
    }

    public Integer getMedecinId() {
        return medecinId;
    }

    public Medecin setNom(String nom) {
        this.nom = nom;
        return this;
    }

    public String getNom() {
        return nom;
    }

    public Medecin setPrenom(String prenom) {
        this.prenom = prenom;
        return this;
    }

    public String getPrenom() {
        return prenom;
    }

    public Double getSalaire() {
        return salaire;
    }

    public void setSalaire(Double salaire) {
        this.salaire = salaire;
    }

    public List<Service> getServiceList() {

        return serviceList;
    }

    public void setServiceList(List<Service> serviceList) {
        this.serviceList = serviceList;
    }

    public Medecin getChef() {
        return chef;
    }

    public void setChef(Medecin chef) {
        this.chef = chef;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Medecin medecin = (Medecin) o;
        return Objects.equals(medecinId, medecin.medecinId) &&
                Objects.equals(nom, medecin.nom) &&
                Objects.equals(prenom, medecin.prenom);
    }

    @Override
    public int hashCode() {
        return Objects.hash(medecinId, nom, prenom);
    }

    @Override
    public String toString() {

        StringBuilder s = new StringBuilder("Medecin{\n" +
                "\tmedecinId=" + medecinId +
                ", nom='" + nom + '\'' +
                ", prenom='" + prenom + '\'' +
                ", salaire=" + salaire +
                ", serviceId=" + (service != null ? service.getServiceId() : "null") +
                ", chef=" + chef +
                ", serviceList= [  \n");
        for (Service service : serviceList)
            s.append("\t\t{ id : ").append(service.getServiceId()).append(" },\n");

        s.append(" ], chef=").append(chef).append('}');
        return s.toString();
    }
}
