package pmc.private_medical_clinic.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "authorizations")
public class Authorization {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "usergroup_id")
    private UserGroup userGroupId;

    @ManyToOne
    @JoinColumn(name = "feat_id")
    private Feat featId;

    private boolean isAccess;
}
