/**
 * Para maiores informações:
 * https://github.com/Ecwid/consul-api
 * 
 */
package br.com.acessandodados.consul;

import java.math.BigDecimal;

import com.ecwid.consul.v1.ConsulClient;
import com.ecwid.consul.v1.Response;
import com.ecwid.consul.v1.kv.model.GetValue;
import com.google.gson.Gson;


public class Testes {
	
	public static void main(String[] args) {
		ConsulClient client = new ConsulClient("localhost");
		Gson gson = new Gson();
		
		Produto produto = new Produto();
		produto.setId(1l);
		produto.setDescricao("Caneta");
		produto.setPreco(BigDecimal.valueOf(12.12));
		produto.setQuantidade(123);
		String chave = "produto_" + produto.getId(); 
		client.setKVValue(chave, gson.toJson(produto));
		
		Response<GetValue> keyValueResponse = client.getKVValue(chave);
		
		Produto produtoCarregado =  gson.fromJson(keyValueResponse.getValue().getDecodedValue(), Produto.class);
		System.out.println(produtoCarregado.getId());
		System.out.println(produtoCarregado.getDescricao());
		System.out.println(produtoCarregado.getPreco());
		System.out.println(produtoCarregado.getQuantidade());
		
		
		
		
	}

}
