 package Model.dao;

import Conexao.Conexao;
import Model.Apartamento;
import Model.Hospedagem;
import Model.Hospede;
import View.Hospedagem.CadastrarHospedagem;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.DefaultTableModel;

public class HospedagemDao {

    private Connection con = null;
    int aux = 0;

    public HospedagemDao() {
        con = Conexao.Criar_conexao();
    }

    CadastrarHospedagem gh = new CadastrarHospedagem();

    public void cadastrarHospedagem(Hospedagem hospedagem) {
        PreparedStatement stmt = null;
        String sql = "insert into hospedagem (dataEntrada,valorHospedagem, numeroPessoa,hospede_idHospede,apartamento_idApartamento,dataSaida,spg) values (?,?,?,?,?,?,?)";
        try {
            stmt = con.prepareStatement(sql);
            stmt.setString(1, String.valueOf(hospedagem.getDataEntrada()));
            stmt.setString(2, String.valueOf(hospedagem.getValorHospedagem()));
            stmt.setString(3, String.valueOf(hospedagem.getNumeroPessoas()));

            HospedeDao hospDao = new HospedeDao();
            ArrayList<Hospede> listHospede = hospDao.buscaHospede(hospedagem.getHospede().getNomeHospede());
            for (Hospede h : listHospede) {
                hospedagem.getHospede().setIdHospede(h.getIdHospede());
                hospedagem.getHospede().setNomeHospede(h.getNomeHospede());
            }
            stmt.setString(4, String.valueOf(hospedagem.getHospede().getIdHospede()));
            stmt.setString(5, String.valueOf(hospedagem.getApartamento().getIdApartamento()));
            stmt.setString(6, String.valueOf(hospedagem.getDataSaida()));
            stmt.setString(7, hospedagem.getStatuspg());
            stmt.executeUpdate();

            sql = "update apartamento set disponibilidade = 'Indisponivel' where idApartamento = " + hospedagem.getApartamento().getIdApartamento() + "";
            stmt = con.prepareStatement(sql);
            stmt.executeUpdate();
            System.out.println("Hospedagem cadastrada com sucesso!");

        } catch (SQLException ex) {

            System.out.println("Erro ao cadastrar hospedagem" + ex);
        }
    }

    public void tabelaRecebeDados(DefaultTableModel modelo) {

        DataInterface di;
        LocalDate dataEntrada = LocalDate.now();
        LocalDate dataSaida = LocalDate.now();
        LocalDate hoje = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        String sql = ("select * from hospede \n"
                + "inner join hospedagem \n"
                + "on hospede_idHospede = idHospede \n"
                + "inner join apartamento\n"
                + "on idApartamento = apartamento_idApartamento \n"
                + "where dataEntrada >= ? and dataEntrada <= ? ORDER BY nomeHospede");
        PreparedStatement stmt = null;
        ResultSet rs = null;

        hoje = hoje.plusDays(-1);
        java.sql.Date mySqlDate = java.sql.Date.valueOf(hoje);

        try {
            stmt = con.prepareStatement(sql);

            stmt.setDate(1, mySqlDate);

            hoje = LocalDate.now();
            mySqlDate = java.sql.Date.valueOf(hoje);

            stmt.setDate(2, mySqlDate);

            rs = stmt.executeQuery();
            Hospedagem hospedagem = new Hospedagem();
            Apartamento ap = new Apartamento();
            Hospede hospede = new Hospede();

            while (rs.next()) {
                hospedagem.setIdHospedagem(rs.getInt("idHospedagem"));
                ap.setIdApartamento(rs.getInt("apartamento_idApartamento"));
                hospede.setIdHospede(rs.getInt("hospede_idHospede"));
                hospede.setNomeHospede(rs.getString("nomeHospede"));
                hospedagem.setApartamento(ap);
                hospedagem.setHospede(hospede);

                mySqlDate = rs.getDate("dataEntrada");

                hospedagem.setDataEntrada(mySqlDate.toLocalDate());

                mySqlDate = rs.getDate("dataSaida");

                hospedagem.setDataSaida(mySqlDate.toLocalDate());

                hospedagem.setNumeroPessoas(rs.getInt("numeroPessoa"));
                hospedagem.setValorHospedagem(rs.getDouble("valorHospedagem"));
                hospedagem.setStatuspg(rs.getString("spg"));

                String dataEntradaF = hospedagem.getDataEntrada().format(formatter);

                String dataSaidaF = hospedagem.getDataSaida().format(formatter);

                modelo.addRow(new Object[]{
                    hospedagem.getIdHospedagem(),
                    hospedagem.getHospede().getNomeHospede(),
                    hospedagem.getApartamento().getIdApartamento(),
                    dataEntradaF,
                    dataSaidaF,
                    hospedagem.getNumeroPessoas(),
                    hospedagem.getValorHospedagem(),
                    hospedagem.getStatuspg()}
                );

            }
        } catch (SQLException ex) {
            System.out.println("Erro na busca" + ex);
        } finally {
            Conexao.Fechar_conexao(con, stmt, rs);
        }

    }

