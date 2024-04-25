package pmc.private_medical_clinic.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import pmc.private_medical_clinic.Entity.Bill;

import java.util.List;

@Repository

public interface BillRepo extends JpaRepository<Bill, Long>{
    @Query("SELECT b FROM Bill b JOIN b.patient JOIN b.appointmentList")
    List<Bill> findAllBill();
}
