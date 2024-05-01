package pmc.private_medical_clinic.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "drugs")
@Getter
@Setter
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Drug {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;
    @Column(name = "drugName")
    private String drugName;
    @Column(name = "price")
    private Long price;
    @Column(name = "count")
    private Long count;
    @ManyToOne
    @JoinColumn (
            name = "unitId",
            referencedColumnName = "id"
    )
    private Unit unit;
    @Column(name = "note")
    private String note;
}
