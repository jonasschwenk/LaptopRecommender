/**
    Copyright 2014-2015 Amazon.com, Inc. or its affiliates. All Rights Reserved.

    Licensed under the Apache License, Version 2.0 (the "License"). You may not use this file except in compliance with the License. A copy of the License is located at

        http://aws.amazon.com/apache2.0/

    or in the "license" file accompanying this file. This file is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the specific language governing permissions and limitations under the License.
 */
package com.amazon.customskill;

import java.util.*;
import java.util.stream.IntStream;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.amazon.speech.json.SpeechletRequestEnvelope;
import com.amazon.speech.slu.Intent;
import com.amazon.speech.speechlet.IntentRequest;
import com.amazon.speech.speechlet.LaunchRequest;
import com.amazon.speech.speechlet.SessionEndedRequest;
import com.amazon.speech.speechlet.SessionStartedRequest;
import com.amazon.speech.speechlet.SpeechletResponse;
import com.amazon.speech.speechlet.SpeechletV2;
import com.amazon.speech.ui.PlainTextOutputSpeech;
import com.amazon.speech.ui.Reprompt;
import com.amazon.speech.ui.SsmlOutputSpeech;

import nlp.dkpro.backend.PosTagger;
import nlp.dkpro.backend.NlpSingleton;

/*
 * This class is the actual skill. Here you receive the input and have to produce the speech output. 
 */

