package br.ufpb.dce.aps.coffeemachine.impl;

import java.util.ArrayList;
import java.util.List;

import br.ufpb.dce.aps.coffeemachine.CashBox;
import br.ufpb.dce.aps.coffeemachine.CoffeeMachine;
import br.ufpb.dce.aps.coffeemachine.CoffeeMachineException;
import br.ufpb.dce.aps.coffeemachine.Coin;
import br.ufpb.dce.aps.coffeemachine.ComponentsFactory;
import br.ufpb.dce.aps.coffeemachine.Drink;
import br.ufpb.dce.aps.coffeemachine.Messages;

public class MyCoffeeMachine implements CoffeeMachine{


	private ComponentsFactory fac;
	private int total, cafe,centavos;
	private ArrayList<Coin> coins;
	private ArrayList<Coin> lista;
	private ArrayList<Coin> Troco;
	
	
	
	public MyCoffeeMachine(ComponentsFactory factory) {
		fac = factory;
		fac.getDisplay().info("Insert coins and select a drink!");
		coins = new ArrayList<Coin>();
		lista = new ArrayList<Coin>();
		Troco = new ArrayList<Coin>();
		cafe = 35;
	}
	
	public void insertCoin(Coin coin) {
		if (coin == null) {
		throw new CoffeeMachineException("Moeda não aceita");
		}
		this.coins.add(coin);
		centavos += coin.getValue();
		fac.getDisplay()
		.info("Total: US$ " + centavos / 100 + "." + centavos
		% 100);
		}
		public void retornaTroco(int change) {
		for (Coin moeda : this.Troco) {
		fac.getCashBox().release(moeda);
		}
		}
		public boolean planejamento(int troco) {
		for (Coin moeda : Coin.reverse()) {
		if (moeda.getValue() <= troco) {
		int count = fac.getCashBox().count(moeda);
		while (moeda.getValue() <= troco && count > 0) {
		troco -= moeda.getValue();
		this.Troco.add(moeda);
		}
		}
		}
		return troco == 0;
		}
		public int calculaTroco() {
		int contador = 0;
		for (Coin aux : this.coins) {
		contador += aux.getValue();
		}
		return contador - this.cafe;
		}
		public void removerCoin(ComponentsFactory fac) {
		List<Integer> remover = new ArrayList<Integer>();
		for (Coin coin : Coin.reverse()) {
		for (int i = 0; i < this.coins.size(); i++) {
		if (this.coins.get(i).equals(coin)) {
		fac.getCashBox().release(coins.get(i));
		remover.add(new Integer(i));
		}
		}
		}
		this.coins.removeAll(remover);
		}
		public void cancel() {
		if (this.centavos == 0) {
		throw new CoffeeMachineException("Não possui moedas inseridas");
		}
		fac.getDisplay().warn(Messages.CANCEL);
		removerCoin(fac);
		fac.getDisplay().info(Messages.INSERT_COINS);
		}
	public void select(Drink drink) {
		Bebidas bebida = null;
		
	
		
		
		switch (drink) {
		case BLACK:
			bebida =new CafePreto();
			bebida.prepararCafe(this,fac);
			
			break;
		case BLACK_SUGAR:
			bebida = new CafePretoSugar();
			bebida.prepararCafe(this,fac);
			this.coins.clear();
			break;
		case WHITE:
			bebida = new CafeWhite();
			bebida.prepararCafe(this,fac);
			break;
		case WHITE_SUGAR:
			bebida = new CafeWhiteSugar();
			bebida.prepararCafe(this,fac);
			break;
		default:
			break;
		}
	
			
		
		
		this.lista.clear(); 
	}
}






