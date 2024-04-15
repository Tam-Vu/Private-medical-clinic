/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pmc.private_medical_clinic.Dto;

import jakarta.persistence.CascadeType;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import pmc.private_medical_clinic.Entity.Unit;

/**
 *
 * @author duyth
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AppointmentListPatientDto {

    private Integer patientId;
    private Integer appointmentListId;

}
