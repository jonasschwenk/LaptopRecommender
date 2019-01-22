package com.amazon.customskill;

public class Senioren {
 private String selectQuestion(int i) {
        String question;
        switch(i){
            case 0:
                question ="Möchtet er oder sie der laptop auch unterwegs oder im urlaub benutzen?";
                counter ++;
                break;
            case 1:
                question = "Hat der senior oder die seniorin Sehprobleme?";
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
