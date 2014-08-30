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
	
	public boolean blackPlan(){  
		
		if (!getFactory().getCupDispenser().contains(1)) { 
			GerenteDeMenssagens.setWarnMessage(Messages.OUT_OF_CUP);
			return false;
		}

		if (!getFactory().getWaterDispenser().contains(80)) { 
			GerenteDeMenssagens.setWarnMessage(Messages.OUT_OF_WATER);
			return false;
		}

		if (!getFactory().getCoffeePowderDispenser().contains(15)) {
			GerenteDeMenssagens.setWarnMessage(Messages.OUT_OF_COFFEE_POWDER); 
			return false;
		}
		
		return true;
	}

	public boolean whitePlan(){
		if (!blackPlan()) { 
			return false;
		}
		if(!getFactory().getCreamerDispenser().contains(20)){
			GerenteDeMenssagens.setWarnMessage(Messages.OUT_OF_CREAMER);
			return false;
		}
		
		
		return true;
	}
	
	public void blackMix(){
		getFactory().getCoffeePowderDispenser().release(15); 
		getFactory().getWaterDispenser().release(80);
	}
	
	public void whiteMix(){
		blackMix();
		getFactory().getCreamerDispenser().release(20); 
	} 
	

}
