package com.example.demo.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.demo.model.User;

@Repository
public interface UserRepository extends JpaRepository<User,Integer> {
	/*@Query(value ="select p.name from users e join personnel p on e.id_personnel=p.id_p",nativeQuery = true)
	List<User>find_id_user();*/
	@Query(value ="select * from users e where e.username=:username",nativeQuery = true)
	User find_username(@Param("username") String username);
}
