package rithik.hotel_management_system.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import rithik.hotel_management_system.model1.User;
import java.util.Optional;



public interface UserRepository extends JpaRepository<User , Long>{
	
	Optional<User> findByEmail(String email);
}
