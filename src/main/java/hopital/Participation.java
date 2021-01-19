package hopital;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "participation")
public class Participation implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "participation_id", nullable = false)
    private Integer participationId;

    @Column(name = "id_medecin")
    private Integer medecin;

    @Column(name = "id_equipe")
    private Integer equipe;

    @Column(name = "fonction")
    private String fonction;

    public Participation() {
    }
}
