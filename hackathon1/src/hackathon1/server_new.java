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
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
//import com.google.gson.Gson;  

/**
 * Servlet implementation class AuthenticateServlet
 */

@WebServlet("/server_new")
public class server_new extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static String temp1=null;
	private static String message=null;
	private static ArrayList<Message> list1=null;
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
	   {System.out.println("inside idoitghhff");

	    response.getWriter().write("idiot1"); 
		   updateLocation(verification,x_co,y_co);
		   if(list1!=null)
		   {
			   List<Integer> index=checkid(list1, verification);
			   if(index.size()>0)
			   {
				   String result="";
				   for(int i=0;i<index.size();i++)
				   {
					   result+=list1.get(i).getId()+":  "+list1.get(i).getMessage();
					   list1.remove(i);
				   }
				    response.getWriter().write(result);
				  
			   }
			   else
			   {
				   System.out.println("inside idoit");
				    response.getWriter().write("idiot");
			   }
		   }
		  /*if(temp1!=null)
		   {
			   if(temp1==verification && message!=null)
			   {
				    response.getWriter().write(message); 
				    message=null;
				    temp1=null;
			   }
			   else
				   
			   {
				 System.out.println("inside idoit");
				    response.getWriter().write("idiot"); 

			   }
		   }
			 
	*/

	   }
	   else
	   {
		   String result = "";
		   updateLocation(verification,x_co,y_co);
		   ArrayList<GPSLocation> gpsLocations=getLocations(verification);
//		 Gson gson = new Gson();  
		   message=request.getParameter("message");
		   System.out.println(message);
		   if(gpsLocations!=null)
		   {
			   Double max=1000000.0;
			   int size=gpsLocations.size()-1;
			   int count=0;
			   for(int i=0;i<gpsLocations.size();i++)
			   {
				   
				   GPSLocation gps=gpsLocations.get(i);
				   if(!gps.getId().equals(verification))
				   {
					   if(count==0)
					  result+=gps.getId()+","+gps.getX().toString()+","+gps.getY().toString();
					   else
				      result+=","+gps.getId()+","+gps.getX().toString()+","+gps.getY().toString();
	                 count++;
					   Double x1=gps.getX();
				   Double y1=gps.getY();
				   System.out.println(x1+"    "+y1);

				   Double temp=(x_co-x1)*(x_co-x1)+(y_co-y1)*(y_co-y1);
				   System.out.println(result);
				     if(temp<max)
				     {
				    	 if(list1==null)
				    		 list1=new ArrayList<Message>();
					   temp1=gps.getId();
					   System.out.println("id is  "+temp1);
					   list1.add(new Message(temp1,message));
				     }
				   }
					   
			   }
				    response.getWriter().write(result); 

			   
			   
		   }
	   }
	    
	  }	//creating new us
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
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
	   {System.out.println("inside idoitghhff");

	    response.getWriter().write("idiot1"); 
		   updateLocation(verification,x_co,y_co);
		   if(list1!=null)
		   {
			   List<Integer> index=checkid(list1, verification);
			   if(index.size()>0)
			   {
				   String result="";
				   for(int i=0;i<index.size();i++)
				   {
					   result+=list1.get(i).getId()+":  "+list1.get(i).getMessage();
					   list1.remove(i);
				   }
				    response.getWriter().write(result);
				  
			   }
			   else
			   {
				   System.out.println("inside idoit");
				    response.getWriter().write("idiot");
			   }
		   }
		  /*if(temp1!=null)
		   {
			   if(temp1==verification && message!=null)
			   {
				    response.getWriter().write(message); 
				    message=null;
				    temp1=null;
			   }
			   else
				   
			   {
				 System.out.println("inside idoit");
				    response.getWriter().write("idiot"); 

			   }
		   }
			 
	*/

	   }
	   else
	   {
		   String result = "";
		   updateLocation(verification,x_co,y_co);
		   ArrayList<GPSLocation> gpsLocations=getLocations(verification);
//		 Gson gson = new Gson();  
		   message=request.getParameter("message");
		   System.out.println(message);
		   if(gpsLocations!=null)
		   {
			   Double max=1000000.0;
			   int size=gpsLocations.size()-1;
			   int count=0;
			   for(int i=0;i<gpsLocations.size();i++)
			   {
				   
				   GPSLocation gps=gpsLocations.get(i);
				   if(!gps.getId().equals(verification))
				   {
					   if(count==0)
					  result+=gps.getId()+","+gps.getX().toString()+","+gps.getY().toString();
					   else
				      result+=","+gps.getId()+","+gps.getX().toString()+","+gps.getY().toString();
	                 count++;
					   Double x1=gps.getX();
				   Double y1=gps.getY();
				   System.out.println(x1+"    "+y1);

				   Double temp=(x_co-x1)*(x_co-x1)+(y_co-y1)*(y_co-y1);
				   System.out.println(result);
				     if(temp<max)
				     {
				    	 if(list1==null)
				    		 list1=new ArrayList<Message>();
					   temp1=gps.getId();
					   System.out.println("id is  "+temp1);
					   list1.add(new Message(temp1,message));
				     }
				   }
					   
			   }
				    response.getWriter().write(result); 

			   
			   
		   }
	   }
	    
	  }	//creating new us
		}
	
	public List<Integer> checkid(ArrayList<Message> message,String ver)
	{
		List<Integer> result=new ArrayList<Integer>();
		for(int i=0;i<message.size();i++)
		{
			if(message.get(i).getId()==ver)
				result.add(i);
		}
		return result;
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
				System.out.println("get dat   "+rs.getString("identity")+" "+rs.getDouble("x")+" "+rs.getDouble("y"));
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
