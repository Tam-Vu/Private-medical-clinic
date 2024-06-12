package pmc.private_medical_clinic.Repositories;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import pmc.private_medical_clinic.Entity.UserGroup;

import java.util.Date;

public interface UserGroupRepo extends JpaRepository<UserGroup, Long> {
    @Query("select ug from UserGroup ug")
    List<UserGroup> findAllUserGroup();
}
