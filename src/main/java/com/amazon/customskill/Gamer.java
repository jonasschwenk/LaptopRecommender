package com.amazon.customskill;

public class Gamer implements Nutzer{
	
	
	//Spezifikationen
	private String leuchten="true";
	private String aktuell="false";
	private String bildschirm = "false";
	
	protected String[][] laptopDataBase = new String [8][2];
	
	protected String kompletteAnfrage = leuchten + "," + aktuell + "," + bildschirm; 
	private int frageCounter = 0;
		
	public int getFrageCounter() {
		return frageCounter;
	}
	public void setFrageCounter(int frageCounter) {
		this.frageCounter = frageCounter;
	}
	
	private boolean noMoreQuestions = false;
	
	public boolean getNoMoreQuestions() {
		return noMoreQuestions;
	}
	
	public void takeAnswer(int answerAsInt) {
		switch(frageCounter) {
			case 0:
//				leuchten = (answerAsInt == 1) ? "true" : "false";
				if(answerAsInt == 1) {
					leuchten = "true";	
					frageCounter++;
				}
				else if(answerAsInt == 0) {
					leuchten = "false";
					frageCounter++;
				}
				break;
			case 1:
				if (answerAsInt == 1) {
					aktuell = "true";
					frageCounter++;
				}
				else if(answerAsInt == 0) {
					aktuell = "false"; 
					frageCounter++;
				}
				break;
			case 2:
				if(answerAsInt == 1) {
					bildschirm = "gross";
					noMoreQuestions = true;
				}
				else if(answerAsInt == 0) {
					bildschirm = "klein";
					noMoreQuestions = true;
				}				
				break;
			default: 
				break;
		}
		kompletteAnfrage = leuchten + "," + aktuell + "," + bildschirm;
	}
	
	public String getLaptopFromAnswers() {
		//array durchgehen und mit anfrage vergleichen
		for (int i = 0 ; i < laptopDataBase.length ; i++ )
			if (kompletteAnfrage.equals(laptopDataBase[i][0]))
				return laptopDataBase[i][1];
		return "error";
	}
	
	public void listAusfuellen() {
		laptopDataBase[0][0] = "false,false,klein";		laptopDataBase[0][1]="Acer Aspire E15";
		laptopDataBase[1][0] = "false,true,klein"; 		laptopDataBase[1][1]="Asus FX565";
		laptopDataBase[2][0] = "true,false,klein";		laptopDataBase[2][1]="Acer Nitro 5";
		laptopDataBase[3][0] = "true,true,klein";		laptopDataBase[3][1]="Acer Predator Helios 300";
		laptopDataBase[4][0] = "false,false,gross";		laptopDataBase[4][1]="DELL G3 17";
		laptopDataBase[5][0] = "false,true,gross"; 		laptopDataBase[5][1]="In dieser Kategorie hat jedes Laptop eine beleuchtete Tastatur, deshalb empfehle ich dir den Razer Blade 15";
		laptopDataBase[6][0] = "true,false,gross";		laptopDataBase[6][1]="MSI GL72M";
		laptopDataBase[7][0] = "true,true,gross";		laptopDataBase[7][1]="Razer Blade 15";
	}	

	public String selectQuestion() {
	        String question;
	        switch(frageCounter){
	            case 0:
	                question ="Moechtest du eine beleuchtete Tastatur haben?";	               
	                break;
	            case 1:
	                question = "Moechtest du auch aktuelle Spiele spielen?";
	                break;
	        
	            case 2:
	            	question = "Moechtest du einen grossen Bildschirm haben?";
	            	break;
	            default:
	                question = "Der frageCounter ist kleiner 0 oder groesser 2!";
	        }
	        return question;
	}
	public Gamer() {
		listAusfuellen();
	}
}
