package main;

import java.time.LocalDate;

public class Secretario extends Funcionario{
    public Secretario(String nome, double salarioBase, String cpf, LocalDate dataDecontratacao){
        super(nome, salarioBase, cpf, dataDecontratacao);
    }


    public double calculaSalarioFinal() {
        double salarioBaseSomado = getSalarioBase() + (getSalarioBase() * 0.20) + 1000;
        return salarioBaseSomado;
    }
}
