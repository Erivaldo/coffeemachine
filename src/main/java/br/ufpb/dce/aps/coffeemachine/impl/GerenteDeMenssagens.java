package br.ufpb.dce.aps.coffeemachine.impl;

public class GerenteDeMenssagens {

	private static String warnMessage;
	public static void setWarnMessage(String msg){
	warnMessage = msg;
	}	
	public static String getWarnMessage(){
	return warnMessage;
	}

}
