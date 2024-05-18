package pmc.private_medical_clinic.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import pmc.private_medical_clinic.Entity.Authorization;

import java.util.List;

public interface AuthorizationRepo extends JpaRepository<Authorization, Long> {

    @Query("select au from Authorization au JOIN au.userGroupId ug JOIN au.featId f")
    List<Authorization> findAllAuthorizations();

    @Query("select au from Authorization au JOIN au.userGroupId ug JOIN au.featId f WHERE ug.id= :userGroupId")
    List<Authorization> findAllAuthorizationsByUserGroup(long userGroupId);

    @Query("select au from Authorization au JOIN au.userGroupId ug JOIN au.featId f WHERE ug.id= :userGroupId And f.id = :featId")
    Authorization findAllAuthorizations(Long featId, Long userGroupId);
}
