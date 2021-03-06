package com.amazon.customskill;

public class Senioren implements Nutzer {

	private String[][] laptopDataBase = new String[4][2];
	int frageCounter = 0;

	private boolean noMoreQuestions = false;

	public boolean getNoMoreQuestions() {
		return noMoreQuestions;
	}

	// Spezifikationen
	String Gewicht = "<= 2kg";
	String Bildschirm = "grosser bildschirm";
	String kompletteAnfrage = Gewicht + "," + Bildschirm;

	public Senioren() {
		listAusfuellen();
	}

	public String selectQuestion() {
		String question = "";
		switch (frageCounter) {
		case 0:
			question = "Moechtest du den laptop auch unterwegs oder im urlaub benutzen?";
			break;
		case 1:
			question = "Hast du Probleme mit den Augen und ein groesserer Bildschirm wuerde dir helfen?";
			noMoreQuestions = true;
			break;
		default:
		}
		return question;
	}

	public void takeAnswer(int answerAsInt) {
		switch (frageCounter) {
		case 0:
			if (answerAsInt == 1) {
				Gewicht = "<= 2kg";
				frageCounter++;
			} else if (answerAsInt == 0) {
				Gewicht = "> 2kg";
				frageCounter++;
			}
			break;
		case 1:
			if (answerAsInt == 1) {
				Bildschirm = "grosser bildschirm";
				noMoreQuestions = true;
			} else if (answerAsInt == 0) {
				Bildschirm = "normaler bildschirm";
				noMoreQuestions = true;
			}
			break;
		}
		kompletteAnfrage = Gewicht + "," + Bildschirm;
	}

	public void listAusfuellen() { // Diese Methode fuellt unsere Database.

		// laptopDataBase[i][0] = "Gewicht, Bildschirm grosse" ; //laptopDataBase[i][1]
		// = "Notebook" ;

		laptopDataBase[0][0] = "<= 2kg,grosser bildschirm";			laptopDataBase[0][1] = "Lenovo  Win 10";
		laptopDataBase[1][0] = "<= 2kg,normaler bildschirm";		laptopDataBase[1][1] = "HP 15 IPS M Win10";
		laptopDataBase[2][0] = "> 2kg,grosser bildschirm";			laptopDataBase[2][1] = "HP 17-by0101ng Intel Core Win10";
		laptopDataBase[3][0] = "> 2kg,normaler bildschirm";			laptopDataBase[3][1] = "Lenovo Ideapad Win1"; // die Tabelle ist komplett ausgefuellt

	}

	public String getLaptopFromAnswers() {
		// array durchgehen und mit anfrage vergleichen
		for (int i = 0; i <= laptopDataBase.length; i++)
			if (kompletteAnfrage.equals(laptopDataBase[i][0]))
				return laptopDataBase[i][1];
		return "error";
	}
}
