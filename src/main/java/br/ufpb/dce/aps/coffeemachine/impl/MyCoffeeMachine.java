package br.ufpb.dce.aps.coffeemachine.impl;

import java.util.ArrayList;

import br.ufpb.dce.aps.coffeemachine.CoffeeMachine;
import br.ufpb.dce.aps.coffeemachine.CoffeeMachineException;
import br.ufpb.dce.aps.coffeemachine.Coin;
import br.ufpb.dce.aps.coffeemachine.ComponentsFactory;
import br.ufpb.dce.aps.coffeemachine.Drink;
import br.ufpb.dce.aps.coffeemachine.Messages;

public class MyCoffeeMachine implements CoffeeMachine{
	protected int total ; 
	protected ComponentsFactory fac;
	protected ArrayList<Coin> coins, listaTroco;
	protected Coin[] aux = new Coin[2];
	protected int cafe = 35;
	
	public MyCoffeeMachine(ComponentsFactory factory) {
		fac = factory;
		fac.getDisplay().info("Insert coins and select a drink!");
		coins = new ArrayList<Coin>();
		listaTroco = new ArrayList<Coin>();
	}

	public void insertCoin(Coin coin) {
		if (coin == null) {
			throw new CoffeeMachineException("Insert null coin");
		}
		total += coin.getValue();
		fac.getDisplay().info("Total: US$ " + total/100 + "." + total%100);
		coins.add(coin);
		
	}

	public void cancel() {
		if (this.total == 0) {
			throw new CoffeeMachineException(" Cancel without inserting coins");
		}
		fac.getDisplay().warn(Messages.CANCEL);
		removerMoeda();	
		fac.getDisplay().info("Insert coins and select a drink!");
			
		

		
	}
	
	public void removerMoeda(){
		Coin[] reverso = Coin.reverse();
		
		for (Coin r : reverso) {
			for (Coin aux : this.coins) {
				if (aux == r) {
					this.fac.getCashBox().release(aux);
				}
			}
		}
	}
	
	public int calculaTroco() {
		int contador = 0;
		for (Coin c : this.coins) {
			contador += c.getValue();
		}
		return (contador - this.cafe);

	}
	
	public boolean planejamento(int troco) {
		for (Coin coin : Coin.reverse()) {
			if (coin.getValue() <= troco && this.fac.getCashBox().count(coin) > 0) {
				
				troco -= coin.getValue();
			}

		}
		return troco == 0;
	}
	
	public ArrayList<Coin> retornarTroco(int troco) {
		for (Coin coin : Coin.reverse()) {
			while (coin.getValue() <= troco) {
				fac.getCashBox().release(coin);
				this.listaTroco.add(coin);
				troco -= coin.getValue();
			}
		}
		return listaTroco;
	}

	public void select(Drink drink) {
		if(Drink.BLACK == drink){
			if(!(fac.getCupDispenser().contains(1))){
				fac.getDisplay().warn(Messages.OUT_OF_CUP);
				removerMoeda();
				fac.getDisplay().info("Insert coins and select a drink!");
				return;
			}
			if(!(fac.getWaterDispenser().contains(3.0))){
				fac.getDisplay().warn(Messages.OUT_OF_WATER);
				removerMoeda();
				fac.getDisplay().info("Insert coins and select a drink!");
				return;
			}
			if(!(fac.getCoffeePowderDispenser().contains(3.0))){
				fac.getDisplay().warn(Messages.OUT_OF_COFFEE_POWDER);
				removerMoeda();
				fac.getDisplay().info("Insert coins and select a drink!");
				return;
			}
				
			fac.getDisplay().info("Mixing ingredients.");
			fac.getCoffeePowderDispenser().release(3.0);
				
			fac.getWaterDispenser().release(3.0);
			fac.getDisplay().info("Releasing drink.");
				
			fac.getCupDispenser().release(1);
			fac.getDrinkDispenser().release(3.0);
			fac.getDisplay().info("Please, take your drink.");
			fac.getDisplay().info("Insert coins and select a drink!");
				
		}
		else if(Drink.BLACK_SUGAR == drink){
				
			if(!(fac.getCupDispenser().contains(1))){
				fac.getDisplay().warn(Messages.OUT_OF_CUP);
				removerMoeda();
				fac.getDisplay().info("Insert coins and select a drink!");
				return;
			}
			fac.getWaterDispenser().contains(3.0);
			fac.getCoffeePowderDispenser().contains(3.0);
			if(!(fac.getSugarDispenser().contains(1.0))){
				fac.getDisplay().warn(Messages.OUT_OF_SUGAR);
				removerMoeda();
				fac.getDisplay().info("Insert coins and select a drink!");
				return;
			}
				
		
			fac.getDisplay().info("Mixing ingredients.");
			fac.getCoffeePowderDispenser().release(3.0);
			fac.getWaterDispenser().release(3.0);
			fac.getSugarDispenser().release(2.0);
			fac.getDisplay().info("Releasing drink.");
				
			fac.getCupDispenser().release(1);
			fac.getDrinkDispenser().release(3.0);
			fac.getDisplay().info("Please, take your drink.");
			fac.getDisplay().info("Insert coins and select a drink!");
			
				
			this.coins.clear();
				
		}
		else if(Drink.WHITE == drink){
			fac.getCupDispenser().contains(1);
			fac.getWaterDispenser().contains(3.0);
			fac.getCoffeePowderDispenser().contains(3.0);
			fac.getCreamerDispenser().contains(3.0);
			
			fac.getDisplay().info(Messages.MIXING);
			fac.getCoffeePowderDispenser().release(3.0);
			fac.getWaterDispenser().release(3.0);
			fac.getCreamerDispenser().release(3.0);
			
			fac.getDisplay().info(Messages.RELEASING);
			fac.getCupDispenser().release(1);
			fac.getDrinkDispenser().release(3.0);
			fac.getDisplay().info(Messages.TAKE_DRINK);
			
			fac.getDisplay().info(Messages.INSERT_COINS);
		}
		else if (Drink.WHITE_SUGAR == drink) {
			fac.getCupDispenser().contains(1);
			fac.getWaterDispenser().contains(3.0);
			fac.getCoffeePowderDispenser().contains(3.0);
			fac.getCreamerDispenser().contains(3.0);
			fac.getSugarDispenser().contains(3.0);

			planejamento(calculaTroco());
			
			fac.getDisplay().info(Messages.MIXING);
			fac.getCoffeePowderDispenser().release(3.0);
			fac.getWaterDispenser().release(3.0);
			fac.getCreamerDispenser().release(3.0);
			fac.getSugarDispenser().release(3.0);
			
			fac.getDisplay().info(Messages.RELEASING);
			fac.getCupDispenser().release(1);
			fac.getDrinkDispenser().release(3.0);
			fac.getDisplay().info(Messages.TAKE_DRINK);
			
			retornarTroco(calculaTroco());
			
			fac.getDisplay().info(Messages.INSERT_COINS);
		}
	}
}
