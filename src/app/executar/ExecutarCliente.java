package app.executar;

import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

import app.acessoUsuario.AcessarUsuario;
import app.venda.Venda;
import produto.Produto;
import usuario.Usuario;
import usuario.cliente.Cliente;
import usuario.empresa.Empresa;

public class ExecutarCliente {

	public static void executarCliente(List<Usuario> usuarios, List<Cliente> clientes, List<Empresa> empresas,
			List<Produto> produtos, List<Produto> carrinho, List<Venda> vendas) {
		Scanner sc = new Scanner(System.in);

		if (AcessarUsuario.getAcesso().equals("Cliente")) {
			System.out.println("1 - Relizar Compras");
			System.out.println("2 - Ver Compras");
			System.out.println("0 - Deslogar");
			Integer escolha = sc.nextInt();
			switch (escolha) {
			case 1: {
				System.out.println("\nPara realizar uma compra, escolha a empresa onde deseja comprar: ");
				empresas.stream().forEach(x -> {
					System.out.println(x.getId() + " - " + x.getNome());
				});
				Integer escolhaEmpresa = sc.nextInt();
				Integer escolhaProduto;
				do {
					System.out.println("\nEscolha os seus produtos: ");
					produtos.stream().forEach(x -> {
						if (x.getEmpresa().getId().equals(escolhaEmpresa)) {
							System.out.println(x.getId() + " - " + x.getNome());
						}
					});
					System.out.println("0 - Finalizar compra");
					escolhaProduto = sc.nextInt();
					for (Produto produtoSearch : produtos) {
						if (produtoSearch.getId().equals(escolhaProduto))
							carrinho.add(produtoSearch);
					}
				} while (escolhaProduto > 0);
				System.out.println("************************************************************");
				System.out.println("Resumo da compra: ");
				carrinho.stream().forEach(x -> {
					if (x.getEmpresa().getId().equals(escolhaEmpresa)) {
						System.out.println(x.getId() + " - " + x.getNome() + "    R$" + x.getPreco());
					}
				});
				Empresa empresaEscolhida = empresas.stream().filter(x -> x.getId().equals(escolhaEmpresa))
						.collect(Collectors.toList()).get(0);
				Cliente clienteLogado = clientes.stream()
						.filter(x -> x.getUsername().equals(AcessarUsuario.getUsuarioLogado().getUsername()))
						.collect(Collectors.toList()).get(0);
				Venda venda = ExecutarEmpresa.criarVenda(carrinho, empresaEscolhida, clienteLogado, vendas);
				System.out.println("Total: R$" + venda.getValor());
				System.out.println("************************************************************");
				carrinho.clear();
				executarCliente(usuarios, clientes, empresas, produtos, carrinho, vendas);

				break;
			}
			case 2: {
				System.out.println();
				System.out.println("************************************************************");
				System.out.println("COMPRAS EFETUADAS");
				vendas.stream().forEach(venda -> {
					if (venda.getCliente().getUsername().equals(AcessarUsuario.getUsuarioLogado().getUsername())) {
						System.out.println("************************************************************");
						System.out.println("Compra de código: " + venda.getCódigo() + " na empresa "
								+ venda.getEmpresa().getNome() + ": ");
						venda.getItens().stream().forEach(x -> {
							System.out.println(x.getId() + " - " + x.getNome() + "    R$" + x.getPreco());
						});
						System.out.println("Total: R$" + venda.getValor());
						System.out.println("************************************************************");
					}
				});

				executarCliente(usuarios, clientes, empresas, produtos, carrinho, vendas);

				break;
			}
			case 0: {

				System.out.println("Deslogado com sucesso!");

				break;

			}
			}
		}
	}
}
