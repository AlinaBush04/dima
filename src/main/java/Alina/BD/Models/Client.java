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
@Table(name = "clients")
public class Client {
    @OneToMany(mappedBy = "client", cascade = CascadeType.REMOVE)
    private Set<Commit> commits = new LinkedHashSet<Commit>();
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @NonNull
    private String full_name;
    @NonNull
    @ManyToOne
    @JoinColumn(name = "fk_employee_id")
    private Employee employee;
}
