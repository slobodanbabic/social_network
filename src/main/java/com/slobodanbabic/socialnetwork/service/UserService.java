package com.slobodanbabic.socialnetwork.service;

import java.util.Map;

import org.springframework.http.ResponseEntity;

import com.slobodanbabic.socialnetwork.dto.UserDTO;

public interface UserService extends CrudService<UserDTO> {

	public ResponseEntity<Map<String, Object>> findAll(int page, int size);

}
