package pedido;

import java.util.TreeMap;
import ingredientes.Ingrediente;

public class Cardapio {
	private TreeMap<Ingrediente, Double> precos;

	public Cardapio() {
		this.precos = new TreeMap<>();
	}

	public TreeMap<Ingrediente, Double> getPrecos() {
		return this.precos;
	}

	public void adicionarIngrediente(Ingrediente ingrediente, Double preco) {
		validarParametros(preco);
		if (this.precos.get(ingrediente)==null)
			this.precos.put(ingrediente, preco);
		else
			atualizarIngrediente(ingrediente, preco);
	}

	public boolean atualizarIngrediente(Ingrediente ingrediente, Double preco) {
		validarParametros(ingrediente, preco);
		this.precos.replace(ingrediente, preco);
		return true;
	}

	public boolean removerIngrediente(Ingrediente ingrediente) {
		validarParametros(ingrediente);
        this.precos.remove(ingrediente);
		return true;
	}

	public Double buscarPreco(Ingrediente ingrediente) {
		validarParametros(ingrediente);
		return this.precos.get(ingrediente);
	}

	private void organizarOrdemAlfabetica() {

	}

	private void validarParametros(Ingrediente ingrediente, Double preco) {
		if (preco <= 0)
			throw new IllegalArgumentException("Preco invalido.");
		if (this.precos.get(ingrediente)==null)
			throw new IllegalArgumentException("Ingrediente nao existe no cardapio.");
	}

	private void validarParametros(Ingrediente ingrediente) {
		if (this.precos.get(ingrediente)==null)
			throw new IllegalArgumentException("Ingrediente nao existe no cardapio.");
	}

	private void validarParametros(Double preco) {
		if (preco <= 0)
			throw new IllegalArgumentException("Preco invalido.");
	}

	@Override
	public String toString() {
		return this.precos.toString();
	}

}
