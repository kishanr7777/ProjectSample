package com.tconnect.service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

import org.bson.types.ObjectId;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.tatva.tconnectGeneralConfigs.TconnectConsts;
import com.tconnect.config.RandomNameGenerator;
import com.tconnect.config.UserServiceConsts;
import com.tconnect.dto.OrganisationDto;
import com.tconnect.dto.PersonDto;
import com.tconnect.dto.UserDto;
import com.tconnect.entity.Education;
import com.tconnect.entity.Organisation;
import com.tconnect.entity.ResponseStatus;
import com.tconnect.entity.UserEntity;
import com.tconnect.entity.UserProfileEntity;
import com.tconnect.repository.OrganisationRepository;
import com.tconnect.repository.UserProfileRepository;
import com.tconnect.repository.UserRepository;

@Service
public class UserService {
	@Autowired
	UserRepository userRepository;
	@Autowired
	UserProfileRepository userProfileRepository;
	@Autowired
	OrganisationRepository organisationRepository;
	@Autowired
	BCryptPasswordEncoder bCryptPasswordEncoder;
	
	Random random = new Random();
	
	Logger logger = LoggerFactory.getLogger(UserService.class);
	
	@Transactional
	public String registerUser(UserDto userDto, String type) {
		String isNotValid = userDto.validateUserDto(type);
		
		if(isNotValid != null)
			return isNotValid;
		
		Organisation orgProfile = null;
		boolean orgCreated = false;
		if (type.equals(TconnectConsts.ROLE_AGENT)) {
			try { 
				if(userDto.getAccessCode()==null){
					orgProfile = createOrg(userDto.getCompany(), TconnectConsts.ROLE_COMPANY); 
					orgCreated = true;
				} else {
					orgProfile = organisationRepository.findByCompanyName(userDto.getCompany());
				}
			} 
			catch (Exception e) { 
				return UserServiceConsts.MESSAGE_ERROR_COMPANY_ALREADY_REGISTERED; 
			}
		} else if(type.equals(TconnectConsts.ROLE_STUDENT)) {
			try { 
				orgProfile = createOrg(userDto.getUniversity(), TconnectConsts.ROLE_UNIVERSITY); 
				orgCreated = true;
			} 
			catch (Exception e) { 
			}
		}
		
		UserEntity userEntity = new UserEntity();
		userEntity.setEmail(userDto.getEmail());
		userEntity.setPassword(bCryptPasswordEncoder.encode(userDto.getPassword()));
		userEntity.setActive(true);
		Set<String> roll = new HashSet<>();
		roll.add(type);
		userEntity.setRoles(roll);
		
		try { 
			userRepository.save(userEntity); 
		} 
		catch (DuplicateKeyException e) { 
			if(orgCreated)
				organisationRepository.delete(orgProfile);
			return UserServiceConsts.MESSAGE_ERROR_EMAIL_ALREADY_REGISTER; 
		}
		
		UserProfileEntity userProfile = new UserProfileEntity();
		
		userProfile.setFirstName(userDto.getFirstName());
		userProfile.setLastName(userDto.getLastName());
		userProfile.setEmail(userDto.getEmail());
		userProfile.setType(type);
		userProfile.setOrgId(orgProfile.get_id());

		userProfileRepository.save(userProfile);
		
		logger.info("Registration successfull for " + userDto.getEmail());
		return UserServiceConsts.MESSAGE_REGISTRATION_SUCCESSFUL;
	}
	@Transactional
	public String manageAgent(UserDto userDto) {
		try {
			if(userDto.getAccessCode()==null){
				return UserServiceConsts.MESSAGE_ACCESS_CODE_NOT_VALID;
			}
			Organisation organisation = getCompanyProfile(userDto.getCompany());
			if(userDto.getAccessCode().equals(organisation.getAccessCode())){
				return registerUser(userDto, TconnectConsts.ROLE_AGENT);
			}
		} catch (Exception e) {
			logger.warn(e.getLocalizedMessage());
		} 
		return UserServiceConsts.MESSAGE_ACCESS_CODE_NOT_VALID;
	}
	
	@Transactional
	public List<UserProfileEntity> getUserByOrganisation(String company) {
		Organisation organisation = organisationRepository.findByCompanyName(company);
		return userProfileRepository.findByOrgId(organisation.get_id());
	}
	
	@Transactional
	public Organisation createOrg(String name, String type) {
		Organisation org = new Organisation();
		org.setName(name);
		org.setType(type);
		if(type.equals(TconnectConsts.ROLE_COMPANY)){
			String accessCode = String.format("%04d", random.nextInt(10000));
			org.setAccessCode(accessCode);
		}
		return organisationRepository.save(org);
	}
	
