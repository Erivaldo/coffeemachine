package br.ufpb.dce.aps.coffeemachine.impl;

import java.util.ArrayList;

import br.ufpb.dce.aps.coffeemachine.CoffeeMachine;
import br.ufpb.dce.aps.coffeemachine.CoffeeMachineException;
import br.ufpb.dce.aps.coffeemachine.Coin;
import br.ufpb.dce.aps.coffeemachine.ComponentsFactory;
import br.ufpb.dce.aps.coffeemachine.Drink;

public class MyCoffeeMachine implements CoffeeMachine{
	protected int total ; 
	protected ComponentsFactory fac;
	protected ArrayList<Coin> coins = new ArrayList<Coin>();
	protected Coin[] aux = new Coin[2];
	
	public MyCoffeeMachine(ComponentsFactory factory) {
		fac = factory;
		fac.getDisplay().info("Insert coins and select a drink!");
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
		}else if (coins.size() >= 2) {
			Coin[] reverso = Coin.reverse();
			fac.getDisplay().warn("Cancelling drink. Please, get your coins.");
			for (Coin r : reverso) {
				for (Coin aux : this.coins) {
					if (aux == r) {
						this.fac.getCashBox().release(aux);
					}
				}
			}
			fac.getDisplay().info("Insert coins and select a drink!");
		}
		fac.getDisplay().warn("Cancelling drink. Please, get your coins.");
		fac.getCashBox().release(Coin.halfDollar);
		fac.getDisplay().info("Insert coins and select a drink!");

		
		
	}
//	
//	verifyBlackPlan(inOrder);
//	verifyBlackMix(inOrder);
//	verifyDrinkRelease(inOrder);

	public void select(Drink drink) {
		// TODO Auto-generated method stub
		if(Drink.BLACK == drink){
			fac.getCupDispenser().contains(1);
			fac.getWaterDispenser().contains(3.0);
			fac.getCoffeePowderDispenser().contains(3.0);
			fac.getDisplay().info("Mixing ingredients.");
			fac.getCoffeePowderDispenser().release(3.0);
			fac.getWaterDispenser().release(3.0);
			fac.getDisplay().info("Releasing drink.");
			fac.getCupDispenser().release(1);
			fac.getDrinkDispenser().release(3.0);
			fac.getDisplay().info("Please, take your drink.");
			fac.getDisplay().info("Insert coins and select a drink!");
			
		}else if(Drink.BLACK_SUGAR == drink){
			fac.getCupDispenser().contains(1);
			fac.getWaterDispenser().contains(3.0);
			fac.getCoffeePowderDispenser().contains(3.0);
			fac.getSugarDispenser().contains(1.0);
			
			fac.getDisplay().info("Mixing ingredients.");
			fac.getCoffeePowderDispenser().release(3.0);
			fac.getWaterDispenser().release(3.0);
			fac.getSugarDispenser().release(2.0);
			
			fac.getDisplay().info("Releasing drink.");
			fac.getCupDispenser().release(1);
			fac.getDrinkDispenser().release(3.0);
			fac.getDisplay().info("Please, take your drink.");
			fac.getDisplay().info("Insert coins and select a drink!");
		}
		
	}
}
