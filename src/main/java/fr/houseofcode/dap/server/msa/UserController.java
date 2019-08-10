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
    //TODO MSA by Djer |Audit Code| (Checkstyle) Commentaire JavaDoc manquant
    private AppUserRepository appUserRepo;

    @RequestMapping("user/all")
    //TODO MSA by Djer |Audit Code| (Checkstyle) Commentaire JavaDoc manquant
    public Iterable<AppUser> displayAllUsers() {

        return appUserRepo.findAll();

    }

    @RequestMapping("/user/add")
    //TODO MSA by Djer |Audit Code| (Checkstyle) Commentaire JavaDoc manquant
    //TODO MSA by Djer |Audit Code| (Checkstyle) LE paramèrte "name" devrait être final
    public void adduser(@RequestParam String name) {
        AppUser entity = new AppUser();
        entity.setName(name);
        appUserRepo.save(entity);
    }
}
