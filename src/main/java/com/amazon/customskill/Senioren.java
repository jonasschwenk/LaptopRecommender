package com.amazon.customskill;

public class Senioren {
	
	String allAnswers = "";
	Boolean noMoreQuestions = false;
	int frageCounter = 0;
	
 private String selectQuestion() {
        String question;
        switch(frageCounter){
            case 0:
                question ="Möchtet er oder sie der laptop auch unterwegs oder im urlaub benutzen?";
                counter = counter + 1;
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
        	  answer0 ="ja";
          }else {
        	  answer0 = "nein";
          }
          counter = counter + 1;
          break;
      case 1:
    	  if(antwort.equalsIgnoreCase("ja")) {
        	  answer1 = "ja";
          }else {
        	  answer1 = "nein";
          }
          break;
   }
	  allAnswers = answer0 + answer1;
  return allAnswers;
  }
  
  private String computerName() {
	  String laptopName;
	  String noInput = "";
	  if(allAnswers.equals("jaja")) {
		  laptopName = ""; //laptop name oder ausgabe
		 }else if(allAnswers.equals("janein")) {
		         laptopName = ""; //laptop name oder ausgabe
		 }else if(allAnswers.equals("neinnein")) {
			 laptopName = ""; //laptop name oder ausgabe
		 }else if(allAnswers.equals("neinja")) {
			 laptopName = ""; //laptop name oder ausgabe
		 }else{
		  laptopName = noInput;
		  System.out.println("error");
		 }
	  return laptopName;
  }

}

