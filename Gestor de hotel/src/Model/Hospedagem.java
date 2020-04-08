package Model;

import java.time.LocalDate;

public class Hospedagem {
    private  int idHospedagem;
    private Hospede hospede;
    private Apartamento apartamento;
    private LocalDate dataEntrada;
    private LocalDate dataSaida;
    private double valorHospedagem;
    private int numeroPessoas;
    private String statuspg;

    public Hospedagem(int idHospedagem, Hospede hospede, Apartamento apartamento, LocalDate dataEntrada, LocalDate dataSaida, double valorHospedagem, int numeroPessoas, String statuspg) {
        this.idHospedagem = idHospedagem;
        this.hospede = hospede;
        this.apartamento = apartamento;
        this.dataEntrada = dataEntrada;
        this.dataSaida = dataSaida;
        this.valorHospedagem = valorHospedagem;
        this.numeroPessoas = numeroPessoas;
        this.statuspg = statuspg;
    }

    public Hospedagem() {
    }
    

    public int getIdHospedagem() {
        return idHospedagem;
    }

    public void setIdHospedagem(int idHospedagem) {
        this.idHospedagem = idHospedagem;
    }

    public Hospede getHospede() {
        return hospede;
    }

    public void setHospede(Hospede hospede) {
        this.hospede = hospede;
    }

    public Apartamento getApartamento() {
        return apartamento;
    }

    public void setApartamento(Apartamento apartamento) {
        this.apartamento = apartamento;
    }

    public LocalDate getDataEntrada() {
        return dataEntrada;
    }

    public void setDataEntrada(LocalDate dataEntrada) {
        this.dataEntrada = dataEntrada;
    }

    public LocalDate getDataSaida() {
        return dataSaida;
    }

    public void setDataSaida(LocalDate dataSaida) {
        this.dataSaida = dataSaida;
    }

    public double getValorHospedagem() {
        return valorHospedagem;
    }

    public void setValorHospedagem(double valorHospedagem) {
        this.valorHospedagem = valorHospedagem;
    }

    public int getNumeroPessoas() {
        return numeroPessoas;
    }

    public void setNumeroPessoas(int numeroPessoas) {
        this.numeroPessoas = numeroPessoas;
    }

    public String getStatuspg() {
        return statuspg;
    }

    public void setStatuspg(String statuspg) {
        this.statuspg = statuspg;
    }
    
    
    
}