package pmc.private_medical_clinic.Dto;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;

import java.util.Date;

@Getter
@Data
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class BookingDto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Date bookingDate;
    private String fullName;
    private String phoneNumber;
    private String gender;
    private Long birthYear;
    private String address;
    private Date bookingAppointment;
    private String status;
}
