package com.slobodanbabic.socialnetwork.service.impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.slobodanbabic.socialnetwork.dto.LikeDTO;
import com.slobodanbabic.socialnetwork.entity.PostLike;
import com.slobodanbabic.socialnetwork.entity.Post;
import com.slobodanbabic.socialnetwork.entity.User;
import com.slobodanbabic.socialnetwork.maper.LikeMapper;
import com.slobodanbabic.socialnetwork.repository.LikeRepository;
import com.slobodanbabic.socialnetwork.repository.PostRepository;
import com.slobodanbabic.socialnetwork.repository.UserRepository;
import com.slobodanbabic.socialnetwork.service.LikeService;

@Service
public class LikeServiceImpl implements LikeService {

	@Autowired
	LikeMapper likeMapper;
	@Autowired
	PostRepository postRepository;

	@Autowired
	UserRepository userRepository;

	@Autowired
	LikeRepository likeRepository;

	@Override
	public LikeDTO findOne(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<LikeDTO> findAll() {
		return likeRepository.findAll().stream().map(i -> likeMapper.transformToDTO(i)).collect(Collectors.toList());
	}

	@Override
	public LikeDTO save(LikeDTO dto) {
		PostLike entity = likeMapper.transformToEntity(dto);
		Integer postId = dto.getPostId();
		Optional<Post> post = postRepository.findById(postId);
		if (!post.isPresent()) {
			throw new IllegalArgumentException("Post with the following id = " + postId + " is not found.");
		}
		Integer userId = dto.getUserId();
		Optional<User> user = userRepository.findById(userId);
		if (!user.isPresent()) {
			throw new IllegalArgumentException("User with the following id = " + userId + " is not found.");
		}
		entity.setPost(post.get());
		entity.setUser(user.get());
		return likeMapper.transformToDTO(likeRepository.save(entity));
	}

	@Override
	public void remove(Integer id) throws IllegalArgumentException {
		likeRepository.deleteById(id);
	}

	@Override
	public LikeDTO update(Integer id, LikeDTO dto) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<LikeDTO> findLikesByPostId(Integer postId) {
		if (!postRepository.findById(postId).isPresent()) {
			throw new IllegalArgumentException("Post with the following id = " + postId + ".");
		}
		List<PostLike> likes = likeRepository.findLikeByPostId(postId);
		return likeMapper.transformToListOfDTO(likes);
	}

	@Override
	public int findCountLikeByPostId(int postId) {
		return likeRepository.countLikeByPostId(postId);
	}

	@Override
	public int findCountDislikeByPostId(int postId) {
		return likeRepository.countDislikeByPostId(postId);
	}

}
