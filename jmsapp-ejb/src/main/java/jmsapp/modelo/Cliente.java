package jmsapp.modelo;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Cliente {
	
	@Id
	private int id;
	
	private String nome;
	private String email;
	private Long cpf;
	private String endereco;
	
	public Cliente() {
		
	}

	public Cliente(String nome, String email, Long cpf, String endereco) {
		super();
		this.nome = nome;
		this.email = email;
		this.cpf = cpf;
		this.endereco = endereco;
	}


	public int getId() {
		return id;
	}


	public void setId(int idCli) {
		this.id = idCli;
	}


	public String getNome() {
		return nome;
	}


	public void setNome(String nome) {
		this.nome = nome;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public Long getCpf() {
		return cpf;
	}


	public void setCpf(Long cpf) {
		this.cpf = cpf;
	}


	public String getEndereco() {
		return endereco;
	}


	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}
	
	@Override
	public String toString() {
		return "Id: "+id+"\n Nome: "+nome+"\n cpf: "+cpf+"\n email: "+email+"\n endereço: "+endereco;
	}
	
	

}
