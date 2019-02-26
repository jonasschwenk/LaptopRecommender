package com.amazon.customskill;

public class Schueler implements Nutzer {

	private int frageCounter = 0;
	private String[][] laptopDataBase = new String[4][2];

	private boolean noMoreQuestions = false;

	public boolean getNoMoreQuestions() {
		return noMoreQuestions;
	}

	// Spezifikationen
	private String konfiguration = "1";
	private String convertible = "false";
	private String kompletteAnfrage = konfiguration + "," + convertible;

	public Schueler() {
		listAusfuellen();
	}

	public String selectQuestion() {
		String question = "";
		switch (frageCounter) {
		case 0:
			question = "Bist du aelter als zwoelf?";
			break;
		case 1:
			question = "Wird der Laptop zum Spielen verwendet?";
			break;
		case 2:
			question = "Moechtest du ein Convertible haben, sprich soll man die Tastatur nach hinten weg klappen koennen?";
			break;
		}
		return question;
	}

	public void takeAnswer(int answerAsInt) {
		switch (frageCounter) {
		case 0:
			if (answerAsInt == 1) {
				frageCounter++;
			} else if (answerAsInt == 0) {
				frageCounter += 2;
				konfiguration = "1";
			}
			break;
		case 1:
			if (answerAsInt == 1) {
				konfiguration = "3";
				convertible = "false";
				noMoreQuestions = true;
			} else if (answerAsInt == 0) {
				konfiguration = "1";
				frageCounter++;
			}
			break;
		case 2:
			if (answerAsInt == 1) {
				convertible = "true";
				noMoreQuestions = true;
			} else if (answerAsInt == 0) {
				convertible = "false";
				noMoreQuestions = true;
			}
			break;
		}
		kompletteAnfrage = konfiguration + "," + convertible;
	}

	public void listAusfuellen() {
		laptopDataBase[0][0] = "1,false";		laptopDataBase[0][1] = "HP 250 G6 in Silber-Grau";
		laptopDataBase[1][0] = "1,true";		laptopDataBase[1][1] = "Acer Spin 3";
		laptopDataBase[2][0] = "3,false";		laptopDataBase[2][1] = "Lenovo Legion Y720-15IKB";
	}

	public String getLaptopFromAnswers() {
		// array durchgehen und mit anfrage vergleichen
		for (int i = 0; i <= laptopDataBase.length; i++)
			if (kompletteAnfrage.equals(laptopDataBase[i][0]))
				return laptopDataBase[i][1];
		return "error";
	}
}
