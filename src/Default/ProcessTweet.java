package Default;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Default.CoreNLP;

/**
 * Servlet implementation class ProcessTweet
 */
@WebServlet("/ProcessTweet")
public class ProcessTweet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	
	protected static CoreNLP m_CoreNLP;
	static String clientSentence;  
	static String result;
	
	
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ProcessTweet() {
        super();
        // TODO Auto-generated constructor stub
    }

    public void init() throws ServletException {
    	
    	
    		m_CoreNLP = new CoreNLP();
		         
	    	System.out.println("Received: " + "Im Going to work today");  
	    	String message = m_CoreNLP.ProccessData("Im Going to work today");
	    	result = message; 
	    	
    	
    	
    	//String text = "Hello, my name is jawad and I love stanford university.";
        //CoreNLP.init();
        //System.out.println("Result : " + CoreNLP.findSentiment(text));
        //score = CoreNLP.findSentiment(text);
    	
    }
    
    
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html; charset=utf-8");
		PrintWriter out = response.getWriter();
		
    	out.println("\n Happiness Score is : "+result);
    	out.close();
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
