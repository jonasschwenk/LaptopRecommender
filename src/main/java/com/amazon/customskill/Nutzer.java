package com.amazon.customskill;

public interface Nutzer{
	
	//Informationen und Statuswerte für Nutzergruppe
	int counter = 0;    //"Zählt" die Fragen, gibt an welche Frage als nächstes dran ist
	int minimalPrice = 0; //Gibt mindestpreis für Laptopkategorie an
	int NumberOfQuestions = 0; //Gibt an, wie viele Fragen es gibt
	int answers [] = {0}; //Array zum speichern der Nutzeranforderungen
	
	//getter und setter
	public int getCounter();
	public void setCounter(int counter);
	public int getAnswers(int index);
	public void setAnswers(int index, int value);
	
	//Methoden
	public String selectQuestion();  //Ist dafür da die Frage auszuwählen, intern ist Antwort von counter abhängig
	public String getLaptopFromAnswers(); //Wertet das Array "answers" aus, ausgabe ist intern von diesem abhängig
	
}