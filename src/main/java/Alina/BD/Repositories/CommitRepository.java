package Alina.BD.Repositories;

import Alina.BD.Models.Client;
import Alina.BD.Models.Commit;
import Alina.BD.Models.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CommitRepository extends JpaRepository<Commit, Long> {
    Iterable<Commit> findByEmployee(Employee employee);
    Iterable<Commit> findByClient(Client client);
}
