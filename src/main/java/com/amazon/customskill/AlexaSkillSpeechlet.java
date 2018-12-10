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
	public int counter = 0;
	public static String userRequest;

    static Logger logger = LoggerFactory.getLogger(AlexaSkillSpeechlet.class);

    private PosTagger p;
    
    private static int[] answers = new int[4];

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
    	
        IntentRequest request = requestEnvelope.getRequest();

        Intent intent = request.getIntent();

        userRequest = intent.getSlot("Alles").getValue();
        int answerasint = analyseAnswer(userRequest);//Analysiere die Antwort und gibt bei nein 0 ja 1 und sonst 2 zurück
        if (counter > 0&& answerasint<2) //Antwort verstanden und gespeichert im Antwortenarray
            answers[counter - 1] = answerasint;
        logger.info("Received following text: [" + userRequest + "]");

        //String result = analyze(userRequest);
        String question;
        if(counter>1&&answerasint==2){ // Fall mindestens erste Frage gestellt und antwort nicht verstanden
            counter-=1;
            question= selectQuestion();
            return askUserResponse(question);
        }
        else {
            if (counter < 4) { //Antwort verstanden und nächste Frage wird gestellt
                question = selectQuestion();
                return askUserResponse(question);
            } else {        //Antwort verstanden und letzte Frage gestellt
                String end = createAnswerURL(answers);
                counter = 0;
                return endResponse(end);
            }
        }
    }
    public int analyseAnswer(String answer){            // Antwort analysieren
        int analysedanswer=2;
        StringTokenizer token = new StringTokenizer(answer);
        int length = token.countTokens();
        String[] cuttedanswer= new String[length];
        for(int i=0; i<length;i++){             //zurückgeliferte Antwort in einzelne Wörter zerlegen
            cuttedanswer[i]=token.nextToken();
        }
        for(int i=0; i<length;i++){         // alle Wörter durchgehen und auf ja und nein überprüfen
            if(cuttedanswer[i].equalsIgnoreCase("ja")||cuttedanswer[i].equalsIgnoreCase("genau")||cuttedanswer[i].equalsIgnoreCase("exakt")||cuttedanswer[i].equalsIgnoreCase("jeden")||cuttedanswer[i].equalsIgnoreCase("immer")) {
                analysedanswer = 1; // case answer ja
            }
            if (cuttedanswer[i].equalsIgnoreCase("nein")||cuttedanswer[i].equalsIgnoreCase("keinen")||cuttedanswer[i].equalsIgnoreCase("niemals")||cuttedanswer[i].equalsIgnoreCase("ne")) {
                analysedanswer= 0;// case answer nein
            }
        }
        return analysedanswer;
    }

    public String createAnswerURL(int[] answerArray){
        String url= new String();
        if(answerArray[0]==0&&answerArray[1]==0&&answerArray[2]==0&&answerArray[3]==0)
            url="";
        if(answerArray[0]==0&&answerArray[1]==0&&answerArray[2]==0&&answerArray[3]==1)
            url="";
        if(answerArray[0]==0&&answerArray[1]==0&&answerArray[2]==1&&answerArray[3]==0)
            url="";
        if(answerArray[0]==0&&answerArray[1]==0&&answerArray[2]==1&&answerArray[3]==1)
            url="";
        if(answerArray[0]==0&&answerArray[1]==1&&answerArray[2]==0&&answerArray[3]==0)
            url="";
        if(answerArray[0]==0&&answerArray[1]==1&&answerArray[2]==0&&answerArray[3]==1)
            url="";
        if(answerArray[0]==0&&answerArray[1]==1&&answerArray[2]==1&&answerArray[3]==0)
            url="";
        if(answerArray[0]==0&&answerArray[1]==1&&answerArray[2]==1&&answerArray[3]==1)
            url="";
        if(answerArray[0]==1&&answerArray[1]==0&&answerArray[2]==0&&answerArray[3]==0)
            url="";
        if(answerArray[0]==1&&answerArray[1]==0&&answerArray[2]==0&&answerArray[3]==1)
            url="";
        if(answerArray[0]==1&&answerArray[1]==0&&answerArray[2]==1&&answerArray[3]==0)
            url="";
        if(answerArray[0]==1&&answerArray[1]==0&&answerArray[2]==1&&answerArray[3]==1)
            url="";
        if(answerArray[0]==1&&answerArray[1]==1&&answerArray[2]==0&&answerArray[3]==0)
            url="";
        if(answerArray[0]==1&&answerArray[1]==1&&answerArray[2]==0&&answerArray[3]==1)
            url="";
        if(answerArray[0]==1&&answerArray[1]==1&&answerArray[2]==1&&answerArray[3]==0)
            url="";
        if(answerArray[0]==1&&answerArray[1]==1&&answerArray[2]==1&&answerArray[3]==1)
            url="";

        return url;
    }
    
    private String selectQuestion() {
    	String question;
    	 switch(counter){ 
         case 0: 
        	 question ="Möchten Sie das Notebook produktiv einsetzen, um zum Beispiel TExte zu ferfassen oder Fotos und Videos zu bearbeiten?";
        	 counter ++;
             break;
         case 1:
        	 question = "Möchten Sie das Notebook häufig transportieren?";
        	counter ++;
             break;
         case 2:
         	question = "Möchten Sie mit dem Notebook Spiele spielen?";
         	counter ++;
         	break;
         case 3:
         	question = "Benötigen Sie viel Speicherplatz?";
         	counter ++;
         	break;
         default: 
        	 question = "Der Counter ist kleiner 0 oder größer 7!";
        	 counter = 0;
        	 //The program doesn't stop at the moment rather it starts at question 1
        
         }
    	 return question;
    }

    /**
     * formats the text in weird ways
     * @param text
     * @param i
     * @return
     */
    private SpeechletResponse responseWithFlavour(String text, int i) {
       
    	SsmlOutputSpeech speech = new SsmlOutputSpeech();
    	 switch(i){ 
         case 0: 
        	 speech.setSsml("<speak><amazon:effect name=\"whispered\">" + text + "</amazon:effect></speak>");
             break; 
         case 1: 
        	 speech.setSsml("<speak><emphasis level=\"strong\">" + text + "</emphasis></speak>");
             break; 
         case 2: 
        	 String half1=text.split(" ")[0];
        	 String[] rest = Arrays.copyOfRange(text.split(" "), 1, text.split(" ").length);
        	 speech.setSsml("<speak>"+half1+"<break time=\"3s\"/>"+ StringUtils.join(rest," ") + "</speak>");
             break; 
         case 3: 
        	 String firstNoun="erstes erkanntes nomen";
        	 String firstN=text.split(" ")[3];
        	 speech.setSsml("<speak>"+firstNoun+ "<say-as interpret-as=\"spell-out\">"+firstN+"</say-as>"+"</speak>");
             break; 
         case 4: 
        	 speech.setSsml("<speak><audio src='soundbank://soundlibrary/transportation/amzn_sfx_airplane_takeoff_whoosh_01'/></speak>");
             break;
         default: 
        	 speech.setSsml("<speak><amazon:effect name=\"whispered\">" + text + "</amazon:effect></speak>");
         } 

        return SpeechletResponse.newTellResponse(speech);
	}

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
    	return askUserResponse("Willkommen bei der Notebookberatung durch Alexa! Ich werde Ihnen eine Reihe an Fragen stellen und an Hand Ihrer Antworten ein Anforderungsprofil erstellen, auf dessen Basis ich Ihnen eine Laptop Empfehlung aussprechen werde!");
       //    	return askUserResponse("<amazon:effect name=\"whispered\">Hey Leute</amazon:effect>, ich bin ein <phoneme alphabet=\"ipa\" ph=\"ËˆfÊŒni\">funny</phoneme> Nomen <phoneme alphabet=\"ipa\" ph=\"bÉ’t\">bot</phoneme>! Sag einen Satz und ich nenne dir die enthaltenen Nomen");
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
    	
    	return SpeechletResponse.newTellResponse(outputSpeech);
    }
   /* public static String arrayToString (String[] input) {
    	String output ="";
    	for (int i = 0 ; i < input.length() ; i++)
    		output += input[i];
    	return output;
    }*/

}
