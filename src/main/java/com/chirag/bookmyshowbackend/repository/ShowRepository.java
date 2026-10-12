package com.chirag.bookmyshowbackend.repository;

import com.chirag.bookmyshowbackend.entity.Show;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;
import java.util.List;

public interface ShowRepository extends JpaRepository<Show,Long> {

    boolean existsByMovieIdAndTheatreIdAndStartAt(Long movieId, Long theatreId, LocalDateTime startAt);

    @Query("select s from Show s join fetch s.movie m join fetch s.theatre t where s.active = true and m.active = true and t.city = :city and s.startAt >=:from and s.endAt <= :to order by s.startAt")
    List<Show> findActiveShows(String city, LocalDateTime from, LocalDateTime to);
}
