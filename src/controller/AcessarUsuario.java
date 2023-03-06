package controller;

import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

import model.entidades.Usuario;

public class AcessarUsuario {
	static Usuario usuarioLogado;
	static String acesso;

	public static void acessarUsuario(List<Usuario> usuarios, String username, String senha) {

		Scanner sc = new Scanner(System.in);

		List<Usuario> usuariosSearch = usuarios.stream().filter(x -> x.getUsername().equals(username))
				.collect(Collectors.toList());
		if (usuariosSearch.size() > 0) {
			usuarioLogado = usuariosSearch.get(0);
			if (usuarioLogado.getSenha().equals(senha)) {
				System.out.println("Escolha uma opção para iniciar\n");
				if (usuarioLogado.IsEmpresa()) {
					acesso = "Empresa";
				} else if (usuarioLogado.IsCliente()) {
					acesso = "Cliente";
				} else {
					acesso = "";
				}
			} else {
				acesso = "Negado";
				System.out.println("\nSenha incorreta!");
			}
		} else {
			acesso = "Negado";
			System.out.println("\nUsuário não encontrado!");
		}
	}

	public static Usuario getUsuarioLogado() {
		return usuarioLogado;
	}

	public static String getAcesso() {
		return acesso;
	}

	public static void setAcesso(String acesso) {
		AcessarUsuario.acesso = acesso;
	}
}
