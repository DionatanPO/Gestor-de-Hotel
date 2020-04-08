package Control;

import Model.Apartamento;
import Model.Hospedagem;
import Model.Hospede;
import Model.dao.HospedagemDao;
import java.time.LocalDate;
import java.util.List;

public class HospedagemControl {

    public void receberDados(List<String> dados, Apartamento ap) {
        Hospedagem h = new Hospedagem();
        HospedagemDao hd = new HospedagemDao();
        Hospede hospede = new Hospede();
        //manipulando data do dia
        LocalDate hoje = LocalDate.now();
       // java.sql.Date mySqlDate = java.sql.Date.valueOf(hoje);

        h.setHospede(hospede);
        System.out.println(ap);
        h.setApartamento(ap);
        h.getHospede().setNomeHospede(dados.get(0));
        h.setDataEntrada(hoje);
        h.setNumeroPessoas(Integer.parseInt(dados.get(1)));
        h.setValorHospedagem(Double.parseDouble(dados.get(2)));
        h.setStatuspg(dados.get(3));
        
        //testar notificacoes com data ja vencida
        //hoje.plusDays(1);
        
        hoje = hoje.plusDays(1);
       // mySqlDate = java.sql.Date.valueOf(hoje);

        h.setDataSaida(hoje);

        if (6 == dados.size()) {
            if (dados.get(5).equals("Alterar")) {
                h.setIdHospedagem(Integer.parseInt(dados.get(4)));
                hd.alterarHospedagem(h);
            }
        } else {
            hd.cadastrarHospedagem(h);
        }
     

    }

}
