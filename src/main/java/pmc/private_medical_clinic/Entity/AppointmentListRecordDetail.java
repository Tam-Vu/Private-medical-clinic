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
    private Integer id;
    @ManyToOne
    @JoinColumn(name = "appointment_list_record_id")
    private AppointmentListRecord appointmentListRecord;

    @ManyToOne
    @JoinColumn(name = "medicine_id")
    private Medicine medicine;

    private Integer quantity;
    @ManyToOne
    @JoinColumn(name = "usage_id")
    private Usage usage;
}
