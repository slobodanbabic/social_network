package com.slobodanbabic.socialnetwork.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.slobodanbabic.socialnetwork.dto.PostPerMonth;
import com.slobodanbabic.socialnetwork.entity.Post;

@Repository
public interface PostRepository extends JpaRepository<Post, Integer> {

	public Page<Post> findAll(Pageable pageable);

	public List<Post> findAllByOrderByDateOfCreationDesc();

	public List<Post> findByCategoryIdOrderByDateOfCreationDesc(int categoryId);
	
	public Page<Post> findByCategoryId(int categoryId,Pageable pageable);

	@Query(value = "SELECT date_format(date_of_creation,'%Y-%m') month_year, count(*) total FROM post p \n"
			+ "WHERE p.date_of_creation BETWEEN :fromDate AND :toDate GROUP BY date_format(date_of_creation,'%Y-%m') ORDER BY date_format(date_of_creation,'%Y-%m');", nativeQuery = true)
	public List<PostPerMonth> findPostsCountPerMonth(@Param(value = "fromDate") String fromDate,
			@Param(value = "toDate") String toDate);

}
