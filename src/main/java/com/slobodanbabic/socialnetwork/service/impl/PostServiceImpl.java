package com.slobodanbabic.socialnetwork.service.impl;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;
import org.springframework.web.multipart.MultipartFile;

import com.slobodanbabic.socialnetwork.dto.PostDTO;
import com.slobodanbabic.socialnetwork.dto.PostPerMonth;
import com.slobodanbabic.socialnetwork.entity.Post;
import com.slobodanbabic.socialnetwork.entity.User;
import com.slobodanbabic.socialnetwork.maper.PostMapper;
import com.slobodanbabic.socialnetwork.repository.PostRepository;
import com.slobodanbabic.socialnetwork.repository.UserRepository;
import com.slobodanbabic.socialnetwork.service.PostService;

@Service
public class PostServiceImpl implements PostService {

	@Autowired
	PostRepository postRepository;

	@Autowired
	UserRepository userRepository;

	@Autowired
	PostMapper postMapper;

	@Override
	public PostDTO findOne(Integer id) {

		return null;

	}

	public ResponseEntity<Map<String, Object>> findAll(int page, int size) {
		// Pageable paging = PageRequest.of(page, size);
		Pageable paging = PageRequest.of(page, size, Sort.by("dateOfCreation").descending());
		Page<Post> pagePost = postRepository.findAll(paging);		
		Map<String, Object> response = new HashMap<>();
		response.put("count", pagePost.getTotalElements());
		response.put("results",
				pagePost.getContent().stream().map(i -> postMapper.transformToDTO(i)).collect(Collectors.toList()));
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	public ResponseEntity<Map<String, Object>> findByCategoryIdPageable(int categoryId, int page, int size) {
		Pageable paging = PageRequest.of(page, size, Sort.by("dateOfCreation").descending());
		Page<Post> pagePost = postRepository.findByCategoryId(categoryId, paging);
		Map<String, Object> response = new HashMap<>();
		response.put("count", pagePost.getTotalElements());
		response.put("results",
				pagePost.getContent().stream().map(i -> postMapper.transformToDTO(i)).collect(Collectors.toList()));
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@Override
	public List<PostDTO> findByCategoryId(int categoryId) {
		return postRepository.findByCategoryIdOrderByDateOfCreationDesc(categoryId).stream()
				.map(i -> postMapper.transformToDTO(i)).collect(Collectors.toList());
	}

	@Override
	public List<PostDTO> findAll() {
		return postRepository.findAll().stream().map(i -> postMapper.transformToDTO(i)).collect(Collectors.toList());
	}

	@Override
	public PostDTO save(PostDTO dto) {
		Post post = postMapper.transformToEntity(dto);
		Integer userId = dto.getUserId();
		Optional<User> user = userRepository.findById(userId);

		if (!user.isPresent()) {
			throw new IllegalArgumentException("User with the following id = " + userId + " is not found.");
		}

		post.setUser(user.get());
		return postMapper.transformToDTO(postRepository.save(post));
	}

	public boolean savePostsImage(MultipartFile img) {
		String imgName = img.getOriginalFilename();
		String separator = File.separator;
		try {
			URL s = ResourceUtils.getURL("src" + separator + "main" + separator + "resources" + separator + "static"
					+ separator + "images" + separator);
			String path = s.getPath();
			File upl = new File(path + imgName);
			FileOutputStream fout = new FileOutputStream(upl);
			fout.write(img.getBytes());
			fout.close();
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	@Override
	public void remove(Integer id) throws IllegalArgumentException {
		postRepository.deleteById(id);
	}

	public PostDTO removeById(Integer id) {
		Optional<Post> post = postRepository.findById(id);
		if (!post.isPresent()) {
			throw new IllegalArgumentException("Post with the following id = " + id + " is not found.");
		}
		remove(id);
		return postMapper.transformToDTO(post.get());
	}

	public PostDTO findById(Integer id) {
		return postMapper.transformToDTO(postRepository.findById(id).get());
	}

	@Override
	public PostDTO update(Integer id, PostDTO dto) {
		Post post = postRepository.getById(id);
		post.setContent(dto.getContent());
		post.setDateOfCreation(dto.getDateOfCreation());
		post.setImg_url(dto.getImg_url());
		return postMapper.transformToDTO(postRepository.save(post));
	}

	@Override
	public List<PostDTO> findAllByOrderByDateOfCreationDesc() {
		return postRepository.findAllByOrderByDateOfCreationDesc().stream().map(i -> postMapper.transformToDTO(i))
				.collect(Collectors.toList());
	}

	@Override
	public List<PostPerMonth> findPostsCountPerMonth(String dateFrom, String dateTo) {
		return postRepository.findPostsCountPerMonth(dateFrom, dateTo);
	}

}
