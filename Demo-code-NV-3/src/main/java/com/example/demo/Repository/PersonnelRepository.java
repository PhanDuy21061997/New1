package com.example.demo.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.demo.domain.ApiResponse;
import com.example.demo.model.Personnel;

@Repository
public interface PersonnelRepository extends JpaRepository<Personnel, Integer> {

	@Query(value = "select * from users e join personnel p on e.id_personnel=p.id_p where e.username=:username and e.passwork=:passwork", nativeQuery = true)
	Personnel login(@Param("username") String username, @Param("passwork") String passwork);

	@Query(value = "select * from personnel p where p.id_manage_p=:id_manage_p", nativeQuery = true)
	List<Personnel> find_manage_p(@Param("id_manage_p") int id_manage_p);

	@Query(value = "select * from users e join personnel p on e.id_personnel=p.id_p where e.username=:username and e.passwork=:passwork", nativeQuery = true)
	ApiResponse loginapi(@Param("username") String username, @Param("passwork") String passwork);

	@Query(value = "select * from users e join personnel p on e.id_personnel=p.id_p where e.username=:username ", nativeQuery = true)
	Personnel findby_id(@Param("username") String username);

	@Query(value = "select * from personnel p where (  p.name  LIKE  %:name% or  p.address   LIKE  %:name% ) ", nativeQuery = true)
	List<Personnel> Seach_p(@Param("name") String name);

	@Query(value = "select * from personnel LIMIT :someLimit OFFSET :someOffset", nativeQuery = true)
	List<Personnel> Paging(@Param("someLimit") int someLimit, @Param("someOffset") int someOffset);

	@Query(value = "select count(*) from personnel ", nativeQuery = true)
	int totalNumber();

}
