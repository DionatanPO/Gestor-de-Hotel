package Model;

public class Apartamento {

    private int idApartamento;
    private double valor;
    private String disponibilidade;
    
    public Apartamento() {
    }
    
    public Apartamento(int idApartamento, double valor, String disponibilidade) {
        this.idApartamento = idApartamento;
        this.valor = valor;
        this.disponibilidade = disponibilidade;
    }
    
    public String verificarDisponibilidade() {
        return this.disponibilidade;
    }
    
    public void atualizarDisponibilidade(String disponibilidade) {
        this.setDisponibilidade(disponibilidade);
    }
    
    public int getIdApartamento() {
        return idApartamento;
    }
    
    public void setIdApartamento(int idApartamento) {
        this.idApartamento = idApartamento;
    }
    
    public double getValor() {
        return valor;
    }
    
    public void setValor(double valor) {
        this.valor = valor;
    }
    
    public String isDisponibilidade() {
        return disponibilidade;
    }
    
    public void setDisponibilidade(String disponibilidade) {
        this.disponibilidade = disponibilidade;
    }

    @Override
    public String toString() {
        return String.valueOf(getIdApartamento())+" "+isDisponibilidade(); //To change body of generated methods, choose Tools | Templates.
    }
    
    
}
