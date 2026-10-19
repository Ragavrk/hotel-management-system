package rithik.hotel_management_system.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import rithik.hotel_management_system.model1.Booking;
import java.time.LocalDate;

import java.util.*;

public interface BookingRepository extends JpaRepository<Booking , Long> {
	
	boolean existsByRoomIdAndBookingStatusAndCheckInLessThanAndCheckOutGreaterThan(
			Long roomId,
			Booking.BookingStatus bookingStatus,
			LocalDate checkOut,
			LocalDate checkIn
			);
	
	List<Booking> findByUserId(Long userId);
}
