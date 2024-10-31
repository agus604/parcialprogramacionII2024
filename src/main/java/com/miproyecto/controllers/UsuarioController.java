package com.miproyecto.controllers;

//import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.miproyecto.modelo.Usuario;
import com.miproyecto.repository.UsuarioRepository;

import de.mkammerer.argon2.Argon2;
import de.mkammerer.argon2.Argon2Factory;

@RestController
public class UsuarioController {
	@Autowired
	private UsuarioRepository usuarioRepository;
		
	
	@RequestMapping(value="api/usuarios")
	public List<Usuario> getUsuario(){
		
		List<Usuario> user=usuarioRepository.getUsuarios();
		
		return user;
	}
	
	@RequestMapping(value="api/usuarios/{id}", method=RequestMethod.DELETE)
	public void eliminar(@PathVariable Long id) {
		
		usuarioRepository.eliminar(id);
	}
	
	@RequestMapping(value="api/usuarios", method=RequestMethod.POST)
public void registrar(@RequestBody Usuario usuario) {
    Argon2 argon2 = Argon2Factory.create(Argon2Factory.Argon2Types.ARGON2d);
    String passwordHasheado = argon2.hash(1, 1024, 1, usuario.getPassword());
    usuario.setPassword(passwordHasheado);
    usuarioRepository.registrar(usuario);
}
}


