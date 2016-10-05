package jhc.jms;

import java.io.IOException;
import java.io.PrintWriter;

import javax.annotation.Resource;
import javax.jms.ConnectionFactory;
import javax.jms.Message;
import javax.jms.Queue;
import javax.jms.Session;
import javax.jms.TextMessage;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class ReadMessageQueueServlet
 */
@WebServlet({ "/ReadMessageQueue.html" })
public class ReadMessageQueueServlet extends HttpServlet {
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
    public ReadMessageQueueServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		try {
			doPost(request, response);
		} catch (Exception e) {
			System.out.println(
				"JHC ***************************************Error in doGet: "
					+ e);
		}		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub

		response.setContentType("text/html");
		PrintWriter out = response.getWriter();

		out.println("<html>");
		out.println("<head><title>Example of reading messages from a queue</title></head>");
		out.println("<body>");
		out.println("<H1><U>Reading messages</U></H1>");

		try {

			
			// Create a connection using the connectionFactory
		      
		      // Next create the session. Indicate that transaction will not be supported
				
					
			//Start connection
	
			// USe the session to create a consumer
			
			out.println(" Menssages sent </BR>");
			Message mensaje = null;
			while (true) {
				// Use the message consumer to try to retrieve a message. Timing 1000
				
				if (mensaje != null) {
					// Check if the message is an instance of TextMessage
					// If it is an instance of TextMessage, cast it and add it to the out
				} else // there are no messages
					{
					out.println(" No more messages </br>");
					break;
				}

			}

			//Close the session
			
			//Close the connection
			
		} catch (Exception e) {
			System.out.println(
				"JHC *************************************** Error in doPost: "
					+ e);
		}
		out.println(
		" >>>>>>  <A href=\"SendMessageToQueue.html\">Back</A></P>");
		out.println("</body></html>");
			
	}

}
