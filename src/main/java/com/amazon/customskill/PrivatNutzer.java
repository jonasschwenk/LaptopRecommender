package com.amazon.customskill;

public class PrivatNutzer implements Nutzer {
	protected final int minimalPrice = 899;
	// protected final int NumberOfQuestions = 3;

	protected String[][] laptopDataBase = new String[8][2];

	protected String completteAnfrage = "";
	private int frageCounter = 0;

	// Spezifikationen
	private String convertible = "true";
	private String laufwerk = "true";
	private String blueRayLaufwerk = "false";

	private boolean noMoreQuestions = false;

	public boolean getNoMoreQuestions() {
		return noMoreQuestions;
	}

	public PrivatNutzer() {
		listAusfuellen();
	}

	public int getFrageCounter() {
		return frageCounter;
	}

	public void setFrageCounter(int frageCounter) {
		this.frageCounter = frageCounter;
	}
//	public int getAnswers (int index) {
//		return answers[index];
//	}
//	public void setAnswers (int index, int value) {
//		answers[index] = value;
//	}

	public String selectQuestion() {
		String question = "";
		;
		switch (frageCounter) {
		case 1:
			question = "Moechtest du ein Covertible haben, sprich soll man die Tastatur abnehmen koennen?";
			break;
		case 2:
			question = "Benoetigst du ein Laufwerk?";
			break;
		case 3:
			question = "Moechtest du ein Blue Ray abspielen koennen?";
			break;

		default:
			break;
		// counter = 0;
		// The program doesn't stop at the moment rather it starts at question 1

		}
		return question;
	}

	public void takeAnswer(int answerAsInt) {
		// initialisiert mit standartwerten

		switch (frageCounter) {
		case 0:
			if (answerAsInt == 1) {
				convertible = "true";
				frageCounter++;
			} else if (answerAsInt == 0) {
				convertible = "false";
				frageCounter++;
			}
			break;
		case 1:
			if (answerAsInt == 1) {
				laufwerk = "true";
				frageCounter++;
			} else if (answerAsInt == 0) {
				laufwerk = "false";
				noMoreQuestions = true;
			}
			break;
		case 2:
			if (answerAsInt == 1) {
				blueRayLaufwerk = "true";
				noMoreQuestions = true;
			} else if (answerAsInt == 0) {
				blueRayLaufwerk = "false";
				noMoreQuestions = true;
			}
			break;
		default:
			break;
		}
		completteAnfrage = convertible + "," + laufwerk + "," + blueRayLaufwerk;
	}

	public void listAusfuellen() {
		laptopDataBase[0][0] = "true,true,false";		laptopDataBase[0][1] = "Leider gibt es keine Convertibles mit eingebautem Laufwerk. Ich kann dir als Alternative das Acer Spin 3 empfehlen. Dazu kaufst du dir dann noch ein USB Laufwerk, falls das notwendig ist.";
		laptopDataBase[1][0] = "true,false,true";		laptopDataBase[1][1] = "...";
		laptopDataBase[2][0] = "false,true,true";		laptopDataBase[2][1] = "...";
		laptopDataBase[3][0] = "false,false,true";		laptopDataBase[3][1] = "...";
		laptopDataBase[4][0] = "false,true,false";		laptopDataBase[4][1] = "HP 250 G6";
		laptopDataBase[5][0] = "true,false,false";		laptopDataBase[5][1] = "Acer Spin 3";
		laptopDataBase[6][0] = "true,true,true";		laptopDataBase[6][1] = "...";
		laptopDataBase[7][0] = "false,false,false";		laptopDataBase[7][1] = "HP 250 G6"; // Die Tabelle ist komplett ausgefüllt
	}

	public String getLaptopFromAnswers() {
		// array durchgehen und mit anfrage vergleichen
		for (int i = 0; i <= laptopDataBase.length; i++)
			if (completteAnfrage.equals(laptopDataBase[i][0]))
				return laptopDataBase[i][1];
		return "error";
	}
}
