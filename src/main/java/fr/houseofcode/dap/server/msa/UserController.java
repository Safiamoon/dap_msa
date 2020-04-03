package fr.houseofcode.dap.server.msa;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import fr.houseofcode.dap.server.msa.data.AppUser;
import fr.houseofcode.dap.server.msa.data.AppUserRepository;

/**
 *
 * @author msa
 */
@RestController
public class UserController {

	/**
	 * AppUser repository.
	 */
	@Autowired
	private AppUserRepository appUserRepo;

	/**
	 * Display all users.
	 *
	 * @return list of users
	 */
	@RequestMapping("user/all")
	public Iterable<AppUser> displayAllUsers() {
		return appUserRepo.findAll();
	}

	/**
	 * Add a user.
	 *
	 * @param name
	 */
	@RequestMapping("/user/add")
	public void addUser(@RequestParam final String name) {
		AppUser entity = new AppUser();
		entity.setName(name);
		appUserRepo.save(entity);
	}

}
