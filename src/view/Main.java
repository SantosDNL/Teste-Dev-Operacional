package view;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import controller.AcessarUsuario;
import controller.executar.ExecutarAdm;
import controller.executar.ExecutarCliente;
import controller.executar.ExecutarEmpresa;
import model.Venda;
import model.bd.BancoDeDados;
import model.entidades.Cliente;
import model.entidades.Empresa;
import model.entidades.Usuario;
import model.Produto;

public class Main {

	public static void main(String[] args) {

		// SIMULANDO BANCO DE DADOS

		List<Produto> carrinho = new ArrayList<Produto>();
		List<Venda> vendas = new ArrayList<Venda>();

		List<Usuario> usuarios = Arrays.asList(BancoDeDados.getUsuario1(), BancoDeDados.getUsuario2(),
				BancoDeDados.getUsuario3(), BancoDeDados.getUsuario4(), BancoDeDados.getUsuario5(),
				BancoDeDados.getUsuario6());
		List<Cliente> clientes = Arrays.asList(BancoDeDados.getCliente(), BancoDeDados.getCliente2());
		List<Empresa> empresas = Arrays.asList(BancoDeDados.getEmpresa(), BancoDeDados.getEmpresa2(),
				BancoDeDados.getEmpresa3());
		List<Produto> produtos = Arrays.asList(BancoDeDados.getProduto(), BancoDeDados.getProduto2(),
				BancoDeDados.getProduto3(), BancoDeDados.getProduto4(), BancoDeDados.getProduto5(),
				BancoDeDados.getProduto6(), BancoDeDados.getProduto7(), BancoDeDados.getProduto8(),
				BancoDeDados.getProduto9(), BancoDeDados.getProduto10());

		while (true) {
			Scanner sc = new Scanner(System.in);

			System.out.println("Entre com seu usuário e senha ou pressione enter duas vezes para encerrar o programa:");
			System.out.print("Usuário: ");
			String username = sc.nextLine();
			System.out.print("Senha: ");
			String senha = sc.nextLine();

			AcessarUsuario.acessarUsuario(usuarios, username, senha);

			if (AcessarUsuario.getAcesso().equals("Empresa")) {
				System.out.println("Bem vindo! " + AcessarUsuario.getUsuarioLogado().getEmpresa().getNome() + "\n");
				ExecutarEmpresa.executarEmpresa(usuarios, clientes, empresas, produtos, carrinho, vendas);
			} else if (AcessarUsuario.getAcesso().equals("Cliente")) {
				System.out.println("Bem vindo! " + AcessarUsuario.getUsuarioLogado().getCliente().getNome() + "\n");
				ExecutarCliente.executarCliente(usuarios, clientes, empresas, produtos, carrinho, vendas);
			} else if (AcessarUsuario.getAcesso().equals("Negado")) {
				System.out.println("\n");
			} else {
				ExecutarAdm.executarAdm(usuarios, clientes, empresas, produtos, carrinho, vendas);
			}
			if (username == "" && senha == "") {
				System.out.println("Programa encerrado!");
				break;
			}
		}
	}
}
