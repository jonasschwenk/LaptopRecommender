package com.amazon.customskill;

public class Schueler implements Nutzer {
	private int frageCounter = 0;

	private String[][] laptopDataBase = new String[3][2];
	private boolean noMoreQuestions = false;
	String kompletteAnfrage = "";
	String konfiguration = "1";
	String convertible = "false";

	public Schueler() {
		listAusfuellen();
	}

	public String selectQuestion() {
		String question = "";
		switch (frageCounter) {
		case 0:
			question = "Ist der schüler oder die schülerin älter als zwölf?";
//                frageCounter ++;
			break;
		case 1:
			question = "Wird der Laptop zum Spielen verwendet?";
			break;
		case 2:
			question = "Soll es sich bei  dem Laptop um ein Convertible handeln?";
			noMoreQuestions = true;
			break;
		}
		return question;
	}

	public void takeAnswer(int answerAsInt) {
		switch (frageCounter) {
		case 0:
			if (answerAsInt == 1) {
				frageCounter++;
			} else {
				frageCounter = +2;
				konfiguration = "1";
			}
			break;
		case 1:
			if (answerAsInt == 1) {
				konfiguration = "3";
				convertible = "false";
				noMoreQuestions = true;
			} else {
				konfiguration = "1";
				frageCounter++;
			}
			break;
		case 2:
			if (answerAsInt == 1) {
				convertible = "true";
			} else {
				convertible = "false";
			}
			break;

		}
		kompletteAnfrage = konfiguration + "," + convertible;

	}

	public void listAusfuellen() {
		laptopDataBase[0][0] = "1,false";	laptopDataBase[0][1] = "...";
		laptopDataBase[1][0] = "1, true";	laptopDataBase[1][1] = "...";
		laptopDataBase[2][0] = "2, false";	laptopDataBase[2][1] = "...";
	}

	public String getLaptopFromAnswers() {

		String antwort = "";
		for (int i = 0; i < laptopDataBase.length; i++) {
			if (laptopDataBase[i][0].equals(kompletteAnfrage)) {
				antwort = laptopDataBase[i][1];
				break;
			}
		}

		String aussage1 = "Der " + antwort + " ist für dich eine gute Auswahl.";
		return aussage1;
	}
	public boolean getNoMoreQuestions() {
		return noMoreQuestions;
	}

}


