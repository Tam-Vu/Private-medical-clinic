/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pmc.private_medical_clinic.Dto;

import jakarta.persistence.CascadeType;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import pmc.private_medical_clinic.Entity.Unit;

/**
 *
 * @author duyth
 */
@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AppointmentListPatientDto {

    private Long patientId;
    private Long appointmentListId;
    private Integer orderNumber;
    private Date timeUpdate;

}
