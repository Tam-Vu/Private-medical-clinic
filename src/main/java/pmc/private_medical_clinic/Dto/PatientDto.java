/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pmc.private_medical_clinic.Dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *
 * @author duyth
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PatientDto {
   private String fullName;
   private String gender;
   private Long birthYear;
   private String phoneNumber;
   private String address;
}
