package com.slobodanbabic.socialnetwork.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.slobodanbabic.socialnetwork.dto.UserCommentI;
import com.slobodanbabic.socialnetwork.entity.Comment;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Integer> {

	@Query("SELECT c FROM Post p join p.comments c WHERE p.id = :postId")
	public List<Comment> findCommentByPostId(@Param("postId") Integer postId);

	public int countByPostId(int postId);

	@Query(value = "SELECT u.id, first_name as firstName, last_name as lastName, email, COUNT(*) total FROM user u, comment c WHERE c.user_id = u.id GROUP BY u.id, u.first_name, u.last_name,u.email ORDER BY count(*) DESC LIMIT 10", nativeQuery = true)
	public List<UserCommentI> findTenUserCommentTheMost();

}
