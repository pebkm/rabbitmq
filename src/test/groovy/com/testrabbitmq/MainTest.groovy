package com.testrabbitmq

//import static org.junit.jupiter.Assert.*
import org.junit.jupiter.api.Disabled
import org.junit.jupiter.api.Test
import java.time.LocalDateTime

import com.rabbitmq.client.AMQP

class MainTest{

    @Test
    //@Disabled
    void simpleTest(){
        def producer = new RabbitMQProducer("192.168.64.2",5672,"guest","guest","testvhost")
        AMQP.BasicProperties.Builder builder = new AMQP.BasicProperties.Builder()
        def properties = builder.deliveryMode(2).build()
        def msg = "This message is published in RabbitMQ at " + LocalDateTime.now()
        
        def queueName = "testq"
        producer.declareQueue(queueName)
        
        producer.publishMQMessage("",queueName,null,msg.bytes)
        

        def consumer = new RabbitMQConsumer("192.168.64.2",5672,"guest","guest","testvhost")
        consumer.consumeMQMessage(queueName)
        consumer.closeConnection()
        
    }
}