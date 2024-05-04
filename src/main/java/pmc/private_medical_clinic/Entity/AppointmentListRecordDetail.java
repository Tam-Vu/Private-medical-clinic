package pmc.private_medical_clinic.Entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "appointmentlistrecorddetail")
public class AppointmentListRecordDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "appointment_list_record_id")
    private AppointmentRecord appointmentListRecord;

    @ManyToOne
    @JoinColumn(name = "drug_id")
    private Drug drug;

    private Long count;
    @ManyToOne
    @JoinColumn(name = "usage_id")
    private Usage usage;
}
