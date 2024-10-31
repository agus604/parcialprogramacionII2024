package com.miproyecto.repository;

import java.util.List;

import com.miproyecto.modelo.Usuario;

import jakarta.transaction.Transactional;

@Transactional
public interface UsuarioRepository {
	
	List <Usuario>getUsuarios();
	
	void eliminar(Long id);

	void registrar(Usuario usuario);

	boolean verificarCredenciales(Usuario usuario);

}
