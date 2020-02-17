package com.peepalsoft.app;

import com.peepalsoft.app.entity.Office;
import com.peepalsoft.app.entity.Staffs;
import com.peepalsoft.app.entity.Users;
import com.peepalsoft.app.repo.OfficeRepo;
import com.peepalsoft.app.repo.StaffsRepo;
import com.peepalsoft.app.repo.UsersRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.event.EventListener;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
@EntityScan(basePackages = "com.peepalsoft.app")
public class AssetManagementApplication extends SpringBootServletInitializer {
    private UsersRepo usersRepo;
    private StaffsRepo staffsRepo;
    private OfficeRepo officeRepo;

    @Autowired
    public void setUsersRepo(UsersRepo usersRepo) {
        this.usersRepo = usersRepo;
    }

    @Autowired
    public void setStaffsRepo(StaffsRepo staffsRepo) {
        this.staffsRepo = staffsRepo;
    }

    @Autowired
    public void setOfficeRepo(OfficeRepo officeRepo) {
        this.officeRepo = officeRepo;
    }

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(AssetManagementApplication.class);
    }


    public static void main(String[] args) {
        SpringApplication.run(AssetManagementApplication.class, args);

    }

    @EventListener(ApplicationReadyEvent.class)
    public void doSomethingAfterStartup() {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        Users user = usersRepo.findByUsername("admin");
        if (user == null) {
            user = new Users();
            user.setUsername("admin");
            user.setPassword(encoder.encode("admin"));
            user.setStatus(true);

            Office office = null;
            if (officeRepo.findById(1).isPresent()) {
                office = officeRepo.findById(1).get();
            } else {
                office = new Office();
                office.setId(1);
                office.setName("Head Office");
                office.setOffice_level("HO");
                office.setRo_code("000");
                office.setAddress("Kathmandu");
                office = officeRepo.save(office);
            }


            Staffs staffs = null;
            if (staffsRepo.findById("000").isPresent()) {
                staffs = staffsRepo.findById("000").get();
            } else {
                staffs = new Staffs();
                staffs.setCode("000");
                staffs.setFirstName("admin");
                staffs.setLastName("admin");
                staffs.setPhoneNumber("9999999999");
                staffs.setPost("admin");
                staffs.setGender("m");
                staffs.setOffice(office);
                staffs = staffsRepo.save(staffs);
            }

            user.setStaffs(staffs);
            usersRepo.save(user);
        }
    }


}
