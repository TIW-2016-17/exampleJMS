package jhc.jms;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;

import java.io.PrintWriter;

import javax.annotation.Resource;
import javax.jms.ConnectionFactory;
import javax.jms.JMSException;
import javax.jms.Queue;
import javax.jms.Connection;
import javax.jms.Session;
import javax.jms.MessageProducer;
import javax.jms.TextMessage;
import javax.jms.TopicSession;

/**
 * Servlet implementation class SendMessageQueueServlet
 */
@WebServlet(urlPatterns = { "/SendMessageQueue.html" })
public class SendMessageQueueServlet extends HttpServlet {
	private static final String MESSAGE_PARAMETER = "message";

	private static final long serialVersionUID = 1L;

	// Inject the connectionFactory using annotations
	@Resource
	private ConnectionFactory tiwconnectionfactory;
	// Inject the queue using annotations
	@Resource(mappedName="tiwqueue")
	private Queue queue;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public SendMessageQueueServlet() {
		super();

	}

	public void doPost(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response)
			throws javax.servlet.ServletException, java.io.IOException {

		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		out.println("<!DOCTYPE html>");
		out.println("<html>");
		out.println("<head><title>Sending message to a queue</title></head>");
		out.println("<body>");
		out.println("<h1><u>Sending the message</u></h1>");

		try {

			// - In the following steps we write the message and send it
			// First create a connection using the connectionFactory
			try (Connection connection = tiwconnectionfactory.createConnection()) {
				// Next create the session. Indicate that transaction will not
				// be supported
				try (Session session = connection.createSession(false, TopicSession.AUTO_ACKNOWLEDGE);) {

					// Now use the session to create a message producer
					// associated to the queue

					try (MessageProducer messageProducer = session.createProducer(queue)) {

						// Now use the session to create a text message

						TextMessage textMessage = session.createTextMessage();

						// We retrieve the parameter 'message' from the request,
						// and use it as text of our message

						textMessage.setText(request.getParameter(MESSAGE_PARAMETER));

						// Use the message producer to send the message

						messageProducer.send(textMessage);

					} // Close the producer
					
				} // Close the session

			} // Close the connection

			out.println(" Menssage sent </br>");

		} catch (JMSException e) {
			System.out.println("JHC *************************************** Error in doPost: " + e);
			System.out.println(
					"JHC *************************************** Error MQ: " + e.getLinkedException().getMessage());
			System.out.println(
					"JHC *************************************** Error MQ: " + e.getLinkedException().toString());
			out.println(" Error when sending the message</BR>");

		} catch (Exception e) {
			System.out.println("JHC *************************************** Error in doPost: " + e.toString());
			out.println(" Error when sending the message</BR>");

		}

		out.println(" >>>>>>  <a href=\"SendMessageToQueue.html\">Back</a></p>");
		out.println("</body></html>");
	}

	public void doGet(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response)
			throws javax.servlet.ServletException, java.io.IOException {

		try {
			// Llamamos al método doPost con los parametros que recibe este
			// método
			doPost(request, response);
		} catch (Exception e) {
			System.out.println("JHC ***************************************Error in doGet: " + e);
		}

	}

}
