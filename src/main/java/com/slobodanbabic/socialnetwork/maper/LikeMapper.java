package com.slobodanbabic.socialnetwork.maper;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.slobodanbabic.socialnetwork.dto.LikeDTO;
import com.slobodanbabic.socialnetwork.entity.Post;
import com.slobodanbabic.socialnetwork.entity.PostLike;
import com.slobodanbabic.socialnetwork.entity.User;
import com.slobodanbabic.socialnetwork.repository.PostRepository;
import com.slobodanbabic.socialnetwork.repository.UserRepository;

@Component
public class LikeMapper {

	@Autowired
	UserRepository userRepository;

	@Autowired
	PostRepository postRepository;

	public LikeDTO transformToDTO(PostLike like) {
		LikeDTO likeDTO = new LikeDTO();
		likeDTO.setId(like.getId());
		likeDTO.setUserId(like.getUser().getId());
		likeDTO.setPostId(like.getPost().getId());
		likeDTO.setLike(like.isLike());
		return likeDTO;
	}

	public PostLike transformToEntity(LikeDTO likeDTO) {
		PostLike like = new PostLike();
	    User user = userRepository.findById(likeDTO.getUserId()).get();
	    Post post = postRepository.findById(likeDTO.getPostId()).get();
	    like.setLike(likeDTO.isLike());
	    like.setUser(user);
	    like.setPost(post);
		return like;
	}

	public List<LikeDTO> transformToListOfDTO(List<PostLike> likes) {
		List<LikeDTO> likesDTO = new ArrayList<>(likes.size());
		for (PostLike like : likes) {
			likesDTO.add(transformToDTO(like));
		}
		return likesDTO;
	}
}