	@Transactional
	public UserProfileEntity updateUserProfile(PersonDto personDto) {
		
		UserProfileEntity userProfile = getUserProfile(personDto.getEmail());
		userProfile.setFirstName(personDto.getFirstName());
		userProfile.setLastName(personDto.getLastName());
		userProfile.setCurrentDegreeYear(personDto.getCurrentDegreeYear());
		userProfile.setCurrentDegreeName(personDto.getCurrentDegreeName());
		userProfile.setCareerGoal(personDto.getCareerGoal());
		userProfile.setSkills(personDto.getSkills());
		userProfile.setAchievement(personDto.getAchievement());
		userProfile.setExperience(personDto.getExperience());
		userProfile.setJobTitle(personDto.getJobTitle());
		userProfile.setBio(personDto.getBio());
		
		if(personDto.getCompany() != null && !personDto.getCompany().equals("")) {
			Organisation org = organisationRepository.findByCompanyName(personDto.getCompany());
			if(org == null)
				return null;
			userProfile.setOrgId(org.get_id());
		} else {
			if(personDto.getEducation() != null) {
				Education[] educations = personDto.getEducation();
				for(int i = 0; i < educations.length; i++) {
					Organisation org = organisationRepository.findByUniversity(educations[i].getUniversity());
					if(org != null && org.get_id() != null) {
						educations[i].setUniversityId(org.get_id());
					} else {
						try { 
							Organisation university = createOrg(educations[i].getUniversity(), TconnectConsts.ROLE_UNIVERSITY);
							educations[i].setUniversityId(university.get_id());
						} 
						catch (Exception e) { 
						}
					}
				}
			}
		}
		
		userProfile.setEducation(personDto.getEducation());
		
		logger.info("Profile update successfully for " + userProfile.getEmail());
		return userProfileRepository.save(userProfile);	
	}
	
	@Transactional
	public Organisation updateOrganisationProfile(OrganisationDto organisationDto) {
		Organisation organisationProfile = getCompanyProfile(organisationDto.getName());
		
		organisationProfile.setName(organisationDto.getName());
		organisationProfile.setOrgType(organisationDto.getOrgType());
		organisationProfile.setSize(organisationDto.getSize());
		organisationProfile.setSector(organisationDto.getSector());
		organisationProfile.setLocation(organisationDto.getLocation());
		organisationProfile.setWebsite(organisationDto.getWebsite());
		organisationProfile.setBio(organisationDto.getBio());
		organisationProfile.setMission(organisationDto.getMission());
		organisationProfile.setProject(organisationDto.getProject());
		organisationProfile.setBio(organisationDto.getBio());
		organisationProfile.setWorkingHere(organisationDto.getWorkingHere());
		
		logger.info("Profile update successfully for " + organisationProfile.getName());
		return organisationRepository.save(organisationProfile);	
	}

	@Transactional
	public UserProfileEntity getUserProfile(String email) {
		UserProfileEntity userProfileEntity = userProfileRepository.findByEmail(email);
		
		if(userProfileEntity.getOrgId() == null) {
			if(userProfileEntity.getEducation() != null) {
				for(Education edu : userProfileEntity.getEducation()) {
					Organisation org = organisationRepository.findBy_id(edu.getUniversityId());
					if(org != null) {
						edu.setUniversity(org.getName());
					}
				}
			}
		} else {
			Organisation org = organisationRepository.findBy_id(userProfileEntity.getOrgId());
			userProfileEntity.setCompany(org.getName());
		}
		
		return userProfileEntity;
	}

	@Transactional
	public Organisation getCompanyProfile(String companyName) {
		return organisationRepository.findByCompanyName(companyName);
	}
	
	@Transactional
	public Organisation getCompanyProfileForStudent(String email, String name) {
		Organisation company = organisationRepository.findByCompanyName(name);
		UserProfileEntity user = userProfileRepository.findByEmail(email);
		company.setFollowed(user.getFollowedCompanyIds().contains(company.get_id()));
		
		return company;
	}

	@Transactional
	public Organisation[] getAllCompanies() {
		return organisationRepository.getAllCompanies();
	}
	
	@Transactional
	public Organisation[] getUniversities() {
		return organisationRepository.getAllUniversities();
	}

	@Transactional
	public Organisation[] getUniversitiesNames() {
		return organisationRepository.getAllUniversitiesNames();
	}
	
	@Transactional
	public Organisation[] getCompaniesNames() {
		return organisationRepository.getallCompaniesNames();
	}

