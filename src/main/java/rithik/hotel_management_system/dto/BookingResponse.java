package rithik.hotel_management_system.dto;

import java.time.LocalDate;

import rithik.hotel_management_system.model1.Booking;



public class BookingResponse {
	
	private Long bookingId;
	
	private Long userId;
	
	private Long roomId;
	
	private LocalDate checkIn;
	
	private LocalDate checkOut;
	
	private int guests;
	
	private Booking.PaymentMode paymentMode;
	
	private Booking.PaymentStatus paymentStatus;
	
	private Booking.BookingStatus bookingStatus;
	
	private String roomNumber;
	
	private String roomType;
	
	private double roomPrice;

	public Long getBookingId() {
		return bookingId;
	}

	public void setBookingId(Long bookingId) {
		this.bookingId = bookingId;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public Long getRoomId() {
		return roomId;
	}

	public void setRoomId(Long roomId) {
		this.roomId = roomId;
	}

	public LocalDate getCheckIn() {
		return checkIn;
	}

	public void setCheckIn(LocalDate checkIn) {
		this.checkIn = checkIn;
	}

	public LocalDate getCheckOut() {
		return checkOut;
	}

	public void setCheckOut(LocalDate checkOut) {
		this.checkOut = checkOut;
	}

	public int getGuests() {
		return guests;
	}

	public void setGuests(int guests) {
		this.guests = guests;
	}

	public Booking.PaymentMode getPaymentMode() {
		return paymentMode;
	}

	public void setPaymentMode(Booking.PaymentMode paymentMode) {
		this.paymentMode = paymentMode;
	}

	public Booking.PaymentStatus getPaymentStatus() {
		return paymentStatus;
	}

	public void setPaymentStatus(Booking.PaymentStatus paymentStatus) {
		this.paymentStatus = paymentStatus;
	}

	public Booking.BookingStatus getBookingStatus() {
		return bookingStatus;
	}

	public void setBookingStatus(Booking.BookingStatus bookingStatus) {
		this.bookingStatus = bookingStatus;
	}

	public String getRoomNumber() {
		return roomNumber;
	}

	public void setRoomNumber(String roomNumber) {
		this.roomNumber = roomNumber;
	}

	public String getRoomType() {
		return roomType;
	}

	public void setRoomType(String roomType) {
		this.roomType = roomType;
	}

	public double getRoomPrice() {
		return roomPrice;
	}

	public void setRoomPrice(double roomPrice) {
		this.roomPrice = roomPrice;
	}
	
	
	
	
	
	
	
	
	
	

}
