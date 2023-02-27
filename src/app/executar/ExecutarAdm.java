package app.executar;

import java.util.List;
import java.util.Scanner;

import app.acessoUsuario.AcessarUsuario;
import app.venda.Venda;
import produto.Produto;
import usuario.Usuario;
import usuario.cliente.Cliente;
import usuario.empresa.Empresa;

public class ExecutarAdm {

	public static void executarAdm(List<Usuario> usuarios, List<Cliente> clientes, List<Empresa> empresas,
			List<Produto> produtos, List<Produto> carrinho, List<Venda> vendas) {

		Scanner sc = new Scanner(System.in);

		System.out.println("Você logou como administrador!\n");

		System.out.println("Escolha a plataforma que deseja logar (Digite somente números):");

		System.out.println("1 - Empresa\n2 - Cliente\nPressione ENTER para encerrar o programa");

		Integer escolha = sc.nextInt();

		do {
			if (escolha == 1) {
				AcessarUsuario.setAcesso("Empresa");
				ExecutarEmpresa.executarEmpresa(usuarios, clientes, empresas, produtos, carrinho, vendas);
			} else if (escolha == 2) {
				AcessarUsuario.setAcesso("Cliente");
				ExecutarCliente.executarCliente(usuarios, clientes, empresas, produtos, carrinho, vendas);
			} else {
				System.out.println("Opção errada, deslogando...");
			}
		} while (escolha != 0);
	}
}
