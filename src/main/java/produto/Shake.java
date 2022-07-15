package produto;

import ingredientes.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class Shake {
	private Base base;
	private Fruta fruta;
	private Topping topping;
	private List<Adicional> adicionais;
	private TipoTamanho tipoTamanho;

	public Shake(Base base, Fruta fruta, Topping topping, List<Adicional> adicionais, TipoTamanho tipoTamanho) {
		this.base = base;
		this.fruta = fruta;
		this.topping = topping;
		this.adicionais = adicionais;
		this.tipoTamanho = tipoTamanho;
	}

	public Shake(Base base, Fruta fruta, Topping topping, TipoTamanho tipoTamanho) {
		this.base = base;
		this.fruta = fruta;
		this.topping = topping;
		this.tipoTamanho = tipoTamanho;
	}

	public Base getBase() {
		return base;
	}

	public void setBase(Base base) {
		this.base = base;
	}

	public Fruta getFruta() {
		return fruta;
	}

	public void setFruta(Fruta fruta) {
		this.fruta = fruta;
	}

	public Topping getTopping() {
		return topping;
	}

	public void setTopping(Topping topping) {
		this.topping = topping;
	}

	public List<Adicional> getAdicionais() {
		return adicionais;
	}

	public void setAdicionais(List<Adicional> adicionais) {
		this.adicionais = adicionais;
	}

	public TipoTamanho getTipoTamanho() {
		return tipoTamanho;
	}

	public void setTipoTamanho(TipoTamanho tipoTamanho) {
		this.tipoTamanho = tipoTamanho;
	}

	@Override
	public String toString() {
		return this.base.getTipoBase().toString() + " / " + this.fruta.getTipoFruta().toString() + " / "
				+ this.topping.getTipoTopping().toString() + " / " + this.adicionais + " / "
				+ this.tipoTamanho.toString();
	}

	public Boolean compareTo(Shake shake) {

		if (!shake.getFruta().equals(this.getFruta()))
			return false;
		else if (!shake.getBase().equals(this.getBase()))
			return false;
		else if (!shake.getTipoTamanho().equals(this.getTipoTamanho()))
			return false;
		else if (!shake.getTopping().equals(this.getTopping()))
			return false;
		else if (!shake.getFruta().equals(this.getFruta()))
			return false;

		List<Adicional> current = this.adicionais.stream().sorted().collect(Collectors.toList());
		List<Adicional> neaveuList = shake.getAdicionais().stream().sorted().collect(Collectors.toList());

		if (!current.equals(neaveuList))
			return false;

		return true;
	}
}
