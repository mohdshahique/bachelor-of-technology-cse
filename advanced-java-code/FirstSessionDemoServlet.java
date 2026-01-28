package in.ac.jamiahamdard.SessionMgtServlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
public class FirstSessionDemoServlet extends HttpServlet {
 
  public void doGet(HttpServletRequest request,
                    HttpServletResponse response)
            throws ServletException, IOException
  {
      // Create a session object if it is already not  created.
      HttpSession session = request.getSession();
      
      // Get session creation time.
      Date createTime = new Date(session.getCreationTime());
      // Get last access time of this web page.
      Date lastAccessTime = 
                        new Date(session.getLastAccessedTime());
      String firstName=null;
      String lastName=null;
      String title = "Welcome Back to my website";
      Integer visitCount = Integer.valueOf("1");
      String visitCountKey = new String("visitCount");
      String userIDKey= new String("userID");
      String userIDValue;

      // Check if this is new comer on your web page.
      if (session.isNew()){
         title = "Welcome to my website";
         
         userIDValue = new String(request.getParameter("name"));
         session.setAttribute(userIDKey, userIDValue);
         firstName=request.getParameter("first_name");
         lastName=request.getParameter("last_name");
         session.setAttribute("first_name", firstName);	
         session.setAttribute("last_name", lastName);
         session.setAttribute("User Name",firstName+" "+lastName);
         
      } else {
    	  firstName=(String)session.getAttribute("first_name");
    	  lastName=(String)session.getAttribute("last_name");
         visitCount = (Integer)session.getAttribute(visitCountKey);
         visitCount = visitCount + 1;
         userIDValue = (String)session.getAttribute(userIDKey);
      }
      session.setAttribute(visitCountKey,  visitCount);

      // Set response content type
      response.setContentType("text/html");
      PrintWriter out = response.getWriter();

      String docType =
      "<!doctype html public \"-//w3c//dtd html 4.0 " +
      "transitional//en\">\n";
      out.println(docType +
                "<html>\n" +
                "<head><title>" + title + "</title></head>\n" +
                "<body bgcolor=\"#f0f0f0\">\n" +
                "<h1 align=\"center\">" + title +" "+session.getAttribute("User Name")+ "</h1>\n" +
                 "<h2 align=\"center\">Session Infomation</h2>\n" +
                 "<h5 align=\"right\">"+"<a href=\"logout\">Logout</a>\r\n" +                  
                 "<form action=\"SessionDemo\" align=\"center\" >\n"+
                 "<table border=\"1\" align=\"center\">\n" +
                "<tr bgcolor=\"#949494\">\n" +
                "  <th>Session info</th><th>value</th></tr>\n" +
                "<tr>\n" +
                "  <td>id</td>\n" +
                "  <td>" + session.getId() +  "</td></tr>\n" +
                "<tr>\n" +
                "  <td>Creation Time</td>\n" +
                "  <td>" + createTime + 
                "  </td></tr>\n" +
                "<tr>\n" +
                "  <td>Time of Last Access</td>\n" +
                "  <td>" + lastAccessTime + 
                "  </td></tr>\n" +
                "<tr>\n" +
                "  <td>User ID</td>\n" +
                "  <td>" + userIDValue + 
                "  </td></tr>\n" +
                "  <td>Number of visits</td>\n" +
                "  <td>" +  visitCount+ 
                "  </td></tr>\n" +
                "<tr>\n" +
                "  <td>First Name : </td>\n" +
                "  <td>" +  firstName + "</td></tr>\n" +
                "<tr>\n" +
                "  <td>Last Name : </td>\n" +
                "  <td>" +  lastName + "</td></tr>\n" +
                "<tr>\n" +
                "  <td> </td>\n" +
                "  <td>" +  "<input type=\"submit\" value=\"Re-View Session data and info\" />\n" + "</td></tr>\n" +
                "</table>\n" +                        
                
                "</form>\n"+
                "</body>\n"+
                "</html>");
  
  //session.invalidate();
  //session.setMaxInactiveInterval(60*60);
 // session.getMaxInactiveInterval();
  }
}

