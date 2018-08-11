package activeMQ_helloworld;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.MessageListener;
import javax.jms.Queue;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.junit.Test;

public class ActiveMQCustomer {
	@Test
	//普通消费
	public void activeMQCustomer() throws Exception {
		// 创建连接工厂
		// 使用默认的用户名,密码,路径
		ConnectionFactory connectionFactory = new ActiveMQConnectionFactory();
		// 取得连接
		Connection connection = connectionFactory.createConnection();
		//开启连接
		connection.start();
		// 建立会话
		//第一个参数表示的是是否使用事务,如果为true 操作消息队列后必须使用session.commit()
		Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
		// 创建队列或者话题对象
		Queue queue = session.createQueue("Helloworld");
		// 创建生产者或者消费者
		MessageConsumer messageConsumer = session.createConsumer(queue);
		while (true) {
			TextMessage textMessage = (TextMessage) messageConsumer.receive(1000);
			if(textMessage!=null) {
				String text = textMessage.getText();
				System.out.println(text);
			}else {
				break;
			}
		}
	}
	
	
	
	
	@Test
	//监听器消费
	public void activeMQCustomer1() throws Exception {
		// 创建连接工厂
		// 使用默认的用户名,密码,路径
		ConnectionFactory connectionFactory = new ActiveMQConnectionFactory();
		// 取得连接
		Connection connection = connectionFactory.createConnection();
		//开启连接
		connection.start();
		// 建立会话
		//第一个参数表示的是是否使用事务,如果为true 操作消息队列后必须使用session.commit()
		Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
		// 创建队列或者话题对象
		Queue queue = session.createQueue("Helloworld");
		// 创建生产者或者消费者
		MessageConsumer messageConsumer = session.createConsumer(queue);
		//创建消费者监听对象
		messageConsumer.setMessageListener(new MessageListener() {
			
			public void onMessage(Message message) {
				TextMessage textMessage = (TextMessage) message; 
				try {
					System.out.println(textMessage.getText());
				} catch (JMSException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
		});
		//因为是测试类,所以不能让junit线程死掉
		while(true) {
			
		}
	}
	
	
	
}
