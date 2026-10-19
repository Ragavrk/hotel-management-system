package rithik.hotel_management_system.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import  org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import rithik.hotel_management_system.dto.BookingRequest;
import rithik.hotel_management_system.dto.BookingResponse;
import rithik.hotel_management_system.service.BookingService;


@RestController
public class BookingController{
	
	private BookingService bookingService;
	
	public BookingController(BookingService bookingService) {
		this.bookingService = bookingService;
	}
	
	@PostMapping("/bookings")
	public BookingResponse createBooking(
			@Valid @RequestBody BookingRequest request){
		return bookingService.createBooking(
				request.getRoomId(),
				request.getCheckIn(),
				request.getCheckOut(),
				request.getGuests(),
				request.getPaymentMode()
				);
	}
	@GetMapping("/bookings")
	public List<BookingResponse> getAllBookings(){
		return bookingService.getAllBookings();
	}
	
	@GetMapping("/bookings/{id}")
	public BookingResponse getBookingById(@PathVariable Long id) {
		return bookingService.getBookingById(id);
	}
	
	@GetMapping("/bookings/user/{userId}")
	public List<BookingResponse> getBookingsByUser(@PathVariable Long userId){
		return bookingService.getBookingsByUser(userId);
	}
	
	@GetMapping("/bookings/my")
	public List<BookingResponse> getMyBookings(){
	return bookingService.getMyBookings();
	}
	
	@PatchMapping("/bookings/{id}/cancel")
	public BookingResponse cancelBooking(@PathVariable Long id) {
	    return bookingService.cancelBooking(id);
	}
}

