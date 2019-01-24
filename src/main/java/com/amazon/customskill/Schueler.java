package com.amazon.customskill;

public class Schueler implements Nutzer {
	private int frageCounter = 0;

	private String[][] laptopDataBase = new String[3][2];
	protected boolean noMoreQuestions = false;
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

	public void takeAnswer(String answer) {
		switch (frageCounter) {
		case 0:
			if (answer.equalsIgnoreCase("ja")) {
				frageCounter++;
			} else {
				frageCounter = +2;
				konfiguration = "1";
			}
			break;
		case 1:
			if (answer.equalsIgnoreCase("ja")) {
				konfiguration = "3";
				convertible = "false";
				noMoreQuestions = true;
			} else {
				konfiguration = "1";
				frageCounter++;
			}
			break;
		case 2:
			if (answer.equalsIgnoreCase("ja")) {
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
		for (int i = 1; i < laptopDataBase.length; i++) {
			if (laptopDataBase[i][0].equals(kompletteAnfrage)) {
				antwort = laptopDataBase[i][1];
				break;
			}
		}

		String aussage1 = "Der " + antwort + " ist für dich eine gute Auswahl.";
		return aussage1;
	}

	@Override
	public int getCounter() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void setFrageCounter(int frageCounter) {
		// TODO Auto-generated method stub
		this.frageCounter = frageCounter;

	}

}

