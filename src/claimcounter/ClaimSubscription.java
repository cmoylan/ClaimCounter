/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package claimcounter;

import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.Consumer;
import com.rabbitmq.client.DefaultConsumer;
import com.rabbitmq.client.Envelope;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author chrismoylan
 */
public class ClaimSubscription {
    private String AMQP_URI = "";

    private ClaimData claimData = new ClaimData();

    Channel channel;
    Connection conn;

    public ClaimSubscription(final ClaimCounter counter) {
        ConnectionFactory  factory = new ConnectionFactory();
        try {
            factory.setUri(AMQP_URI);
            conn = factory.newConnection();

            channel = conn.createChannel();

            boolean autoAck = false;
            String queueName = "claim_count";

            channel.basicConsume(queueName, autoAck, "myConsumerTag",
                new DefaultConsumer(channel) {
                    @Override
                    public void handleDelivery(String consumerTag,
                                    Envelope envelope,
                                    AMQP.BasicProperties properties,
                                    byte[] body)
                    throws IOException
                    {
                        String routingKey = envelope.getRoutingKey();
                        //contentType = properties.contentType;
                        long deliveryTag = envelope.getDeliveryTag();
                        // (process the message components here ...)
                        String message = new String(body);
                        claimData.updateData(message);
                        //System.out.print("updating claimData with:");
                        //System.out.print(message);
                        counter.setGauges(claimData);
                        //System.out.print(message);
                        channel.basicAck(deliveryTag, false);
                    }
                });

        } catch(Exception e) {
            // TODO: bad things
        }
    }

    public void stop() {
        try {
            // TODO: make the consumer tag more meaningful
            channel.basicCancel("myConsumerTag");
            channel.close();
            conn.close();

        } catch (IOException ex) {
            // TODO: bad stuff
            Logger.getLogger(ClaimSubscription.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
