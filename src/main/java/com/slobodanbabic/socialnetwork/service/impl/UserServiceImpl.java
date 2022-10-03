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

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;
import org.springframework.web.multipart.MultipartFile;

import com.slobodanbabic.socialnetwork.dto.LoginDTO;
import com.slobodanbabic.socialnetwork.dto.UserDTO;
import com.slobodanbabic.socialnetwork.entity.User;
import com.slobodanbabic.socialnetwork.maper.UserMapper;
import com.slobodanbabic.socialnetwork.repository.RoleRepository;
import com.slobodanbabic.socialnetwork.repository.UserRepository;
import com.slobodanbabic.socialnetwork.security.TokenHelper;
import com.slobodanbabic.socialnetwork.service.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserDetailsService userDetailsService;

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private TokenHelper tokenUtils;

	@Autowired
	UserRepository userRepository;

	@Autowired
	RoleRepository roleRepository;

	@Autowired
	UserMapper userMapper;

	public ResponseEntity<?> login(LoginDTO loginDTO) {
		try {
			UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(loginDTO.getEmail(),
					loginDTO.getPassword());

			Authentication authentication = authenticationManager.authenticate(token);
			SecurityContextHolder.getContext().setAuthentication(authentication);

			UserDetails details = userDetailsService.loadUserByUsername(loginDTO.getEmail());
			UserDTO response = new UserDTO();
			response.setToken(tokenUtils.generateToken(details));
			// find user in db
			User user = userRepository.findByEmail(details.getUsername());
			// set other property to userdto
			BeanUtils.copyProperties(user, response);
			response.setRoleName(user.getRoles().get(0).getName());
			return new ResponseEntity<UserDTO>(response, HttpStatus.OK);
		} catch (Exception ex) {
			return new ResponseEntity<String>("{text: \"Invalid login\"}", HttpStatus.BAD_REQUEST);

		}

	}

	public UserDTO findById(Integer id) {
		return userMapper.transformToDTO(userRepository.findById(id).get());
	}

	@Override
	public UserDTO save(UserDTO userDTO) {
		if (userDTO.getRoleName() == null)
			userDTO.setRoleName("ROLE_USER");
		User user = userMapper.transformToEntity(userDTO);
		PasswordEncoder pe = new BCryptPasswordEncoder();
		user.setPassword(pe.encode(user.getPassword()));
		return userMapper.transformToDTO(userRepository.save(user));
	}

	@Override
	public UserDTO findOne(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<UserDTO> findAll() {
		return userRepository.findAll().stream().map(i -> userMapper.transformToDTO(i)).collect(Collectors.toList());
	}

	@Override
	public void remove(Integer id) throws IllegalArgumentException {
		Optional<User> user = userRepository.findById(id);
		if (!user.isPresent()) {
			throw new IllegalArgumentException("User with the following id = " + id + " is not found.");
		}
		userRepository.deleteById(id);

	}

	public boolean saveImage(MultipartFile img) {
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
	public UserDTO update(Integer id, UserDTO dto) {
		User user = userRepository.getById(id);
		user.setFirstName(dto.getFirstName());
		user.setLastName(dto.getLastName());
		user.setImg_url(dto.getImg_url());
		user.setBan(dto.getBan());
		return userMapper.transformToDTO(userRepository.save(user));
	}

	@Override
	public ResponseEntity<Map<String, Object>> findAll(int page, int size) {
		Pageable paging = PageRequest.of(page, size);
		Page<User> pageUser = userRepository.findAll(paging);		
		Map<String, Object> response = new HashMap<>();
		response.put("count", pageUser.getTotalElements());
		response.put("results",
				pageUser.getContent().stream().map(i -> userMapper.transformToDTO(i)).collect(Collectors.toList()));
		return new ResponseEntity<>(response, HttpStatus.OK);
	}
}
