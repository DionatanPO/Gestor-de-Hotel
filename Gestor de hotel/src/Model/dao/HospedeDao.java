package Model.dao;

import Conexao.Conexao;
import Model.Hospede;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.table.DefaultTableModel;

public class HospedeDao {

    private Connection con = null;

    public HospedeDao() {
        con = Conexao.Criar_conexao();
    }

    public void cadastrarHospede(Hospede h) {
        PreparedStatement stmt = null;

        String sql = "insert into hospede (nomeHospede) values (?)";

        try {
            stmt = con.prepareStatement(sql);
            stmt.setString(1, h.getNomeHospede());
            stmt.executeUpdate();
            System.out.println("Hospede cadastrado com sucesso!");
        } catch (SQLException ex) {
            System.out.println("Erro ao cadastrar hospede!");
        }
    }

    public void AlterarDadosHospede(Hospede h) {
        PreparedStatement stmt = null;

        String sql = "update hospede set nomeHospede = ? where idHospede = ? ";

        try {
            stmt = con.prepareStatement(sql);
            stmt.setString(1, h.getNomeHospede());
            stmt.setInt(2, h.getIdHospede());
            stmt.executeUpdate();
            System.out.println("Dados do hospede alteradocom sucesso!");
        } catch (SQLException ex) {
            System.out.println("Erro ao alterar dados do hospede!"+ex);
        }
    }

    public ArrayList<Hospede> buscaHospede(String nome) {
        ArrayList<Hospede> listaH = new ArrayList<>();

        Hospede h = new Hospede();

        String sql = ("select * from hospede where nomeHospede like ? ");
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            stmt = con.prepareStatement(sql);
            stmt.setString(1, "%" + nome + "%");
            rs = stmt.executeQuery();

            while (rs.next()) {
                h.setIdHospede(rs.getInt("idHospede"));
                h.setNomeHospede(String.valueOf(rs.getString("nomeHospede")));
                listaH.add(h);
            }
            System.out.println("Busca de hospede ok!");
        } catch (SQLException ex) {
            System.out.println("Erro na busca hospede" + ex);
        } finally {
            Conexao.Fechar_conexao(con, stmt, rs);
        }
        return listaH;
    }

    public void buscaHospedeNome(String nome, DefaultTableModel modelo) {

        Hospede h = new Hospede();

        String sql = ("select * from hospede where nomeHospede like ? ");
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            stmt = con.prepareStatement(sql);
            stmt.setString(1, "%" + nome + "%");
            rs = stmt.executeQuery();

            while (rs.next()) {
                h.setIdHospede(rs.getInt("idHospede"));
                h.setNomeHospede(String.valueOf(rs.getString("nomeHospede")));

                modelo.addRow(new Object[]{
                    h.getIdHospede(),
                    h.getNomeHospede(),}
                );

            }
            System.out.println("Busca de hospede ok!");
        } catch (SQLException ex) {
            System.out.println("Erro na busca hospede" + ex);
        } finally {
            Conexao.Fechar_conexao(con, stmt, rs);
        }

    }

    public void buscaHospedeNome(DefaultTableModel modelo) {

        Hospede h = new Hospede();

        String sql = ("select * from hospede ");
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            stmt = con.prepareStatement(sql);
            rs = stmt.executeQuery();

            while (rs.next()) {
                h.setIdHospede(rs.getInt("idHospede"));
                h.setNomeHospede(String.valueOf(rs.getString("nomeHospede")));

                modelo.addRow(new Object[]{
                    h.getIdHospede(),
                    h.getNomeHospede(),}
                );

            }
            System.out.println("Busca de hospede ok!");
        } catch (SQLException ex) {
            System.out.println("Erro na busca hospede" + ex);
        } finally {
            Conexao.Fechar_conexao(con, stmt, rs);
        }

    }
}
