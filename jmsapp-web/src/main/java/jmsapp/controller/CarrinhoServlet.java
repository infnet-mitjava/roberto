package jmsapp.controller;

import java.io.IOException;
import java.io.PrintWriter;
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

	@Inject
	private CarrinhoEjb carrinho;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		resp.setContentType("text/html");
		
		String nome = req.getParameter("nome");
		String preco = req.getParameter("preco");
		
		Produto produto = new Produto();
		produto.setNome(nome);
		produto.setPreco(Double.valueOf(preco));
		
		produto = carrinho.adicionar(produto);
		
         PrintWriter out = resp.getWriter();
         out.println("hello ejb " +  produto );
         out.close();
		
	}

}
