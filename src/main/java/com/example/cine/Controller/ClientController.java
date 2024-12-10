package com.example.cine.Controller;

import com.example.cine.Controller.Auth.CredencialesRequest;
import com.example.cine.Entity.Users.Client;
import com.example.cine.Entity.Users.Erol;
import com.example.cine.Repository.Services.ClientService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import java.util.List;


@RestController
@RequestMapping("Api/Client")
@Slf4j
public class ClientController {

    private final ClientService clientService;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public ClientController(ClientService ClientService, PasswordEncoder passwordEncoder) {
        this.clientService = ClientService;
        this.passwordEncoder = passwordEncoder;
    }

    @PostMapping("/save")
    public ResponseEntity<String> saveClient(@RequestBody Client client) {
        if (validarExistenciaUsuario(client.getUsername())) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("El usuario ya existe");
        }
        client.setPassword(passwordEncoder.encode(client.getPassword()));
        client.setRol(Erol.CLIENT);
        clientService.save(client);
        log.info("El usuario " + client.getUsername() + " se ha registrado correctamente");
        return ResponseEntity.status(HttpStatus.CREATED).body("Usuario creado correctamente");

    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/find/{id}")
    public Client findClient(@PathVariable long id) {
        return clientService.findById(id);
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/findAll")
    public List<Client> findAllClients() {
        return clientService.findAll();
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/delete/{username}")
    public void deleteClient(@PathVariable String username){
        clientService.deleteByUsername(username);
    }


    @ResponseStatus(HttpStatus.OK)
    @PostMapping("/actualizar")
    public void actualizarCredenciales(@RequestBody CredencialesRequest credencialesRequest){
        clientService.actualizarCredenciales(credencialesRequest);
    }


    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/addTarjeta")
    public void addTarjeta(){
        //logica de agregar una tarjeta
    }

    public boolean validarExistenciaUsuario(String username){
        Client c = clientService.findByUsername(username);
        return c != null;
    }


}

