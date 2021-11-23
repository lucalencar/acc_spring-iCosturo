package com.accenture.icosturo.repository;

import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;

import com.accenture.icosturo.entity.Costureiro;
import com.accenture.icosturo.entity.Modelo;

public interface CostureiroRepository extends JpaRepository<Costureiro, Integer> {
	Set<Costureiro> findByModelos(Modelo modelos);
}
