package com.niit.rest.controller;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.niit.skill.model.Employee;
import com.niit.skill.service.EmployeeService;


@RestController
@RequestMapping("/api/user")
public class UserController {
	@Autowired
	EmployeeService empService;
	
	@GetMapping
	public ResponseEntity<List<Employee>> userList() {
		if (empService.getAllEmployeeFormanager().size() != 0) {
			return new ResponseEntity<List<Employee>>(empService.getAllEmployeeFormanager(), HttpStatus.OK);
		} else {
			return new ResponseEntity<List<Employee>>(HttpStatus.NO_CONTENT);
		}
	}
	
//	
//	@GetMapping("/{userId}")
//	public ResponseEntity<Employee> getUserById(@PathVariable("userId") int userId) {
//		if (empService.getAllEmployeeFormanager().size() != 0) {
//			return new ResponseEntity<Employee>(empService.getEmployeeById(userId), HttpStatus.CREATED);
//		} else {
//			return new ResponseEntity<Employee>(HttpStatus.NO_CONTENT);
//		}
//	}
//	
	
	@PostMapping
	public ResponseEntity<Void> addEmployee(@RequestBody Employee user) {
		
		if(empService.getEmployeeById(user.getId())!=null) {
			
			return new ResponseEntity<Void>(HttpStatus.CONFLICT);
		}
		else {
			empService.insertEmployee(user);
			return new ResponseEntity<Void>(HttpStatus.CREATED);
		}
	}
	
	

	@DeleteMapping
	public ResponseEntity<Void> deleteUser(@RequestBody Employee user) {
		empService.deleteEmployee(user);
		return new ResponseEntity<Void>(HttpStatus.OK);
	}
	
	
	@PutMapping
	public ResponseEntity<Void> updateUser(@RequestBody Employee user) {
		empService.updateEmployee(user);
		return new ResponseEntity<Void>(HttpStatus.OK);
	}
	
	
	@PutMapping("/flag")
	public ResponseEntity<Void> approve(@RequestBody Employee user) {
		empService.setflage(user);
		return new ResponseEntity<Void>(HttpStatus.OK);
	}
	
	@PostMapping("/login")
	//@RequestMapping(value = "/login", method = { RequestMethod.POST })
	public ResponseEntity<Employee> getUserById(@RequestBody String email,String password,String type) 
	{
		if (empService.getAllEmployeeFormanager().size() != 0) {
			return new ResponseEntity<Employee>(empService.validateuser(email, password, type), HttpStatus.CREATED);
		
		} else {
			return new ResponseEntity<Employee>(HttpStatus.NO_CONTENT);
		}
	}
	
	
	
}