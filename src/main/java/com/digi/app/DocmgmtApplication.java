package com.digi.app;

import com.digi.app.entity.Office;
import com.digi.app.entity.Staffs;
import com.digi.app.entity.Users;
import com.digi.app.repo.OfficeRepo;
import com.digi.app.repo.StaffsRepo;
import com.digi.app.repo.UsersRepo;
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
@EntityScan(basePackages = "com.digi.app")
public class DocmgmtApplication extends SpringBootServletInitializer {
   @Autowired
    private UsersRepo usersRepo;
    @Autowired
    private StaffsRepo staffsRepo;
    @Autowired
    private OfficeRepo officeRepo;

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(DocmgmtApplication.class);
    }


    public static void main(String[] args) {
        SpringApplication.run(DocmgmtApplication.class, args);

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
