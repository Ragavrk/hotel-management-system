package rithik.hotel_management_system.exception;

public class RoomAlreadyBookedException extends RuntimeException {
	public RoomAlreadyBookedException(String message) {
		super(message);
	}

}
