package com.amazon.customskill;

public class Schueler {

 private String selectQuestion(int i) {
    String allAnswers = "";
	  Boolean noMoreQuestions = false;
	
 private String selectQuestion(int i) {
        String question;
        switch(i){
            case 0:
                question ="Ist der schüler oder die schülerin aelter als zwölf?";
                counter = counter + 1;
                break;
            case 1:
                question = "Welche farbe bevorzügt er oder sie, weiß oder schwarz?";
                noMoreQuestions = true;
                break;       
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
    	  if(antwort.equals("weiß")) {
        	  allAnswers = answers + "weiß";
          }else {
        	  allAnswers = answers + "schwarz";
          }
          break;
   }
  return allAnswers;
  }
  
  private String computerName() {
	  String laptopName;
    String noInput = "";
    //String tipp = "";
	  if(allAnswers.equals("jaweiß")) {
		  laptopName = ""; //laptop name oder ausgabe
		 }else if(allAnswers.equals("jaschwarz")) {
			 laptopName = ""; //laptop name oder ausgabe
		 }else if(allAnswers.equals("neinweiß")) {
			 laptopName = ""; //laptop name oder ausgabe
		 }else if(allAnswers.equals("neinschwarz")) {
			 laptopName = ""; //laptop name oder ausgabe
		 }else{
      laptopName = noInput;
			 System.out.println("error");
		 }
	  return laptopName;
  }


}
