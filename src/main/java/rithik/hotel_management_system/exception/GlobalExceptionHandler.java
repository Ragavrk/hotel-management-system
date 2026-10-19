package rithik.hotel_management_system.exception;

import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import org.springframework.dao.DataIntegrityViolationException;


@RestControllerAdvice
public class GlobalExceptionHandler {
	
	@ExceptionHandler(RoomAlreadyBookedException.class)
	public ResponseEntity <String> handleRoomAlreadyBooked(RoomAlreadyBookedException ex) {
		return new ResponseEntity<>(ex.getMessage(),HttpStatus.CONFLICT);
		
	}
	
	@ExceptionHandler(InvalidBookingException.class)
	public ResponseEntity <String> handleInvalidBooking(InvalidBookingException ex){
		return new ResponseEntity<> (ex.getMessage(), HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<String> handleResourceNotFound(ResourceNotFoundException ex){
		return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public  ResponseEntity<String> handleValidationErrors(
			MethodArgumentNotValidException ex){
		String errors = ex.getBindingResult()
				.getFieldErrors()
				.stream()
				.map(error -> error.getField()+":"
						+ error.getDefaultMessage())
				.collect(Collectors.joining(", "));
		
		return new ResponseEntity<>(
				errors, HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(HttpMessageNotReadableException.class)
	public ResponseEntity<String> handleInvalidRequestBody(
			HttpMessageNotReadableException ex){
		
		return new ResponseEntity<>(
				"Invalid request body or unsupported field value",
				HttpStatus.BAD_REQUEST
				);
	}
	
	@ExceptionHandler(DataIntegrityViolationException.class)
	public ResponseEntity<String> handleDataIntegrityViolation(
	        DataIntegrityViolationException ex) {

	    return ResponseEntity
	            .status(HttpStatus.CONFLICT)
	            .body("Cannot delete this room because it has existing bookings.");
	}
}
