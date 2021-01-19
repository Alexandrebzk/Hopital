package hopital;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "service")
public class Service implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "service_id", nullable = false)
    private Integer serviceId;

    @Column(name = "nom", nullable = false)
    private String nom;

    @Column(name = "localisation", nullable = false)
    private String localisation;

    @ManyToOne(cascade = CascadeType.DETACH)
    @JoinColumn(name = "id_chef")
    private Medecin chef;

    /* Liste des services que le médecin dirige */
    @OneToMany(mappedBy = "service")
    private List<Medecin> medecinList;

    public Service() {
    }

    public Service(String name, String fullAddress) {
        this.nom = name;
        this.localisation = fullAddress;
        this.medecinList = new ArrayList<>();
    }

    public List<Medecin> getMedecinList() {
        return medecinList;
    }

    public void setMedecinList(List<Medecin> medecinList) {
        this.medecinList = medecinList;
    }

    public Service setServiceId(Integer serviceId) {
        this.serviceId = serviceId;
        return this;
    }

    public Integer getServiceId() {
        return serviceId;
    }

    public Service setNom(String nom) {
        this.nom = nom;
        return this;
    }

    public String getNom() {
        return nom;
    }

    public Service setLocalisation(String localisation) {
        this.localisation = localisation;
        return this;
    }

    public String getLocalisation() {
        return localisation;
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
        Service service = (Service) o;
        return Objects.equals(serviceId, service.serviceId) &&
                Objects.equals(nom, service.nom) &&
                Objects.equals(chef, service.chef);
    }

    @Override
    public int hashCode() {
        return Objects.hash(serviceId, nom, chef);
    }

    @Override
    public String toString() {

        StringBuilder s = new StringBuilder("Service{\n" +
                "\tserviceId=" + serviceId +
                ", nom='" + nom + '\'' +
                ", localisation='" + localisation + '\'' +
                ", chefId=" + (chef != null ? chef.getMedecinId() : "null") +
                ", medecinList= [ \n");
        for (Medecin medecin : medecinList)
            s.append("\t\t{ id : ").append(medecin.getMedecinId()).append(" },\n");

        return s.toString();
    }
}