public class AlexaSkillSpeechlet
    implements SpeechletV2
{
	int budget = 0;
	public boolean student = false;
	public boolean schueler = false;
	public boolean privatnutzer = false;
	public boolean senior = false;
	public boolean gamer = false;
	public int counter = 0;
	public int fragenid = 0;
	public static String userRequest;
	String lastQuestion = "";
	int minimumPrice = 370;
	Nutzer nutzer = null;
	/*
	Schueler schueler1 = new Schueler();
	Senioren senior1 = new Senioren();
	Student student1 = new Student();
	Gamer gamer1 = new Gamer();*/
	PrivatNutzer privatNutzer = new PrivatNutzer();
	int groupCounter = 0;

    static Logger logger = LoggerFactory.getLogger(AlexaSkillSpeechlet.class);

    private PosTagger p;
    
    private static int[] answers = new int[6];


    @Override
    public void onSessionStarted(SpeechletRequestEnvelope<SessionStartedRequest> requestEnvelope)
    {
        p = NlpSingleton.getInstance();
        logger.info("Alexa session begins");
    }

    @Override
    public SpeechletResponse onLaunch(SpeechletRequestEnvelope<LaunchRequest> requestEnvelope)
    {
        return getWelcomeResponse();
    }


    @Override
    public SpeechletResponse onIntent(SpeechletRequestEnvelope<IntentRequest> requestEnvelope)
    {
    	String group = "";
        IntentRequest request = requestEnvelope.getRequest();

        Intent intent = request.getIntent();
        String nomen = "";

        userRequest = intent.getSlot("Alles").getValue();
        if(userRequest.equalsIgnoreCase("abbrechen")) {
        	return endResponse("Beratung abgebrochen!");
        }
//        int answerasint = analyseAnswer(userRequest);//Analysiere die Antwort und gibt bei nein 0 ja 1 und sonst 2 zur�ck
//        if (fragenid > 0&& answerasint<2) //Antwort verstanden und gespeichert im Antwortenarray
//            answers[counter - 1] = answerasint;
        if(lastQuestion.equalsIgnoreCase("Wie viel m�chtest du maximal ausgeben?")) {
        	budget = stringToNumber(userRequest);
//        	System.out.println(budget);
        	lastQuestion = "";
        	if(budget < minimumPrice)
        		return askUserResponse("Dein Budget liegt unterhalb des Preises meiner Minimalkonfiguration! Daher werde ich dir keinen Laptop innerhalb deines Budegets empfehlen k�nnen. M�chtest du die Beratung dennoch Fortsetzen?");
        }
        if(counter != 0) {
            if(student) {
            	nutzer = new Student()
            }else if(schueler) {
            	nutzer = new Schueler();
            }else if(senior) {
            	nutzer = new Senior();
            }else if(privatnutzer) {
            	nutzer = new PrivatNutzer();
            }else if(gamer) {
            	nutzer = new Gamer();
            }
        }
        if(counter == 0) {
        	nomen = analyze(userRequest);
        	group = selectGroup(nomen);
        }
        
        logger.info("Received following text: [" + userRequest + "]");

        String question;
        
        return askUserResponse(group);
    }
    public int analyseAnswer(String answer){            // Antwort analysieren
        int analysedanswer=2;
        StringTokenizer token = new StringTokenizer(answer);
        int length = token.countTokens();
        String[] cuttedanswer= new String[length];
        for(int i=0; i<length;i++){             //zur�ckgeliferte Antwort in einzelne W�rter zerlegen
            cuttedanswer[i]=token.nextToken();
        }
        for(int i=0; i<length;i++){   // alle W�rter durchgehen und auf ja und nein �berpr�fen
            if(cuttedanswer[i].equalsIgnoreCase("ja")||cuttedanswer[i].equalsIgnoreCase("genau")||cuttedanswer[i].equalsIgnoreCase("exakt")||cuttedanswer[i].equalsIgnoreCase("jeden")||cuttedanswer[i].equalsIgnoreCase("immer")) {
                analysedanswer = 1; // case answer ja
            }
            if (cuttedanswer[i].equalsIgnoreCase("nein")||cuttedanswer[i].equalsIgnoreCase("keinen")||cuttedanswer[i].equalsIgnoreCase("niemals")||cuttedanswer[i].equalsIgnoreCase("ne")|| cuttedanswer[i].equalsIgnoreCase("keine")) {
                analysedanswer= 0;// case answer nein
            }
        }
        return analysedanswer;
    }

    
    

    
    public String selectGroup(String group) {

    	String firstQuestion;
    	
    	switch(group) {
    	case "student":
    		student = true;
    		firstQuestion = "Wie viel m�chtest du maximal ausgeben?";
    		counter ++;
    		groupCounter = student1.counter;
    		break;
    	case "sch�ler":
    		schueler = true;
    		firstQuestion = "Wie viel m�chtest du maximal ausgeben?";
    		counter ++;
    		groupCounter = schueler1.counter;
    		break;
    	case "senior":
    		senior = true;
    		firstQuestion = "Wie viel m�chtest du maximal ausgeben?";
    		counter ++;
    		groupCounter = senior1.counter;
    		break;
    	case "gamer":
    		gamer = true;
    		firstQuestion = "Wie viel m�chtest du maximal ausgeben?";
    		counter ++;
    		groupCounter = gamer1.counter;
    		break;
    	case "privatnutzer":
    		privatnutzer = true;
    		firstQuestion = "Wie viel m�chtest du maximal ausgeben?";
    		counter ++;
    		groupCounter = privatNutzer.counter;
    		break;
    	default:
    		firstQuestion = "Bitte eine der folgenden Kategorien w�hlen: Privatnutzer, Student, Sch�ler, Gamer oder Senior!";
    		break;
    	}
    	lastQuestion = firstQuestion;
    	return firstQuestion;
    }

    /**
     * formats the text in weird ways
     * @param text
     * @param i
     * @return
     */


	private String analyze(String request)
    {
        List<String> nouns = new ArrayList<>();
        try {
            nouns = p.findNouns(userRequest);
            logger.info("Detected following nouns: [" + StringUtils.join(nouns, " ") + "]");
        }
        catch (Exception e) {
            throw new UnsupportedOperationException();
        }

        if (nouns.isEmpty()) {
            return("Ich habe keine Nomen erkannt");
        }
        
        return StringUtils.join(nouns, " und ");
    }

    @Override
    public void onSessionEnded(SpeechletRequestEnvelope<SessionEndedRequest> requestEnvelope)
    {
        logger.info("Alexa session ends now");
    }

    /*
     * The first question presented to the skill user (entry point)
     */
    private SpeechletResponse getWelcomeResponse(){
    	return askUserResponse("Willkommen bei der Notebookberatung durch Alexa! Ich werde dir eine Reihe an Fragen stellen und an Hand deiner Antworten werde ich dir eine Liste von zu dir passenden Laptops ausgeben! Zu welcher der folgenden Nutzergruppen wird der Nutzer des Ger�tes geh�ren? Ist er ein Student, ein Sch�ler, ein Senior, ein Privatnutzer oder ein Gamer?");
       //    	return askUserResponse("<amazon:effect name=\"whispered\">Hey Leute</amazon:effect>, ich bin ein <phoneme alphabet=\"ipa\" ph=\"ˈfʌni\">funny</phoneme> Nomen <phoneme alphabet=\"ipa\" ph=\"bɒt\">bot</phoneme>! Sag einen Satz und ich nenne dir die enthaltenen Nomen");
    }

    /**
     * Tell the user something - the Alexa session ends after a 'tell'
     */
    
    

    /**
     * A response to the original input - the session stays alive after an ask request was send.
     *  have a look on https://developer.amazon.com/de/docs/custom-skills/speech-synthesis-markup-language-ssml-reference.html
     * @param text
     * @return
     */
    private SpeechletResponse askUserResponse(String text)
    {
        SsmlOutputSpeech speech = new SsmlOutputSpeech();
        speech.setSsml("<speak>" + text + "</speak>");

        SsmlOutputSpeech repromptSpeech = new SsmlOutputSpeech();
        repromptSpeech.setSsml("<speak><emphasis level=\"strong\">Hey!</emphasis> Bist du noch da?</speak>");

        Reprompt rep = new Reprompt();
        rep.setOutputSpeech(repromptSpeech);

        return SpeechletResponse.newAskResponse(speech, rep);
    }
    
    private SpeechletResponse endResponse(String text) {
    	SsmlOutputSpeech outputSpeech = new SsmlOutputSpeech();
    	outputSpeech.setSsml("<speak>" + text + "</speak>");
    	counter = 0;
    	budget = 0;
    	
    	return SpeechletResponse.newTellResponse(outputSpeech);
    }
    
    private int stringToNumber(String input) {
    	boolean isValidInput = true;
        long result = 0;
        int finalResult = 0;
        List<String> allowedStrings = Arrays.asList
        (
        "null","eins","zwei","drei","vier","f�nf","sechs","sieben",
        "acht","neun","zehn","elf","zw�lf","dreizehn","vierzehn",
        "f�nfzehn","sechzehn","siebzehn","achtzehn","neunzehn",
        "zwanzig","einzwanzig","drei�ig","eindrei�ig","vierzig","einvierzig",
        "f�nfzig","einf�nfzig","sechzig","einsechzig","siebzig","einsiebzig",
        "achtzig","einachtzig","neunzig","einneunzig","einhundert","eintausend",
        "ein","euro","hundert","tausend"
        );

        if(input != null && input.length()> 0)
        {
            input = input.replaceAll("-", " ");
            input = input.toLowerCase().replaceAll("euro", "");
            input = input.toLowerCase().replaceAll("ein und ", "ein");
            input = input.toLowerCase().replaceAll("ein ", "ein");
            input = input.toLowerCase().replaceAll(" und", " ");
            String[] splittedParts = input.trim().split("\\s+");

            for(String str : splittedParts)
            {
                if(!allowedStrings.contains(str))
                {
                    isValidInput = false;
                    logger.info("Invalid word found : "+str);
                    break;
                }
            }
            if(isValidInput)
            {
                for(String str : splittedParts)
                {
                    if(str.equalsIgnoreCase("null")) {
                        result += 0;
                    }
                    else if(str.equalsIgnoreCase("eins")) {
                        result += 1;
                    }
                    else if(str.equalsIgnoreCase("zwei")) {
                        result += 2;
                    }
                    else if(str.equalsIgnoreCase("drei")) {
                        result += 3;
                    }
                    else if(str.equalsIgnoreCase("vier")) {
                        result += 4;
                    }
                    else if(str.equalsIgnoreCase("f�nf")) {
                        result += 5;
                    }
                    else if(str.equalsIgnoreCase("sechs")) {
                        result += 6;
                    }
                    else if(str.equalsIgnoreCase("sieben")) {
                        result += 7;
                    }
                    else if(str.equalsIgnoreCase("acht")) {
                        result += 8;
                    }
                    else if(str.equalsIgnoreCase("neun")) {
                        result += 9;
                    }
                    else if(str.equalsIgnoreCase("zehn")) {
                        result += 10;
                    }
                    else if(str.equalsIgnoreCase("elf")) {
                        result += 11;
                    }
                    else if(str.equalsIgnoreCase("zw�lf")) {
                        result += 12;
                    }
                    else if(str.equalsIgnoreCase("dreizehn")) {
                        result += 13;
                    }
                    else if(str.equalsIgnoreCase("vierzehn")) {
                        result += 14;
                    }
                    else if(str.equalsIgnoreCase("f�nfzehn")) {
                        result += 15;
                    }
                    else if(str.equalsIgnoreCase("sechzehn")) {
                        result += 16;
                    }
                    else if(str.equalsIgnoreCase("siebzehn")) {
                        result += 17;
                    }
                    else if(str.equalsIgnoreCase("achtzehn")) {
                        result += 18;
                    }
                    else if(str.equalsIgnoreCase("neunzehn")) {
                        result += 19;
                    }
                    else if(str.equalsIgnoreCase("zwanzig")) {
                        result += 20;
                    }
                    else if(str.equalsIgnoreCase("einzwanzig")) {
                    	result += 21;
                    }
                    else if(str.equalsIgnoreCase("drei�ig")) {
                        result += 30;
                    }
                    else if(str.equalsIgnoreCase("eindrei�g")) {
                    	result += 31;
                    }
                    else if(str.equalsIgnoreCase("vierzig")) {
                        result += 40;
                    }
                    else if(str.equalsIgnoreCase("einvierzig")) {
                    	result += 41;
                    }
                    else if(str.equalsIgnoreCase("f�nfzig")) {
                        result += 50;
                    } else if(str.equalsIgnoreCase("einf�nfzig")) {
                    	result += 51;
                    }
                    else if(str.equalsIgnoreCase("sechzig")) {
                        result += 60;
                    }
                    else if(str.equalsIgnoreCase("einsechzig")) {
                    	result += 61;
                    }
                    else if(str.equalsIgnoreCase("siebzig")) {
                        result += 70;
                    }
                    else if(str.equalsIgnoreCase("einsiebzig")) {
                    	result += 71;
                    }
                    else if(str.equalsIgnoreCase("achtzig")) {
                        result += 80;
                    }
                    else if(str.equalsIgnoreCase("einachtzig")) {
                    	result += 81;
                    }
                    else if(str.equalsIgnoreCase("neunzig")) {
                        result += 90;
                    }
                    else if(str.equalsIgnoreCase("einneunzig")) {
                    	result += 91;
                    }
                    else if(str.equalsIgnoreCase("hundert")) {
                    	result *= 100;
                    }
                    else if(str.equalsIgnoreCase("einhundert")) {
                        result += 100;
                    }
                    else if(str.equalsIgnoreCase("tausend")) {
                    	result *= 1000;
                    }
                    else if(str.equalsIgnoreCase("eintausend")) {
                        result += 1000;
                        finalResult += result;
                        result=0;
                    }
                }

                finalResult += result;
                result=0;
            }
        }
        return finalResult;
    }
    
//  private String selectQuestion(int i) {
//  String question;
//  switch(i){
//      case 0:
//          question ="M�chtest du das Notebook zum arbeiten also produktiv einsetzten?";
//          counter ++;
//          break;
//      case 1:
//          question = "M�chtest du �ber deinen Laptop Filme und Videos schauen?";
//          counter ++;
//          break;
//      case 2:
//          question = "M�chtest du an deinem Laptop Texte verfassen?";
//          counter ++;
//          break;
//      case 3:
//          question = "M�chtest du auf dem Laptop Spiele spielen?";
//          counter ++;
//          break;
//      case 4:
//          question = "M�chtest du auf dem Laptop Bilder und Videos bearbeiten?";
//          counter ++;
//          break;
//      case 5:
//          question = "M�chtest du auf dem Laptop moderne aufwendige Spiele spielen?";
//          counter ++;
//          break;
//      case 6:
//          question = "Soll der Laptop sich f�r Office-Anwendungen gut eignen?";
//          counter ++;
//          break;
//      case 7:
//          question = "Soll der Laptop denn �ltere, aber trotzdem noch aufwendigere Spiele unterst�tzen?";
//          counter ++;
//          break;
//      case 8:
//          question = "M�chtest du den Laptop zum Programmieren nutzen?";
//          counter ++;
//          break;
//      case 9:
//          question = "M�chten sie denn einfache Spiele wie Solit�r oder Browserspiele spielen?";
//          counter ++;
//          break;
//      default:
//          question = "Der Counter ist kleiner 0 oder gr��er 7!";
//          counter = 0;
//          //The program doesn't stop at the moment rather it starts at question 1
//
//  }
//  return question;
//}
    
   /* public static String arrayToString (String[] input) {
    	String output ="";
    	for (int i = 0 ; i < input.length() ; i++)
    		output += input[i];
    	return output;
    }*/

    
    
    /*
     * if(counter==0){
            question= selectQuestion(0);
            return askUserResponse(question);
        }
        if(counter==1) {
            if (answerasint == 2) {
                counter = counter - 1;
                question = selectQuestion(0);
                return askUserResponse(question);
            }
            if (answerasint == 0) { //erste antwort nein
                fragenid=1;
                question = selectQuestion(fragenid);
                return askUserResponse(question);
            }
            if (answerasint == 1) { //erste antwort ja
                fragenid=2;
                question = selectQuestion(fragenid);
                return askUserResponse(question);
            }
        }
        if(fragenid==1){
            if (answerasint == 2) {
                question = selectQuestion(fragenid);
                return askUserResponse(question);
            }
            if (answerasint == 0|| answerasint==1) { //zweite antwort nein oder ja
                fragenid=3;
                question = selectQuestion(fragenid);
                return askUserResponse(question);
            }
        }
        if(fragenid==2){
            if(answerasint==2){
                question=selectQuestion(fragenid);
                return askUserResponse(question);
            }
            if(answerasint==0||answerasint==1){
                fragenid=4;
                question=selectQuestion(fragenid);
                return askUserResponse(question);

            }
        }
        if(fragenid==3) {
        	if(counter == 5) {
        		if (answerasint == 2) {
        			counter = 4;
        			question = selectQuestion(fragenid);
                	return askUserResponse(question);
        		}
        		if(answerasint == 0) {
        			String answer = "Laptop 7";
        			return endResponse(answer);
        		}
        		if(answerasint == 1) {
        			fragenid=5;
	                question = selectQuestion(fragenid);
	                return askUserResponse(question);
        		}
        	}
        	else {
	            if (answerasint == 2) {
	                question = selectQuestion(fragenid);
	                return askUserResponse(question);
	            }
	            if (answerasint == 0) {
	                String answer="Laptop 1";
	                return endResponse(answer);
	            }
	            if (answerasint == 1) {
	                fragenid=5;
	                question = selectQuestion(fragenid);
	                return askUserResponse(question);
	            }
        	}

        }
        if(fragenid==4) {
            if (answerasint == 2) {
                question = selectQuestion(fragenid);
                return askUserResponse(question);
            }  if (answerasint == 0) {
                fragenid=6;
                question=selectQuestion(fragenid);
                return askUserResponse(question);
            }
            if (answerasint == 1) {
                String answer="Laptop 5";
                return endResponse(answer);
            }
        }
        if(fragenid==5) {
        	if(counter == 6) {
        		if (answerasint == 2) {
        			counter = 5;
	                question = selectQuestion(fragenid);
	                return askUserResponse(question);
	            }  if (answerasint == 0) {
	                fragenid=7;
	                question=selectQuestion(fragenid);
	                return askUserResponse(question);
	            }
	            if (answerasint == 1) {
	                String answer="Laptop 8";
	                return endResponse(answer);
	            }
        	}
        	else {
	            if (answerasint == 2) {
	                question = selectQuestion(fragenid);
	                return askUserResponse(question);
	            }  if (answerasint == 0) {
	                fragenid=7;
	                question=selectQuestion(fragenid);
	                return askUserResponse(question);
	            }
	            if (answerasint == 1) {
	                String answer="Laptop 2";
	                return endResponse(answer);
	            }
        	} 
        }
        if(fragenid==6){
            if(answerasint==2){
                question=selectQuestion(fragenid);
                return askUserResponse(question);
            }
            if(answerasint==0||answerasint==1){
                fragenid=8;
                question=selectQuestion(fragenid);
                return askUserResponse(question);

            }
        }
        if(fragenid==7) {
        	if(counter == 7) {
        		if (answerasint == 2) {
        			counter = 6;
                    question = selectQuestion(fragenid);
                    return askUserResponse(question);
                }  if (answerasint == 0) {
                    fragenid=9;
                    question=selectQuestion(fragenid);
                    return askUserResponse(question);
                }
                if (answerasint == 1) {
                    String answer="Laptop 9";
                    return endResponse(answer);
                }
        	}
        	else {
	            if (answerasint == 2) {
	                question = selectQuestion(fragenid);
	                return askUserResponse(question);
	            }  if (answerasint == 0) {
	                fragenid=9;
	                question=selectQuestion(fragenid);
	                return askUserResponse(question);
	            }
	            if (answerasint == 1) {
	                String answer="Laptop 3";
	                return endResponse(answer);
	            }
        	}
        }
        if(fragenid==8){
            if(answerasint==2){
                question=selectQuestion(fragenid);
                return askUserResponse(question);
            }
            if(answerasint==0||answerasint==1){
//                String answer="Laptop 6";
//                return endResponse(answer);
            	counter = 4;
            	fragenid = 3;
            	question = selectQuestion(fragenid);
            	return askUserResponse(question);
            }
        }
        if(fragenid==9){
        	if(counter == 8) {
        		if(answerasint==2){
        			counter = 7;
                    question=selectQuestion(fragenid);
                    return askUserResponse(question);
                }
                if(answerasint==0||answerasint==1){
//                    fragenid=8;
                    String answer="Laptop 10";
                    return endResponse(answer);
                }
        	}
        	else {
        		if(answerasint==2){
                    question=selectQuestion(fragenid);
                    return askUserResponse(question);
                }
                if(answerasint==0||answerasint==1){
//                    fragenid=8;
                    String answer="Laptop 4";
                    return endResponse(answer);
                }
        	}
            
        }
        return askUserResponse("Dieser Zustand h�tte nie eintreffen d�rfen!!");
        */
}
