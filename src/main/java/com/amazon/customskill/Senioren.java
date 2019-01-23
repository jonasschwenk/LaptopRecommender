package com.amazon.customskill;

public class Senioren {
	
	String allAnswers = "";
	Boolean noMoreQuestions = false;
	
 private String selectQuestion(int i) {
        String question;
        switch(i){
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
	 	  String answers = "";
	  switch(i){
      case 0:
          if(antwort.equals("ja")) {
        	  answers ="ja";
          }else {
        	  answers = "nein";
          }
          counter = counter + 1;
          break;
      case 1:
    	  if(antwort.equals("ja")) {
        	  allAnswers = answers + "ja";
          }else {
        	  allAnswers = answers + "nein";
          }
          break;
   }
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

