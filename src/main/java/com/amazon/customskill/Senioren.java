package com.amazon.customskill;

public class Senioren implements Nutzer {
		
	private String[][] laptopDataBase = new String[4][2];
	String completteAnfrage = "";
	int frageCounter = 0;
	
	protected boolean noMoreQuestions = false;
	
	public boolean getNoMoreQuestions() {return noMoreQuestions;}

	String Gewicht = "";
	String Bildschirm = "";

	public Senioren(){
		listAusfuellen();
	}	
	
	public String selectQuestion() {
		String question="";
		switch(frageCounter){
			case 0:
				question ="Moechtest du den laptop auch unterwegs oder im urlaub benutzen?";
				frageCounter = frageCounter + 1;
				break;
			case 1:
				question = "Hast du Probleme mit den Augen und ein groesserer Bildschirm wuerde dir helfen?";
				noMoreQuestions = true;
				break;
			default:
		}
		return question;
	}
	
	public void takeAnswer(int answerAsInt) {
		switch(frageCounter){
			case 0:
				if(answerAsInt == 1)
					Gewicht ="<= 2kg";
				else 
					Gewicht = "> 2kg";
				frageCounter++;
				break;
			case 1:
				if(answerAsInt == 1) 
					Bildschirm = "grosses bildschirm";
				else 
					Bildschirm = "normales bildschirm";
				noMoreQuestions = true;
				break;
		}
		completteAnfrage = Gewicht + "," + Bildschirm;
	}
  
	public void listAusfuellen() { // Diese Methode fuellt unsere Database. 
  	
			//laptopDataBase[i][0] = "Gewicht, Bildschirm grosse" ;    //laptopDataBase[i][1] = "Notebook" ;
  	
		laptopDataBase[0][0] = "<= 2kg,grosses bildschirm"  ;    	laptopDataBase[1][1] =  "..." ;
		laptopDataBase[1][0] = "<= 2kg,normales bildschirm" ;    	laptopDataBase[2][1] =  "..." ;
		laptopDataBase[2][0] = "> 2kg,grosses bildschirm" ;	    	laptopDataBase[3][1] =  "..." ;
		laptopDataBase[3][0] = "> 2kg,normales bildschirm" ;	 	laptopDataBase[4][1] =  "..." ;   // die Tabelle ist komplett ausgefuellt
	}
  

	public String getLaptopFromAnswers() {
		//array durchgehen und mit anfrage vergleichen
		for (int i = 0 ; i <= laptopDataBase.length ; i++ ) 
			if (completteAnfrage.equals(laptopDataBase[i][0])){
				return laptopDataBase[i][1];		
			}
		return "error";
	}
}

