package pmc.private_medical_clinic.failureHandler;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import pmc.private_medical_clinic.Entity.ResponeInfo;

@ControllerAdvice

public class ShowExceptionToApi {

    @ExceptionHandler(GlobalExceptionHandler.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ResponeInfo<Object> handleCustomException(GlobalExceptionHandler ex) {
        ResponeInfo<Object> responeInfo = new ResponeInfo<>();
        responeInfo.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
        responeInfo.setMessage(ex.getMessage());
        return responeInfo;
    }
}
