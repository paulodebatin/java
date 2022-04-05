/**
 * Para maiores informações:
 * https://www.rabbitmq.com/java-client.html
 * https://www.rabbitmq.com/tutorials/tutorial-one-java.html
 */

package br.com.mensageria.rabbitmq;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

public class Send {
	
	private final static String EXCHANGE_NAME = "msg-exchange";
	private final static String QUEUE_NAME = "queue-1";
	
	public static void main(String[] args) throws IOException, TimeoutException {
		//envioParaExchangeTipoDirect();
	    envioParaExchangeTipoFonout();
	    
	    /* Temos um 3 tipo ainda que é o Topic
	     * Neste caso, mando a mensagem com uma key para uma Exchange. Todas as filas desta Exchange que atenderem a key receberão a msg.
	     * Alguns exemplos que podem ser utilizados na amarração da queue com a exchange:
	     *     - routing.#
	     *     - routing.*
	     *     - *.key.*
	     * Mais detalhes em https://faun.pub/different-types-of-rabbitmq-exchanges-9fefd740505d
	     */
	}
	
	
	// Manda para um Exchange e todas as queues/filas com binding para este exchange recebem
	private static void envioParaExchangeTipoFonout() throws IOException, TimeoutException {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");
        try (Connection connection = factory.newConnection();
               Channel channel = connection.createChannel()) {
               channel.exchangeDeclare(EXCHANGE_NAME, "fanout");

               String message = "Hello World 2!";

               channel.basicPublish(EXCHANGE_NAME, "", null, message.getBytes("UTF-8"));
               System.out.println(" [x] Sent '" + message + "'");
           }
    }

	// Manda direto para uma queue/fila
    private static void envioParaExchangeTipoDirect() throws IOException, TimeoutException {
        ConnectionFactory factory = new ConnectionFactory();
		factory.setHost("localhost");
		try (Connection connection = factory.newConnection();
    		Channel channel = connection.createChannel()) {
    		
    		channel.queueDeclare(QUEUE_NAME, false, false, false, null);
    		String message = "Hello World!";
    		channel.basicPublish("", QUEUE_NAME, null, message.getBytes());
    		System.out.println(" [x] Sent '" + message + "'");
		}
    }

}
