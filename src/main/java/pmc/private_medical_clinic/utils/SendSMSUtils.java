package pmc.private_medical_clinic.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import pmc.private_medical_clinic.Dto.OTPBody;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

@Service
public class SendSMSUtils {

    @Autowired
    private RestTemplate restTemplate;

    private static final String smsApi = "https://sms.capcom.me/api/3rdparty/v1/message";
    private static final String smsUsername = "2U95TR";
    private static final String smsPassword = "a_msstv_r1u8ut";
    public SendSMSUtils(){
        restTemplate = new RestTemplate();
    }

    public void sendSMS(String phoneNumber, OTPBody otpBody) throws JsonProcessingException {
        phoneNumber = phoneNumber.replaceFirst("0", "+84");
        String[] phoneNumberArray = new String[1];
        phoneNumberArray[0] = phoneNumber;
        System.out.println(smsUsername);
        System.out.println(smsPassword);
        System.out.println(smsApi);
        //create the payload
        Map<String, Object> payload = new HashMap<>();
        payload.put("message", otpBody.getMessage());
        payload.put("phoneNumbers", phoneNumberArray);

        // Create headers
        HttpHeaders headers = new HttpHeaders();
        headers.setBasicAuth(smsUsername, smsPassword);
        headers.setContentType(MediaType.APPLICATION_JSON);
        //convert the payload to JSON
        ObjectMapper mapper = new ObjectMapper();
        String jsonPayload = mapper.writeValueAsString(payload);

        // Create request
        HttpEntity<String> request = new HttpEntity<>(jsonPayload, headers);

        // Make the POST request
        ResponseEntity<String> response = restTemplate.exchange(smsApi, HttpMethod.POST, request, String.class);

        // Get the response data
        String data = response.getBody();
        System.out.println(data);
    }
}
