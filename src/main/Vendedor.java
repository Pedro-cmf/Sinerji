package main;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

public class Vendedor extends Funcionario {
    private Map<Integer, Map<Integer, Double>> vendas = new HashMap<>();

    public Vendedor(String nome, double salarioBase, String cpf, LocalDate dataDeContratacao) {
        super(nome, salarioBase, cpf, dataDeContratacao);
    }

    public void adicionaVenda(int ano, int mes, double valor) {
        vendas.putIfAbsent(ano, new HashMap<>());
        Map<Integer, Double> vendasPorMes = vendas.get(ano);
        vendasPorMes.put(mes, vendasPorMes.getOrDefault(mes, 0.0) + valor);
    }

    public double getTotalVendas() {
        return vendas.values().stream()
                .flatMap(mesMap -> mesMap.values().stream())
                .mapToDouble(Double::doubleValue)
                .sum();
    }

    @Override
    public double calculaSalarioFinal() {
        return getSalarioBase() + (getSalarioBase() * 0.30) + 1800;
    }

    public Map<Integer, Map<Integer, Double>> getVendas() {
        return vendas;
    }
}
