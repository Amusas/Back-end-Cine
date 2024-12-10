package com.example.cine.Controller.Auth;

import com.example.cine.Entity.Users.Admin;
import com.example.cine.Entity.Users.Client;
import com.example.cine.Entity.Users.Usuario;
import com.example.cine.Repository.Services.AdminService;
import com.example.cine.Repository.Services.ClientService;
import com.example.cine.Util.JwtUtil;
import java.util.logging.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/Api/Auth")
public class AuthController {

    private static final Logger logger = Logger.getLogger(AuthController.class.getName());

    private final ClientService clientService;
    private final AdminService adminService;
    private final JwtUtil jwtUtil;

    

    @Autowired
    public AuthController(ClientService clientService, AdminService adminService, JwtUtil jwtUtil) {
        this.clientService = clientService;
        this.adminService = adminService;
        this.jwtUtil = jwtUtil;
    }

    /**
     * metodo para obtener el rol del usuario mediante el token de sesion*
     * @return devuelve el rol del usuario
     */

    @PostMapping("/login")
    public ResponseEntity<?> login (@RequestBody AuthRequest usuario){//mejorar el auth, creando una clase de authResponse
        Usuario usuarioLog = userExists(usuario.getUsername());
        if (usuarioLog == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("El usuario no existe");
        }

        if (!authenticateUser(usuario, usuarioLog)) {
            logger.warning("el usuario " + usuario.getUsername() + " ingreso mal su contraseña");
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Contraseña incorrecta");
        }

        String jwtToken = "";
        String date = obtenerFecha();
        if (usuarioLog instanceof Admin){
            logger.info("El Administrador: " +usuarioLog.getUsername()+ " ha iniciado sesion a las " + date);
        }else {
            logger.info("El Cliente: " +usuarioLog.getUsername()+ " ha iniciado sesion a las " + date);
        }
        jwtToken = jwtUtil.generateToken(usuarioLog);
        return ResponseEntity.ok(new AuthResponse(usuario.getUsername(), jwtToken));
    }

    private boolean authenticateUser(AuthRequest user,Usuario usuarioLog) {
        try {
            PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
            if (passwordEncoder.matches(user.getPassword(), usuarioLog.getPassword()))
            {
                return true;
            }
        } catch (BadCredentialsException e) {
            // Contraseña incorrecta
            return false;
        }
        return false;
    }

    public Usuario userExists(String username){
        //Primero verifico si es un cliente, luego si no es un admin
        Client c = clientService.findByUsername(username);
        if(c != null){
            return c;
        }
        return adminService.findByUsername(username);
    }

    public String obtenerFecha(){
        // Obtener la fecha y hora actual
        LocalDateTime ahora = LocalDateTime.now();
        // Formatear la fecha y hora según tus necesidades
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        // Convertir la fecha y hora a una cadena formateada
        return ahora.format(formatter);
    }

}
