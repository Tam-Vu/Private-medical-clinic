package pmc.private_medical_clinic.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pmc.private_medical_clinic.Dto.PrincipleDto;
import pmc.private_medical_clinic.Entity.Principle;
import pmc.private_medical_clinic.Repositories.PrincipleRepo;
import pmc.private_medical_clinic.failureHandler.GlobalExceptionHandler;

import java.security.Principal;

@Service
public class PrincipleServiceImpl implements PrincipleService {

    @Autowired
    private PrincipleRepo principleRepo;
    @Override
    public Principle updateDetails(PrincipleDto principleDto) {
        Principle updatedPrinciple = principleRepo.getById(1);
        if(principleDto.getSoBenhNhanToiDa() < 0) {
            throw new GlobalExceptionHandler("Số lượng bệnh nhân phải là một số không âm!");
        }
        else {
            updatedPrinciple.setSoBenhNhanToiDa(principleDto.getSoBenhNhanToiDa());
        }
        if(principleDto.getTienKham() < 0) {
            throw new GlobalExceptionHandler("Số tiền khám phải là một số không âm!");
        }
        else {
            updatedPrinciple.setTienKham(principleDto.getTienKham());
        }
        return principleRepo.save(updatedPrinciple);
    }
}
