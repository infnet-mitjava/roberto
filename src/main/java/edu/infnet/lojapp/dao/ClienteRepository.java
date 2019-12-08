package edu.infnet.lojapp.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import edu.infnet.lojapp.model.Cliente;


@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Integer>  {

	
	
	
}
