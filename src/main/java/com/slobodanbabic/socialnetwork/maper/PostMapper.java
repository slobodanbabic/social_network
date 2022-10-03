package com.slobodanbabic.socialnetwork.maper;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.slobodanbabic.socialnetwork.dto.PostDTO;
import com.slobodanbabic.socialnetwork.entity.Post;
import com.slobodanbabic.socialnetwork.repository.CategoryRepository;
import com.slobodanbabic.socialnetwork.repository.UserRepository;

@Component
public class PostMapper {

	@Autowired
	private UserRepository userRepository;
	@Autowired
	private CategoryRepository categoryRepository;

	public PostDTO transformToDTO(Post post) {
		PostDTO postDTO = new PostDTO();
		postDTO.setContent(post.getContent());	
		postDTO.setDateOfCreation(post.getDateOfCreation());
		postDTO.setId(post.getId());
		//if(post.getImg_url() != null)
			postDTO.setImg_url(post.getImg_url());		
		postDTO.setUserId(post.getUser().getId());
		postDTO.setCategoryId(post.getCategory().getId());		
		return postDTO;
	}

	public Post transformToEntity(PostDTO postDTO) {
		Post post = new Post();
		post.setContent(postDTO.getContent());
		post.setDateOfCreation(postDTO.getDateOfCreation());
		//if(postDTO.getImg_url() != null)
			post.setImg_url(postDTO.getImg_url());
		// pronadjemo usera na osvnou id-a i setujemo usera u entity post
		post.setUser(userRepository.findById(postDTO.getUserId()).get());
		post.setCategory(categoryRepository.findById(postDTO.getCategoryId()).get());		
		return post;
	}

	public List<PostDTO> transformToListOfDTO(List<Post> posts) {
		List<PostDTO> postsDTO = new ArrayList<>(posts.size());
		for (Post post : posts) {
			postsDTO.add(transformToDTO(post));
		}
		return postsDTO;
	}
}
