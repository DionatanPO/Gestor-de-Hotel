package Conexao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class Conexao { //DAO('Data Acess Object')

   private static final String DRIVE = "com.mysql.jdbc.Driver";
    private static final String URL = "jdbc:mysql://127.0.0.1:3306/hotel";
    private static final String USER = "root";
    private static final String PASS = "";
    
    public static Connection Criar_conexao(){ //criar conexao com servidor
        try {
            Class.forName(DRIVE);
            return (Connection) DriverManager.getConnection(URL,USER, PASS);
        } catch (ClassNotFoundException | SQLException ex) {
            System.out.println("Não foi possivel estabelecer coneçao com o banco de dados");
            return null;
        }
      
    }
    
    public static void Fechar_conexao(Connection con){ //Fechar conexao com o servidor
        if(con != null){ 
            try {
            con.close();
            } catch (SQLException ex) {
                System.out.println("Erro" +ex);
            }
        }
    }
        public static void Fechar_conexao(Connection con, PreparedStatement stmt){ //Fechar conexao com o servidor metodo de sobvrecarga do Fexar conexao
        if(stmt != null){ 
            try {
            stmt.close();
            } catch (SQLException ex) {
                System.out.println("Erro" +ex);
            }
        }
        Fechar_conexao(con);
    }
        public static void Fechar_conexao(Connection con, PreparedStatement stmt, ResultSet rs){ //Fechar conexao com o servidor metodo de sobvrecarga do Fexar conexao
        if(rs != null){ 
            try {
            rs.close();
            } catch (SQLException ex) {
                 System.out.println("Erro" +ex);
            }
        }
        Fechar_conexao(con, stmt);
    }
           
}
