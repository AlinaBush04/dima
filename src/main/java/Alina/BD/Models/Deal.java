package Alina.BD.Models;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@RequiredArgsConstructor
@NoArgsConstructor
public class Deal {
    @OneToMany(mappedBy = "deal", cascade = CascadeType.REMOVE)
    private Set<Commit> commits = new LinkedHashSet<Commit>();
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @NonNull
    private String name;
    @NonNull
    @Column(columnDefinition = "DATE")
    private LocalDate date;
    @NonNull
    private Integer profit;
}