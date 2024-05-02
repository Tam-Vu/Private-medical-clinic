package pmc.private_medical_clinic.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pmc.private_medical_clinic.Dto.UsageDto;
import pmc.private_medical_clinic.Entity.Usage;
import pmc.private_medical_clinic.Repositories.UsageRepo;

import java.util.List;

@Service
public class UsageServiceImpl implements UsageService{
    @Autowired
    private final UsageRepo usageRepo;

    public UsageServiceImpl(UsageRepo usageRepo) {
        this.usageRepo = usageRepo;
    }

    @Override
    public List<Usage> getAllUsages() {
        return usageRepo.findAll();
    }

    @Override
    public Usage updateUsageById(Long id, UsageDto usageDto) {
        Usage usage = usageRepo.findById(id).get();
        if (usage != null) {
            usage.setUsageDes(usageDto.getUsageDes());
            usageRepo.save(usage);
        }
        return usage;
    }

    @Override
    public Usage createUsage(UsageDto usageDto) {
        Usage usage = new Usage();
        usage.setUsageDes(usageDto.getUsageDes());
        usageRepo.save(usage);
        return usage;
    }

    @Override
    public Usage deleteUsageById(Long id) {
        Usage usage = usageRepo.findById(id).get();
        if (usage != null) {
            usageRepo.deleteById(id);
        }
        return usage;
    }
}
