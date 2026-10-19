package rithik.hotel_management_system.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import rithik.hotel_management_system.dto.BookingResponse;
import rithik.hotel_management_system.exception.InvalidBookingException;
import rithik.hotel_management_system.exception.ResourceNotFoundException;
import rithik.hotel_management_system.exception.RoomAlreadyBookedException;
import rithik.hotel_management_system.model1.Booking;
import rithik.hotel_management_system.model1.Room;
import rithik.hotel_management_system.model1.User;
import rithik.hotel_management_system.repository.BookingRepository;
import rithik.hotel_management_system.repository.RoomRepository;
import rithik.hotel_management_system.repository.UserRepository;

@Service
public class BookingService {
	private final BookingRepository bookingRepository;
	private final UserRepository userRepository;
	private final RoomRepository roomRepository;
	
	public BookingService(
			BookingRepository bookingRepository,
			UserRepository userRepository,
			RoomRepository roomRepository) {
			
			this.bookingRepository = bookingRepository;
			this.userRepository = userRepository;
			this.roomRepository = roomRepository;

}


public BookingResponse createBooking(
		Long roomId,
		LocalDate checkIn,
		LocalDate checkOut,
		int guests,
		Booking.PaymentMode paymentMode) {
	
		
	if(roomId == null) {
		throw new InvalidBookingException("Room ID is required");
	}
	
	if(checkIn == null || checkOut == null) {
		throw new InvalidBookingException(
				"Check-in and Check-out dates are required");
	}
	
	if(checkIn.isBefore(LocalDate.now())) {
		throw new InvalidBookingException(
				"Check-in date cannot be in the past");
	}
	if(!checkOut.isAfter(checkIn)) {
		throw new InvalidBookingException(
				"Check-out date must be after check-in date");
	}
	
	if(guests <=0) {
		throw new InvalidBookingException(
				"Number of Guests must be greater than 0");
	}
	Authentication authentication =
			SecurityContextHolder.getContext().getAuthentication();
	
	String email = authentication.getName();
	
	User user = userRepository.findByEmail(email)
			.orElseThrow(()-> new ResourceNotFoundException("User Not Found"));
	Room room = roomRepository.findById(roomId)
			.orElseThrow(()-> new ResourceNotFoundException("Room Not Found"));
	
	boolean alreadyBooked =
			 bookingRepository.existsByRoomIdAndBookingStatusAndCheckInLessThanAndCheckOutGreaterThan(
					roomId,
					Booking.BookingStatus.CONFIRMED,
					checkOut,
					checkIn
					);
	if(alreadyBooked) {
		throw new RoomAlreadyBookedException("Room is already booked for these dates");
	}
	
	Booking booking = new Booking();
	
	booking.setUser(user);
	booking.setRoom(room);
	booking.setCheckIn(checkIn);
	booking.setCheckOut(checkOut);
	booking.setGuests(guests);
	booking.setPaymentMode(paymentMode);
	booking.setPaymentStatus( Booking.PaymentStatus.PENDING);
	booking.setBookingStatus(Booking.BookingStatus.CONFIRMED);

	Booking savedBooking = bookingRepository.save(booking);
	
	BookingResponse response = new BookingResponse();
	
	response.setBookingId(savedBooking.getId());
	response.setUserId(savedBooking.getUser().getId());
	response.setRoomId(savedBooking.getRoom().getId());
	response.setCheckIn(savedBooking.getCheckIn());
	response.setCheckOut(savedBooking.getCheckOut());
	response.setGuests(savedBooking.getGuests());
	response.setRoomNumber(savedBooking.getRoom().getRoomNumber());
	response.setRoomType(savedBooking.getRoom().getType());
	response.setRoomPrice(savedBooking.getRoom().getPrice());
	response.setPaymentMode(savedBooking.getPaymentMode());
	response.setPaymentStatus(savedBooking.getPaymentStatus());
	response.setBookingStatus(savedBooking.getBookingStatus());
	
	
	return response;
	
}

public List <BookingResponse> getAllBookings(){
	
	List<Booking> bookings = bookingRepository.findAll();
	
	List<BookingResponse> responses = new ArrayList<>();
	
	for(Booking booking : bookings) {
	
		BookingResponse response = new  BookingResponse();
		response.setBookingId(booking.getId());
		response.setUserId(booking.getUser().getId());
		response.setRoomId(booking.getRoom().getId());
		response.setCheckIn(booking.getCheckIn());
		response.setCheckOut(booking.getCheckOut());
		response.setGuests(booking.getGuests());
		response.setRoomNumber(booking.getRoom().getRoomNumber());
		response.setRoomType(booking.getRoom().getType());
		response.setRoomPrice(booking.getRoom().getPrice());
		response.setPaymentMode(booking.getPaymentMode());
		response.setPaymentStatus(booking.getPaymentStatus());
		response.setBookingStatus(booking.getBookingStatus());
	responses.add(response);

	}
	return responses;




}


public List<BookingResponse> getBookingsByUser(Long userId) {

    List<Booking> bookings = bookingRepository.findByUserId(userId);

    List<BookingResponse> responses = new ArrayList<>();

    for (Booking booking : bookings) {

        BookingResponse response = new BookingResponse();

        response.setBookingId(booking.getId());

        response.setUserId(booking.getUser().getId());

        response.setRoomId(booking.getRoom().getId());

        response.setCheckIn(booking.getCheckIn());

        response.setCheckOut(booking.getCheckOut());

        response.setGuests(booking.getGuests());

        response.setRoomNumber(booking.getRoom().getRoomNumber());

        response.setRoomType(booking.getRoom().getType());

        response.setRoomPrice(booking.getRoom().getPrice());

        response.setPaymentMode(booking.getPaymentMode());

        response.setPaymentStatus(booking.getPaymentStatus());

        response.setBookingStatus(booking.getBookingStatus());

        responses.add(response);
    }

    return responses;
}


public BookingResponse getBookingById(Long id) {

    Booking booking = bookingRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException(
                    "Booking not found with id: " + id));
    
