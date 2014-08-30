package br.ufpb.dce.aps.coffeemachine.impl;

import br.ufpb.dce.aps.coffeemachine.ComponentsFactory;
import br.ufpb.dce.aps.coffeemachine.Dispenser;
import br.ufpb.dce.aps.coffeemachine.Display;
import br.ufpb.dce.aps.coffeemachine.DrinkDispenser;
import br.ufpb.dce.aps.coffeemachine.Messages;

public class CafeWhite extends CafePreto{
	
	public CafeWhite(ComponentsFactory factory) {
		super(factory);
		}
		public boolean whitePlan(){
		if (!blackPlan()) { //verifyBlackPlan(getCupDispenser(), getWaterDispenser(), getCoffeePowderDispenser()
		return false;
		}
		if(!getFactory().getCreamerDispenser().contains(2.0)){//inOrder.verify(creamerDispenser).contains(anyDouble());
		GerenteDeMenssagens.setWarnMessage(Messages.OUT_OF_CREAMER);
		return false;
		}
		return true;
		}
		public void whiteMix(){
		blackMix();//blackMix(coffeePowderDispenser.release,waterDispenser).release )
		getFactory().getCreamerDispenser().release(2.0); //inOrder.verify(creamerDispenser).release
		}

}