    public ArrayList<String> buscarHospedagensNaoPagaas() {
        java.sql.Date mySqlDate;

        String sql = ("select * from hospede \n"
                + "inner join hospedagem \n"
                + "on hospede_idHospede = idHospede \n"
                + "inner join apartamento\n"
                + "on idApartamento = apartamento_idApartamento "
                + "where dataSaida = (SELECT CURDATE()) and spg = 'Não pago' ORDER BY nomeHospede ");

        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            stmt = con.prepareStatement(sql);
            rs = stmt.executeQuery();

            Hospedagem hospedagem = new Hospedagem();
            Apartamento ap = new Apartamento();
            Hospede hospede = new Hospede();

            while (rs.next()) {

                hospedagem.setIdHospedagem(rs.getInt("idHospedagem"));
                ap.setIdApartamento(rs.getInt("apartamento_idApartamento"));
                hospede.setIdHospede(rs.getInt("hospede_idHospede"));
                hospede.setNomeHospede(rs.getString("nomeHospede"));
                hospedagem.setApartamento(ap);
                hospedagem.setHospede(hospede);
                mySqlDate = rs.getDate("dataEntrada");

                hospedagem.setDataEntrada(mySqlDate.toLocalDate());

                mySqlDate = rs.getDate("dataSaida");

                hospedagem.setDataSaida(mySqlDate.toLocalDate());

                hospedagem.setNumeroPessoas(rs.getInt("numeroPessoa"));
                hospedagem.setValorHospedagem(rs.getDouble("valorHospedagem"));
                aux++;

            }
            ArrayList<String> resultado = new ArrayList<>();
            resultado.add(String.valueOf(aux));

            if (aux == 0) {

                resultado.add("Nenhuma notificação!");
            }
            if (aux > 1) {
                resultado.add("A " + aux + " diárias próximas de serem concluídas, que ainda não foram efetuadas o pagamento!");
            }
            if (aux == 1) {
                resultado.add("A " + aux + " diária próxima de ser concluída, que ainda não foi efetuado o pagamento!");
            }

            return resultado;

        } catch (SQLException ex) {
            System.out.println("Erro na busca" + ex);
        } finally {

        }
        return null;

    }

    public void tabelaBuscarHospedagensNaoPagaas(DefaultTableModel modelo) {
        java.sql.Date mySqlDate;

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        String sql = ("select * from hospede \n"
                + "inner join hospedagem \n"
                + "on hospede_idHospede = idHospede \n"
                + "inner join apartamento\n"
                + "on idApartamento = apartamento_idApartamento "
                + "where dataSaida = (SELECT CURDATE()) and spg = 'Não pago' ORDER BY nomeHospede ");

        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            stmt = con.prepareStatement(sql);
            rs = stmt.executeQuery();

            Hospedagem hospedagem = new Hospedagem();
            Apartamento ap = new Apartamento();
            Hospede hospede = new Hospede();

            while (rs.next()) {
                hospedagem.setIdHospedagem(rs.getInt("idHospedagem"));
                ap.setIdApartamento(rs.getInt("apartamento_idApartamento"));
                hospede.setIdHospede(rs.getInt("hospede_idHospede"));
                hospede.setNomeHospede(rs.getString("nomeHospede"));
                hospedagem.setApartamento(ap);
                hospedagem.setHospede(hospede);

                mySqlDate = rs.getDate("dataEntrada");

                hospedagem.setDataEntrada(mySqlDate.toLocalDate());

                mySqlDate = rs.getDate("dataSaida");

                hospedagem.setDataSaida(mySqlDate.toLocalDate());

                hospedagem.setNumeroPessoas(rs.getInt("numeroPessoa"));
                hospedagem.setValorHospedagem(rs.getDouble("valorHospedagem"));
                hospedagem.setStatuspg(rs.getString("spg"));

                String dataEntradaF = hospedagem.getDataEntrada().format(formatter);

                String dataSaidaF = hospedagem.getDataSaida().format(formatter);

                modelo.addRow(new Object[]{
                    hospedagem.getIdHospedagem(),
                    hospedagem.getHospede().getNomeHospede(),
                    hospedagem.getApartamento().getIdApartamento(),
                    dataEntradaF,
                    dataSaidaF,
                    hospedagem.getNumeroPessoas(),
                    hospedagem.getValorHospedagem(),
                    hospedagem.getStatuspg()}
                );

            }

        } catch (SQLException ex) {
            System.out.println("Erro na busca" + ex);
        } finally {

        }

    }

    public void tabelaRecebeDadosPorData(DefaultTableModel modelo, List<String> lista) {
        DataInterface di;
        java.sql.Date mySqlDate;
        LocalDate dataEntrada = LocalDate.now();
        LocalDate dataSaida = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        String sql = ("select * from hospede \n"
                + "inner join hospedagem \n"
                + "on hospede_idHospede = idHospede \n"
                + "inner join apartamento\n"
                + "on idApartamento = apartamento_idApartamento \n"
                + "where dataEntrada >= ? and dataEntrada <= ? ORDER BY nomeHospede ");
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            stmt = con.prepareStatement(sql);
            stmt.setString(1, lista.get(0));
            stmt.setString(2, lista.get(1));
            rs = stmt.executeQuery();
            Hospedagem hospedagem = new Hospedagem();
            Apartamento ap = new Apartamento();
            Hospede hospede = new Hospede();

            while (rs.next()) {
                hospedagem.setIdHospedagem(rs.getInt("idHospedagem"));
                ap.setIdApartamento(rs.getInt("apartamento_idApartamento"));
                hospede.setIdHospede(rs.getInt("hospede_idHospede"));
                hospede.setNomeHospede(rs.getString("nomeHospede"));
                hospedagem.setApartamento(ap);
                hospedagem.setHospede(hospede);

                mySqlDate = rs.getDate("dataEntrada");

                hospedagem.setDataEntrada(mySqlDate.toLocalDate());

                mySqlDate = rs.getDate("dataSaida");

                hospedagem.setDataSaida(mySqlDate.toLocalDate());

                hospedagem.setNumeroPessoas(rs.getInt("numeroPessoa"));
                hospedagem.setValorHospedagem(rs.getDouble("valorHospedagem"));
                hospedagem.setStatuspg(rs.getString("spg"));

                String dataEntradaF = hospedagem.getDataEntrada().format(formatter);

                String dataSaidaF = hospedagem.getDataSaida().format(formatter);

                modelo.addRow(new Object[]{
                    hospedagem.getIdHospedagem(),
                    hospedagem.getHospede().getNomeHospede(),
                    hospedagem.getApartamento().getIdApartamento(),
                    dataEntradaF,
                    dataSaidaF,
                    hospedagem.getNumeroPessoas(),
                    hospedagem.getValorHospedagem(),
                    hospedagem.getStatuspg()}
                );

            }
        } catch (SQLException ex) {
            System.out.println("Erro na busca" + ex);
        } finally {
            Conexao.Fechar_conexao(con, stmt, rs);
        }

    }

    public void alterarHospedagem(Hospedagem hospedagem) {

        PreparedStatement stmt = null;

        String sql = "update hospedagem "
                + "set dataEntrada = ? ,"
                + "valorHospedagem = ? ,"
                + "numeroPessoa = ? ,"
                + "hospede_idHospede = ? ,"
                + "apartamento_idApartamento = ? ,"
                + "dataSaida = ?,"
                + "spg = ? "
                + "where idHospedagem = ?";
        try {
            stmt = con.prepareStatement(sql);
            stmt.setString(1, String.valueOf(hospedagem.getDataEntrada()));
            stmt.setString(2, String.valueOf(hospedagem.getValorHospedagem()));
            stmt.setString(3, String.valueOf(hospedagem.getNumeroPessoas()));

            HospedeDao hospDao = new HospedeDao();
            ArrayList<Hospede> listHospede = hospDao.buscaHospede(hospedagem.getHospede().getNomeHospede());
            for (Hospede h : listHospede) {
                hospedagem.getHospede().setIdHospede(h.getIdHospede());
                hospedagem.getHospede().setNomeHospede(h.getNomeHospede());
            }
            stmt.setString(4, String.valueOf(hospedagem.getHospede().getIdHospede()));
            stmt.setString(5, String.valueOf(hospedagem.getApartamento().getIdApartamento()));
            stmt.setString(6, String.valueOf(hospedagem.getDataSaida()));
            stmt.setString(7, hospedagem.getStatuspg());
            stmt.setString(8, String.valueOf(hospedagem.getIdHospedagem()));
            stmt.executeUpdate();

            sql = "update apartamento set disponibilidade = 'Indisponivel' where idApartamento = " + hospedagem.getApartamento().getIdApartamento() + "";
            stmt = con.prepareStatement(sql);
            stmt.executeUpdate();
            System.out.println("Hospedagem alterada com sucesso!");

        } catch (SQLException ex) {

            System.out.println("Erro ao alterar hospedagem" + ex);
        }
    }
    
    public void alterarStatusPagamento(ArrayList<String> hospedagem) {

        PreparedStatement stmt = null;

        String sql = "update hospedagem set spg = ? where idHospedagem = ?";
        try {
            stmt = con.prepareStatement(sql);

            stmt.setString(1, hospedagem.get(0));
            stmt.setString(2,hospedagem.get(1));
            stmt.executeUpdate();
            
            System.out.println("Status pagamento alterado");

        } catch (SQLException ex) {

            System.out.println("Erro ao alterar status do pagamento" + ex);
        }
    }
    
    public void apagarHospedagem(Hospedagem h){
         PreparedStatement stmt = null;
        String sql = "delete from hospedagem where idHospedagem = ?";
        

        try {
            stmt = con.prepareStatement(sql);
            stmt.setInt(1, h.getIdHospedagem());
            stmt.executeUpdate();
            System.out.println("Hospedagem apagada");
        } catch (SQLException ex) {
            System.out.println("Erro ao apagar hospedagem"+ex);
        }
        
    }
}
