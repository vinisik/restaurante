import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Restaurante {
    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            Pedido pedido = new Pedido();
            System.out.println("-----BALCAO-----");
            System.out.print("Digite o nome do atendente: ");
            pedido.nomeGarcom = scanner.nextLine();

            System.out.print("Digite o nome do cliente: ");
            pedido.nomeCliente = scanner.nextLine();

            System.out.print("Digite o numero da mesa: ");

            //verificar se foi inserido um numero na mesa
            boolean mesaValida = false;
            while (!mesaValida) {
                String tableNumberInput = scanner.nextLine();
                if (tableNumberInput.matches("\\d+")) {
                    pedido.numMesa = tableNumberInput;
                    mesaValida = true;
                } else {
                    System.out.println("Entrada invalida. Digite apenas numeros para o numero da mesa.");
                }
            }
            
            System.out.println(" ");

            List<Menu> menuLanches = new ArrayList<>();
            menuLanches.add(new Menu("X-Burguer", 5.99));
            menuLanches.add(new Menu("X-Salada", 7.99));
            menuLanches.add(new Menu("Batata-Frita", 3.25));
            menuLanches.add(new Menu("X-Tudo", 9.99));
            menuLanches.add(new Menu("Espetinho de Carne", 6.99));
            menuLanches.add(new Menu("Pizza", 11.99));

            List<Menu> menuBebidas = new ArrayList<>();
            menuBebidas.add(new Menu("Agua", 1.00));
            menuBebidas.add(new Menu("Refrigerante", 3.50));
            menuBebidas.add(new Menu("Refresco", 2.55));
            menuBebidas.add(new Menu("Cerveja", 5.55));

            while (true) {
                System.out.println("-----PEDIDO-----");
                System.out.println("Menu de Comidas:");
                for (int j = 0; j < menuLanches.size(); j++) {
                    System.out.println(j + ". " + menuLanches.get(j).nome + " - R$" + String.format("%.2f", menuLanches.get(j).preco));
                }

                System.out.print("Digite o numero do item desejado ou pressione 'd' para ir para as bebidas ");
                String input = scanner.nextLine();
                if ("d".equalsIgnoreCase(input)) {
                    break;
                }

                try {
                    int numItem = Integer.parseInt(input);
                    if (numItem >= 0 && numItem < menuLanches.size()) {
                        pedido.adicionaItem(menuLanches.get(numItem));
                        System.out.println("Item adicionado.");
                    } else {
                        System.out.println("Numero de item invalido.");
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Entrada invalida, digite uma entrada correta ou pressione 's' para sair.");
                }
            }

            while (true) {
                System.out.println("Menu de Bebidas:");
                for (int j = 0; j < menuBebidas.size(); j++) {
                    System.out.println(j + ". " + menuBebidas.get(j).nome + " - R$" + String.format("%.2f", menuBebidas.get(j).preco));
                }

                System.out.print("Digite o numero de uma bebida ou pressione 's' para sair: ");
                String input = scanner.nextLine();
                if ("s".equalsIgnoreCase(input)) {
                    break;
                }

                try {
                    int numItem = Integer.parseInt(input);
                    if (numItem >= 0 && numItem < menuBebidas.size()) {
                        pedido.adicionaItem(menuBebidas.get(numItem));
                        System.out.println("Bebida adicionada.");
                    } else {
                        System.out.println("Numero de item invalido.");
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Entrada invalida, digite uma entrada correta ou pressione 's' para sair.");
                }
            }

            boolean taxa = true;
            while (taxa) {
                System.out.print("Deseja adicionar 10% de taxa para o garcom? (Digite 't' para sim, 'n' para nao): ");
                String insereTaxa = scanner.nextLine();

                if ("n".equalsIgnoreCase(insereTaxa)) {
                    double totalSemTaxa = pedido.calculaTotal();
                    pedido.mostraPedido(totalSemTaxa, false);
                    System.out.println("Agradecemos por usar nosso APP!");
                    break; //terminar sem taxa
                } else if ("t".equalsIgnoreCase(insereTaxa)) {
                    double totalSemTaxa = pedido.calculaTotal();
                    double totalComTaxa = totalSemTaxa * 1.10; //adicionar 10% de taxa
                    pedido.mostraPedido(totalComTaxa, true);
                    System.out.println("Agradecemos por usar nosso APP!");
                    break; //terminar o programa com taxa
                } else {
                    System.out.println("Entrada invalida. Digite 't' para sim ou 'n' para nao.");
                }
            }
        }
    }
}