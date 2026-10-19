package rithik.hotel_management_system.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import rithik.hotel_management_system.model1.Room;

public interface RoomRepository extends JpaRepository<Room , Long> {

}
