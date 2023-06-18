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
public class Post {
    @OneToMany(mappedBy = "post", cascade = CascadeType.REMOVE)
    private Set<Employee> employees = new LinkedHashSet<Employee>();
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @NonNull
    private String name;
    @NonNull
    private Integer bet;
}
