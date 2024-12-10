package com.example.cine.Repository.Services;
import com.example.cine.Entity.Users.Admin;
import com.example.cine.Repository.Interfaces.IAdminRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
public class AdminService {

    private IAdminRepository adminRepository;

    @Autowired
    public AdminService(IAdminRepository adminRepository) {
        this.adminRepository = adminRepository;
    }

    public List<Admin> findAll() {
        return adminRepository.findAll();
    }

    public Admin findByid(long id) {
        return adminRepository.findById(id).orElse(null);
    }

    @Transactional
    public void save(Admin admin) {
        adminRepository.save(admin);
    }

    @Transactional
    public void deleteById(long id){
        adminRepository.deleteById(id);
    }

    public Admin findByUsername(String username){
        return adminRepository.findByUsername(username).orElse(null);
    }

}
