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

import edu.infnet.lojapp.dao.ClienteRepository;
import edu.infnet.lojapp.model.Cliente;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;



@RestController
public class ClienteController {
	
	@Autowired
	private ClienteRepository repository;
	
	
	@RequestMapping(
			value = "/clientes",
			method = RequestMethod.GET,
			produces = "application/json"
			)
	public ResponseEntity<List<Cliente>> obterTodos(){
		List<Cliente> clientes = repository.findAll();
		
		return new ResponseEntity<>(clientes, HttpStatus.OK);
	}

	@RequestMapping(
			value = "/clientes/{id}",
			method = RequestMethod.GET,
			produces = "application/json"
			)
	public ResponseEntity<Cliente>
		obterPeloId(@PathVariable(value = "id") Integer id){		
		
		Optional<Cliente> cliente = repository.findById(id);
		
		if (!cliente.isPresent()) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}else {
			return new ResponseEntity<>(cliente.get(), HttpStatus.OK);
		}

	}

	
	@ApiOperation(value = "Grava um cliente na base")
	@ApiResponses(
				value = {
						@ApiResponse(code = 200, message = "Retorna o cliente gravado"),
						@ApiResponse(code = 404, message = "Nenhum cliente gravado")
				}
			)
	@RequestMapping(
			value = "/clientes",
			method = RequestMethod.POST,
			produces = "application/json",
			consumes = "application/json"
			)
	public ResponseEntity<Cliente> salvar(@RequestBody Cliente cliente){
		return new ResponseEntity<>(repository.save(cliente), HttpStatus.CREATED);
	}
	
	@RequestMapping( 
			value = "/clientes/{id}",
			method = RequestMethod.PUT,
			produces = "application/json",
			consumes = "application/json"
			)
	public ResponseEntity<Cliente> 
		editar(@PathVariable(value = "id") Integer id, 
			   @RequestBody Cliente cliente){
		
		if (cliente == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}else {
			Cliente clienteEditado = repository.saveAndFlush(cliente);
			return new ResponseEntity<>(clienteEditado, HttpStatus.OK);
		}
	}
	
	@RequestMapping(
			value = "/clientes/{id}",
			method = RequestMethod.DELETE,
			produces = "application/json"
			)
	public ResponseEntity<Cliente>
		deletarPeloId(@PathVariable(value = "id") Integer id){		
		
		try {
			repository.deleteById(id);
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}

	}
	public ClienteRepository getDao() {
		
		return repository;
	}


	public void setDao(ClienteRepository dao) {
		this.repository = dao;
	}

}
