package pmc.private_medical_clinic.Services;

import org.springframework.stereotype.Service;
import pmc.private_medical_clinic.Dto.UsageDto;
import pmc.private_medical_clinic.Entity.Usage;

import java.util.List;

@Service
public interface UsageService {
    List<Usage> getAllUsages();
    Usage updateUsageById(Long id, UsageDto usageDto);
    Usage createUsage(UsageDto usageDto);
    Usage deleteUsageById(Long id);
}
