package org.example.consegna_cartelloni.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface PalinsestiRepository extends JpaRepository<Palinsesto, Integer> {

    @Query("SELECT i.palinsesto FROM Impianto i WHERE i.id = :impiantoId")
    Integer getPalinsesto(@Param("impiantoId") Integer impiantoId);
}
