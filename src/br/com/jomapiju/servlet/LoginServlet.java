package br.com.jomapiju.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import twitter4j.Logger;
import twitter4j.Twitter;
import twitter4j.TwitterFactory;
import twitter4j.auth.RequestToken;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		try {
			processRequest(request, response);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void processRequest(HttpServletRequest request,
			HttpServletResponse response) throws Exception, ServletException, IOException {
		// TODO Auto-generated method stub
		Twitter tw = new TwitterFactory().getInstance();
		tw.setOAuthConsumer(Constants.CONSUMER_KEY, Constants.CONSUMER_SECRET);
		RequestToken rqt = tw.getOAuthRequestToken();
		
		String token = rqt.getToken();
		String tokenSecret = rqt.getTokenSecret();
		
		System.out.println("token: "+ token + " secret: " + tokenSecret);
		
		HttpSession ss = request.getSession();
		ss.setAttribute("token", token);
		ss.setAttribute("tokenSecret", tokenSecret);
		
		String authUrl = rqt.getAuthorizationURL();
		
		System.out.println("authUrl: "+ authUrl);
		
		request.setAttribute("authUrl", authUrl);
		RequestDispatcher rd = request.getRequestDispatcher("index.jsp");
		rd.forward(request, response);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			processRequest(request, response);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
