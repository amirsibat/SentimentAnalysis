package Default;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONArray;


import Default.MongoConnection;




/**
 * Servlet implementation class GetTweetsFromDb
 */
// @WebServlet("/GetTweetsFromDb")
public class GetTweetsFromDb extends HttpServlet {
	
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GetTweetsFromDb() {
        super();
        // TODO Auto-generated constructor stub
    }
    
    
    
 

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		String uri = request.getRequestURI();
		if(uri.indexOf("search") != -1)
		{
			String search = uri.substring(uri.indexOf("search") + 7);
			//String arg="hello";
			//System.out.println(search);
			try {
			String[] callAndArgs={"C:\\Python27\\python.exe","C:\\Users\\Amir\\workspace\\WebApp\\WebContent\\WEB-INF\\cgi\\stream.py", search};

			 Process p = Runtime.getRuntime().exec(callAndArgs);
			
			 //System.out.print(p);
			

			}

			catch (IOException e) {

			System.out.println("exception occured");

			e.printStackTrace();


			}
			
			}
		try {
		    Thread.sleep(10000);                 //1000 milliseconds is one second.
		} catch(InterruptedException ex) {
		    Thread.currentThread().interrupt();
		}
		
		// TODO Auto-generated method stub
					response.setContentType("application/json; charset=UTF-8");
					//response.setContentType("text/html; charset=UTF-8");
					
					PrintWriter out = response.getWriter();
					JSONArray list = MongoConnection.GetTweetsFromDb();
					
					
					list.writeJSONString(out);
					String jsonText = out.toString();
					System.out.println(jsonText);
					out.close();
		}
		
		
		
		
		
		
		
	

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		
		}
	}


