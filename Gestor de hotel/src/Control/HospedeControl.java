package Control;

import Model.Hospede;
import Model.dao.HospedeDao;
import java.util.ArrayList;
import javax.swing.JLabel;

public class HospedeControl {

    public boolean recebeView(ArrayList<String> dados, JLabel txt_alerta) {

        if (validarCampo(dados, txt_alerta) == true) {
            Hospede h = new Hospede();
            h.setNomeHospede(dados.get(0));
            HospedeDao hd = new HospedeDao();
            hd.cadastrarHospede(h);
            return true;
        } else {
            return false;
        }

    }

    public boolean recebeViewAltera(ArrayList<String> dadosAlterados, JLabel txt_alerta) {

        if (validarCampo(dadosAlterados, txt_alerta) == true) {
            Hospede h = new Hospede();
            h.setNomeHospede(dadosAlterados.get(0));
            h.setIdHospede(Integer.parseInt(dadosAlterados.get(1)));
            HospedeDao hd = new HospedeDao();
            hd.AlterarDadosHospede(h);
            return true;

        } else {
            return false;
        }

    }

    public boolean validarCampo(ArrayList<String> s, JLabel txt_alerta) {
        String p = s.get(0);
        boolean contemNumero = false;

        if (p.isEmpty()) {
            txt_alerta.setText("Campo em branco!");
            return false;
        }

        String ss = s.get(0);
        if (ss.matches("^[a-zA-ZÁÂÃÀÇÉÊÍÓÔÕÚÜáâãàçéêíóôõúü]*$")) {     
          //  System.out.println("Só tem letras.");
            return true;
        } else if (ss.matches("^[0-9]*$")) {
            txt_alerta.setText("O campo nome possui numeros!");
           // System.out.println("Só tem numeros.");
            return false;
        } 
        return true;
    }
}
