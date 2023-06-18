package Alina.BD.Models;

import jakarta.persistence.*;
import lombok.*;

import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@RequiredArgsConstructor
@NoArgsConstructor
public class Tax {
    @OneToMany(mappedBy = "tax", cascade = CascadeType.REMOVE)
    private Set<Pays> pays = new LinkedHashSet<Pays>();
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @NonNull
    private Integer tax_deduction;
}
