package controller.executar;

import java.util.List;
import java.util.Scanner;

import controller.AcessarUsuario;
import model.Venda;
import model.entidades.Cliente;
import model.entidades.Empresa;
import model.entidades.Usuario;
import model.Produto;

public class ExecutarEmpresa {

	public static void executarEmpresa(List<Usuario> usuarios, List<Cliente> clientes, List<Empresa> empresas,
			List<Produto> produtos, List<Produto> carrinho, List<Venda> vendas) {
		Scanner sc = new Scanner(System.in);

		if (AcessarUsuario.getAcesso().equals("Empresa")) {
			System.out.println("1 - Listar vendas");
			System.out.println("2 - Ver produtos");
			System.out.println("0 - Deslogar");
			Integer escolha = sc.nextInt();

			while (escolha > 2) {
				System.out.println("Opção errada. Tente novamente: \n");
				escolha = sc.nextInt();
			}

			switch (escolha) {
			case 1: {
				System.out.println();
				System.out.println("************************************************************");
				System.out.println("VENDAS EFETUADAS");
				vendas.stream().forEach(venda -> {
					if (venda.getEmpresa().getId().equals(AcessarUsuario.getUsuarioLogado().getEmpresa().getId())) {
						System.out.println("************************************************************");
						System.out.println("Venda de código: " + venda.getCódigo() + " no CPF "
								+ venda.getCliente().getCpf() + ": ");
						venda.getItens().stream().forEach(x -> {
							System.out.println(x.getId() + " - " + x.getNome() + "    R$" + x.getPreco());
						});
						System.out.println("Total Venda: R$" + venda.getValor());
						System.out.println("Total Taxa a ser paga: R$" + venda.getComissaoSistema());
						System.out.println(
								"Total Líquido  para empresa: R$" + (venda.getValor() - venda.getComissaoSistema()));
						System.out.println("************************************************************");
					}

				});
				System.out.println("Saldo Empresa (Taxas já subtraídas): "
						+ (AcessarUsuario.getUsuarioLogado().getEmpresa().getSaldo()
								- (AcessarUsuario.getUsuarioLogado().getEmpresa().getSaldo()
										* AcessarUsuario.getUsuarioLogado().getEmpresa().getTaxa())));
				System.out.println("************************************************************");

				executarEmpresa(usuarios, clientes, empresas, produtos, carrinho, vendas);

				break;
			}
			case 2: {
				System.out.println();
				System.out.println("************************************************************");
				System.out.println("MEUS PRODUTOS");
				produtos.stream().forEach(produto -> {
					if (produto.getEmpresa().getId().equals(AcessarUsuario.getUsuarioLogado().getEmpresa().getId())) {
						System.out.println("************************************************************");
						System.out.println("Código: " + produto.getId());
						System.out.println("Produto: " + produto.getNome());
						System.out.println("Quantidade em estoque: " + produto.getQuantidade());
						System.out.println("Valor: R$" + produto.getPreco());
						System.out.println("************************************************************");
					}

				});
				System.out.println("Saldo Empresa: " + AcessarUsuario.getUsuarioLogado().getEmpresa().getSaldo());
				System.out.println("************************************************************");

				executarEmpresa(usuarios, clientes, empresas, produtos, carrinho, vendas);

				break;
			}
			case 0: {

				System.out.println("\nDeslogado com sucesso!\n");

				break;
			}
			}

		}
	}

	public static Venda criarVenda(List<Produto> carrinho, Empresa empresa, Cliente cliente, List<Venda> vendas) {
		Double total = carrinho.stream().mapToDouble(Produto::getPreco).sum();
		Double comissaoSistema = total * empresa.getTaxa();
		int idVenda = vendas.isEmpty() ? 1 : vendas.get(vendas.size() - 1).getCódigo() + 1;
		Venda venda = new Venda(idVenda, carrinho.stream().toList(), total, comissaoSistema, empresa, cliente);
		empresa.setSaldo(empresa.getSaldo() + total);
		vendas.add(venda);
		return venda;
	}
}
