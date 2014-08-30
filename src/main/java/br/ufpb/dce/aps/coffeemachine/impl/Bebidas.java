package br.ufpb.dce.aps.coffeemachine.impl;

import br.ufpb.dce.aps.coffeemachine.ComponentsFactory;

interface Bebidas {
public  void instanciarDispenser();
	
	
	public void prepararCafe(MyCoffeeMachine meucafe, ComponentsFactory fac);



}
