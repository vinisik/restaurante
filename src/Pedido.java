import java.util.ArrayList;
import java.util.List;

public class Pedido {
    String numMesa;
    String nomeCliente;
    String nomeGarcom;
    List<Menu> itensEmOrdem = new ArrayList<>();

    void adicionaItem(Menu item) {
        itensEmOrdem.add(item);
    }

    double calculaTotal() {
        double total = 0;
        for (Menu item : itensEmOrdem) {
            total += item.preco;
        }
        return total;
    }

    void mostraPedido(double total, boolean comTaxa) {
        System.out.println(" ");
        System.out.println("-----COMANDA-----");
        System.out.println("Atendente: " + nomeGarcom);
        System.out.println("N. Mesa: " + numMesa);
        System.out.println("Cliente: " + nomeCliente);
        System.out.println("---Itens do pedido---");
        for (Menu item : itensEmOrdem) {
            System.out.println(item.nome + " - R$" + String.format("%.2f", item.preco));
        }
        System.out.println("----------");
        if(comTaxa) {
            System.out.println("Total + taxa de 10%: R$" + String.format("%.2f", total));
        } else {
            System.out.println("Total: R$" + String.format("%.2f", total));
        }
        System.out.println("----------");
    }
}
