package Alina.BD.Models;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@RequiredArgsConstructor
@NoArgsConstructor
public class Commit {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @NonNull
    private Integer bonus;
    @NonNull
    @ManyToOne
    @JoinColumn(name = "fk_deal_id")
    private Deal deal;
    @NonNull
    @ManyToOne
    @JoinColumn(name = "fk_employee_id")
    private Employee employee;
    @NonNull
    @ManyToOne
    @JoinColumn(name = "fk_client_id")
    private Client client;
}