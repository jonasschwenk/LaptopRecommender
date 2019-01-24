package com.amazon.customskill;

public class Gamer implements Nutzer{//protected final int NumberOfQuestions = 3;
	//initialisiert mit standartwerten
	String leuchten="true";
	String aktuell="false";
	String bildschirm = "false";
	
	protected String[][] laptopDataBase = new String [8][2];
	
	protected String completteAnfrage = ""; 
	private int frageCounter = 0;
		
	public int getFrageCounter() {
		return frageCounter;
	}
	public void setFrageCounter(int frageCounter) {
		this.frageCounter = frageCounter;
	}
	
	public String takeAnswers(int answer) {

		switch(frageCounter - 1) {
			case 0:
				(answer == 1) ? leuchten = "true"; : leuchten = "false"; break;
			case 1: 
				(answer == 1) ? aktuell = "true"; : aktuell = "false"; break;
			case 2:
				(answer == 1) ? bildschirm = "gross"; : bildschirm = "klein"; break;
			default : break;
		}
		completteAnfrage = leuchten + "," + aktuell + "," + bildschirm;
		return completteAnfrage;
	}
	
	public String getLaptopFromAnswers() {
		//array durchgehen und mit anfrage vergleichen
		for (int i = 0 ; i <= laptopDataBase.length ; i++ )
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
	                question ="Möchtest du eine beleuchtete Tastatur haben?";
	                frageCounter++;
	                break;
	            case 1:
	                question = "Möchtest du auch aktuelle Spiele spielen?";
	                frageCounter++;
	                break;
	        
	            case 2:
	            	question = "Möchtest du einen großen Bildschirm haben?";
	            	frageCounter++;
	            	break;
	            default:
	                question = "Der frageCounter ist kleiner 0 oder größer 2!";
	                //frageCounter = 0;
	                //The program doesn't stop at the moment rather it starts at question 1

	        }
	        return question;
	}
	public 
	
//	//macht aus binären antworten eine Nummer, um caseswitch verwenden zu können
//	public int answerstoNumber (int[] input) {
//		int output = 0;
//		int j = 1;
//		for (int i = input.length - 1; i<= 0 ; i--) {
//			output += input[i] * j;
//			j += 2;
//		}
//		return output;
//			
//	}
	
	
	
	
	public Gamer() {
		listAusfuellen();
	}
}
