package com.slobodanbabic.socialnetwork.controller;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.nio.file.Files;
import java.util.List;
import java.util.Map;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.slobodanbabic.socialnetwork.dto.PostCountI;
import com.slobodanbabic.socialnetwork.dto.PostDTO;
import com.slobodanbabic.socialnetwork.dto.PostPerMonth;
import com.slobodanbabic.socialnetwork.service.impl.CategoryServiceImpl;
import com.slobodanbabic.socialnetwork.service.impl.PostServiceImpl;

@RestController
@RequestMapping("/post")
public class PostController {

	@Autowired
	PostServiceImpl postServiceImpl;

	@Autowired
	CategoryServiceImpl categoryServiceImpl;

	@PostMapping(value = "/addPost", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public PostDTO savePost(@RequestBody PostDTO postDTO) {
		return postServiceImpl.save(postDTO);
	}

	@PostMapping(value = "/uploadImage", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> saveImage(@RequestParam("imageFile") MultipartFile img) {
		postServiceImpl.savePostsImage(img);
		return new ResponseEntity<>(HttpStatus.OK);
	}

	@GetMapping(path = { "/getImage/{imageName}" }, produces = MediaType.IMAGE_JPEG_VALUE)
	public ResponseEntity<byte[]> getImage(@PathVariable("imageName") String imageName) throws IOException {
		String separator = File.separator;
		URL s = ResourceUtils.getURL("src" + separator + "main" + separator + "resources" + separator + "static"
				+ separator + "images" + separator);
		String path = s.getPath();
		File file = new File(path + imageName);
		byte[] fileContent = Files.readAllBytes(file.toPath());
		InputStream in = new ByteArrayInputStream(fileContent);
		return new ResponseEntity<>(IOUtils.toByteArray(in), HttpStatus.OK);
	}

	@DeleteMapping(value = "/delete/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public PostDTO deletePost(@PathVariable("id") Integer id) {
		return postServiceImpl.removeById(id);
	}

	// radimo paginaciju
	@GetMapping(value = "/getAllPagination", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Map<String, Object>> getAllPostPagination(@RequestParam(defaultValue = "0") int page,
			@RequestParam(defaultValue = "3") int size) {
		return postServiceImpl.findAll(page, size);
	}

	@GetMapping(value = "/getAll", produces = MediaType.APPLICATION_JSON_VALUE)
	public List<PostDTO> getAllPost() {
		return postServiceImpl.findAllByOrderByDateOfCreationDesc();
	}

	@GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public PostDTO getById(@PathVariable("id") Integer id) {
		return postServiceImpl.findById(id);
	}
	

	@GetMapping(value = "/getByCategoryIdPageable/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Map<String, Object>> getByCategoryId(@PathVariable("id") Integer id, @RequestParam(defaultValue = "0") int page,
			@RequestParam(defaultValue = "3") int size) {
		return postServiceImpl.findByCategoryIdPageable(id, page, size);
	}

	
	@GetMapping(value = "/getCountPostsGroupByCategory", produces = MediaType.APPLICATION_JSON_VALUE)
	public List<PostCountI> getCountTotalPostsByCategory() {
		return categoryServiceImpl.getCountTotalPostsByCategory();
	}

	@GetMapping(value = "/findPostsCountPerMonth/{dateFrom}/{dateTo}", produces = MediaType.APPLICATION_JSON_VALUE)
	public List<PostPerMonth> findPostsCountPerMonth(@PathVariable("dateFrom") String dateFrom,
			@PathVariable("dateTo") String dateTo) {
		return postServiceImpl.findPostsCountPerMonth(dateFrom, dateTo);
	}

	@PutMapping(value = "/update/{id}", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public PostDTO updatePost(@PathVariable("id") Integer id, @RequestBody PostDTO postDTO) {
		return postServiceImpl.update(id, postDTO);
	}

}
