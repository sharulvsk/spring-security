package com.example.SpringProject.Repository;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import com.example.SpringProject.Model.Users;

public interface UserRepository extends JpaRepository<Users,Integer>{
	Optional<Users> findByUsername(String username);
}
