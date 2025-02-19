package org.example.consegna_cartelloni.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;



@Repository
public interface ImpiantiRepository extends JpaRepository<Impianto, Integer> {
    @Query("SELECT p.nome FROM Palinsesto p JOIN Impianto i ON p.id = i.palinsesto WHERE i.id = :impiantoId")
    String findPalinsestoNomeByImpiantoId(@Param("impiantoId") Integer impiantoId);
}