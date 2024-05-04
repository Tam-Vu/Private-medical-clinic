package pmc.private_medical_clinic.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pmc.private_medical_clinic.Entity.Argument;
import pmc.private_medical_clinic.Repositories.ArgumentRepo;

@Service
public class ArgumentServiceImpl implements ArgumentService {
    @Autowired
    private final ArgumentRepo argumentRepo;

    public ArgumentServiceImpl(ArgumentRepo argumentRepo) {
        this.argumentRepo = argumentRepo;
    }

   @Override
    public Argument getArgument() {
        return argumentRepo.findAll().get(0);
    }
    @Override
    public Long updateMaxNumOfPatients(Long maxNumOfPatients) {
        Argument argument = argumentRepo.findAll().get(0);
        argument.setMaxNumberOfPatients(maxNumOfPatients);
        argumentRepo.save(argument);
        return maxNumOfPatients;
    }
    @Override
    public Long updateFeeConsult(Long feeConsult) {
        Argument argument = argumentRepo.findAll().get(0);
        argument.setFeeConsult(feeConsult);
        argumentRepo.save(argument);
        return feeConsult;
    }
}
