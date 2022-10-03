package com.slobodanbabic.socialnetwork.maper;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.slobodanbabic.socialnetwork.dto.UserDTO;
import com.slobodanbabic.socialnetwork.entity.User;
import com.slobodanbabic.socialnetwork.repository.RoleRepository;

@Component
public class UserMapper {
	
	@Autowired
	RoleRepository roleRepository;

	public UserDTO transformToDTO(User user) {
		UserDTO userDTO = new UserDTO();
		userDTO.setId(user.getId());
		userDTO.setFirstName(user.getFirstName());
		userDTO.setLastName(user.getLastName());
		userDTO.setEmail(user.getEmail());
		if(user.getImg_url()!= null)
			userDTO.setImg_url(user.getImg_url());
		PasswordEncoder pe = new BCryptPasswordEncoder();
		userDTO.setPassword(pe.encode(user.getPassword()));
		userDTO.setRoleName(user.getRoles().get(0).getName());
		userDTO.setBan(user.getBan());
		return userDTO;
	}

	public User transformToEntity(UserDTO userDTO) {
		User user = new User();		
		BeanUtils.copyProperties(userDTO, user);		
		user.addRole(roleRepository.findByName(userDTO.getRoleName()));		
		return user;
	}
}
