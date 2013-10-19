package hackathon1;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;


public class maindatabase
{
	 Connection conn = null;	
	 String url = "jdbc:mysql://localhost:3306/";
	 String dbName = "hack";
	 String driver = "com.mysql.jdbc.Driver";
	 String userName = "root";
	 String password = "Miniprashu@123";

	public boolean Login_authenticate(String username)
			{
				 
			 Connection con = null;
			 ResultSet rs = null;  
			 Statement stmt = null; 
			 //used to identify function is called
			 System.out.println(" In Login_authenticate ");
			 try
				{ 
					//Load JDBC driver 
					 Class.forName ("com.mysql.jdbc.Driver").newInstance();															
					try{				          
							con = DriverManager.getConnection("jdbc:mysql://localhost:3306/hack?"
							              + "user=root&password=Miniprashu@123");
                        if(!con.isClosed())  
				         {//successfully connected to database
                        	System.out.println("Successfully connected to MySQL server using TCP/IP...");  
				         //code to check user 
				         String searchQuery ="select * from gps where identity='"+ username+"';" ;			         
						         try {
										 stmt=con.createStatement();
										 rs = stmt.executeQuery(searchQuery);		        
										 boolean more= rs.next();
											 if (!more) 
											 {
											    System.out.println("Sorry, you are not a registered user! Please sign up first");
											    if(con!=null)
												con.close();
											    return false;
											    
											 } 
									 
											 else
											 {
												 System.out.println("you are  a registered");
												 if(con!=null)
														con.close();
											     return true;
											 }
								       } 
						         
							         catch (Exception e) {
										// TODO Auto-generated catch block
							        	//closing the connection
							        	 if(con!=null)
												con.close();
										System.out.println("Can't search  MySQL database");
										e.printStackTrace();
									                      }
				         
				         }//end if(!con.isClosed())
                        
				           }//end try{  //connecting to the database of name feedbackn
					
					    catch(Exception sqle){
					    	//closing connection, it is not necessary here
					    	 if(con!=null)
									con.close();
				   			System.out.println("Can't connect to  MySQL database"); 
				            System.err.print(sqle);  
				                              }    
						finally
						{
							if(con!=null)
								con.close();
							
						}
					
					   
					} //end try   //Load JDBC driver
			 
			catch (Exception e)       
			  { 
			System.out.println("Can't find the MySQL JDBC driver"); 
					
	           } 
				
		    return false;
}

	
			
					 
}
