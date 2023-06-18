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
public class Employee {
    @OneToMany(mappedBy = "employee", cascade = CascadeType.REMOVE)
    private Set<Pays> Pays = new LinkedHashSet<Pays>();
    @OneToMany(mappedBy = "employee", cascade = CascadeType.REMOVE)
    private Set<Commit> commits = new LinkedHashSet<Commit>();
    @OneToMany(mappedBy = "employee", cascade = CascadeType.REMOVE)
    private Set<Client> clients = new LinkedHashSet<Client>();
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @NonNull
    private String full_name;
    @NonNull
    @ManyToOne
    @JoinColumn(name = "fk_post_id")
    private Post post;
}
