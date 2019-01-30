/**
 /**
    Copyright 2014-2015 Amazon.com, Inc. or its affiliates. All Rights Reserved.

    Licensed under the Apache License, Version 2.0 (the "License"). You may not use this file except in compliance with the License. A copy of the License is located at

        http://aws.amazon.com/apache2.0/

    or in the "license" file accompanying this file. This file is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the specific language governing permissions and limitations under the License.
 */
package com.amazon.customskill;

import java.util.*;
//import java.util.stream.IntStream;

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
//import com.amazon.speech.ui.PlainTextOutputSpeech;
import com.amazon.speech.ui.Reprompt;
import com.amazon.speech.ui.SsmlOutputSpeech;

import nlp.dkpro.backend.PosTagger;
import nlp.dkpro.backend.NlpSingleton;

/*
 * This class is the actual skill. Here you receive the input and have to produce the speech output. 
 */

public class AlexaSkillSpeechlet implements SpeechletV2 {
	int budget = 0;
	public int counter = 0;
	public static String userRequest;
	String lastQuestion = "";
	int minimumPrice = 370;
	boolean lowBudget = false;
	Nutzer nutzer;
	Synonymfinder Synonym = new Synonymfinder();

	int groupCounter = 0;

	static Logger logger = LoggerFactory.getLogger(AlexaSkillSpeechlet.class);

	private PosTagger p;

//	private static int[] answers = new int[6];

	@Override
	public void onSessionStarted(SpeechletRequestEnvelope<SessionStartedRequest> requestEnvelope) {
		p = NlpSingleton.getInstance();
		logger.info("Alexa session begins");
	}

	@Override
	public SpeechletResponse onLaunch(SpeechletRequestEnvelope<LaunchRequest> requestEnvelope) {
		return getWelcomeResponse();
	}

	@Override
	public SpeechletResponse onIntent(SpeechletRequestEnvelope<IntentRequest> requestEnvelope) {
		IntentRequest request = requestEnvelope.getRequest();

		Intent intent = request.getIntent();

		userRequest = intent.getSlot("Alles").getValue();
		if (Synonym.erkenneSynonym(userRequest).equalsIgnoreCase("abbrechen")) {
			return endResponse("Beratung abgebrochen!");
		}

		if (lastQuestion.equalsIgnoreCase("Wie viel moechtest du maximal ausgeben?")) {
//			budget = stringToNumber(userRequest);
			lastQuestion = "";
//			if (budget < minimumPrice) {
//				lowBudget = true;
//				return askUserResponse(
//						"Dein Budget liegt unterhalb des Preises meiner Minimalkonfiguration! Daher werde ich dir keinen Laptop innerhalb deines Budgets empfehlen koennen. Moechtest du die Beratung dennoch Fortsetzen?");
//			}
			return askUserResponse(nutzer.selectQuestion());
		}

		if (counter == 0) {
			String nomen = analyze(userRequest);
			String group = selectGroup(nomen);
			return askUserResponse(group);
		}
		if (counter != 0 && lastQuestion.equals("")) {
			int answerAsInt = analyseAnswer(userRequest);// Analysiere die Antwort und gibt bei nein 0 ja 1 und sonst 2
															// zurÃ¼ck
			if (lowBudget && answerAsInt == 1) {
				lowBudget = false;
				return askUserResponse(nutzer.selectQuestion());
			} else if (lowBudget && answerAsInt == 0)
				return endResponse(
						"Okay. Ich empfehle dir, noch etwas Geld zu sparen, oder dich auf dem Gebrauchtmarkt umzusehen! Die Beratung wird jetzt beendet!");

			if (userRequest.equalsIgnoreCase("beratung überspringen")) {
				return endResponse(nutzer.getLaptopFromAnswers());
			}

			nutzer.takeAnswer(answerAsInt);
			if (!nutzer.getNoMoreQuestions()) {
				String question = nutzer.selectQuestion();
				return askUserResponse(question);
			} else {
				String laptop = nutzer.getLaptopFromAnswers();
				return endResponse(laptop);
			}
		}

		logger.info("Received following text: [" + userRequest + "]");
		return askUserResponse("Diese Ausgabe haette nicht ausgegeben werden duerfen!");
	}

