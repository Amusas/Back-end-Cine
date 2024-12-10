package com.example.cine.Controller;

import com.example.cine.Entity.Users.Admin;
import com.example.cine.Entity.Users.Erol;
import com.example.cine.Repository.Services.AdminService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("Api/Admin")
@Slf4j
public class AdminController {

    private final AdminService adminService;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public AdminController(AdminService adminService, PasswordEncoder passwordEncoder) {
        this.adminService = adminService;
        this.passwordEncoder = passwordEncoder;
    }

    //debe ser privado este endpoint
    @PostMapping("/save")
    public ResponseEntity<String> saveAdmin (@RequestBody Admin admin){
        if (validarExistenciaUsuario(admin.getUsername())) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("El usuario ya existe");
        }
        admin.setRol(Erol.ADMIN);
        admin.setPassword(passwordEncoder.encode(admin.getPassword()));
        adminService.save(admin);
        log.info("El usuario " + admin.getUsername() + " se ha registrado correctamente");
        return ResponseEntity.status(HttpStatus.CREATED).body("Usuario Creado Correctamente");
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/find/{id}")
    public Admin findAdmin(@PathVariable long id){
        return adminService.findByid(id);
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/findAll")
    public List<Admin> findAllAdmins(){
        return adminService.findAll();
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/delete/{id}")
    public void deleteAdmin(@PathVariable long id) {
        adminService.deleteById(id);
    }

    public boolean validarExistenciaUsuario(String username){
        Admin a = adminService.findByUsername(username);
        return a != null;
    }


}