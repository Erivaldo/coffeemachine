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

	private ComponentsFactory factory;

	private CafePreto coffee;
	private MyDisplay myDisplay;
	private GerenteDeDinheiro myCashBox;

	public void insertCoin(Coin coin) {

		myCashBox.insertCoin(coin);

	}

	public void cancel() {

		if (myCashBox.cancel()) {
			newSession();
		}
	}

	int[] planCoins() {

		int troco = myCashBox.calculaTroco();
		int[] changePlan = new int[6];
		int i = 0;
		for (Coin r : Coin.reverse()) {
			if (r.getValue() <= troco && myCashBox.count(r) > 0) {
				while (r.getValue() <= troco) {
					troco -= r.getValue();
					changePlan[i]++;
				}
			}
			i++;
		}
		if (troco != 0) {
			throw new CoffeeMachineException("");
		}

		return changePlan;
	}

	private void releaseCoins(int[] changePlan) {

		for (int i = 0; i < changePlan.length; i++) {
			int count = changePlan[i];
			Coin coin = Coin.reverse()[i];

			for (int j = 1; j <= count; j++) {
				myCashBox.release(coin);
			}
		}
	}

	private void newSession() {
		myCashBox.clearCoins();
		myDisplay.info(Messages.INSERT_COINS);
	}

	public void select(Drink drink) {
		
		if(drink == drink.BOUILLON){
			myCashBox.setCoffeePrice(25);
		}

		boolean isvalido = true;

		if (myCashBox.calculaTroco() < 0) {
			abortSession(Messages.NO_ENOUGHT_MONEY);// inOrder.verify(display).warn(Messages.NO_ENOUGHT_MONEY);
			return;
		}

		// Chamada de métodos Plan (InOrder inOrder)

		switch (drink) {

		case BLACK:			
			isvalido = coffee.blackPlan();
			break;
		case WHITE:
			coffee = new CafeWhite(factory);
			isvalido = ((CafeWhite) coffee).whitePlan();
			break;
		case WHITE_SUGAR:
			coffee = new CafeWhiteSugar(factory);
			isvalido = ((CafeWhiteSugar) coffee).whiteSugarPlan();
			break;
		case BLACK_SUGAR:
			coffee = new CafePretoSugar(factory);
			isvalido = ((CafePretoSugar) coffee).blackSugarPlan();
			break;
		case BOUILLON:
			coffee = new  Bouillon(factory);
			//myCashBox.setCoffeePrice(25);
			isvalido = ((Bouillon) coffee).bouillonPlan();
			break;

		}

		if (!isvalido) {
			abortSession(GerenteDeMenssagens.getWarnMessage());
			return;
		}

		int[] changePlan = null;

		try {
			changePlan = planCoins();
		} catch (Exception e) {
			abortSession(Messages.NO_ENOUGHT_CHANGE);
			return;
		}
		
		myDisplay.info(Messages.MIXING);

		// Chamada de métodos Mix (InOrder inOrder)

		switch (drink) {

		case BLACK:
			coffee.blackMix();
			break;

		case WHITE:
			((CafeWhite) coffee).whiteMix();
			break;

		case WHITE_SUGAR:
			((CafeWhiteSugar) coffee).whiteSugarMix();
			break;

		case BLACK_SUGAR:
			((CafePretoSugar) coffee).blackSugarMix();
			break;
		case BOUILLON:
			((Bouillon) coffee).bouillonMix();
			break;
		}
		

		// Chamadas de DrinkRelease(InOrder inOrder)

		myDisplay.info(Messages.RELEASING);

		coffee.drinkRelease();

		myDisplay.info(Messages.TAKE_DRINK);

		releaseCoins(changePlan); 

		newSession(); 

	}

	private void abortSession(String msg) {
		myDisplay.warn(msg);
		myCashBox.returnCoins();
		newSession();
	}

	public void setFactory(ComponentsFactory factory) {
		this.factory = factory;
		myDisplay = new MyDisplay(factory);
		myDisplay.info(Messages.INSERT_COINS);
		myCashBox = new GerenteDeDinheiro(factory);
		coffee = new CafePreto(factory);
		myCashBox.setCoffeePrice(35);

	}

	public void readBadge(int badgeCode) {
		myDisplay.info(Messages.BADGE_READ);
			
	}
}






