package pedido;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;
import java.util.stream.Collectors;

import ingredientes.Adicional;
import ingredientes.Base;
import ingredientes.Ingrediente;
import ingredientes.Topping;
import produto.Shake;
import produto.TipoTamanho;

public class Pedido {

	private int id;
	private ArrayList<ItemPedido> itens;
	private Cliente cliente;

	public Pedido(int id, ArrayList<ItemPedido> itens, Cliente cliente) {
		this.id = id;
		this.itens = itens;
		this.cliente = cliente;
	}

	public ArrayList<ItemPedido> getItens() {
		return itens;
	}

	public int getId() {
		return this.id;
	}

	public Cliente getCliente() {
		return this.cliente;
	}

	public double calcularTotal(Cardapio cardapio) {
		double total = 0;
		TreeMap<Ingrediente, Double> precos = cardapio.getPrecos();
		total = calculaAdicional(total, precos);
		total = calculaBase(total, precos);
		return total;
	}

	private double calculaBase(double total, TreeMap<Ingrediente, Double> precos) {
		for (ItemPedido item : this.itens) {
			Base base = item.getShake().getBase();
			Shake sizeShake = item.getShake();
			if (precos.get(base) != null)
				if (sizeShake.getTipoTamanho().equals(TipoTamanho.M))
					total = total + (precos.get(base) * 1.3);
				else if (sizeShake.getTipoTamanho().equals(TipoTamanho.G))
					total = total + (precos.get(base) * 1.5);
				else
					total = total + (precos.get(base));
			total = total * item.getQuantidade();
		}
		return total;
	}

	private double calculaTopping(double total, TreeMap<Ingrediente, Double> precos) {
		for (ItemPedido item : this.itens) {
			Topping topping = item.getShake().getTopping();
			if (precos.get(topping) != null)
				total = total + precos.get(topping);
		}
		return total;
	}

	private Double calculaAdicional(double total, TreeMap<Ingrediente, Double> precos) {
		for (ItemPedido item : this.itens) {
			List<Adicional> adicionais = item.getShake().getAdicionais();
			if (adicionais != null) {
				for (Adicional adicional : adicionais) {
					if (precos.get(adicional) != null)
						total = total + precos.get(adicional);

				}
			}

		}
		return total;
	}

	public void adicionarItemPedido(ItemPedido itemPedidoAdicionado) {
		Boolean exists = this.itens.stream().filter(o -> o.getShake().equals(itemPedidoAdicionado.getShake()))
				.findFirst().isPresent();

		if (!exists) {

			this.itens.add(itemPedidoAdicionado);
		}

		else

		{
			List<ItemPedido> pedidos = this.itens.stream()
					.filter(o -> o.getShake().equals(itemPedidoAdicionado.getShake())).collect(Collectors.toList());
			ItemPedido pedido = pedidos.get(0);
			this.itens.remove(itemPedidoAdicionado);
			pedido.setQuantidade(pedido.getQuantidade() + 1);
			this.itens.add(pedido);
		}

	}

	public boolean removeItemPedido(ItemPedido itemPedidoRemovido) {
		List<ItemPedido> items = this.itens.stream().filter(p -> p.getShake().equals(itemPedidoRemovido.getShake()))
				.collect(Collectors.toList());
		ItemPedido item = itens.get(0);
		if (item != null) {
			if (item.getQuantidade() > 1) {
				item.setQuantidade(item.getQuantidade() - 1);
				this.itens.remove(itemPedidoRemovido);
				this.itens.add(item);
			} else
				this.itens.remove(itemPedidoRemovido);

		} else {
			throw new IllegalArgumentException("Item nao existe no pedido.");
		}
		return false;
	}

	@Override
	public String toString() {
		return this.itens + " - " + this.cliente;
	}
}
