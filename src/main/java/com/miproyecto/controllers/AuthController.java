package com.miproyecto.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.HttpStatus;
import com.miproyecto.modelo.Usuario;
import com.miproyecto.repository.UsuarioRepository;

@RestController
public class AuthController {

    @Autowired 
    UsuarioRepository usuarioRepository;

    @RequestMapping(value="api/login", method=RequestMethod.POST)
    public ResponseEntity<String> login (@RequestBody Usuario usuario) {
        
        if (usuarioRepository.verificarCredenciales(usuario)) {
            return ResponseEntity.ok("credenciales correctas");
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("credenciales incorrectas");
        }
    }
}