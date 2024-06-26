package pmc.private_medical_clinic.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "bills")
public class Bill {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @ManyToOne
    @JoinColumn(name = "appointment_list_id")
    private AppointmentList appointmentList;
    @ManyToOne
    @JoinColumn(name = "patient_id")
    private Patient patient;
    private Long drugExpense;
    private Long feeConsult;
}
