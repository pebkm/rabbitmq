package com.testrabbitmq

import com.rabbitmq.client.Connection
import com.rabbitmq.client.Channel
import com.rabbitmq.client.DeliverCallback
import java.nio.charset.StandardCharsets

public class RabbitMQConsumer extends RabbitMQConnection{

    RabbitMQConsumer(String host, int port, String username, String password, String virtualHost) {
        super(host, port, username, password, virtualHost)
    }

    void consumeMQMessage(String queueName){
        try{
    			String mqmessage = ""
				String queue = queueName //routingKey

				DeliverCallback deliverCallback = (consumerTag, message) -> {
					mqmessage = new String(message.getBody(), StandardCharsets.UTF_8)       		
					try {
                		println("Message recieved " + mqmessage);
            		} finally {
                		channel.basicAck(message.getEnvelope().getDeliveryTag(), false);
            		}
				}
				channel.basicConsume(queue, true, deliverCallback, consumerTag -> { })
				println("Done recieving all messages");
		}catch(IOException ioe){
            println("Error : ", ioe)
        }
	}

}