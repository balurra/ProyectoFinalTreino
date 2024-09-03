package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.SignUpRequest;
import com.example.demo.entity.Admin;
import com.example.demo.entity.Rol;
import com.example.demo.entity.Usuario;
import com.example.demo.service.IAdminService;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = {"https://main.d3et1jtz0tpe31.amplifyapp.com/", "http://localhost:3000", "treino-backend.us-east-2.elasticbeanstalk.com"})
public class AdminRestController {
	
	@Autowired
	private IAdminService adminService;
	
	@PostMapping("/crear_admin")
	public ResponseEntity<?> agregarAdmin(@RequestBody SignUpRequest signUpRequest){
		String email = signUpRequest.getEmail();
		
		if(adminService.findAdminByEmail(email) != null) {
			return new ResponseEntity<Void>(HttpStatus.CONFLICT);
		}else {
	        Admin nuevoAdmin = new Admin();
	        nuevoAdmin.setNombre(signUpRequest.getNombre());
	        nuevoAdmin.setEmail(signUpRequest.getEmail());
	        nuevoAdmin.setPassword(signUpRequest.getPassword());
	        nuevoAdmin.setRol(Rol.ADMIN);

	        adminService.save(nuevoAdmin);
			return new ResponseEntity<Admin>(nuevoAdmin, HttpStatus.CREATED);
		}
	}
	
	/*
	 * @PostMapping("login_admin") public ResponseEntity<?> loginAdmin(@RequestBody
	 * Admin admin){ Admin adminDb = adminService.checkAdminLogin(admin);
	 * if(adminDb!=null) { return new ResponseEntity<>(adminDb, HttpStatus.OK);
	 * }else { return new ResponseEntity<Void>(HttpStatus.NOT_FOUND); } }
	 */
	
	

}
