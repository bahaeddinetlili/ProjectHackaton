package com.example.Hackaton;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface HackatonRepository extends JpaRepository<Hackaton,Integer> {
    @Query("SELECT h FROM Hackaton h LEFT JOIN FETCH h.sponsors WHERE h.idHackaton = :hackatonId")
    Optional<Hackaton> findHackatonWithSponsors(@Param("hackatonId") Integer hackatonId);


}
