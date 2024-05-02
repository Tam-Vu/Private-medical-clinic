package pmc.private_medical_clinic.Services;

import org.springframework.stereotype.Service;
import pmc.private_medical_clinic.Dto.UnitDto;
import pmc.private_medical_clinic.Entity.Unit;

import java.util.List;

@Service
public interface UnitService {
    List<Unit> getAllUnits();
    Unit createUnit(UnitDto unitDto);
    Unit updateUnitById(Long id, UnitDto unitDto);
    Unit deleteUnitById(Long id);
}
