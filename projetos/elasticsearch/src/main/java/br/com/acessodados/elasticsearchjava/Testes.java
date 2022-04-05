// Para maiores informações, acessar: https://www.elastic.co/guide/en/elasticsearch/client/java-api/current/java-docs.html

package br.com.acessodados.elasticsearchjava;

import java.io.IOException;
import java.util.Map;

import org.apache.http.HttpHost;
import org.elasticsearch.ElasticsearchException;
import org.elasticsearch.action.DocWriteResponse;
import org.elasticsearch.action.get.GetRequest;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.xcontent.XContentBuilder;
import org.elasticsearch.common.xcontent.XContentFactory;
import org.elasticsearch.rest.RestStatus;

public class Testes {

	public static void main(String[] args) throws IOException {
		RestHighLevelClient client = new RestHighLevelClient(
				RestClient.builder(new HttpHost("localhost", 9200, "http"))
		);

		adicionarAtualizarProduto(client);
		buscarProduto(client);
		
		client.close();

	}

	private static void buscarProduto(RestHighLevelClient client) throws IOException {
		// Documentação: https://www.programcreek.com/java-api-examples/?api=org.elasticsearch.action.get.GetRequest
		//				 https://www.elastic.co/guide/en/elasticsearch/client/java-rest/current/java-rest-high-document-get.html
		GetRequest request = new GetRequest("produtos", "1");
		GetResponse response = client.get(request, RequestOptions.DEFAULT);
		if  (response.isExists()) {
			 Map<String, Object> sourceAsMap = response.getSourceAsMap();
			System.out.println(sourceAsMap.get("descricao"));
			System.out.println(sourceAsMap.get("preco"));
			System.out.println(sourceAsMap.get("quantidade"));
		} else {
			System.out.println("Produto não encontrado!");
		}
	}

	private static void adicionarAtualizarProduto(RestHighLevelClient client) throws IOException {
		// Documentação: https://www.elastic.co/guide/en/elasticsearch/client/java-rest/current/java-rest-high-document-index.html
		
		XContentBuilder builder = XContentFactory.jsonBuilder();
		builder.startObject();
		{
		    builder.field("descricao", "Caneta");
		    builder.field("preco", 10.32);
		    builder.field("quantidade", 160);
		}
		builder.endObject();
		
		IndexRequest request = new IndexRequest("produtos").id("1").source(builder);  
		try {
			IndexResponse response = client.index(request, RequestOptions.DEFAULT);
			
			if (response.getResult() == DocWriteResponse.Result.CREATED) {
				System.out.println("Registro criado!");
			    
			} else if (response.getResult() == DocWriteResponse.Result.UPDATED) {
				System.out.println("Registro atualizado!");
			}
			
		} catch (ElasticsearchException e) {
			e.printStackTrace();
			if (e.status() == RestStatus.CONFLICT) {

			}
		}

		// Para verificar a informação gravada, acessar: http://localhost:9200/produtos/_doc/1
	}

}
