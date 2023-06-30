	import java.sql.Connection;
	import java.sql.DriverManager;
	import java.sql.ResultSet;
	import java.sql.Statement;

import com.webArquitectura.Usuario.Arquitecto;
	
public class conecta {
	
	public static void main(String[] args) {
	        try
	        {
	            Class.forName("org.hsqldb.jdbcDriver");
	            Connection con=DriverManager.getConnection(
	                    "jdbc:hsqldb:hsql://localhost/");
	            String instruccion = "SELECT * FROM ARQUITECTO";
	            
	            Statement stmt=con.createStatement();  
	           
	            ResultSet rs=stmt.executeQuery(instruccion);
	            
	            while (rs.next()) {
	            	int id = rs.getInt(1);
	    			String nombre = rs.getString(2);
	    			String apellido = rs.getString(3);
	    			String calle = rs.getString(4);
	    			String ciudad = rs.getString(5);
	    			String usuario = rs.getString(6);
	    			String contrasena = rs.getString(7);
	    			Arquitecto elArquitecto = new Arquitecto(id, nombre, apellido, calle, ciudad, usuario, contrasena); 
	    			System.out.println(elArquitecto);
	            }
	            
	            
	        }
	        catch(Exception e)
	        {
	            System.out.println(e);
	        }
	    }  
	

}
