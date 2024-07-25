package com.testrabbitmq

import com.rabbitmq.client.Connection
import com.rabbitmq.client.ConnectionFactory
import com.rabbitmq.client.Channel


class RabbitMQConnection {

    String host
    int port
    String username
    String password
    String virtualHost
    
    Connection connection
    Channel channel

    RabbitMQConnection(String host, int port, String username, String password, String virtualHost) {
        this.host = host
        this.port = port
        this.username = username
        this.password = password
        this.virtualHost = virtualHost
        this.setupConnection()
    }

    void setupConnection() {
        ConnectionFactory factory = new ConnectionFactory()
        factory.setHost(host)
        factory.setPort(port)
        factory.setUsername(username)
        factory.setPassword(password)
        factory.setVirtualHost(virtualHost)

        this.connection = factory.newConnection()
        this.channel = connection.createChannel()
    }

    void closeConnection() {
        if (this.channel != null) {
            this.channel.close()
        }
        if (this.connection != null) {
            this.connection.close()
        }
        
    }
}

