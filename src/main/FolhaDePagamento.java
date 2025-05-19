package main;

import java.util.ArrayList;
import java.util.List;

public class FolhaDePagamento {

    private List<Funcionario> funcionarios;

    public FolhaDePagamento(List<Funcionario> funcionarios) {
        this.funcionarios = funcionarios;
    }

    public void calcularFolha() {
        int mes = 4;
        int ano = 2022;

        System.out.println("=== FOLHA DE PAGAMENTO - " + mes + "/" + ano + " ===");

        // Calcula e exibe o valor total pago (salários + benefícios)
        double totalPago = calcularTotalPago(funcionarios, mes, ano);
        System.out.println("Total pago (salários + benefícios): R$ " + totalPago);

        // Calcula e exibe o total pago apenas em salários
        double totalSalarios = calcularTotalSalarios(funcionarios, mes, ano);
        System.out.println("Total pago apenas em salários: R$ " + totalSalarios);

        // Calcula e exibe o total pago em benefícios
        List<Funcionario> funcionariosComBeneficios = filtrarFuncionariosComBeneficios();
        double totalBeneficios = calcularTotalBeneficios(funcionariosComBeneficios, mes, ano);
        System.out.println("Total pago em benefícios: R$ " + totalBeneficios);

        // Encontra e exibe o funcionário que recebeu o valor mais alto
        Funcionario maisAlto = funcionarioComValorMaisAlto(funcionarios, mes, ano);
        System.out.println("Funcionário com maior remuneração total: " + maisAlto.getNome() +
                " - R$ " + maisAlto.calculaSalarioFinal());

        // Encontra e exibe o funcionário que recebeu o maior benefício
        Funcionario maiorBeneficio = funcionarioComMaiorBeneficio(funcionariosComBeneficios, mes, ano);
        if (maiorBeneficio != null) {
            System.out.println("Funcionário com maior valor em benefícios: " + maiorBeneficio.getNome());
        }

        // Encontra e exibe o vendedor que mais vendeu
        List<Vendedor> vendedores = filtrarVendedores();
        Vendedor melhorVendedor = vendedorQueMaisVendeu(vendedores, mes, ano);
        if (melhorVendedor != null) {
            System.out.println("Vendedor com maior volume de vendas: " + melhorVendedor.getNome() +
                    " - R$ " + obterVendasNoMes(melhorVendedor, mes, ano));
        }
    }

    public double calcularTotalPago(List<Funcionario> funcionarios, int mes, int ano) {
        double total = 0;
        for (Funcionario funcionario : funcionarios) {
            total += funcionario.calculaSalarioFinal();
        }
        return total;
    }

    public double calcularTotalSalarios(List<Funcionario> funcionarios, int mes, int ano) {
        double total = 0;
        for (Funcionario funcionario : funcionarios) {
            if (funcionario instanceof Secretario) {
                total += funcionario.getSalarioBase() + (1000 * funcionario.getAnosDeServico());
            } else if (funcionario instanceof Vendedor) {
                total += funcionario.getSalarioBase() + (1800 * funcionario.getAnosDeServico());
            } else if (funcionario instanceof Gerente) {
                total += funcionario.getSalarioBase() + (3000 * funcionario.getAnosDeServico());
            } else {
                total += funcionario.getSalarioBase();
            }
        }
        return total;
    }

    public double calcularTotalBeneficios(List<Funcionario> funcionariosComBeneficios, int mes, int ano) {
        double total = 0;
        for (Funcionario funcionario : funcionariosComBeneficios) {
            if (funcionario instanceof Secretario) {
                // Secretários recebem 20% do salário base como benefício
                total += funcionario.getSalarioBase() * 0.2;
            } else if (funcionario instanceof Vendedor) {
                // Vendedores recebem 30% do valor vendido como benefício
                Vendedor vendedor = (Vendedor) funcionario;
                total += obterVendasNoMes(vendedor, mes, ano) * 0.3;
            }
        }
        return total;
    }

    public Funcionario funcionarioComValorMaisAlto(List<Funcionario> funcionarios, int mes, int ano) {
        if (funcionarios.isEmpty()) {
            return null;
        }

        Funcionario funcionarioMaiorValor = funcionarios.get(0);
        double maiorValor = funcionarioMaiorValor.calculaSalarioFinal();

        for (Funcionario funcionario : funcionarios) {
            double salarioAtual = funcionario.calculaSalarioFinal();
            if (salarioAtual > maiorValor) {
                maiorValor = salarioAtual;
                funcionarioMaiorValor = funcionario;
            }
        }

        return funcionarioMaiorValor;
    }

    public Funcionario funcionarioComMaiorBeneficio(List<Funcionario> funcionariosComBeneficios, int mes, int ano) {
        if (funcionariosComBeneficios.isEmpty()) {
            return null;
        }

        Funcionario funcionarioMaiorBeneficio = funcionariosComBeneficios.get(0);
        double maiorBeneficio = calcularBeneficioFuncionario(funcionarioMaiorBeneficio, mes, ano);

        for (Funcionario funcionario : funcionariosComBeneficios) {
            double beneficioAtual = calcularBeneficioFuncionario(funcionario, mes, ano);
            if (beneficioAtual > maiorBeneficio) {
                maiorBeneficio = beneficioAtual;
                funcionarioMaiorBeneficio = funcionario;
            }
        }

        return funcionarioMaiorBeneficio;
    }

    private double calcularBeneficioFuncionario(Funcionario funcionario, int mes, int ano) {
        if (funcionario instanceof Secretario) {
            return funcionario.getSalarioBase() * 0.2;
        } else if (funcionario instanceof Vendedor) {
            Vendedor vendedor = (Vendedor) funcionario;
            return obterVendasNoMes(vendedor, mes, ano) * 0.3;
        }
        return 0;
    }

    public Vendedor vendedorQueMaisVendeu(List<Vendedor> vendedores, int mes, int ano) {
        if (vendedores.isEmpty()) {
            return null;
        }

        Vendedor melhorVendedor = vendedores.get(0);
        double maiorVenda = obterVendasNoMes(melhorVendedor, mes, ano);

        for (Vendedor vendedor : vendedores) {
            double vendaAtual = obterVendasNoMes(vendedor, mes, ano);
            if (vendaAtual > maiorVenda) {
                maiorVenda = vendaAtual;
                melhorVendedor = vendedor;
            }
        }

        return melhorVendedor;
    }

    private double obterVendasNoMes(Vendedor vendedor, int mes, int ano) {
        if (vendedor.getVendas().containsKey(ano)) {
            return vendedor.getVendas().get(ano).getOrDefault(mes, 0.0);
        }
        return 0;
    }

    private List<Funcionario> filtrarFuncionariosComBeneficios() {
        List<Funcionario> comBeneficios = new ArrayList<>();

        for (Funcionario funcionario : funcionarios) {
            // Verifica se o funcionário é do tipo que recebe benefícios
            if (funcionario instanceof Secretario || funcionario instanceof Vendedor) {
                comBeneficios.add(funcionario);
            }
        }

        return comBeneficios;
    }
    private List<Vendedor> filtrarVendedores() {
        List<Vendedor> vendedores = new ArrayList<>();

        for (Funcionario funcionario : funcionarios) {
            if (funcionario instanceof Vendedor) {
                vendedores.add((Vendedor) funcionario);
            }
        }

        return vendedores;
    }
}
