package pmc.private_medical_clinic.Dto;

import lombok.Builder;

@Builder
public record MailBody(String to, String subject, String text) {

}
