package com.accenture.icosturo.repository;

import java.util.List;
import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;

import com.accenture.icosturo.entity.Costureiro;
import com.accenture.icosturo.entity.Designer;
import com.accenture.icosturo.entity.Modelo;

public interface ModeloRepository extends JpaRepository<Modelo, Integer>{
	List<Modelo> findByDesigner(Designer designer);
	Set<Modelo> findByCostureiros(Costureiro costureiros);
}
