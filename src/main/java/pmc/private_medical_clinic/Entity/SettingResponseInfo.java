package pmc.private_medical_clinic.Entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SettingResponseInfo {
    private User user;
    private Principle principle;
    private List<Medicine> medicineList;
    private List<Unit> unitList;
}
