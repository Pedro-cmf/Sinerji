package main;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {

        List<Funcionario> funcionarios = new ArrayList<>();

        Funcionario jorge = new Secretario("Jorge Carvalho", 7000, "12345678900", LocalDate.of(2018, 1, 1));
        Funcionario maria = new Secretario("Maria Souza", 7000, "12345678901", LocalDate.of(2015, 12, 1));

        Vendedor ana = new Vendedor("Ana Silva", 12000, "12345678902", LocalDate.of(2021, 12, 1));
        ana.adicionaVenda(2021, 12, 5200);
        ana.adicionaVenda(2022, 1, 4000);
        ana.adicionaVenda(2022, 2, 4200);
        ana.adicionaVenda(2022, 3, 5850);
        ana.adicionaVenda(2022, 4, 7000);

        Vendedor joao = new Vendedor("João Mendes", 12000, "12345678903", LocalDate.of(2021, 12, 1));
        joao.adicionaVenda(2021, 12, 3400);
        joao.adicionaVenda(2022, 1, 7700);
        joao.adicionaVenda(2022, 2, 5000);
        joao.adicionaVenda(2022, 3, 5900);
        joao.adicionaVenda(2022, 4, 6500);

        Funcionario juliana = new Gerente("Juliana Alves", 20000, "12345678904", LocalDate.of(2017, 7, 1));
        Funcionario bento = new Gerente("Bento Albino", 20000, "12345678905", LocalDate.of(2014, 3, 1));

        funcionarios.add(jorge);
        funcionarios.add(maria);
        funcionarios.add(ana);
        funcionarios.add(joao);
        funcionarios.add(juliana);
        funcionarios.add(bento);

        FolhaDePagamento folha = new FolhaDePagamento(funcionarios);
        folha.calcularFolha();
    }
}
