package com.tconnect.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.tconnect.config.UserServiceConsts;
import com.tconnect.dto.OrganisationDto;
import com.tconnect.dto.PersonDto;
import com.tconnect.dto.UserDto;
import com.tconnect.entity.Organisation;
import com.tconnect.entity.ResponseStatus;
import com.tconnect.entity.UserProfileEntity;
import com.tconnect.service.UserService;

@RestController
@CrossOrigin(origins="*")
public class UserController {
	@Autowired
	UserService userService;

	@PostMapping(value="/register/{type}", headers="Accept=application/json")
	public ResponseEntity<Object> registerUser(@RequestBody UserDto userDto,
			@PathVariable(name="type") String type) {
		return ResponseEntity.ok(userService.registerUser(userDto, type));
	}
	
	@PostMapping(value="/manage/agent", headers="Accept=application/json")
	public ResponseEntity<Object> manageAgent(@RequestBody UserDto userDto) {
		return ResponseEntity.ok(userService.manageAgent(userDto));
	}
	
	@GetMapping(value="/profile", headers="Accept=application/json")
	public UserProfileEntity getUserProfile(HttpServletRequest req) {
		return userService.getUserProfile(req.getParameter("email"));		
	}
	
	@GetMapping(value="/profile/agent", headers="Accept=application/json")
	public List<UserProfileEntity> getUserByOrganisation(HttpServletRequest req) {
		return userService.getUserByOrganisation(req.getParameter("company"));		
	}

	@PatchMapping(value="/profile/update", headers="Accept=application/json")
	public UserProfileEntity updateUserProfile(@RequestBody PersonDto personDto) {
		return userService.updateUserProfile(personDto);
	}

	@PostMapping(value="/profile/image") 
	public UserProfileEntity updateUserProfileImage(HttpServletRequest req, @RequestParam("file") MultipartFile imageFile, @RequestParam String email) throws IOException {
		return userService.updateUserProfileImage(imageFile, 
				req.getServletContext().getRealPath(UserServiceConsts.SAVED_FOLDEER_NAME), 
				email);
	}
	
	@PostMapping(value="/profile/company/image") 
	public Organisation updateOrganozationProfileImage(HttpServletRequest req, @RequestParam("file") MultipartFile imageFile, @RequestParam String name) throws IOException {
		return userService.updateOrganizationProfileImage(imageFile, 
				req.getServletContext().getRealPath(UserServiceConsts.SAVED_FOLDEER_NAME), 
				name);
	}
	
	@GetMapping(value="/profile/company", headers="Accept=application/json")
	public Organisation getCompanyProfile(HttpServletRequest req) {
		return userService.getCompanyProfile(req.getParameter("name"));
	}
	
	@PatchMapping(value="/profile/company/update", headers="Accept=application/json")
	public Organisation updateOrganisationProfile(@RequestBody OrganisationDto organisationDto) {
		return userService.updateOrganisationProfile(organisationDto);
	}

	@GetMapping(value="/profile/company/all", headers="Accept=application/json")
	public Organisation[] getAllCompanies() {	
		return userService.getAllCompanies();
	}

	@GetMapping(value="/profile/university/all", headers="Accept=application/json")
	public Organisation[] getUniversities() {	
		return userService.getUniversities();
	}
	
	@GetMapping(value="/university", headers="Accept=application/json")
	public Organisation[] getUniversitiesNames() {	
		return userService.getUniversitiesNames();
	}
	
	@GetMapping(value="/company", headers="Accept=application/json")
	public Organisation[] getCompaniesNames() {	
		return userService.getCompaniesNames();
	}
	
	@GetMapping(value="/company/profile", headers="Accept=application/json")
	public Organisation getCompaniesNames(@RequestParam String name) {	
		return userService.getCompanyNameAndProfileImage(name);
	}
	
	@GetMapping(value="/view/company", headers="Accept=application/json")
	public Organisation getCompanyDetail(HttpServletRequest req) {
		return userService.getCompanyProfileForStudent(req.getParameter("email"), req.getParameter("name"));
	}
	
	@GetMapping(value="/explore", headers="Accept=application/json")
	public Organisation[] getCompaniesNamesWithFollowStatus(@RequestParam String email){	
		return userService.getCompaniesNamesWithFollowStatus(email);
	}
	
	@PostMapping(value="/company/follow") 
	public ResponseStatus followCompany(HttpServletRequest req) throws IOException, JSONException {
		JSONObject jsonObject = readRequestBody(req);
		
		String email = jsonObject.getString("email");
		String companyName = jsonObject.getString("companyName");
		boolean follow = jsonObject.getBoolean("follow");
		
		return userService.followCompany(email, companyName, follow);
	}
	
	public JSONObject readRequestBody(HttpServletRequest req) {
		StringBuffer jb = new StringBuffer();
		String line = null;
		try {
			BufferedReader reader = req.getReader();
			while ((line = reader.readLine()) != null)
				jb.append(line);
		} catch (Exception e) {
		}

		JSONObject jsonObject = null;
		
		try {
			jsonObject = new JSONObject(jb.toString());
		} catch (JSONException e) {
			System.out.println("Error parsing JSON request string");
		}

		return jsonObject;
	}
}
