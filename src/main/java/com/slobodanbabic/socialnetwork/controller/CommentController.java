package com.slobodanbabic.socialnetwork.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.slobodanbabic.socialnetwork.dto.CommentDTO;
import com.slobodanbabic.socialnetwork.dto.UserCommentI;
import com.slobodanbabic.socialnetwork.service.impl.CommentServiceImpl;

@RestController
@RequestMapping("/post/comments")
public class CommentController {

	@Autowired
	CommentServiceImpl commentServiceImpl;

	@PostMapping(value = "/addComment", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public CommentDTO saveComment(@RequestBody CommentDTO commentDTO) {
		return commentServiceImpl.save(commentDTO);
	}

	@DeleteMapping(value = "/delete/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public CommentDTO deletePost(@PathVariable("id") Integer id) {
		return commentServiceImpl.removeById(id);
	}

	@GetMapping(value = "/getAll", produces = MediaType.APPLICATION_JSON_VALUE)
	public List<CommentDTO> getAllComment() {
		return commentServiceImpl.findAll();
	}

	@GetMapping(value = "/getByPostId/{postId}", produces = MediaType.APPLICATION_JSON_VALUE)
	public List<CommentDTO> getCommentByPostId(@PathVariable("postId") Integer postId) {
		return commentServiceImpl.findCommentByPostId(postId);
	}

	@GetMapping(value = "/getCommentsCountByPostId/{postId}", produces = MediaType.APPLICATION_JSON_VALUE)
	public int comentsCountByPostId(@PathVariable("postId") Integer postId) {
		return commentServiceImpl.findCountByPostId(postId);
	}

	@GetMapping(value = "/getTenUserCommentTheMost", produces = MediaType.APPLICATION_JSON_VALUE)
	public List<UserCommentI> getTenUserComemntTheMost() {
		return commentServiceImpl.getTenUserCommentTheMost();
	}

	@PutMapping(value = "/update/{id}", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public CommentDTO updateComment(@PathVariable("id") Integer id, @RequestBody CommentDTO commentDTO) {
		return commentServiceImpl.update(id, commentDTO);
	}
}
