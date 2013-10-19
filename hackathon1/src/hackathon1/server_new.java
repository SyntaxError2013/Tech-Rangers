package hackathon1;

import hackathon1.maindatabase;
import hackathon1.GPSLocation;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class AuthenticateServlet
 */

@WebServlet("/server_new")
public class server_new extends HttpServlet {
	private static final long serialVersionUID = 1L;
    public server_new() {
        super();
    }
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		System.out.println("hercfggffe enter");	
		System.out.println("here enter");
		//Add code to check whether session is valid
		String verification=request.getParameter("ver");
		maindatabase db=new maindatabase();
		if(db.Login_authenticate(verification)==false);
		else
	    {
        String x_co_str=request.getParameter("x_co");
        Double x_co=Double.parseDouble(x_co_str);
        String y_co_str=request.getParameter("y_co");
        Double y_co=Double.parseDouble(y_co_str);
        String par=request.getParameter("help");
       if(par.equals("U"))
       {
    	   updateLocation(verification,x_co,y_co);

       }
       else
       {
    	   updateLocation(verification,x_co,y_co);
    	   ArrayList<GPSLocation> gpsLocations=getLocations(verification);
    	   if(gpsLocations!=null)
    	   {
    		   Double max=1000000.0;
    		   String idToSent=null;
    		   for(int i=0;i<gpsLocations.size();i++)
    		   {
    			   
    			   GPSLocation gps=gpsLocations.get(i);
    			   if(!gps.getId().equals(verification))
    			   {
    				   Double x1=gps.getX();
    			   Double y1=gps.getY();
    			   Double temp=(x_co-x1)*(x_co-x1)+(y_co-y1)*(y_co-y1);
    			     if(temp<max)
    			     {
    				   idToSent=gps.getId();
    			     }
    			   }
    				   
    		   }
    		   
    	   }
       }
        
	  }	//creating new us
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		System.out.println("hercfggffe post hai enter");	
		System.out.println("here enter");
		//Add code to check whether session is valid
		String verification=request.getParameter("ver");
		maindatabase db=new maindatabase();
		if(db.Login_authenticate(verification)==false);
		else
	    {
        String x_co_str=request.getParameter("x_co");
        Double x_co=Double.parseDouble(x_co_str);
        String y_co_str=request.getParameter("y_co");
        Double y_co=Double.parseDouble(y_co_str);
        String par=request.getParameter("help");
       if(par.equals("U"))
       {
    	   updateLocation(verification,x_co,y_co);

       }
       else
       {
    	   updateLocation(verification,x_co,y_co);
    	   ArrayList<GPSLocation> gpsLocations=getLocations(verification);
    	   if(gpsLocations!=null)
    	   {
    		   Double max=1000000.0;
    		   String idToSent=null;
    		   for(int i=0;i<gpsLocations.size();i++)
    		   {
    			   
    			   GPSLocation gps=gpsLocations.get(i);
    			   if(!gps.getId().equals(verification))
    			   {
    				   Double x1=gps.getX();
    			   Double y1=gps.getY();
    			   Double temp=(x_co-x1)*(x_co-x1)+(y_co-y1)*(y_co-y1);
    			     if(temp<max)
    			     {
    				   idToSent=gps.getId();
    			     }
    			   }
    				   
    		   }
    		   
    	   }
       }
        
	  }
	  //creating new user
		
}
	
	
	public boolean updateLocation(String username, Double x, Double y) {
		 // TODO Auto-generated method stub
		 
		 //INSERT INTO table_name
		 //VALUES (value1,value2,value3,...);
		 
		 System.out.println(" In Add Complaint ");
		 String location="Update gps SET x="+x+", y="+y+" where identity='"+username+"';";
		 
		 Connection con = null;
		 boolean rs = false;  
		 Statement stmt = null;
		 
		 //Load JDBC driver
		  try {
			 Class.forName ("com.mysql.jdbc.Driver").newInstance();
		 	 
			 con = DriverManager.getConnection("jdbc:mysql://localhost:3306/hack?"
		              + "user=root&password=Miniprashu@123");
			 
			  if(!con.isClosed())  
	      	{//successfully connected to database
     		 System.out.println("Successfully d to MySQL server using TCP/IP...in addcomplaint");  
	     	 
     		     stmt=con.createStatement();
     		     System.out.println("sfsdgdgdg");
				  rs = stmt.execute(location);    
     		 
			 	System.out.println("location");
			 	return true;
				 
				 
	      	} 
			 
			 
		 } catch (InstantiationException | IllegalAccessException
				 | ClassNotFoundException e1) {
			 // TODO Auto-generated catch block
			 e1.printStackTrace();
		 } catch (SQLException e) {
			 // TODO Auto-generated catch block
			 e.printStackTrace();
		 }    
		 
	 
		 return false;
	 }
	
	public ArrayList<GPSLocation> getLocations(String user) {
		// returns the keyframe addrs from each folder to be used in index.jsp
		try {

			Class.forName ("com.mysql.jdbc.Driver").newInstance();
		 	 
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/hack?"
		              + "user=root&password=Miniprashu@123");
			Statement st = con.createStatement();
			ResultSet rs = st
					.executeQuery("select * from gps");
			ArrayList<GPSLocation> addrs = new ArrayList<>();
			while (rs.next()) {
				addrs.add(new GPSLocation(rs.getString("identity"),rs.getDouble("x"),rs.getDouble("y")));
			}
			st.close();
			rs.close();
			con.close();
			con = null;
			if (addrs.size() > 0)
				return addrs;
			return null;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
}
