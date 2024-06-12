package pmc.private_medical_clinic.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "appointmentrecords")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AppointmentRecord {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;
    @ManyToOne()
    @JoinColumn (
            name = "patientId",
            referencedColumnName = "id"
    )
    private Patient patient;
    @Column(name = "symptoms")
    private String symptoms;
    @ManyToOne()
    @JoinColumn (
            name = "diseaseId",
            referencedColumnName = "id"
    )
    private Disease disease;
    @ManyToOne()
    @JoinColumn(
            name = "appointmentListId",
            referencedColumnName = "id"
    )
    private AppointmentList appointmentList;

}
