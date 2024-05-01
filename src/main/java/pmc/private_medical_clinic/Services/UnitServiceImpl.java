package pmc.private_medical_clinic.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pmc.private_medical_clinic.Dto.UnitDto;
import pmc.private_medical_clinic.Entity.Unit;
import pmc.private_medical_clinic.Repositories.UnitRepo;

import java.util.List;

@Service
public class UnitServiceImpl implements UnitService {
    @Autowired
    private final UnitRepo unitRepo;

    public UnitServiceImpl(UnitRepo unitRepo) {
        this.unitRepo = unitRepo;
    }

    @Override
    public List<Unit> getAllUnits() {
        return unitRepo.findAll();
    }

    @Override
    public Unit createUnit(UnitDto unitDto) {
        Unit unitExists = unitRepo.findUnitByUnitName(unitDto.getUnitName());
        if (unitExists != null) {
            return unitExists;
        }
        Unit unit = new Unit();
        unit.setUnitName(unitDto.getUnitName());
        unitRepo.save(unit);
        return unit;
    }

    @Override
    public Unit updateUnitById(Long id, UnitDto unitDto) {
        Unit unit = unitRepo.findById(id).get();
        if (unit != null) {
            unit.setUnitName(unitDto.getUnitName());
            return unitRepo.save(unit);
        }
        return unit;
    }

    @Override
    public Unit deleteUnitById(Long id) {
        Unit unit = unitRepo.findById(id).get();
        if (unit != null) {
            unitRepo.deleteById(id);
        }
        return unit;
    }
}
