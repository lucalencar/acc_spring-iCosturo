package com.accenture.icosturo.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.accenture.icosturo.entity.Designer;
import com.accenture.icosturo.entity.Modelo;

public interface DesignerRepository extends JpaRepository<Designer, Integer> {
	Optional<Designer> findByModeloList(Modelo modelo);
}
