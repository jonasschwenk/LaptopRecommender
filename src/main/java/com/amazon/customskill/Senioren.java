package com.amazon.customskill;

public class Senioren implements Nutzer {
	private String[][] laptopDataBase = new String[4][2];
	private String allAnswers = "";
	Boolean noMoreQuestions = false;
	int frageCounter = 0;
	
public Senioren(){
	ListAusfuellen();
}	
 private String selectQuestion() {
        String question;
        switch(frageCounter){
            case 0:
                question ="Möchtet er oder sie der laptop auch unterwegs oder im urlaub benutzen?";
                frageCounter = frageCounter + 1;
                break;
            case 1:
                question = "Hat der senior oder die seniorin Sehprobleme?";
                noMoreQuestions = true;
                break;
            default:
            	}
        return question;
 }

 
  private String takeAnswer(String antwort) {
	  String answer0 = "";
 	  String answer1 = "";
 switch(frageCounter){
 case 0:
     if(antwort.equalsIgnoreCase("ja")) {
   	  answer0 ="<= 2kg";
     }else {
   	  answer0 = "> 2kg";
     }
     frageCounter = frageCounter + 1;
     break;
 case 1:
	  if(antwort.equalsIgnoreCase("ja")) {
   	  answer1 = "grosses bildschirm";
     }else {
   	  answer1 = "normales bildschirm";
     }
     break;
}
 allAnswers = answer0 + "," + answer1;
return allAnswers;
}
  
 public void ListAusfuellen() { // Diese Methode fuellt unsere Database. 
  	
  	//laptopDataBase[i][0] = "Gewicht, Bildschirm grosse" ;    //laptopDataBase[i][1] = "Notebook" ;
  	
  laptopDataBase[0][0] = "<= 2kg,grosses bildschirm"  ;     laptopDataBase[1][1] =  "..." ;
  laptopDataBase[1][0] = "<= 2kg,normales bildschirm" ;     laptopDataBase[2][1] =  "..." ;
  laptopDataBase[2][0] = "> 2kg,grosses bildschirm" ;	    laptopDataBase[3][1] =  "..." ;
  laptopDataBase[3][0] = "> 2kg,normales bildschirm ;	    laptopDataBase[4][1] =  "..." ;   // die Tabelle ist komplett ausgefuellt
  }
  

  public String getLaptopFromAnswers() {
   	
   	String antwort = "";
   	for (int i=1; i < laptopDataBase.length; i++) {
   		if (laptopDataBase[i][0].equals(allAnswers)) {
   			antwort = laptopDataBase[i][1];
   			break;
   		}
   	}
   	
   	String aussage1= "Die "+antwort+ " ist für dich ein guter Auswahl. Außerdem empfehle ich dir auch eine Mause zu kaufen." ;
   	return aussage1;
   }
  

}

