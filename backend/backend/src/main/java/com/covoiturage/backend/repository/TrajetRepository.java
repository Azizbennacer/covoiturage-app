package com.covoiturage.backend.repository;

import com.covoiturage.backend.model.Trajet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface TrajetRepository extends JpaRepository<Trajet, Long> {
    List<Trajet> findByConducteurEmail(String email);
    @Query("SELECT t FROM Trajet t WHERE t.villeDepart = :villeDepart AND t.villeArrivee = :villeArrivee AND FUNCTION('DATE', t.dateTrajet) = :date")
    List<Trajet> findByVilleDepartAndVilleArriveeAndDate(
            @Param("villeDepart") String villeDepart,
            @Param("villeArrivee") String villeArrivee,
            @Param("date") LocalDate date
    );

}
