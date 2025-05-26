package com.ncell.app.controller;
import com.ncell.app.dto.AuthenticationRequest;
import com.ncell.app.service.AuthenticatorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/")
@CrossOrigin(origins = "*")
public class AuthenticatorController {

	@Autowired
	private AuthenticatorService authenticatorService;
	
	@PostMapping("/sendOTP")
	public ResponseEntity sendOTP(@RequestBody AuthenticationRequest request) {
		
		long number = request.getNumber();
		boolean validNumber = authenticatorService.validateNumber(number);
		if(!validNumber){
			return new ResponseEntity(HttpStatusCode.valueOf(404));
		}else{
			authenticatorService.sentOTP(number);
		}
		return new ResponseEntity(HttpStatusCode.valueOf(200));
	}

	@PostMapping("/validateOTP")
	public ResponseEntity validateOTP(@RequestBody AuthenticationRequest request) {
		long number = request.getNumber();
		int otp = request.getOtp();
		boolean valid = authenticatorService.validateOTP(number,otp);
		if(valid){
			return new ResponseEntity(HttpStatusCode.valueOf(200));
		}else{
			return new ResponseEntity(HttpStatusCode.valueOf(404));
		}
	}
}
