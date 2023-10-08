package br.com.carloscruz.api.repository;

import br.com.carloscruz.api.entity.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {
}
