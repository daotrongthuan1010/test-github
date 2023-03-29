package thayduc.quanlydancu.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import thayduc.quanlydancu.demo.service.UserService;

import java.util.Arrays;


@Component
public class StoreAppStartupRunner implements CommandLineRunner{

	@Autowired
	private UserService userService;
	
	@Override
	public void run(String... args) throws Exception {
		userService.createUser("admin", "admin", "admin@admin.com", Arrays.asList("ROLE_USER", "ROLE_ADMIN"));
	}
}

