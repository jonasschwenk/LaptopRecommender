package com.amazon.customskill;

public class Gamer implements Nutzer{
	
	
	//Spezifikationen
	private String leuchten="true";
	private String aktuell="false";
	private String bildschirm = "false";
	
	protected String[][] laptopDataBase = new String [8][2];
	
	protected String completteAnfrage = ""; 
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
				if (answerAsInt == 1)
					leuchten = "true";
				else 
					leuchten = "false";
				break;
			case 1:
				if (answerAsInt == 1)
					aktuell = "true";
				else 
					aktuell = "false";
				break;
			case 2:
				if (answerAsInt == 1)
					bildschirm = "klein";
				else 
					bildschirm = "gross";
				noMoreQuestions = true;
				break;
			default : break;
		}
		completteAnfrage = leuchten + "," + aktuell + "," + bildschirm;
	}
	
	public String getLaptopFromAnswers() {
		//array durchgehen und mit anfrage vergleichen
		for (int i = 0 ; i < laptopDataBase.length ; i++ )
			if (completteAnfrage.equals(laptopDataBase[i][0]))
				return laptopDataBase[i][1];
		return "error";
	}
	
	public void listAusfuellen() {
		laptopDataBase[0][0] = "false,false,klein";	laptopDataBase[0][1]="...";
		laptopDataBase[1][0] = "false,true,klein"; 	laptopDataBase[1][1]="...";
		laptopDataBase[2][0] = "true,false,klein";	laptopDataBase[2][1]="...";
		laptopDataBase[3][0] = "true,true,klein";	laptopDataBase[3][1]="...";
		laptopDataBase[4][0] = "false,false,gross";	laptopDataBase[4][1]="...";
		laptopDataBase[5][0] = "false,true,gross"; 	laptopDataBase[5][1]="...";
		laptopDataBase[6][0] = "true,false,gross";	laptopDataBase[6][1]="...";
		laptopDataBase[7][0] = "true,true,gross";	laptopDataBase[7][1]="...";
	}
	//
	public String selectQuestion() {
	        String question;
	        switch(frageCounter){
	            case 0:
	                question ="Moechtest du eine beleuchtete Tastatur haben?";
	                frageCounter++;
	                break;
	            case 1:
	                question = "Moechtest du auch aktuelle Spiele spielen?";
	                frageCounter++;
	                break;
	        
	            case 2:
	            	question = "Moechtest du einen grossen Bildschirm haben?";
	            	frageCounter++;
	            	break;
	            default:
	                question = "Der frageCounter ist kleiner 0 oder groesser 2!";
	                //frageCounter = 0;
	                //The program doesn't stop at the moment rather it starts at question 1

	        }
	        return question;
	}
	public Gamer() {
		listAusfuellen();
	}
}
