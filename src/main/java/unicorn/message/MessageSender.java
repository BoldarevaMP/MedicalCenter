package unicorn.message;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.jms.JMSProducer;
import javax.jms.Queue;

@Component
public class MessageSender {

    private static final Logger logger = Logger.getLogger(MessageSender.class);

    @Autowired
    private JMSProducer jmsProducer;

    @Autowired
    private Queue queue;

    public void send(String message) {
        jmsProducer.send(queue, message);
        logger.info(String.format("Message is sent with text = '%s'", message));
    }


}
