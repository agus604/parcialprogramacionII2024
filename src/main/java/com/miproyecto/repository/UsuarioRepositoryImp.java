package com.miproyecto.repository;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.miproyecto.modelo.Usuario;

import de.mkammerer.argon2.Argon2;
import de.mkammerer.argon2.Argon2Factory;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
@Repository
@Transactional
public class UsuarioRepositoryImp implements UsuarioRepository {
	@PersistenceContext 
	private EntityManager entityManager;

	@SuppressWarnings("unchecked")
	@Override
	public List<Usuario> getUsuarios() {
		
		String query="from Usuario";
		
		return entityManager.createQuery(query).getResultList();
	}

	@Override
	public void eliminar(Long id) {
		
		Usuario usuario=entityManager.find(Usuario.class, id);
		entityManager.remove(usuario);
	}

	@Override
	public void registrar(Usuario usuario) {
		
		entityManager.merge(usuario);
		
	}

	@SuppressWarnings("unchecked")
	@Override
public boolean verificarCredenciales(Usuario usuario) {
    String query = "FROM Usuario WHERE email = :email";
    List<Usuario> lista = (List<Usuario>) entityManager.createQuery(query)
            .setParameter("email", usuario.getEmail())
            .getResultList();

	if (lista.isEmpty()) {
		return false;
	}

		String passHasheada=lista.get(0).getPassword();

		Argon2 argon2=Argon2Factory.create(Argon2Factory.Argon2Types.ARGON2d);
		      boolean passEsIagual= argon2.verify(passHasheada,usuario.getPassword());


    return passEsIagual;
}
	

}
