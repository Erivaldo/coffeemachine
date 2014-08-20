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

	private ComponentsFactory Factory;
	private int inteiro;
	private int centavos;
	private CashBox cb;
	private List<Coin> lista, coins;
	private Drink bebida;
	private final int cafe = 35;

	public MyCoffeeMachine(ComponentsFactory factory) {
		this.centavos = 0;
		this.inteiro = 0;
		this.Factory = factory;
		this.Factory.getDisplay().info("Insert coins and select a drink!");
		this.cb = Factory.getCashBox();
		this.lista = new ArrayList<Coin>();
		this.coins = new ArrayList<Coin>();
	}

	public void insertCoin(Coin coin) {

		if (coin == null) {
			throw new CoffeeMachineException("Moeda inválida.");
		}
		inteiro += coin.getValue() / 100;
		centavos += coin.getValue() % 100;
		this.Factory.getDisplay().info(
				"Total: US$" + " " + inteiro + "." + centavos);
		this.lista.add(coin);
	}
	
	public void cancel() {
		if (this.inteiro == 0 && this.centavos == 0) {
			throw new CoffeeMachineException("Ausencia de moedas.");
		}

		this.Factory.getDisplay().warn(Messages.CANCEL);

		retirarmoedas(Factory);

		this.Factory.getDisplay().info(Messages.INSERT_COINS);

		this.lista.clear(); 
	}

	private void retirarmoedas(ComponentsFactory factory) {
		List<Integer> remover = new ArrayList<Integer>();

		for (Coin coin : Coin.reverse()) {
			for (int i = 0; i < this.lista.size(); i++) {
				if (this.lista.get(i).equals(coin)) {
					this.Factory.getCashBox().release(lista.get(i));
					remover.add(new Integer(i));
				}
			}
		}
		this.lista.removeAll(remover);

	}


	public List<Coin> retornarTroco(int troco) {
		for (Coin coin : Coin.reverse()) {
			while (coin.getValue() <= troco) {
				Factory.getCashBox().release(coin);
				this.coins.add(coin);
				troco -= coin.getValue();
			}
		}
		return coins;
	}
	

	public boolean planejamento(int troco) {
		for (Coin coin : Coin.reverse()) {
			if (coin.getValue() <= troco && this.Factory.getCashBox().count(coin) > 0) {
				troco -= coin.getValue();
			}

		}
		return troco == 0;
		
	}
		
	

	public int calculaTroco() {
		int contador = 0;
		for (Coin c : this.lista) {
			contador += c.getValue();
		}
		return contador - this.cafe;

	}
	public int [] semTrocoTrivial (int dinheiro) {
			int [] changePlan = new int[6];
				int i=0;
				for (Coin r : Coin.reverse()) {
					if (r.getValue() <= dinheiro && cb.count(r) > 0) {
						while (r .getValue() <= dinheiro) {
							dinheiro -= r.getValue();
							changePlan[i]++;
						}
					}
				}		
				
				return changePlan;
			}
	
	public void select(Drink drink) {

		if(Drink.BLACK == drink){
			
			if (semTrocoTrivial(calcularTroco())) {
				retornarTroco(calcularTroco());
								return;}				}
			
			if (calculaTroco() < 0) {
				Factory.getDisplay().warn(Messages.NO_ENOUGHT_MONEY);
				this.retirarmoedas(Factory);
				this.Factory.getDisplay().info(Messages.INSERT_COINS);
				return;
			}	
			if (!Factory.getCupDispenser().contains(1)) {
				Factory.getDisplay().warn(Messages.OUT_OF_CUP);
				retirarmoedas(Factory);
				this.Factory.getDisplay().info(Messages.INSERT_COINS);
				return;

			}
			if (!Factory.getWaterDispenser().contains(1)) {
				Factory.getDisplay().warn(Messages.OUT_OF_WATER);
				retirarmoedas(Factory);
				this.Factory.getDisplay().info(Messages.INSERT_COINS);
				return;

			}
			if (!Factory.getCoffeePowderDispenser().contains(0.1)) {

				Factory.getDisplay().warn(Messages.OUT_OF_COFFEE_POWDER);
				retirarmoedas(Factory);
				this.Factory.getDisplay().info(Messages.INSERT_COINS);
				return;
			}
			if (!planejamento(calculaTroco())){
				Factory.getDisplay().warn(Messages.NO_ENOUGHT_CHANGE);
				this.retirarmoedas(Factory);
				this.Factory.getDisplay().info(Messages.INSERT_COINS);
				return;
			}

			
			Factory.getDisplay().info(Messages.MIXING);
			Factory.getCoffeePowderDispenser().release(1.2);
			Factory.getWaterDispenser().release(1.0);
			

			Factory.getDisplay().info(Messages.RELEASING);
			Factory.getCupDispenser().release(1);
			Factory.getDrinkDispenser().release(1.4);
			Factory.getDisplay().info(Messages.TAKE_DRINK);
			
			retornarTroco(calculaTroco());

			Factory.getDisplay().info(Messages.INSERT_COINS);

		}

		else if(Drink.BLACK_SUGAR == drink){
			if (calculaTroco() < 0) {
				Factory.getDisplay().warn(Messages.NO_ENOUGHT_MONEY);
				this.retirarmoedas(Factory);
				return;
			}
			if (!Factory.getCupDispenser().contains(1)) {
				Factory.getDisplay().warn(Messages.OUT_OF_CUP);
				retirarmoedas(Factory);
				this.Factory.getDisplay().info(Messages.INSERT_COINS);
				return;
			}
			if (!Factory.getWaterDispenser().contains(1)) {
				Factory.getDisplay().warn(Messages.OUT_OF_WATER);
				retirarmoedas(Factory);
				this.Factory.getDisplay().info(Messages.INSERT_COINS);
				return;

			}
			if (!Factory.getCoffeePowderDispenser().contains(1)) {
				Factory.getDisplay().warn(Messages.OUT_OF_COFFEE_POWDER);
				retirarmoedas(Factory);
				this.Factory.getDisplay().info(Messages.INSERT_COINS);
				return;
			}
			if (!Factory.getSugarDispenser().contains(1)) {
				Factory.getDisplay().warn(Messages.OUT_OF_SUGAR);
				retirarmoedas(Factory);
				this.Factory.getDisplay().info(Messages.INSERT_COINS);
				return;

			}
			if (!planejamento(calculaTroco())){
				Factory.getDisplay().warn(Messages.NO_ENOUGHT_CHANGE);
				this.retirarmoedas(Factory);
				this.Factory.getDisplay().info(Messages.INSERT_COINS);
				return;
			}

			Factory.getDisplay().info(Messages.MIXING);
			Factory.getCoffeePowderDispenser().release(1.9);
			Factory.getWaterDispenser().release(1.10);
			Factory.getSugarDispenser().release(1.11);
			

			Factory.getDisplay().info(Messages.RELEASING);
			Factory.getCupDispenser().release(1);
			Factory.getDrinkDispenser().release(0.9);
			Factory.getDisplay().info(Messages.TAKE_DRINK);

			retornarTroco(calculaTroco());
			
			Factory.getDisplay().info(Messages.INSERT_COINS);

			this.lista.clear();
		}

		else if(Drink.WHITE == drink){
			if (calculaTroco() < 0) {
				Factory.getDisplay().warn(Messages.NO_ENOUGHT_MONEY);
				this.retirarmoedas(Factory);
				return;
				}


			Factory.getCupDispenser().contains(1);
			Factory.getWaterDispenser().contains(1);
			Factory.getCoffeePowderDispenser().contains(1);
			Factory.getCreamerDispenser().contains(1.2);
			
			if (!planejamento(calculaTroco())){
				Factory.getDisplay().warn(Messages.NO_ENOUGHT_CHANGE);
				this.retirarmoedas(Factory);
				this.Factory.getDisplay().info(Messages.INSERT_COINS);
				return;
			}
			
			Factory.getDisplay().info(Messages.MIXING);
			Factory.getCoffeePowderDispenser().release(1.9);
			Factory.getWaterDispenser().release(1.10);
			Factory.getCreamerDispenser().release(1.8);

			Factory.getDisplay().info(Messages.RELEASING);
			Factory.getCupDispenser().release(1);
			Factory.getDrinkDispenser().release(0.9);
			Factory.getDisplay().info(Messages.TAKE_DRINK);
			
			retornarTroco(calculaTroco());
			
			Factory.getDisplay().info(Messages.INSERT_COINS);

		}

		else if (Drink.WHITE_SUGAR == drink) {
			
			if (calculaTroco() < 0) {
				Factory.getDisplay().warn(Messages.NO_ENOUGHT_CHANGE);
				this.retirarmoedas(Factory);
				return;
			}
			
			Factory.getCupDispenser().contains(1);
			Factory.getWaterDispenser().contains(1);
			Factory.getCoffeePowderDispenser().contains(1);
			Factory.getCreamerDispenser().contains(1.2);
			Factory.getSugarDispenser().contains(1.1);
			
		
			if (!planejamento(calculaTroco())){
				Factory.getDisplay().warn(Messages.NO_ENOUGHT_MONEY);
				this.retirarmoedas(Factory);
				this.Factory.getDisplay().info(Messages.INSERT_COINS);
				return;
			}

			Factory.getDisplay().info(Messages.MIXING);
			Factory.getCoffeePowderDispenser().release(1.9);
			Factory.getWaterDispenser().release(1.10);
			Factory.getCreamerDispenser().release(1.8);
			Factory.getSugarDispenser().release(1.0);

			Factory.getDisplay().info(Messages.RELEASING);
			Factory.getCupDispenser().release(1);
			Factory.getDrinkDispenser().release(0.9);
			Factory.getDisplay().info(Messages.TAKE_DRINK);

			retornarTroco(calculaTroco());
			
			Factory.getDisplay().info(Messages.INSERT_COINS);

	
		}

			
		
		
		this.lista.clear(); 
	}
}






