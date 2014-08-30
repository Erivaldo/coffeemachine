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
	
	public MyCoffeeMachine(ComponentsFactory factory) {
	this.factory = factory;
	myDisplay = new MyDisplay(factory);
	myDisplay.info(Messages.INSERT_COINS);
	myCashBox = new GerenteDeDinheiro(factory);
	}
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
	boolean validar = true;
	if (myCashBox.calculaTroco() < 0) {
	abortSession(Messages.NO_ENOUGHT_MONEY);// inOrder.verify(display).warn(Messages.NO_ENOUGHT_MONEY);
	return;
	}
	// Chamada de métodos Plan (InOrder inOrder)
	switch (drink) {
	case BLACK:
	coffee = new CafePreto(factory);
	validar = coffee.blackPlan();
	break;
	case WHITE:
	coffee = new CafeWhite(factory);
	validar = ((CafeWhite) coffee).whitePlan();
	break;
	case WHITE_SUGAR:
	coffee = new CafeWhiteSugar(factory);
	validar = ((CafeWhiteSugar) coffee).whiteSugarPlan();
	break;
	case BLACK_SUGAR:
	coffee = new CafePretoSugar(factory);
	validar = ((CafePretoSugar) coffee).blackSugarPlan();
	break;
	}
	if (!validar) {
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
	}
	// Chamadas de DrinkRelease(InOrder inOrder)
	myDisplay.info(Messages.RELEASING);
	coffee.drinkRelease();
	myDisplay.info(Messages.TAKE_DRINK);
	releaseCoins(changePlan); // entrega troco
	newSession(); // nova sessão
	}
	private void abortSession(String msg) {
	myDisplay.warn(msg);
	myCashBox.returnCoins();
	newSession();
	}
}






