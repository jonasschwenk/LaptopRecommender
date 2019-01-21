package com.amazon.customskill;

public class Schueler {

  private String selectQuestion(int i) {
        String question;
        switch(i){
            case 0:
                question ="Ist der schüler oder die schülerin aelter als zwölf?";
                counter ++;
                break;
            case 1:
                question = "Welche farbe bevorzügt er oder sie, weiß schwarz oder grau?";
                counter ++;
                break;
        
            default:
                question = "Der Counter ist kleiner 0 oder größer 1!";
                //counter = 0;
                //The program doesn't stop at the moment rather it starts at question 1

        }
        return question;
    }

}
