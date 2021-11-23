package com.accenture.icosturo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.accenture.icosturo.entity.Cliente;

public interface ClienteRepository extends JpaRepository<Cliente, Integer> {

}
