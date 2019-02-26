package com.amazon.customskill;

public class PrivatNutzer implements Nutzer {
	protected final int minimalPrice = 899;
	// protected final int NumberOfQuestions = 3;

	protected String[][] laptopDataBase = new String[8][2];

	private int frageCounter = 0;

	// Spezifikationen
	private String convertible = "false";
	private String laufwerk = "true";
	private String blueRayLaufwerk = "false";
	protected String kompletteAnfrage = convertible + "," + laufwerk + "," + blueRayLaufwerk;

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

	public String selectQuestion() {
		String question = "";
		switch (frageCounter) {
		case 0:
			question = "Moechtest du ein Covertible haben, sprich soll man die Tastatur abnehmen koennen?";
			break;
		case 1:
			question = "Benoetigst du ein Laufwerk?";
			break;
		case 2:
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
		kompletteAnfrage = convertible + "," + laufwerk + "," + blueRayLaufwerk;
	}

	public void listAusfuellen() {
		laptopDataBase[0][0] = "true,true,false";		laptopDataBase[0][1] = "Leider gibt es keine Convertibles mit eingebautem Laufwerk. Ich kann dir als Alternative das Acer Spin 3 empfehlen. Dazu kaufst du dir dann noch ein USB Laufwerk, falls das notwendig ist.";
		laptopDataBase[2][0] = "false,true,true";		laptopDataBase[2][1] = "Ich empfehle dir das HP 250 G6. Zusätzlich empfehle ich dir, ein BluRay USB Laufwerk zu kaufen oder deine BluRay Filme an einem Fernseher über einen BluRay Player zu schauen.";
		laptopDataBase[4][0] = "false,true,false";		laptopDataBase[4][1] = "HP 250 G6";
		laptopDataBase[5][0] = "true,false,false";		laptopDataBase[5][1] = "Acer Spin 3";
		laptopDataBase[6][0] = "true,true,true";		laptopDataBase[6][1] = "Leider gibt es keine Convertibles mit eingebautem Laufwerk. Ich kann dir als Alternative das Acer Spin 3 empfehlen. Dazu solltest du dir dann noch ein USB BluRay Laufwerk kaufen, falls das notwendig ist.";
		laptopDataBase[7][0] = "false,false,false";		laptopDataBase[7][1] = "Acer Aspire 3"; // Die Tabelle ist komplett ausgefüllt
	}

	public String getLaptopFromAnswers() {
		// array durchgehen und mit anfrage vergleichen
		for (int i = 0; i <= laptopDataBase.length; i++)
			if (kompletteAnfrage.equals(laptopDataBase[i][0]))
				return laptopDataBase[i][1];
		return "error";
	}
}
