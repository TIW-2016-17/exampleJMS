package jhc.jms;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.PrintWriter;

import javax.annotation.Resource;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.Queue;
import javax.jms.Connection;
import javax.jms.Session;
import javax.jms.MessageProducer;
import javax.jms.TextMessage;


/**
 * Servlet implementation class SendMessageQueueServlet
 */
@WebServlet(urlPatterns = {"/SendMessageQueue.html"})
public class SendMessageQueueServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	 // Inject the connectionFactory using annotations
	 .. . .
	 private ConnectionFactory tiwconnectionfactory;
	 // Inject the queue using annotations
	 . . . 
	 private Queue queue;
	 
	 
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SendMessageQueueServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

			
	public void doPost(
			javax.servlet.http.HttpServletRequest request,
			javax.servlet.http.HttpServletResponse response)
			throws javax.servlet.ServletException, java.io.IOException {
			
			response.setContentType("text/html");
			PrintWriter out = response.getWriter();
			out.println("<html>");
			out.println("<head><title>Sending message to a queue</title></head>");
			out.println("<body>");
			out.println("<H1><U>Sending the message</U></H1>");
			

			try {

				
				// - In the following steps we write the message and send it				
				// First create a connection using the connectionFactory
			      
			      // Next create the session. Indicate that transaction will not be supported
					
				// Now use the session to create a message producer associated to the queue

				 // Now use the session to create a text message

				//  We retrieve the parameter 'message' from the request, and use it as text of our message

				// Use the message producer to send the message						messageProducer.send(textMessage);


				// Close the producer
				
				// Close the session 
				
				// Close the connection 
				
				out.println(" Menssage sent </BR>");

			} catch (javax.jms.JMSException e) {
				System.out.println(
					"JHC *************************************** Error in doPost: "
						+ e);
				System.out.println(
					"JHC *************************************** Error MQ: "
						+ e.getLinkedException().getMessage());
				System.out.println(
					"JHC *************************************** Error MQ: "
						+ e.getLinkedException().toString());		
				out.println(" Error when sending the message</BR>");
		
				
			}catch (Exception e) {
				System.out.println(
					"JHC *************************************** Error in doPost: "
						+ e.toString());
				out.println(" Error when sending the message</BR>");
				
			}
			
			out.println(
			" >>>>>>  <A href=\"SendMessageToQueue.html\">Back</A></P>");
			out.println("</body></html>");
		}

				public void doGet(
					javax.servlet.http.HttpServletRequest request,
					javax.servlet.http.HttpServletResponse response)
					throws javax.servlet.ServletException, java.io.IOException {

					try {
						//Llamamos al m�todo doPost con los parametros que recibe este m�todo
						doPost(request, response);
					} catch (Exception e) {
						System.out.println(
							"JHC ***************************************Error in doGet: "
								+ e);
					}

				}

}
