package com.amazon.customskill;

public interface Nutzer{
	
	//Informationen und Statuswerte für Nutzergruppe
	int frageCounter = 0;    //"Zählt" die Fragen, gibt an welche Frage als nächstes dran ist
	int minimalPrice = 0; //Gibt mindestpreis für Laptopkategorie an
	boolean noMoreQuestions = false;
	
	//getter und setter
	public int getCounter();
	public void setFrageCounter(int frageCounter);
	
	//Methoden
	public String selectQuestion();  //Ist dafür da die Frage auszuwählen, intern ist Antwort von counter abhängig
	public String getLaptopFromAnswers(); //Wertet das Array "answers" aus, ausgabe ist intern von diesem abhängig
	public void listAusfuellen();
	public void takeAnswer(String answer);
	

	
}
