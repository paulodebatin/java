/*
 * Para maiores detalhes: 
 * https://www.baeldung.com/jedis-java-redis-client-library
 * https://www.devmedia.com.br/java-redis-utilizando-o-redis-com-colecoes-java/31124
 */
package br.com.acessodados.redis;

import java.math.BigDecimal;

import com.google.gson.Gson;

import redis.clients.jedis.Jedis;

public class Testes {
	
	public static void main(String[] args) {
		Jedis jedis = new Jedis("localhost");
		Gson gson = new Gson();
		 
		
		Produto produto = new Produto();
		produto.setId(1l);
		produto.setDescricao("Caneta");
		produto.setPreco(BigDecimal.valueOf(12.12));
		produto.setQuantidade(123);
		
		String chave = "produto#" + produto.getId(); 
		
		jedis.set(chave, gson.toJson(produto));
		
		
		Produto produtoCarregado = gson.fromJson(jedis.get(chave),Produto.class);
		System.out.println(produtoCarregado.getDescricao());
		System.out.println(produtoCarregado.getPreco());
		System.out.println(produtoCarregado.getQuantidade());
		
		jedis.close();
	}

}
