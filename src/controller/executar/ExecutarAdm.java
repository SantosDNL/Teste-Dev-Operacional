package controller.executar;

import java.util.List;
import java.util.Scanner;

import controller.AcessarUsuario;
import model.Venda;
import model.bd.BancoDeDados;
import model.entidades.Cliente;
import model.entidades.Empresa;
import model.entidades.Usuario;
import model.Produto;

public class ExecutarAdm {

	public static void executarAdm(List<Usuario> usuarios, List<Cliente> clientes, List<Empresa> empresas,
			List<Produto> produtos, List<Produto> carrinho, List<Venda> vendas) {

		Scanner sc = new Scanner(System.in);

		System.out.println("Você logou como administrador!\n");

		while (true) {
			System.out.println("Escolha a plataforma que deseja logar (Digite somente o número):");
			System.out.println("1 - Empresa\n2 - Cliente\n0 - Encerrar o programa\n");
			Integer escolha = sc.nextInt();

			if (escolha == 1) {
				AcessarUsuario.setAcesso("Empresa");
				System.out.println("Qual Empresa deseja logar?\n");
				empresas.stream().forEach(x -> {
					System.out.println(x.getId() + " - " + x.getNome());
					System.out.println("0 - Deslogar\n");
				});

				Integer empresa = sc.nextInt();

				switch (empresa) {
				case 1: {
					AcessarUsuario.acessarUsuario(usuarios, BancoDeDados.getUsuario5().getUsername(),
							BancoDeDados.getUsuario5().getSenha());
					ExecutarEmpresa.executarEmpresa(usuarios, clientes, empresas, produtos, carrinho, vendas);
					break;
				}
				case 2: {
					AcessarUsuario.acessarUsuario(usuarios, BancoDeDados.getUsuario2().getUsername(),
							BancoDeDados.getUsuario2().getSenha());
					ExecutarEmpresa.executarEmpresa(usuarios, clientes, empresas, produtos, carrinho, vendas);
					break;
				}
				case 3: {
					AcessarUsuario.acessarUsuario(usuarios, BancoDeDados.getUsuario6().getUsername(),
							BancoDeDados.getUsuario6().getSenha());
					ExecutarEmpresa.executarEmpresa(usuarios, clientes, empresas, produtos, carrinho, vendas);
					break;
				}
				case 0: {
					System.out.println("\nDeslogando...\n");
					break;
				}

				default: {
					System.out.println("Opção errada! Encerrando...\n");
					break;
				}
				}
			} else if (escolha == 2) {
				System.out.println("Você escolheu entrar no modo Cliente, seus pedidos serão feitos com o Usuário: "
						+ clientes.get(0).getNome());
				AcessarUsuario.acessarUsuario(usuarios, BancoDeDados.getUsuario1().getUsername(),
						BancoDeDados.getUsuario1().getSenha());
				AcessarUsuario.setAcesso("Cliente");
				ExecutarCliente.executarCliente(usuarios, clientes, empresas, produtos, carrinho, vendas);
				break;
			} else {
				System.out.println("Deslogando...");
				break;
			}
		}
	}
}
