package com.slobodanbabic.socialnetwork.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.slobodanbabic.socialnetwork.dto.PostCountI;
import com.slobodanbabic.socialnetwork.entity.Category;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Integer> {

	@Query(value = "SELECT category, COUNT(p.id) total FROM category c, post p where p.category_id = c.id GROUP BY category ORDER BY COUNT(*)", nativeQuery = true)
	public List<PostCountI> countTotalPostsByCategory();

}
