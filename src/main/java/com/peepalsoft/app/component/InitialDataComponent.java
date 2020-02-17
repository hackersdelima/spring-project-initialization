package com.peepalsoft.app.component;

import com.peepalsoft.app.auth.Role;
import com.peepalsoft.app.entity.Office;
import com.peepalsoft.app.entity.Staffs;
import com.peepalsoft.app.entity.Users;
import com.peepalsoft.app.repo.OfficeRepo;
import com.peepalsoft.app.repo.RoleRepo;
import com.peepalsoft.app.repo.StaffsRepo;
import com.peepalsoft.app.repo.UsersRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class InitialDataComponent {
    private UsersRepo usersRepo;
    private StaffsRepo staffsRepo;
    private OfficeRepo officeRepo;
    private RoleRepo roleRepo;

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

    @Autowired
    public void setRoleRepo(RoleRepo roleRepo) {
        this.roleRepo = roleRepo;
    }

    public void saveInitialData() {
        saveRoles();
        saveUserAndOfficeAndStaff();
    }

    private void saveRoles() {
        List<Role> roles = roleRepo.findAll();
        if (roles.isEmpty()) {
            Role role = new Role();
            role.setId(1);
            role.setName("ROLE_ADMIN");
            roleRepo.save(role);
        }
    }

    private Office saveOffice() {
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
        return office;
    }

    private Staffs saveStaff() {
        Office office = saveOffice();
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
        return staffs;
    }


    private void saveUserAndOfficeAndStaff() {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        Optional<Users> userOptional = usersRepo.findByUsername("admin");
        if (!userOptional.isPresent()) {
            Users user = new Users();
            user.setUsername("admin");
            user.setPassword(encoder.encode("admin"));
            user.setStatus(true);

            Staffs staffs = saveStaff();

            user.setStaffs(staffs);
            usersRepo.save(user);
        }
    }


}
