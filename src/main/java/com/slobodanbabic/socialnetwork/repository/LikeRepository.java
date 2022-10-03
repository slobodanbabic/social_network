package com.slobodanbabic.socialnetwork.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.slobodanbabic.socialnetwork.entity.PostLike;

@Repository
public interface LikeRepository extends JpaRepository<PostLike, Integer> {

	@Query("SELECT l FROM Post p join p.likes l WHERE p.id = :postId")
	public List<PostLike> findLikeByPostId(@Param("postId") Integer postId);

	public int countByPostId(int postId);

	@Query("SELECT count(l) FROM PostLike l WHERE post.id = :postId AND l.isLike = TRUE")
	public int countLikeByPostId(@Param("postId") Integer postId);

	@Query("SELECT count(l) FROM PostLike l WHERE post.id = :postId AND l.isLike = FALSE")
	public int countDislikeByPostId(@Param("postId") Integer postId);
}
