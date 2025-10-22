package com.blue.apartamentos.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.blue.apartamentos.models.ClienteModel;

public interface IClienteRepository extends JpaRepository<ClienteModel, Long> { }
