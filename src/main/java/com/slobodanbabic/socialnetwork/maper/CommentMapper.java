package com.slobodanbabic.socialnetwork.maper;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.slobodanbabic.socialnetwork.dto.CommentDTO;
import com.slobodanbabic.socialnetwork.entity.Comment;
import com.slobodanbabic.socialnetwork.entity.Post;
import com.slobodanbabic.socialnetwork.repository.PostRepository;
import com.slobodanbabic.socialnetwork.repository.UserRepository;

@Component
public class CommentMapper {

	@Autowired
	private PostRepository postRepository;
	@Autowired
	private UserRepository userRepository;

	public CommentDTO transformToDTO(Comment comment) {
		CommentDTO commentDTO = new CommentDTO();
		BeanUtils.copyProperties(comment, commentDTO);
		commentDTO.setUserId(comment.getUser().getId());		
		commentDTO.setPostId(comment.getPost().getId());		
		return commentDTO;
	}

	public Comment transformToEntity(CommentDTO commentDTO) {
		Comment comment = new Comment();
		BeanUtils.copyProperties(commentDTO,comment);		
		//pronadjemo usera na osvnou id-a i setujemo usera u entity komentar
		comment.setUser(userRepository.findById(commentDTO.getUserId()).get());		
		Post p = postRepository.findById(commentDTO.getPostId()).get();
		//u entity post u njegovu listo komentara dodajemo trenutni komenar
		//isto tako metod u entity comment dodaje tekuci post
		//dvosmerna veza
		p.addComment(comment);		
		return comment;
	}

	public List<CommentDTO> transformToListOfDTO(List<Comment> comments) {
		List<CommentDTO> commentsDTO = new ArrayList<>(comments.size());
		for (Comment comment : comments) {
			commentsDTO.add(transformToDTO(comment));
		}
		return commentsDTO;
	}
}
