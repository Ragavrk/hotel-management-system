package rithik.hotel_management_system.service;

import org.springframework.stereotype.Service;

import rithik.hotel_management_system.model1.Room;
import rithik.hotel_management_system.repository.RoomRepository;

import java.util.List;

import rithik.hotel_management_system.exception.ResourceNotFoundException;

@Service
public class RoomService {
	
	private final RoomRepository roomRepository;
	
	public RoomService(RoomRepository roomRepository) {
		this.roomRepository = roomRepository;
	}
	
	public Room saveRoom(Room room) {
		return roomRepository.save(room);
	}
	
	public List<Room> getAllRooms(){
		return roomRepository.findAll();
	}
	
	public Room getRoomById(Long id) {
		return roomRepository.findById(id).orElseThrow(() ->
		new ResourceNotFoundException("Room not found with : "+id));
	}
	
	public Room updateRoom(Long id,Room updatedRoom) {
		Room room = roomRepository.findById(id)
				.orElseThrow(()-> new ResourceNotFoundException("Room not found with id : " + id));
		room.setRoomNumber(updatedRoom.getRoomNumber());
		room.setType(updatedRoom.getType());
		room.setPrice(updatedRoom.getPrice());
		room.setAvailable(updatedRoom.isAvailable());
		
		return roomRepository.save(room);
		
	}
	
	public void deleteRoom(Long id) {
		Room room = roomRepository.findById(id)
				.orElseThrow(() ->
				new ResourceNotFoundException(
						"Room not found with id :"+ id));
		roomRepository.delete(room);
	}
}
