package controller.executar;

import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

import controller.AcessarUsuario;
import model.Venda;
import model.entidades.Cliente;
import model.entidades.Empresa;
import model.entidades.Usuario;
import model.Produto;

public class ExecutarCliente {

	public static void executarCliente(List<Usuario> usuarios, List<Cliente> clientes, List<Empresa> empresas,
			List<Produto> produtos, List<Produto> carrinho, List<Venda> vendas) {
		Scanner sc = new Scanner(System.in);

		if (AcessarUsuario.getAcesso().equals("Cliente")) {
			System.out.println("1 - Relizar Compras");
			System.out.println("2 - Ver Compras");
			System.out.println("0 - Deslogar");
			Integer escolha = sc.nextInt();

			while (escolha > 2) {
				System.out.println("Opção errada. Tente novamente: \n");
				System.out.println("1 - Relizar Compras");
				System.out.println("2 - Ver Compras");
				System.out.println("0 - Deslogar");
				escolha = sc.nextInt();
			}

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

				if (carrinho.size() == 0) {

					System.out.println("Você não realizou compras! Voltando ao menu do Cliente...\n");

					carrinho.clear();
					executarCliente(usuarios, clientes, empresas, produtos, carrinho, vendas);
					break;
				} else {

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

			}
			case 2: {

				if (vendas.size() == 0) {

					System.out.println("Não foram realizadas compras! Voltando ao menu do Cliente...\n");

					executarCliente(usuarios, clientes, empresas, produtos, carrinho, vendas);
					break;
				}

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

				System.out.println("\nDeslogado com sucesso!\n");

				break;

			}
			}
		}
	}
}
