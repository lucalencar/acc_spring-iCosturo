package com.accenture.icosturo_tabelas.icosturo;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

import com.accenture.icosturo.entity.Usuario;
import com.accenture.icosturo.repository.UsuarioRepository;

@RunWith(SpringRunner.class)
@DataJpaTest
public class UsuarioJunitTest {
	
	@Autowired
	private TestEntityManager entityManager;
	
	@Autowired
	UsuarioRepository usuarioRepository;
	
	@Test
	public void whenFindByNome_ReturnUsuario() {
		Usuario usuario = new Usuario();
		usuario.setNome("jorge");
		usuario.setSobrenome("Silva");
		usuario.setEmail("jorge.silva@gmail.com");
		usuario.setPassword("12345");
		entityManager.persist(usuario);
		entityManager.flush();
		
		Usuario encontrado = usuarioRepository.findByNome("jorge");
		
		assertThat(encontrado.getNome()).isEqualTo(usuario.getNome());
		
	}
	


}
