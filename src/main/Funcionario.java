package main;

import java.time.LocalDate;
import java.time.Period;

public class Funcionario {
    private String nome;
    private double salarioBase;
    private String cpf;
    private LocalDate dataDeContratacao;

    public Funcionario(String nome, double salarioBase, String cpf, LocalDate dataDeContratacao) {
        this.nome = nome;
        this.salarioBase = salarioBase;
        this.cpf = cpf;
        this.dataDeContratacao = dataDeContratacao;
    }

    public double calculaSalarioFinal() {
        return salarioBase;
    }

    public int getAnosDeServico() {
        return Period.between(dataDeContratacao, LocalDate.now()).getYears();
    }

    public String getNome() {
        return nome;
    }

    public double getSalarioBase() {
        return salarioBase;
    }

}