	public int analyseAnswer(String answer) { // Antwort analysieren
		int analysedanswer = 2;
		StringTokenizer token = new StringTokenizer(answer);
		int length = token.countTokens();
		String[] cuttedanswer = new String[length];
		for (int i = 0; i < length; i++) { // zurÃ¼ckgeliferte Antwort in einzelne WÃ¶rter zerlegen
			cuttedanswer[i] = token.nextToken();
		}
		for (int i = 0; i < length; i++) { // alle WÃ¶rter durchgehen und auf ja und nein Ã¼berprÃ¼fen
			if (Synonym.erkenneSynonym(cuttedanswer[i]).equalsIgnoreCase("ja")) {
				analysedanswer = 1; // case answer ja
			}
			if (Synonym.erkenneSynonym(cuttedanswer[i]).equalsIgnoreCase("nein")) {
				analysedanswer = 0;// case answer nein
			}
		}
		return analysedanswer;
	}

	public String selectGroup(String group) {

		String firstQuestion;
		group = Synonym.erkenneSynonym(group).toLowerCase();

		if (group.equalsIgnoreCase("student")) {
			nutzer = new Student();
			firstQuestion = "Wie viel moechtest du maximal ausgeben?";
			counter++;
		} else if (group.equalsIgnoreCase("schueler")) {
			nutzer = new Schueler();
			firstQuestion = "Wie viel moechtest du maximal ausgeben?";
			counter++;
		} else if (group.equalsIgnoreCase("senior")) {
			nutzer = new Senioren();
			firstQuestion = "Wie viel moechtest du maximal ausgeben?";
			counter++;
		} else if (group.equalsIgnoreCase("gamer")) {
			nutzer = new Gamer();
			firstQuestion = "Wie viel moechtest du maximal ausgeben?";
			counter++;
		} else if (group.equalsIgnoreCase("privatnutzer")) {
			nutzer = new PrivatNutzer();
			firstQuestion = "Wie viel moechtest du maximal ausgeben?";
			counter++;
		} else
			firstQuestion = "Bitte eine der folgenden Kategorien waehlen: Privatnutzer, Student, Schueler, Gamer oder Senior!";
		lastQuestion = firstQuestion;
		return firstQuestion;
	}

	/**
	 * formats the text in weird ways
	 * 
	 * @param text
	 * @param i
	 * @return
	 */

	private String analyze(String request) {
		List<String> nouns = new ArrayList<>();
		try {
			nouns = p.findNouns(userRequest);
			logger.info("Detected following nouns: [" + StringUtils.join(nouns, " ") + "]");
		} catch (Exception e) {
			throw new UnsupportedOperationException();
		}

		if (nouns.isEmpty()) {
			return ("Ich habe keine Nomen erkannt");
		}

		return StringUtils.join(nouns, " und ");
	}

	@Override
	public void onSessionEnded(SpeechletRequestEnvelope<SessionEndedRequest> requestEnvelope) {
		logger.info("Alexa session ends now");
	}

	/*
	 * The first question presented to the skill user (entry point)
	 */
	private SpeechletResponse getWelcomeResponse() {
		return askUserResponse(
				"Willkommen bei der Notebookberatung durch Alexa! Ich werde dir eine Reihe an Fragen stellen und an Hand deiner Antworten werde ich dir eine Liste von zu dir passenden Laptops ausgeben! Zu welcher der folgenden Nutzergruppen gehoerst du? Bist du ein Student, ein Schueler, ein Senior, ein Privatnutzer oder ein Gamer?");
	}

	/**
	 * Tell the user something - the Alexa session ends after a 'tell'
	 */

