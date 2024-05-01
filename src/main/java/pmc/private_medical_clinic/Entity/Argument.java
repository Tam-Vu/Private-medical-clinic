package pmc.private_medical_clinic.Entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Entity
@Setter
@Table(name = "arguments")
public class Argument {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;
    @Column(name = "maxNumberOfPatients")
    private Long maxNumberOfPatients;
    @Column(name = "feeConsult")
    private Long feeConsult;

    public Argument(Long id, Long maxNumberOfPatients, Long feeConsult) {
        this.id = id;
        this.maxNumberOfPatients = maxNumberOfPatients;
        this.feeConsult = feeConsult;
    }

    public Argument() {
    }
}
