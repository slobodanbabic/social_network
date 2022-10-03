package com.slobodanbabic.socialnetwork.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.slobodanbabic.socialnetwork.dto.LikeDTO;
import com.slobodanbabic.socialnetwork.service.impl.LikeServiceImpl;

@RestController
@RequestMapping("/post/likes")
public class LikeController {

	@Autowired
	LikeServiceImpl likeServiceImpl;
	
	@PostMapping(value = "/addLike", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public LikeDTO saveLike(@RequestBody LikeDTO likeDTO) {				
		return likeServiceImpl.save(likeDTO);
	}
	
	@GetMapping(value = "/getByPostId/{postId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<LikeDTO> getLikesByPostId(@PathVariable("postId") Integer postId) {
		return likeServiceImpl.findLikesByPostId(postId);		
    }
	
	@GetMapping(value = "/countLikeByPostId/{postId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public int countLikeByPostId(@PathVariable("postId") Integer postId) {
        return likeServiceImpl.findCountLikeByPostId(postId);
    }
	
	@GetMapping(value = "/countDislikeByPostId/{postId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public int countDislikeByPostId(@PathVariable("postId") Integer postId) {
        return likeServiceImpl.findCountDislikeByPostId(postId);
    }
}
