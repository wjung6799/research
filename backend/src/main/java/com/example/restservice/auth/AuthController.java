package com.example.restservice.auth;

import com.example.restservice.accessingdatamongodb.CarrotUser;
import com.example.restservice.dto.LoginRequestDTO;
import com.example.restservice.dto.LoginResponseDTO;
import com.example.restservice.dto.SignupRequestDTO;
import com.example.restservice.dto.SignupResponseDTO;
import com.example.restservice.error.ErrorDTO;
import com.example.restservice.services.CarrotUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("auth")
public class AuthController {

	@Autowired
	CarrotUserService carrotUserService;

	// initiate login
	@PostMapping("/login")
	public ResponseEntity<LoginResponseDTO> login(@RequestBody LoginRequestDTO loginRequest) {
		LoginResponseDTO loginResponseDTO = new LoginResponseDTO();
		CarrotUser user = carrotUserService.authenticateUser(loginRequest.getEmail(), loginRequest.getPassword());
		if (user == null) {
			ErrorDTO errorDTO = new ErrorDTO(HttpStatus.BAD_REQUEST, "Bad Request", "Wrong credentials");
			loginResponseDTO.addError(errorDTO);
			return new ResponseEntity<>(loginResponseDTO, new HttpHeaders(), errorDTO.getStatus());
		}
		return ResponseEntity.ok(loginResponseDTO);
	}

	// initiate logout
	@GetMapping("/logout")
	public ResponseEntity<Void> logout(String name) {
		return ResponseEntity.ok().build();
	}

	// initiate register
	@PostMapping("/register")
	public ResponseEntity<SignupResponseDTO> register(@RequestBody SignupRequestDTO signupRequestDTO) {

		SignupResponseDTO responseDTO = new SignupResponseDTO();

		if (signupRequestDTO == null || signupRequestDTO.getUsername() == null
				|| signupRequestDTO.getPassword() == null || signupRequestDTO.getEmail() == null) {

			ErrorDTO errorDTO = new ErrorDTO(HttpStatus.BAD_REQUEST, "Bad Request", "Invalid request body");
			responseDTO.addError(errorDTO);
			return new ResponseEntity<>(new SignupResponseDTO(), new HttpHeaders(), errorDTO.getStatus());
		}

		// check if email is valid
		if (!signupRequestDTO.getEmail().contains("@")) {
			ErrorDTO errorDTO = new ErrorDTO(HttpStatus.BAD_REQUEST, "Bad Request", "Invalid email format");
			responseDTO.addError(errorDTO);
			return new ResponseEntity<>(new SignupResponseDTO(), new HttpHeaders(), errorDTO.getStatus());
		}

		// check if username is already taken
		if (carrotUserService.getUserByEmail(signupRequestDTO.getEmail()) != null) {
			ErrorDTO errorDTO = new ErrorDTO(HttpStatus.BAD_REQUEST, "Bad Request", "Email already taken");
			responseDTO.addError(errorDTO);
			return new ResponseEntity<>(responseDTO, new HttpHeaders(), errorDTO.getStatus());
		}


		carrotUserService.createUser(BCrypt.hashpw(signupRequestDTO.getPassword(), BCrypt.gensalt())
				, signupRequestDTO.getEmail());


		return ResponseEntity.ok(responseDTO);
	}

}