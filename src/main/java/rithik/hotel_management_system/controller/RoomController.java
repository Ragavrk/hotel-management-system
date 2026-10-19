package rithik.hotel_management_system.controller;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;

import rithik.hotel_management_system.model1.Room;
import rithik.hotel_management_system.service.RoomService;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;

import jakarta.validation.Valid;



@RestController
public class RoomController {
	
	private final RoomService roomService;
	
	public RoomController(RoomService roomService) {
		this.roomService = roomService;
	}
	
	@PostMapping("/rooms")
	public Room createRoom(@Valid @RequestBody Room room) {
		return roomService.saveRoom(room);
	}
	
	@GetMapping("/rooms")
	public List<Room> getAllRooms(){
		return roomService.getAllRooms();
		
	}
	
	@GetMapping("/rooms/{id}")
	public Room getRoomById(@PathVariable Long id) {
		return roomService.getRoomById(id);
	}
	
	@PutMapping("/rooms/{id}")
	public Room updateRoomm(
			@PathVariable Long id,
			@Valid @RequestBody Room room) {
		return roomService.updateRoom(id, room);
	}
	
	@DeleteMapping("/rooms/{id}")
	public String deleteRoom(@PathVariable Long id) {
		roomService.deleteRoom(id);
		
		return "Room deleted successfully";
	}

}
