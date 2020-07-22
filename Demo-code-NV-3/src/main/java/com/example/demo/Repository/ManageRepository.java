package com.example.demo.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.demo.model.Manage;

@Repository
public interface ManageRepository extends JpaRepository<Manage, Integer> {

	@Query(value = "select * from manage_id m where m.id_m=:id_m", nativeQuery = true)
	Boolean check_id_manage(@Param("id_m") int id_m);

	@Query(value = "SELECT * FROM capnhat_9.manage_id m join capnhat_9.personnel p on m.id_personnel=p.id_manage_p where p.id_manage_p=:id_manage_p;", nativeQuery = true)
	Boolean List_personnel(@Param("id_manage_p") int id_manage_p);

}
