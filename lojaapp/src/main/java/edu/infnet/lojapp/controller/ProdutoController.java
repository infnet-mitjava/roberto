package edu.infnet.lojapp.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import edu.infnet.lojapp.dao.ProdutoRepository;
import edu.infnet.lojapp.model.Produto;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;



@RestController
public class ProdutoController {
	
	@Autowired
	private ProdutoRepository repository;
	
	
	@RequestMapping(
			value = "/produtos",
			method = RequestMethod.GET,
			produces = "application/json"
			)
	public ResponseEntity<List<Produto>> obterTodos(){
		List<Produto> produtos = repository.findAll();
		
		return new ResponseEntity<>(produtos, HttpStatus.OK);
	}

	@RequestMapping(
			value = "/produtos/{id}",
			method = RequestMethod.GET,
			produces = "application/json"
			)
	public ResponseEntity<Produto>
		obterPeloId(@PathVariable(value = "id") Integer id){		
		
		Optional<Produto> produto = repository.findById(id);
		
		if (!produto.isPresent()) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}else {
			return new ResponseEntity<>(produto.get(), HttpStatus.OK);
		}

	}

	
	@ApiOperation(value = "Grava um produto na base")
	@ApiResponses(
				value = {
						@ApiResponse(code = 200, message = "Retorna o produto gravado"),
						@ApiResponse(code = 404, message = "Nenhum produto gravado")
				}
			)
	@RequestMapping(
			value = "/produtos",
			method = RequestMethod.POST,
			produces = "application/json",
			consumes = "application/json"
			)
	public ResponseEntity<Produto> salvar(@RequestBody Produto produto){
		return new ResponseEntity<>(repository.save(produto), HttpStatus.CREATED);
	}
	
	@RequestMapping( 
			value = "/produtos/{id}",
			method = RequestMethod.PUT,
			produces = "application/json",
			consumes = "application/json"
			)
	public ResponseEntity<Produto> 
		editar(@PathVariable(value = "id") Integer id, 
			   @RequestBody Produto produto){
		
		if (produto == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}else {
			Produto produtoEditado = repository.saveAndFlush(produto);
			return new ResponseEntity<>(produtoEditado, HttpStatus.OK);
		}
	}
	
	@RequestMapping(
			value = "/produtos/{id}",
			method = RequestMethod.DELETE,
			produces = "application/json"
			)
	public ResponseEntity<Produto>
		deletarPeloId(@PathVariable(value = "id") Integer id){		
		
		try {
			repository.deleteById(id);
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}

	}
	public ProdutoRepository getDao() {
		
		return repository;
	}


	public void setDao(ProdutoRepository dao) {
		this.repository = dao;
	}

}
