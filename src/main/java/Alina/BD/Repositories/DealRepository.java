package Alina.BD.Repositories;

import Alina.BD.Models.Deal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;

@Repository
public interface DealRepository extends JpaRepository<Deal, Long> {
    Iterable<Deal> findByDateBetween(LocalDate minDate, LocalDate maxDate);
}
