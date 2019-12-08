package edu.infnet.lojapp.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import edu.infnet.lojapp.model.Produto;


@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Integer>  {

	
	
	
}
