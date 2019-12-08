package jmsapp.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jmsapp.ejb.CarrinhoEjb;
import jmsapp.modelo.Cliente;
import jmsapp.modelo.Produto;

@WebServlet(name = "cart", urlPatterns = "/cart")
public class CarrinhoServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Inject
	private CarrinhoEjb carrinhoEjb;
	
	@Inject
	private Cliente cliente;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("===============================================================================================");
		System.out.println("Entrei no doGet");
		Integer clienteId = null;
		
		String action = req.getParameter("action");
		
		switch (action) {
		case "add":
			String nome = req.getParameter("nome");
			Double preco = Double.parseDouble(req.getParameter("preco"));
			Produto produto = new Produto(nome,preco);
			
			System.out.println("===============================================================================================");
			clienteId=Integer.parseInt(req.getParameter("clienteId"));
			String clienteNome = req.getParameter("clienteNome");
			String clienteEmail = req.getParameter("clienteEmail");
			String clienteEnd = req.getParameter("clienteEnd");
			Long clienteCpf = Long.parseLong(req.getParameter("clienteCpf"));
			
			System.out.println(clienteId);
			produto.setIdCli(clienteId);
			
			cliente.setCpf(clienteCpf);
			cliente.setEmail(clienteEmail);
			cliente.setEndereco(clienteEnd);
			cliente.setId(clienteId);
			cliente.setNome(clienteNome);
			
			carrinhoEjb.add(produto);
			break;
			
		case "remove":
			System.out.println(req.getParameter("produtoId"));
			Integer produtoId = Integer.parseInt(req.getParameter("produtoId"));
			clienteId=Integer.parseInt(req.getParameter("clienteId"));
			
			carrinhoEjb.remove(produtoId,clienteId);
			break;
		}
		
		List<Produto> carrinho = new ArrayList<>();
		carrinho=carrinhoEjb.findAll(clienteId);
		
		System.out.println("==================================================================================================");
		System.out.println("Vou imprimir o produto no carrinho servlet");
		System.out.println(carrinho);
		
		Double total=0.0;
		for (Produto produto: carrinho) {
			total+=produto.getPreco();
		}
		
		req.setAttribute("total", total);
		req.setAttribute("cliente", cliente);
		req.setAttribute("carrinho", carrinho);
		req.getRequestDispatcher("venda.jsp").forward(req, resp);
		
		System.out.println("Vou imprimir o total");
		System.out.println(total);
		System.out.println("===============================================================================================");
		System.out.println("Sai do doGet");
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		// DESSA MANEIRA NÃO FUNCIONOU
		List<Produto> carrinho = new ArrayList<>();
		
		System.out.println("===============================================================================================");
		System.out.println("Entrei no doPos");
		
		System.out.println("===============================================================================================");
		Integer clienteId=Integer.parseInt(req.getParameter("clienteId"));

		req.getRequestDispatcher("http://localhost:8080/jmsapp-web/send?mensagem=Outra%20coisa").forward(req, resp);
		
		System.out.println("===============================================================================================");
		System.out.println("Sai do doPost");
	}
	

}
