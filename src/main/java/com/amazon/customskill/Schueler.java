package com.amazon.customskill;

public class Schueler implements Nutzer {
	
	private int frageCounter = 0;
	private String[][] laptopDataBase = new String[3][2];
	
	private boolean noMoreQuestions = false;
	public boolean getNoMoreQuestions() {return noMoreQuestions;}
	
	//Spezifikationen
	private String konfiguration = "1";
	private String convertible = "false";
	private String completteAnfrage = "";

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
			question = "Moechtest du ein Covertible haben, sprich soll man die Tastatur abnehmen koennen?";
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
				frageCounter += 2;
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
			if (answerAsInt == 1) 
				convertible = "true";
			else 
				convertible = "false";
			noMoreQuestions = true;
			break;
		}
		completteAnfrage = konfiguration + "," + convertible;
	}

	public void listAusfuellen() {
		laptopDataBase[0][0] = "1,false";	laptopDataBase[0][1] = "...";
		laptopDataBase[1][0] = "1, true";	laptopDataBase[1][1] = "...";
		laptopDataBase[2][0] = "2, false";	laptopDataBase[2][1] = "...";
	}

	public String getLaptopFromAnswers() {
		//array durchgehen und mit anfrage vergleichen
		for (int i = 0 ; i <= laptopDataBase.length ; i++) 
			if (completteAnfrage.equals(laptopDataBase[i][0]))
				return laptopDataBase[i][1];		
		return "error";
	}
}


