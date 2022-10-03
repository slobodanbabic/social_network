package com.slobodanbabic.socialnetwork.service.impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.slobodanbabic.socialnetwork.dto.CommentDTO;
import com.slobodanbabic.socialnetwork.dto.UserCommentI;
import com.slobodanbabic.socialnetwork.entity.Comment;
import com.slobodanbabic.socialnetwork.entity.Post;
import com.slobodanbabic.socialnetwork.maper.CommentMapper;
import com.slobodanbabic.socialnetwork.repository.CommentRepository;
import com.slobodanbabic.socialnetwork.repository.PostRepository;
import com.slobodanbabic.socialnetwork.service.CommentService;

@Service
public class CommentServiceImpl implements CommentService {

	@Autowired
	CommentMapper commentMapper;

	@Autowired
	CommentRepository commentRepository;

	@Autowired
	PostRepository postRepository;

	@Override
	public CommentDTO findOne(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<CommentDTO> findAll() {
		// TREBA URADITI PAGINACIJU ZA POSTOVE
		return commentRepository.findAll().stream().map(i -> commentMapper.transformToDTO(i))
				.collect(Collectors.toList());
	}

	@Override
	public CommentDTO save(CommentDTO dto) {
		Comment entity = commentMapper.transformToEntity(dto);
		Integer postId = dto.getPostId();
		Optional<Post> post = postRepository.findById(postId);
		if (!post.isPresent()) {
			throw new IllegalArgumentException("Post with the following id = " + postId + " is not found.");
		}		
		return commentMapper.transformToDTO(commentRepository.save(entity));

	}

	@Override
	public void remove(Integer id) throws IllegalArgumentException {
		commentRepository.deleteById(id);
	}

	public CommentDTO removeById(Integer id) {
		Optional<Comment> comment = commentRepository.findById(id);
		if (!comment.isPresent()) {
			throw new IllegalArgumentException("Comment with the following id = " + id + " is not found.");
		}
		remove(id);
		return commentMapper.transformToDTO(comment.get());
	}

	@Override
	public CommentDTO update(Integer id, CommentDTO dto) {
		Comment comment = commentRepository.getById(id);
		comment.setContent(dto.getContent());
		comment.setDateOfCreation(dto.getDateOfCreation());
		return commentMapper.transformToDTO(commentRepository.save(comment));
	}

	@Override
	public List<CommentDTO> findCommentByPostId(Integer postId) {
		if (!postRepository.findById(postId).isPresent()) {
			throw new IllegalArgumentException("Post with the following id = " + postId + ".");
		}
		List<Comment> comments = commentRepository.findCommentByPostId(postId);
		return commentMapper.transformToListOfDTO(comments);
	}

	@Override
	public int findCountByPostId(int postId) {
		return commentRepository.countByPostId(postId);
	}

	@Override
	public List<UserCommentI> getTenUserCommentTheMost() {
		return commentRepository.findTenUserCommentTheMost();
	}
}
