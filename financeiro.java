
    import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

    public class financeiro {
        public static void main(String[] args) {
            new SistemaFinanceiro().executar();
        }
    }

    class SistemaFinanceiro {
        static class Movimento {
            public enum Tipo { RECEITA, DESPESA }
            private String descricao;
            private double valor;
            private Tipo tipo;

            public Movimento(String descricao, double valor, Tipo tipo) {
                this.descricao = descricao;
                this.valor = valor;
                this.tipo = tipo;
            }

            public double getValor() {
                return tipo == Tipo.RECEITA ? valor : -valor;
            }

            public String toString() {
                return String.format("%-10s : %-10.2f - %s", tipo, valor, descricao);
            }
        }

        private List<Movimento> movimentos = new ArrayList<>();
        private Scanner scanner = new Scanner(System.in);

        private void mostrarMenu() {
            System.out.println("\n### Sistema Financeiro ###");
            System.out.println("1 - Adicionar Receita");
            System.out.println("2 - Adicionar Despesa");
            System.out.println("3 - Listar Movimentos");
            System.out.println("4 - Mostrar Resumo");
            System.out.println("5 - Sair");
            System.out.print("Escolha uma opção: ");
        }

        private void adicionarMovimento(Movimento.Tipo tipo) {
            System.out.print("Descrição: ");
            String descricao = scanner.nextLine();
            System.out.print("Valor: ");
            double valor = 0;
            try {
                valor = Double.parseDouble(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Valor inválido!");
                return;
            }
            movimentos.add(new Movimento(descricao, valor, tipo));
            System.out.println("Movimento adicionado.");
        }

        private void listarMovimentos() {
            if (movimentos.isEmpty()) {
                System.out.println("Nenhum movimento registrado.");
                return;
            }
            System.out.println("Movimentos cadastrados:");
            for (Movimento m : movimentos) {
                System.out.println(m);
            }
        }

        private void mostrarResumo() {
            double receitaTotal = 0;
            double despesaTotal = 0;
            for (Movimento m : movimentos) {
                if (m.tipo == Movimento.Tipo.RECEITA) {
                    receitaTotal += m.valor;
                } else {
                    despesaTotal += m.valor;
                }
            }
            double saldo = receitaTotal - despesaTotal;
            System.out.printf("Receitas totais: %.2f\n", receitaTotal);
            System.out.printf("Despesas totais: %.2f\n", despesaTotal);
            System.out.printf("Saldo atual: %.2f\n", saldo);
        }

        public void executar() {
            boolean executando = true;
            while (executando) {
                mostrarMenu();
                String opcao = scanner.nextLine();
                switch(opcao) {
                    case "1":
                        adicionarMovimento(Movimento.Tipo.RECEITA);
                        break;
                    case "2":
                        adicionarMovimento(Movimento.Tipo.DESPESA);
                        break;
                    case "3":
                        listarMovimentos();
                        break;
                    case "4":
                        mostrarResumo();
                        break;
                    case "5":
                        executando = false;
                        System.out.println("Encerrando o sistema. Obrigado!");
                        break;
                    default:
                        System.out.println("Opção inválida.");
                }
            }
            scanner.close();
        }
    }
