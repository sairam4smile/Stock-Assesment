package com.share.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.share.model.Userdeatils;
import java.lang.String;
import java.util.List;

@Repository
public interface UserDeatilsRepository extends JpaRepository<Userdeatils, Long> {
	
	public List<Userdeatils> findByUserName(String username);
	
	public List<Userdeatils> findByUserNameAndPassword(String userName,String password);

}
