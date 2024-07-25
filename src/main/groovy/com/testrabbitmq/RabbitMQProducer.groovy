package com.testrabbitmq

import com.rabbitmq.client.Channel

class RabbitMQProducer extends RabbitMQConnection {

    RabbitMQProducer(String host, int port, String username, String password, String virtualHost) {
        super(host, port, username, password, virtualHost)
    }
    
    void declareQueue(String queueName) {
        try{
            this.channel.queueDeclare(queueName, true, false, false, null)
        }
        catch(Exception e){
            println("Error in Queue Declare : " + e)
        }
        
    }
    
    void publishMQMessage(String exchange, String routingKey, String props, Byte [] message){
        try{  
            this.channel.basicPublish(exchange, routingKey, props, message)
            println("Message published to " + routingKey + "\n")

        }catch(IOException ioe){
            println("Error in Producer : " + ioe)
        }
    }
}