	/**
	 * A response to the original input - the session stays alive after an ask
	 * request was send. have a look on
	 * https://developer.amazon.com/de/docs/custom-skills/speech-synthesis-markup-language-ssml-reference.html
	 * 
	 * @param text
	 * @return
	 */
	private SpeechletResponse askUserResponse(String text) {
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
		List<String> allowedStrings = Arrays.asList("null", "eins", "zwei", "drei", "vier", "fünf", "sechs", "sieben",
				"acht", "neun", "zehn", "elf", "zwölf", "dreizehn", "vierzehn", "fünfzehn", "sechzehn", "siebzehn",
				"achtzehn", "neunzehn", "zwanzig", "einzwanzig", "dreißig", "eindreißig", "vierzig", "einvierzig",
				"fünfzig", "einfünfzig", "sechzig", "einsechzig", "siebzig", "einsiebzig", "achtzig", "einachtzig",
				"neunzig", "einneunzig", "einhundert", "eintausend", "ein", "euro", "hundert", "tausend");

		if (input != null && input.length() > 0) {
			input = input.replaceAll("-", " ");
			input = input.toLowerCase().replaceAll("euro", "");
			input = input.toLowerCase().replaceAll("ein und ", "ein");
			input = input.toLowerCase().replaceAll("ein ", "ein");
			input = input.toLowerCase().replaceAll(" und", " ");
			String[] splittedParts = input.trim().split("\\s+");

			for (String str : splittedParts) {
				if (!allowedStrings.contains(str)) {
					isValidInput = false;
					logger.info("Invalid word found : " + str);
					break;
				}
			}
			if (isValidInput) {
				for (String str : splittedParts) {
					if (str.equalsIgnoreCase("null")) {
						result += 0;
					} else if (str.equalsIgnoreCase("eins")) {
						result += 1;
					} else if (str.equalsIgnoreCase("zwei")) {
						result += 2;
					} else if (str.equalsIgnoreCase("drei")) {
						result += 3;
					} else if (str.equalsIgnoreCase("vier")) {
						result += 4;
					} else if (str.equalsIgnoreCase("fünf")) {
						result += 5;
					} else if (str.equalsIgnoreCase("sechs")) {
						result += 6;
					} else if (str.equalsIgnoreCase("sieben")) {
						result += 7;
					} else if (str.equalsIgnoreCase("acht")) {
						result += 8;
					} else if (str.equalsIgnoreCase("neun")) {
						result += 9;
					} else if (str.equalsIgnoreCase("zehn")) {
						result += 10;
					} else if (str.equalsIgnoreCase("elf")) {
						result += 11;
					} else if (str.equalsIgnoreCase("zwölf")) {
						result += 12;
					} else if (str.equalsIgnoreCase("dreizehn")) {
						result += 13;
					} else if (str.equalsIgnoreCase("vierzehn")) {
						result += 14;
					} else if (str.equalsIgnoreCase("fünfzehn")) {
						result += 15;
					} else if (str.equalsIgnoreCase("sechzehn")) {
						result += 16;
					} else if (str.equalsIgnoreCase("siebzehn")) {
						result += 17;
					} else if (str.equalsIgnoreCase("achtzehn")) {
						result += 18;
					} else if (str.equalsIgnoreCase("neunzehn")) {
						result += 19;
					} else if (str.equalsIgnoreCase("zwanzig")) {
						result += 20;
					} else if (str.equalsIgnoreCase("einzwanzig")) {
						result += 21;
					} else if (str.equalsIgnoreCase("dreißig")) {
						result += 30;
					} else if (str.equalsIgnoreCase("eindreißg")) {
						result += 31;
					} else if (str.equalsIgnoreCase("vierzig")) {
						result += 40;
					} else if (str.equalsIgnoreCase("einvierzig")) {
						result += 41;
					} else if (str.equalsIgnoreCase("fünfzig")) {
						result += 50;
					} else if (str.equalsIgnoreCase("einfünfzig")) {
						result += 51;
					} else if (str.equalsIgnoreCase("sechzig")) {
						result += 60;
					} else if (str.equalsIgnoreCase("einsechzig")) {
						result += 61;
					} else if (str.equalsIgnoreCase("siebzig")) {
						result += 70;
					} else if (str.equalsIgnoreCase("einsiebzig")) {
						result += 71;
					} else if (str.equalsIgnoreCase("achtzig")) {
						result += 80;
					} else if (str.equalsIgnoreCase("einachtzig")) {
						result += 81;
					} else if (str.equalsIgnoreCase("neunzig")) {
						result += 90;
					} else if (str.equalsIgnoreCase("einneunzig")) {
						result += 91;
					} else if (str.equalsIgnoreCase("hundert")) {
						result *= 100;
					} else if (str.equalsIgnoreCase("einhundert")) {
						result += 100;
					} else if (str.equalsIgnoreCase("tausend")) {
						result *= 1000;
					} else if (str.equalsIgnoreCase("eintausend")) {
						result += 1000;
						finalResult += result;
						result = 0;
					}
				}

				finalResult += result;
				result = 0;
			}
		}
		return finalResult;
	}
}