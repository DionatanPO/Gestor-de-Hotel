package Control;

import Model.Apartamento;
import Model.dao.ApartamentoDao;
import java.util.ArrayList;
import javax.swing.table.DefaultTableModel;

public class ApartamentoControl{


    public void recebeView(ArrayList<String> dados) {
        Apartamento ap = new Apartamento();
        ap.setValor(Double.parseDouble(dados.get(0)));
        ap.setDisponibilidade(dados.get(1));
        ApartamentoDao dao = new ApartamentoDao();
        dao.cadastrarApartamento(ap);
    }

    public void receberViewAlterarDisponibilidade(ArrayList<String> dados) {
        Apartamento ap = new Apartamento();
        ap.setIdApartamento(Integer.parseInt(dados.get(0)));
        ap.setDisponibilidade(dados.get(1));
        ApartamentoDao dao = new ApartamentoDao();
        dao.alterarDisponibilidade(ap);
    }

    public void tabelaRecebeDados(DefaultTableModel modelo) {

        ArrayList<String> tabela = new ArrayList<>();
     
        ApartamentoDao apdao = new ApartamentoDao();

//        modelo.setNumRows(0);
        for (Apartamento ap : apdao.buscarApartamento()) {
            modelo.addRow(new Object[]{
                ap.getIdApartamento(),
                ap.isDisponibilidade(),}
            );
        }
    }
    
}
