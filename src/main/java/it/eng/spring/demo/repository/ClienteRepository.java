package it.eng.spring.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import it.eng.spring.demo.model.Cliente;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {
}
