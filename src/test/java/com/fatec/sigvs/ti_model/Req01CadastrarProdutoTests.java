package com.fatec.sigvs.ti_model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.util.Arrays;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.fatec.sigvs.model.Produto;
import com.fatec.sigvs.service.IProdutoRepository;

@SpringBootTest
class Req01CadastrarProdutoTests {
	@Autowired
	IProdutoRepository repository;

	@Test
	void ct01_cadastrar_produto_com_sucesso() {
		repository.deleteAll();
		Produto produto1 = new Produto("eletrobomba 110v", "maquina de lavar", "22.30", "10");
		Produto produto2 = new Produto("Tirante Original Brastemp E Consul De 7 A 12 Kg 11cm", "lavar louça", "3.90", "20");
		Produto produto3 = new Produto("Termoatuador Lavadora Colormaq Electrolux GE", "maquina de lavar", "29.70", "40");
		repository.saveAll(Arrays.asList(produto1, produto2, produto3));
		assertEquals(3, repository.count());
	}

	@Test
	void ct02_cadastrar_produto_descricao_invalida() {
		Produto produto1 = null;
		try {
			produto1 = new Produto("", "maquina de lavar", "22.30", "10");
		} catch (Exception e) {
			System.out.println(e.getMessage());
			assertEquals("A descrição não deve estar em branco", e.getMessage());
			assertNull(produto1);
		}
	}

	@Test
	void ct03_cadastrar_produto_custo_invalido() {
		Produto produto1 = null;
		try {
			produto1 = new Produto("eletrobomba 110v", "maquina de lavar", "-1", "10");
		} catch (Exception e) {
			System.out.println(e.getMessage());
			assertEquals("O custo deve ser maior que zero", e.getMessage());
			assertNull(produto1);
		}
	}

	@Test
	void ct04_cadastrar_produto_categoria_invalida() {
		Produto produto1 = null;
		try {
			produto1 = new Produto("eletrobomba 110v", "", "-1", "10");
		} catch (Exception e) {
			System.out.println(e.getMessage());
			assertEquals("A categoria não deve estar em branco", e.getMessage());
			assertNull(produto1);
		}
	}

	@Test
	void ct05_cadastrar_produto_categoria_null() {
		Produto produto1 = null;
		try {
			produto1 = new Produto("eletrobomba 110v", null, "-1","10");
		} catch (Exception e) {
			System.out.println(e.getMessage());
			assertEquals("A categoria não deve estar em branco", e.getMessage());
			assertNull(produto1);
		}
	}

	@Test
	void ct06_cadastrar_produto_descricao_null() {
		Produto produto1 = null;
		try {
			produto1 = new Produto(null, "maquina de lavar", "22.30", "10");
		} catch (Exception e) {
			System.out.println(e.getMessage());
			assertEquals("A descrição não deve estar em branco", e.getMessage());
			assertNull(produto1);
		}
	}

	@Test
	void ct07_cadastrar_produto_quantidade_invalida_zero() {
		Produto produto1 = null;
		try {
			produto1 = new Produto("eletrobomba 110v", "maquina de lavar", "22.30", "0");
		} catch (Exception e) {
			System.out.println(e.getMessage());
			assertEquals("A quantidade deve ser maior que zero", e.getMessage());
			assertNull(produto1);
		}
	}

	@Test
	void ct08_cadastrar_produto_quantidade_invalida_negativo() {
		Produto produto1 = null;
		try {
			produto1 = new Produto("eletrobomba 110v", "maquina de lavar", "22.30", "-1");
		} catch (Exception e) {
			System.out.println(e.getMessage());
			assertEquals("A quantidade no estoque deve ser maior que zero", e.getMessage());
			assertNull(produto1);
		}
	}
}
