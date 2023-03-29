package thayduc.quanlydancu.demo.service.serviceIpm;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import thayduc.quanlydancu.demo.dto.CommentDTO;
import thayduc.quanlydancu.demo.dto.UserDTO;
import thayduc.quanlydancu.demo.entity.User;
import thayduc.quanlydancu.demo.entity.security.Role;
import thayduc.quanlydancu.demo.entity.security.UserRole;
import thayduc.quanlydancu.demo.repository.RoleRepository;
import thayduc.quanlydancu.demo.repository.UserRepository;
import thayduc.quanlydancu.demo.service.UserService;
import thayduc.quanlydancu.demo.utility.ChuanHoa;
import thayduc.quanlydancu.demo.utility.SecurityUtility;

import java.io.IOException;
import java.util.*;


@Service
@Transactional
public class UserServiceImpl implements UserService {
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private RoleRepository roleRepository;
	@Autowired
	private Cloudinary cloudinary;

	@Override
	public Optional<User> findById(Long id) {
		Optional<User> opt = userRepository.findById(id);
		return opt;
	}
	
	@Override
	public User findByUsername(String username) {
		return userRepository.findByUsername(username);
	}
	

	@Override
	public User findByEmail(String email) {
		return userRepository.findByEmail(email);
	}

	@Override
	public void save(User user) {
		ChuanHoa chuanHoa = new ChuanHoa();
		String hoten = chuanHoa.chuanHoaToanBo(user.getHoTen());
		user.setHoTen(hoten);
		userRepository.save(user);
	}

	@Override
	@Transactional
	public User createUser(String username, String password, String email, List<String> roles) {
		User user = findByUsername(username);
		if (user != null) {
			return user;
		} else {
			user = new User();
			user.setUsername(username);
			user.setPassword(SecurityUtility.passwordEncoder().encode(password));
			user.setEmail(email);			
			Set<UserRole> userRoles = new HashSet<>();
			for (String rolename : roles) {
				Role role = roleRepository.findByName(rolename);
				if (role == null) {
					role = new Role();
					role.setName(rolename);
					roleRepository.save(role);
				}
				userRoles.add(new UserRole(user, role));
			}

			user.setUserRoles(userRoles);
			return userRepository.save(user);
		}
	}

	@Override
	public List<UserDTO> findAllUser(String name) {
		List<UserDTO> list = new ArrayList<>();
		List<Map<String, Object>> obj = userRepository.findAllUserByHoTenorEmail(name);
		obj.forEach(x ->
		{
			UserDTO userDTO = new UserDTO();
			userDTO.setId(String.valueOf(x.get("id")));
			userDTO.setEmail(String.valueOf(x.get("email")));
			userDTO.setHoTen(String.valueOf(x.get("ho_ten")));


			list.add(userDTO);

		});

		return list;
	}

}
