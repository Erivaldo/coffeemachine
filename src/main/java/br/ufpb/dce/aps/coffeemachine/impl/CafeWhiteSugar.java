package br.ufpb.dce.aps.coffeemachine.impl;

import br.ufpb.dce.aps.coffeemachine.ComponentsFactory;
import br.ufpb.dce.aps.coffeemachine.Dispenser;
import br.ufpb.dce.aps.coffeemachine.Display;
import br.ufpb.dce.aps.coffeemachine.DrinkDispenser;
import br.ufpb.dce.aps.coffeemachine.Messages;

public class CafeWhiteSugar extends CafeWhite {
	public CafeWhiteSugar(ComponentsFactory factory) {
		 super(factory);
		 }
	 public boolean whiteSugarPlan(){
		 if(!whitePlan()){ // inOrder.verify(creamerDispenser).contains(anyDouble());
		 return false;
		 }
		 getFactory().getSugarDispenser().contains(5); // inOrder.verify(getSugarDispenser).contains(anyDouble());
		 return true;
		 }
		 public void whiteSugarMix(){
		 whiteMix();
		 getFactory().getSugarDispenser().release(5); //inOrder.verify(sugarDispenser).release
		 }
}
