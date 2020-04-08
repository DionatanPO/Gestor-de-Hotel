
package Model;

public class Hospede {
    private int idHospede;
    private String nomeHospede;

    public Hospede() {
    }

    public Hospede(int idHospede, String nomeHospede) {
        this.idHospede = idHospede;
        this.nomeHospede = nomeHospede;
    }
    
    public int getIdHospede() {
        return idHospede;
    }

    public void setIdHospede(int idHospede) {
        this.idHospede = idHospede;
    }

    public String getNomeHospede() {
        return nomeHospede;
    }

    public void setNomeHospede(String nomeHospede) {
        this.nomeHospede = nomeHospede;
    }
    
    
}