    Authentication authentication = 
    		SecurityContextHolder.getContext().getAuthentication();
    
    String email = authentication.getName();
    
    User user = userRepository.findByEmail(email)
    		.orElseThrow(()-> new ResourceNotFoundException("User not found"));
    
    boolean isAdmin = authentication.getAuthorities().stream()
    		.anyMatch(authority -> authority.getAuthority().equals("ROLE_ADMIN"));
    
    if(!isAdmin && booking.getUser().getId() != user.getId()){
    	throw new ResourceNotFoundException("Booking not found");
    }
    

    BookingResponse response = new BookingResponse();
    
    response.setBookingId(booking.getId());
    response.setUserId(booking.getUser().getId());
    response.setRoomId(booking.getRoom().getId());
    response.setCheckIn(booking.getCheckIn());
    response.setCheckOut(booking.getCheckOut());
    response.setGuests(booking.getGuests());
    response.setRoomNumber(booking.getRoom().getRoomNumber());
    response.setRoomType(booking.getRoom().getType());
    response.setRoomPrice(booking.getRoom().getPrice());
    response.setPaymentMode(booking.getPaymentMode());
    response.setPaymentStatus(booking.getPaymentStatus());
    response.setBookingStatus(booking.getBookingStatus());

    return response;
}

public List<BookingResponse> getMyBookings(){
	
	String email = SecurityContextHolder.getContext()
			.getAuthentication().getName();
	
	User user = userRepository.findByEmail(email)
	.orElseThrow(() -> new ResourceNotFoundException("User not found"));
	
	return getBookingsByUser(user.getId());
}



public BookingResponse cancelBooking(Long id) {

    Booking booking = bookingRepository.findById(id)
            .orElseThrow(() ->
                    new ResourceNotFoundException("Booking not found: "+id));

    Authentication authentication =
            SecurityContextHolder.getContext().getAuthentication();

    String email = authentication.getName();

    User user = userRepository.findByEmail(email)
            .orElseThrow(() ->
                    new ResourceNotFoundException("User not found"));

    boolean isAdmin = authentication.getAuthorities().stream()
            .anyMatch(authority ->
                    authority.getAuthority().equals("ROLE_ADMIN"));

    if (!isAdmin && booking.getUser().getId() != user.getId()) {
        throw new ResourceNotFoundException("You are not allowed to cancel this booking");
    }

    if (booking.getBookingStatus() == Booking.BookingStatus.CANCELLED) {
        throw new InvalidBookingException("Booking is already cancelled");
    }

    if (booking.getBookingStatus() == Booking.BookingStatus.COMPLETED) {
        throw new InvalidBookingException("Completed booking cannot be cancelled");
    }

    booking.setBookingStatus(Booking.BookingStatus.CANCELLED);

    Booking savedBooking = bookingRepository.save(booking);

    BookingResponse response = new BookingResponse();

    response.setBookingId(savedBooking.getId());
    response.setUserId(savedBooking.getUser().getId());
    response.setRoomId(savedBooking.getRoom().getId());
    response.setCheckIn(savedBooking.getCheckIn());
    response.setCheckOut(savedBooking.getCheckOut());
    response.setGuests(savedBooking.getGuests());
    response.setRoomNumber(savedBooking.getRoom().getRoomNumber());
    response.setRoomType(savedBooking.getRoom().getType());
    response.setRoomPrice(savedBooking.getRoom().getPrice());
    response.setPaymentMode(savedBooking.getPaymentMode());
    response.setPaymentStatus(savedBooking.getPaymentStatus());
    response.setBookingStatus(savedBooking.getBookingStatus());

    return response;
}

}
