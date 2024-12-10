package com.example.cine.Repository.Services;

import com.example.cine.Controller.Auth.CredencialesRequest;
import com.example.cine.Entity.Users.Client;
import com.example.cine.Repository.Interfaces.IClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
public class ClientService {

    private final IClientRepository clientRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public ClientService(IClientRepository clientRepository, PasswordEncoder passwordEncoder) {
        this.clientRepository = clientRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public List<Client> findAll() {
        return clientRepository.findAll();
    }

    public Client findById(long id) {
        return clientRepository.findById(id).orElse(null);
    }

    @Transactional
    public void save(Client client) {
        clientRepository.save(client);
    }

    @Transactional
    public void deleteByUsername(String username){
        clientRepository.deleteClientByUsername(username);
    }

    public Client findByUsername(String username){return clientRepository.findByUsername(username).orElse(null);}

    @Transactional
    public void actualizarCredenciales(CredencialesRequest credencialesRequest) {
        Client c = findByUsername(credencialesRequest.getOldUsername());
        c.setUsername(credencialesRequest.getUsername());
        if(!credencialesRequest.getPassword().isEmpty()){
            c.setPassword(passwordEncoder.encode(credencialesRequest.getPassword()));
        }
        save(c);
    }
}
