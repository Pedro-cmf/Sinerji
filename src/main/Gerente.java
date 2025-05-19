package main;

import java.time.LocalDate;


public class Gerente extends Funcionario{
    public Gerente(String nome, double salarioBase, String cpf, LocalDate dataDeContratacao){
        super(nome, salarioBase, cpf, dataDeContratacao);
    }

    @Override
    public double calculaSalarioFinal() {
        double salarioBaseSomado = getSalarioBase() + (getSalarioBase() + 3000);
        return salarioBaseSomado;
    }
}
