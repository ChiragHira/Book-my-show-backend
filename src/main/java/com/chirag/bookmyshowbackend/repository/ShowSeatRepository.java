package com.chirag.bookmyshowbackend.repository;

import com.chirag.bookmyshowbackend.entity.ShowSeat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ShowSeatRepository extends JpaRepository<ShowSeat,Long> {

    @Query("select s.seatLabel from ShowSeat s where s.show.id = :showId and s.reserved=false  order by s.id")
    List<String> findAvailableLabels(Long showId);

    @Query("select s from ShowSeat s where s.show.id = :showId and s.seatLabel IN :label")
    List<ShowSeat> findForUpdate(@Param("showId") Long showId, @Param("label") List<String> label);
}
