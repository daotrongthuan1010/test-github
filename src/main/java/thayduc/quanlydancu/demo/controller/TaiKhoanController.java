package thayduc.quanlydancu.demo.controller;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import thayduc.quanlydancu.demo.entity.User;
import thayduc.quanlydancu.demo.exception.NotFoundException;
import thayduc.quanlydancu.demo.service.UserService;
import thayduc.quanlydancu.demo.service.serviceIpm.UserSecurityService;
import thayduc.quanlydancu.demo.utility.SecurityUtility;
import javax.validation.Valid;
import java.io.IOException;
import java.security.Principal;
import java.util.Arrays;
import java.util.Map;


@Controller
public class TaiKhoanController {

	@Autowired
	private UserService userService;

	@Autowired
	private UserSecurityService userSecurityService;
	@Autowired
	private Cloudinary cloudinary;

	@GetMapping("/login")
	public String login(Model model) throws IOException {
		model.addAttribute("usernameExists", model.asMap().get("usernameExists"));
		model.addAttribute("emailExists", model.asMap().get("emailExists"));
		return "myAccount";
	}
	@GetMapping("/my-profile")
	public String myProfile(Model model, Authentication authentication) {
		User user = (User) authentication.getPrincipal();
		model.addAttribute("user", user);
		return "myProfile";
	}


	@PostMapping(value="/new-user")
	public String newUserPost(@Valid @ModelAttribute("user") User user, BindingResult bindingResults,
							  @ModelAttribute("new-password") String password,
							  RedirectAttributes redirectAttributes, Model model) {
		model.addAttribute("email", user.getEmail());
		model.addAttribute("username", user.getUsername());
		boolean invalidFields = false;
		if (bindingResults.hasErrors()) {
			return "redirect:/login";
		}
		if (userService.findByUsername(user.getUsername()) != null) {
			redirectAttributes.addFlashAttribute("usernameExists", true);
			invalidFields = true;
		}
		if (userService.findByEmail(user.getEmail()) != null) {
			redirectAttributes.addFlashAttribute("emailExists", true);
			invalidFields = true;
		}
		if (invalidFields) {
			return "redirect:/login";
		}
		user = userService.createUser(user.getUsername(), password,  user.getEmail(), Arrays.asList("ROLE_USER"));
		userSecurityService.authenticateUser(user.getUsername());
		return "redirect:/my-profile";
	}

	@PostMapping(value="/update-user-info")
	public String updateUserInfo( @Valid @ModelAttribute("user") User user,
								  @RequestParam("newPassword") String newPassword,
								  @RequestParam("confirmPassword") String confirmPassword,
								  @RequestParam("file") MultipartFile file,
								  Model model, Principal principal) throws Exception {
		User currentUser = userService.findByUsername(principal.getName());
		if(currentUser == null) {
			throw new Exception ("User not found");
		}

		User existingUser = userService.findByUsername(user.getUsername());
		if (existingUser != null && !existingUser.getId().equals(currentUser.getId()))  {
			model.addAttribute("usernameExists", true);
			return "myProfile";
		}

		existingUser = userService.findByEmail(user.getEmail());
		if (existingUser != null && !existingUser.getId().equals(currentUser.getId()))  {
			model.addAttribute("emailExists", true);
			return "myProfile";
		}

		if (newPassword != null && !newPassword.isEmpty() && !newPassword.equals("") && confirmPassword.equals(newPassword)==true){
			BCryptPasswordEncoder passwordEncoder = SecurityUtility.passwordEncoder();
			String dbPassword = currentUser.getPassword();
			if(passwordEncoder.matches(user.getPassword(), dbPassword)){
				currentUser.setPassword(passwordEncoder.encode(newPassword));
			} else {
				model.addAttribute("incorrectPassword", true);
				return "myProfile";
			}

		}
		try {

			Map uploadResult = cloudinary.uploader().upload(file.getBytes(), ObjectUtils.emptyMap());
			String img =(String) uploadResult.get("secure_url");
			currentUser.setImgAvatar(img);

		}
		catch (IOException exception){
			System.out.println(exception.getMessage());
		}
		currentUser.setHoTen(user.getHoTen());
		currentUser.setUsername(user.getUsername());
		currentUser.setEmail(user.getEmail());
		userService.save(currentUser);
		model.addAttribute("updateSuccess", true);
		model.addAttribute("user", currentUser);
		userSecurityService.authenticateUser(currentUser.getUsername());
		return "myProfile";
	}





}
