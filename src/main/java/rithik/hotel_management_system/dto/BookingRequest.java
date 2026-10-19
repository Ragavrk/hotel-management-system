package rithik.hotel_management_system.dto;

import java.time.LocalDate;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import rithik.hotel_management_system.model1.Booking;

import jakarta.validation.constraints.NotNull;


public class BookingRequest {
	
	@NotNull(message ="Room ID is required")
	private Long roomId;
	
	@NotNull(message ="Check-in date is required")
	private LocalDate checkIn;
	
	@NotNull(message ="Check-out date is required")
	private LocalDate checkOut;
	
	@Min(value=1,message="Atleast 1 guests is required")
	@Max(value=4,message="Maximum 4 guests allowed per booking")
	private int guests;
	
	@NotNull(message = "Payment mode is required")
	private Booking.PaymentMode paymentMode;


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
	
	
	
	
}
