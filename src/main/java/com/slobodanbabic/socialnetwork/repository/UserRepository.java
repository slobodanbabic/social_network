package com.slobodanbabic.socialnetwork.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.slobodanbabic.socialnetwork.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
	public User findByEmail(String email);

	@Query(value = "SELECT * FROM user u WHERE(current_timestamp() > u.ban);", nativeQuery = true)
	public List<User> getBannedUser();

	public Page<User> findAll(Pageable pageable);
}
