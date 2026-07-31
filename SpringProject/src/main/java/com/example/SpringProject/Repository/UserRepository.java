package com.example.SpringProject.Repository;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.SpringProject.Model.Users;

@Repository
public interface UserRepository extends JpaRepository<Users,Integer>{
	Optional<Users> findByUsername(String username);
}
