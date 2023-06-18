package Alina.BD.Models;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Getter
@Setter
@RequiredArgsConstructor
@NoArgsConstructor
public class Pays {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @NonNull
    @Column(columnDefinition = "DATE")
    private LocalDate date;
    @NonNull
    @ManyToOne
    @JoinColumn(name = "fk_tax_id")
    private Tax tax;
    @NonNull
    @ManyToOne
    @JoinColumn(name = "fk_employee_id")
    private Employee employee;
}
