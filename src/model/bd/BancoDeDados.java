package model.bd;

import model.Produto;
import model.entidades.Cliente;
import model.entidades.Empresa;
import model.entidades.Usuario;

public class BancoDeDados {

	static Empresa empresa = new Empresa(2, "SafeWay Padaria", "30021423000159", 0.15, 0.0);
	static Empresa empresa2 = new Empresa(1, "Level Varejo", "53239160000154", 0.05, 0.0);
	static Empresa empresa3 = new Empresa(3, "SafeWay Restaurante", "41361511000116", 0.20, 0.0);

	static Produto produto = new Produto(1, "Pão Frances", 5, 3.50, empresa);
	static Produto produto2 = new Produto(2, "Coturno", 10, 400.0, empresa2);
	static Produto produto3 = new Produto(3, "Jaqueta Jeans", 15, 150.0, empresa2);
	static Produto produto4 = new Produto(4, "Calça Sarja", 15, 150.0, empresa2);
	static Produto produto5 = new Produto(5, "Prato feito - Frango", 10, 25.0, empresa3);
	static Produto produto6 = new Produto(6, "Prato feito - Carne", 10, 25.0, empresa3);
	static Produto produto7 = new Produto(7, "Suco Natural", 30, 10.0, empresa3);
	static Produto produto8 = new Produto(8, "Sonho", 5, 8.50, empresa);
	static Produto produto9 = new Produto(9, "Croissant", 7, 6.50, empresa);
	static Produto produto10 = new Produto(10, "Chá Gelado", 4, 5.50, empresa);

	static Cliente cliente = new Cliente("07221134049", "Allan da Silva", "cliente", 20);
	static Cliente cliente2 = new Cliente("72840700050", "Samuel da Silva", "cliente2", 24);

	static Usuario usuario1 = new Usuario("admin", "1234", null, null);
	static Usuario usuario2 = new Usuario("empresa", "1234", null, empresa);
	static Usuario usuario3 = new Usuario("cliente", "1234", cliente, null);
	static Usuario usuario4 = new Usuario("cliente2", "1234", cliente2, null);
	static Usuario usuario5 = new Usuario("empresa2", "1234", null, empresa2);
	static Usuario usuario6 = new Usuario("empresa3", "1234", null, empresa3);

	public static Empresa getEmpresa() {
		return empresa;
	}

	public static Empresa getEmpresa2() {
		return empresa2;
	}

	public static Empresa getEmpresa3() {
		return empresa3;
	}

	public static Produto getProduto() {
		return produto;
	}

	public static Produto getProduto2() {
		return produto2;
	}

	public static Produto getProduto3() {
		return produto3;
	}

	public static Produto getProduto4() {
		return produto4;
	}

	public static Produto getProduto5() {
		return produto5;
	}

	public static Produto getProduto6() {
		return produto6;
	}

	public static Produto getProduto7() {
		return produto7;
	}

	public static Produto getProduto8() {
		return produto8;
	}

	public static Produto getProduto9() {
		return produto9;
	}

	public static Produto getProduto10() {
		return produto10;
	}

	public static Cliente getCliente() {
		return cliente;
	}

	public static Cliente getCliente2() {
		return cliente2;
	}

	public static Usuario getUsuario1() {
		return usuario1;
	}

	public static Usuario getUsuario2() {
		return usuario2;
	}

	public static Usuario getUsuario3() {
		return usuario3;
	}

	public static Usuario getUsuario4() {
		return usuario4;
	}

	public static Usuario getUsuario5() {
		return usuario5;
	}

	public static Usuario getUsuario6() {
		return usuario6;
	}
}