	@Transactional
	public UserProfileEntity updateUserProfileImage(MultipartFile imageFile, String pathToImageFolder, String email) throws IOException {
		String profileImageURL = saveImageToFolder(imageFile, pathToImageFolder);
		
		UserProfileEntity user = userProfileRepository.findByEmail(email);
		
		String profileImageName = user.getProfileImageURL();
		
		if(profileImageName != null) {
			profileImageName = profileImageName.substring(profileImageName.lastIndexOf("/")+1);
			File img = new File(pathToImageFolder + profileImageName);
			if(img.exists())
				img.delete();
		}
		
		user.setProfileImageURL(profileImageURL);
		
		logger.info("Profile image updated successfully for " + email);
		
		user = userProfileRepository.save(user);
		
		if(user.getOrgId() != null) {
			Organisation org = organisationRepository.findBy_id(user.getOrgId());
			user.setCompany(org.getName());
		}
		
		return user;
	}
	
	@Transactional
	public Organisation updateOrganizationProfileImage(MultipartFile imageFile, String pathToImageFolder, String name) throws IOException {
		
		String profileImageURL = saveImageToFolder(imageFile, pathToImageFolder);
		
		Organisation org = organisationRepository.findByCompanyName(name);
		
		String profileImageName = org.getProfileImageURL();
		
		if(profileImageName != null) {
			profileImageName = profileImageName.substring(profileImageName.lastIndexOf("/")+1);
			File img = new File(pathToImageFolder + profileImageName);
			if(img.exists())
				img.delete();
		}
		
		
		org.setProfileImageURL(profileImageURL);
		
		logger.info("Profile image updated successfully for company = " + name);
			
		return organisationRepository.save(org);
	}
	
	public String saveImageToFolder(MultipartFile imageFile, String pathToImageFolder) throws IOException {
		int index = imageFile.getOriginalFilename().lastIndexOf('.');
		String extension = imageFile.getOriginalFilename().substring(index);
		
		if(!UserServiceConsts.ALLOWED_IMAGE_EXTENSIONS.contains(extension.toLowerCase())) {
			logger.info("Image is rejected due to " + extension + " is not allowed extension.");
			return null;
		}
		
		String imageName = "";
		while(true) {
			imageName = RandomNameGenerator.generateImageName();
			if(index != -1) {
				imageName += extension;
			}
			
			File img = new File(pathToImageFolder + imageName);
			
			if(img.exists())
				continue;
			
			FileOutputStream fout = new FileOutputStream(img);
			fout.write(imageFile.getBytes());
			
			fout.close();
			break;
		}
		
		return TconnectConsts.TCONNECT_HTTP_PROTOCOL + TconnectConsts.TCONNECT_GATEWAY_ADDRESS
				+ "/user" + UserServiceConsts.SAVED_FOLDEER_NAME + imageName;
	}
	
	@Transactional
	public ResponseStatus followCompany(String email, String comapanyName, boolean follow) {
		try {
		UserProfileEntity user = userProfileRepository.findByEmail(email);
		Organisation org = organisationRepository.findByCompanyName(comapanyName);
		
		if(follow) {
			if(user.getFollowedCompanyIds() == null) {
				user.setFollowedCompanyIds(new LinkedHashSet<>());
			}
			
			user.getFollowedCompanyIds().add(org.get_id());
			userProfileRepository.save(user);
			return new ResponseStatus("true", UserServiceConsts.MESSAGE_COMPANY_FOLLOWED);
		}
		else {
			user.getFollowedCompanyIds().remove(org.get_id());
			userProfileRepository.save(user);
			return new ResponseStatus("true", UserServiceConsts.MESSAGE_COMPANY_UNFOLLOWED);
		}
		
		} catch (Exception e) {
			logger.info(comapanyName + " can't be followed by " + email + " because " + e.getLocalizedMessage());
			if(follow)
				return new ResponseStatus("false", UserServiceConsts.MESSAGE_ERROR_COMPANY_CAN_NOT_BE_FOLLOWED);
			else 
				return new ResponseStatus("false", UserServiceConsts.MESSAGE_ERROR_COMPANY_CAN_NOT_BE_UNFOLLOWED);
		}
	}
	
	@Transactional
	public Organisation[] getCompaniesNamesWithFollowStatus(String email) {
		Organisation[] companies = organisationRepository.getCompaniesNamesWithOpportunitiesAndSize();
		UserProfileEntity user = userProfileRepository.getFollowedCompaniesIds(email);
		
		LinkedHashSet<ObjectId> companyIds = user.getFollowedCompanyIds();
		
		if(companyIds == null)
			return companies;
		
		for(ObjectId id : companyIds) {
			for(Organisation company: companies) {
				if(company.get_id().equals(id)) {
					company.setFollowed(true);
				}
			}
		}
		
		return companies;
	}
	
	@Transactional
	public Organisation getCompanyNameAndProfileImage(String name) {
		return organisationRepository.getCompanyNameAndProfileImage(name);
	}
	
}
