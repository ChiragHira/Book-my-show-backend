package com.chirag.bookmyshowbackend.service;

import com.chirag.bookmyshowbackend.dto.BookingResponse;
import com.chirag.bookmyshowbackend.dto.CreateBookingRequest;
import com.chirag.bookmyshowbackend.entity.Booking;
import com.chirag.bookmyshowbackend.entity.BookingStatus;
import com.chirag.bookmyshowbackend.entity.Show;
import com.chirag.bookmyshowbackend.entity.ShowSeat;
import com.chirag.bookmyshowbackend.repository.BookingRepository;
import com.chirag.bookmyshowbackend.repository.CustomerRepository;
import com.chirag.bookmyshowbackend.repository.ShowRepository;
import com.chirag.bookmyshowbackend.repository.ShowSeatRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.math.BigDecimal;
import java.util.List;
import java.util.Locale;

@Service
public class BookingService {

    private final BookingRepository bookingRepository;

    private final ShowRepository showRepository;

    private final ShowSeatRepository showSeatRepository;

    private final CustomerRepository customerRepository;

    @Autowired
    public BookingService(BookingRepository bookingRepository, ShowRepository showRepository, ShowSeatRepository showSeatRepository, CustomerRepository customerRepository) {
        this.bookingRepository = bookingRepository;
        this.showRepository = showRepository;
        this.showSeatRepository = showSeatRepository;
        this.customerRepository = customerRepository;
    }

    @Transactional
    public BookingResponse book(Long showId , CreateBookingRequest request){
        Show show = showRepository.findById(showId).orElseThrow(()->new ResourceNotFoundException("Show not found"));

        var customer = customerRepository.findById(request.profileId()).orElseThrow(()->new ResourceNotFoundException("Customer not found"));

        List<String> labels = request.seatLabels().stream()
                .map(label -> label.trim().toUpperCase(Locale.ROOT)).toList();

        if(labels.stream().distinct().count() != labels.size()){
            throw new SeatUnavailableException("Duplicate Seat Labels are not allowed");
        }

        List<ShowSeat> seats = showSeatRepository.findForUpdate(showId,labels);

        if (seats.size()!= labels.size() || seats.stream().anyMatch(ShowSeat::isReserved)){
            throw new SeatUnavailableException("Some of the Seats are already booked");
        }

        seats.forEach(ShowSeat::reserve);
        show.reserveSeats(labels.size());

        BigDecimal totalPrice = show.getTicketPrice().multiply(new BigDecimal(labels.size()));
        Booking booking = bookingRepository.save(new Booking(show,customer,totalPrice,labels));
        return BookingResponse.from(booking);

    }

    @Transactional
    public BookingResponse find(Long bookingId){
        Booking booking = bookingRepository.findById(bookingId).orElseThrow(()->new ResourceNotFoundException("Booking not found"));
        return BookingResponse.from(booking);
    }

    @Transactional
    public List<BookingResponse> findByProfileId(Long profileId){

        if (!customerRepository.existsById(profileId)){
            throw new ResourceNotFoundException("Profile not found");
        }

        return bookingRepository.findByCustomerIdOrderByBookedAtDesc(profileId).stream()
                .map(BookingResponse::from).toList();
    }

    @Transactional
    public BookingResponse cancel(Long bookingId,Long profileId){

        Booking booking = bookingRepository.findByIdAndCustomerId(bookingId,profileId).orElseThrow(()->new ResourceNotFoundException("Booking not found"));

        if (booking.getStatus() == BookingStatus.CONFIRMED){

            List<ShowSeat> seats = showSeatRepository.findForUpdate(booking.getShow().getId(),booking.getSeatLabels());
            seats.forEach(ShowSeat::release);
            booking.getShow().releaseSeats(booking.getSeatLabels().size());
            booking.cancel();
        }

        return BookingResponse.from(booking);
    }
}
