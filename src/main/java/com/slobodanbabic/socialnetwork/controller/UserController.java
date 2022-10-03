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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.slobodanbabic.socialnetwork.dto.LoginDTO;
import com.slobodanbabic.socialnetwork.dto.UserDTO;
import com.slobodanbabic.socialnetwork.service.impl.UserServiceImpl;

@RestController
@RequestMapping("/user")
public class UserController {

	@Autowired
	private UserServiceImpl userServiceImpl;

	@ResponseBody
	@PostMapping(value = "/login")
	public ResponseEntity<?> login(@RequestBody LoginDTO loginDTO) {
		return userServiceImpl.login(loginDTO);
	}

	@PostMapping(value = "/addUser", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public UserDTO saveuser(@RequestBody UserDTO userDTO) {
		return userServiceImpl.save(userDTO);
	}

	@PutMapping(value = "/update/{id}", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public UserDTO updateUser(@PathVariable("id") Integer id, @RequestBody UserDTO userDTO) {
		return userServiceImpl.update(id, userDTO);
	}

	@GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public UserDTO getById(@PathVariable("id") Integer id) {
		return userServiceImpl.findById(id);
	}

	@GetMapping(value = "/getAll", produces = MediaType.APPLICATION_JSON_VALUE)
	public List<UserDTO> getAll() {
		return userServiceImpl.findAll();
	}

	// radimo paginaciju
	@GetMapping(value = "/getAllPagination", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Map<String, Object>> getAllPostPagination(@RequestParam(defaultValue = "0") int page,
			@RequestParam(defaultValue = "5") int size) {
		return userServiceImpl.findAll(page, size);
	}

	@GetMapping(value = "/getImgUrl/{id}")
	public ResponseEntity<String> getImgUrlById(@PathVariable("id") Integer id) {
		UserDTO user = userServiceImpl.findById(id);
		return new ResponseEntity<>(user.getImg_url(), HttpStatus.OK);
	}

	@PostMapping(value = "/uploadImage", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> saveImage(@RequestParam("imageFile") MultipartFile img) {
		userServiceImpl.saveImage(img);
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

}
