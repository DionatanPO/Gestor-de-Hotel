package Model.dao;

import Conexao.Conexao;
import Model.Apartamento;
import java.util.List;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;


public class ApartamentoDao {

    private Connection con = null;

    public ApartamentoDao() {
        
        con = Conexao.Criar_conexao();
    }

    public void cadastrarApartamento(Apartamento ap) {
        con = Conexao.Criar_conexao();
        
        PreparedStatement stmt = null;
        String sql = "insert into apartamento (valor,disponibilidade) values (?,?)";

        try {
            stmt = con.prepareStatement(sql);
            stmt.setString(1, String.valueOf(ap.getValor()));
            stmt.setString(2, ap.verificarDisponibilidade());
            stmt.executeUpdate();
            System.out.println("Apartamento Salvo");
        } catch (SQLException ex) {
            System.out.println("Erro ao salvar apartamento");
            ex.printStackTrace();
        }finally {
            Conexao.Fechar_conexao(con, stmt);
        }

    }

    public void alterarDisponibilidade(Apartamento ap) {
        
        con = Conexao.Criar_conexao();
        
        PreparedStatement stmt = null;
        String sql = "update apartamento set disponibilidade = ? where idApartamento = ?";
        try {
            stmt = con.prepareStatement(sql);
            stmt.setString(1, ap.isDisponibilidade());
            stmt.setString(2, String.valueOf(ap.getIdApartamento()));
            stmt.executeUpdate();
            System.out.println("Disponibilidade alterada!");
        } catch (SQLException ex) {
            System.out.println("Erro ao alterar disponibilidade!");
            ex.printStackTrace();
        }finally {
            Conexao.Fechar_conexao(con, stmt);
        }

    }

    public List<Apartamento> buscarApartamento() {

        String sql = ("select * from apartamento");
        PreparedStatement stmt = null;
        ResultSet rs = null;

        List<Apartamento> dados = new ArrayList<>();
        try {
            stmt = con.prepareStatement(sql);
            rs = stmt.executeQuery();

            while (rs.next()) {
                Apartamento ap = new Apartamento();
                ap.setIdApartamento(rs.getInt("idApartamento"));
                ap.setValor(rs.getDouble("valor"));
                ap.setDisponibilidade(rs.getString("disponibilidade"));
                dados.add(ap);

            }
        } catch (SQLException ex) {
            System.out.println("Erro na busca" + ex);
        } finally {
            Conexao.Fechar_conexao(con, stmt, rs);
        }
        return dados;

    }
        public List<Apartamento> buscarApartamentoDisponibilidade() {

        String sql = ("select * from apartamento where disponibilidade = 'Disponivel'");
        PreparedStatement stmt = null;
        ResultSet rs = null;

        List<Apartamento> dados = new ArrayList<>();
        try {
            stmt = con.prepareStatement(sql);
            rs = stmt.executeQuery();

            while (rs.next()) {
                Apartamento ap = new Apartamento();
                ap.setIdApartamento(rs.getInt("idApartamento"));
                ap.setValor(rs.getDouble("valor"));
                ap.setDisponibilidade(rs.getString("disponibilidade"));
                dados.add(ap);

            }
        } catch (SQLException ex) {
            System.out.println("Erro na busca" + ex);
        } finally {
            Conexao.Fechar_conexao(con, stmt, rs);
        }
        return dados;

    }

}
