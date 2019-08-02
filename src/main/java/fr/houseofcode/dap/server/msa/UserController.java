package fr.houseofcode.dap.server.msa;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import fr.houseofcode.dap.server.msa.data.AppUser;
import fr.houseofcode.dap.server.msa.data.AppUserRepository;

/**
 * @author msa
 *
 */
@RestController
public class UserController {

    @Autowired
    private AppUserRepository appUserRepo;

    @RequestMapping("user/all")
    public Iterable<AppUser> displayAllUsers() {

        return appUserRepo.findAll();

    }

    @RequestMapping("/user/add")
    public void adduser(@RequestParam String name) {
        AppUser entity = new AppUser();
        entity.setName(name);
        appUserRepo.save(entity);
    }
}
