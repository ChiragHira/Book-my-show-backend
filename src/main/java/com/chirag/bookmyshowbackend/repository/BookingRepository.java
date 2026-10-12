package com.chirag.bookmyshowbackend.repository;

import com.chirag.bookmyshowbackend.entity.Booking;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface BookingRepository extends JpaRepository <Booking,Long>{

    List<Booking> findByCustomerPhoneOrderByBookedAtDesc(String phone);

    Optional<Booking> findByIdAndCustomerPhone(Long id,String phone);

    List<Booking> findByCustomerIdOrderByBookedAtDesc(Long customerId);

    Optional<Booking> findByIdAndCustomerId(Long id,Long customerId);

}
