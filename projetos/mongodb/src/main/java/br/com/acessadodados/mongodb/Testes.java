package br.com.acessadodados.mongodb;

import org.bson.Document;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import static com.mongodb.client.model.Filters.*;

public class Testes {

	public static void main(String[] args) {
		
		// Para colocar o servidor do Mongo, crie um container em docker.
		// Para verificar os dados, pode-se utilizar a IDE MongoDB Compass
		// Documentação do driver: http://mongodb.github.io/mongo-java-driver/3.9/driver/getting-started/quick-start/#find-all-documents-in-a-collection
		
		MongoClient mongoClient = MongoClients.create("mongodb://admin:123456@localhost:27017");
		
		MongoDatabase database = mongoClient.getDatabase("vendas");
		
		
		MongoCollection<Document> collection = database.getCollection("pessoas");
		
		criarDococumento(collection, "Maria", 42, "M");
		criarDococumento(collection, "João", 39, "F");
		criarDococumento(collection, "Ciclano", 8, "M");
		
		collection.updateOne(eq("idade", 42), new Document("$set", new Document("nome", "Paulo Cesar")));
		
		for (Document cur : collection.find()) {
		    System.out.println(cur.toJson());
		}		
		
		collection.deleteOne(eq("idade", 42));
		
		mongoClient.close();
	}

	@SuppressWarnings("unused")
	private static void criarDococumento(MongoCollection<Document> collection, String nome, Integer idade, String sexo) {
		Document doc = new Document("nome", nome)
                .append("idade", idade)
				.append("sexo", sexo);
		
		collection.insertOne(doc);
	}
}
