package jhc.jms;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;

import javax.annotation.Resource;
import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Queue;
import javax.jms.QueueBrowser;
import javax.jms.Session;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class ReadMessageQueueBrowserServlet
 */
@WebServlet({ "/ReadMessageQueueBrowserServlet", "/ReadMessageQueueBrowser.html" })
public class ReadMessageQueueBrowserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	// Inject the connectionFactory using annotations
	@Resource
	private ConnectionFactory tiwconnectionfactory;
	// Inject the queue using annotations
	@Resource(mappedName = "tiwqueue")
	private Queue queue;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ReadMessageQueueBrowserServlet() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		try {
			doPost(request, response);
		} catch (Exception e) {
			System.out.println("JHC ***************************************Error in doGet: " + e);
		}

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		out.println("<!DOCTYPE html>");
		out.println("<html>");
		out.println("<head><title>An example on how to read messages in browser mode</title></head>");
		out.println("<body>");
		out.println("<h1><em>Reading a message in browser mode</rm></h1>");

		try {

			// Create a connection using the connectionFactory
			try (Connection connection = tiwconnectionfactory.createConnection();) {
				boolean bTransacted = false;

				int iAcknowledgeMode = javax.jms.Session.CLIENT_ACKNOWLEDGE;

				// Next create the session. Indicate that transaction will not
				// be
				// supported
				try (Session session = connection.createSession(bTransacted, iAcknowledgeMode);) {

					// USe the session to create a browser associated to the
					// queue

					try (QueueBrowser browser = session.createBrowser(queue);) {

						// Start the connection
						connection.start();

						Enumeration<?> enum1 = browser.getEnumeration();

						//Vector vctMessage = new java.util.Vector();

						// User the browser to retrieve a collection
						// (enumeration) of
						// messages
						// java.util.Enumeration enum1 = ..

						while (enum1.hasMoreElements()) {
							// Get a message from the enumeration
							javax.jms.Message message = (javax.jms.Message) enum1.nextElement();
							javax.jms.Message message2 = message;
							if (message2 != null)
								// Check if the message is an instance of
								// TextMessage
								if (message2 instanceof javax.jms.TextMessage) {
								// If it is an instance of TextMessage, cast it
								// and add it
								// to the out
								javax.jms.TextMessage Tmensaje = (javax.jms.TextMessage) message2;
								out.println("  Message: " + Tmensaje.getText() + "</br>");
								}

						}
						// Stop connection
						connection.stop();

					} // Close browser

				} // Close session

			} // Close connection

		} catch (Exception e) {
			System.out.println("JHC *************************************** Error in doPost: " + e);
		}

		out.println(" >>>>>>  <A href=\"SendMessageToQueue.html\">Back</A></P>");
		out.println("</body></html>");
	}

}
