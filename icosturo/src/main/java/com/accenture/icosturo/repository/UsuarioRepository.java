package com.accenture.icosturo.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.accenture.icosturo.entity.Cliente;
import com.accenture.icosturo.entity.Costureiro;
import com.accenture.icosturo.entity.Designer;
import com.accenture.icosturo.entity.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {
	Optional<Usuario> findByClienteList(Cliente cliente);
	Optional<Usuario> findByCostureiroList(Costureiro costureiro);
	Optional<Usuario> findByDesignerList(Designer designer);
	Usuario findByNome(String nome);
	
}
