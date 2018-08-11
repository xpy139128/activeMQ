package activeMQ_helloworld;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.MessageProducer;
import javax.jms.Queue;
import javax.jms.Session;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.junit.Test;

public class ActiveMQProducer {

	@Test
	public void produceActiveMQ() throws Exception {
		//连接工厂
		//使用默认的用户名,密码,路径
		ConnectionFactory connectionFactory = new ActiveMQConnectionFactory();
		//获取一个连接
		Connection connection = connectionFactory.createConnection();
		//建立会话
		Session session = connection.createSession(true, Session.AUTO_ACKNOWLEDGE);
		//创建队列或者话题对象
		Queue queue = session.createQueue("Helloworld");
		//创建生产者或者消费者
		MessageProducer messageProducer = session.createProducer(queue);
		
		for(int i = 0;i<10;i++) {
			//发送消息
			messageProducer.send(session.createTextMessage("你好!ActiveMQ"+i));
		}
		session.commit();
		
	}

}
