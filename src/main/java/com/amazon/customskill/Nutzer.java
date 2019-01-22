package com.amazon.customskill;

public interface Nutzer{
	
	//Informationen und Statuswerte f�r Nutzergruppe
	int counter = 0;    //"Z�hlt" die Fragen, gibt an welche Frage als n�chstes dran ist
	int minimalPrice = 0; //Gibt mindestpreis f�r Laptopkategorie an
	int NumberOfQuestions = 0; //Gibt an, wie viele Fragen es gibt
	int answers [] = {0}; //Array zum speichern der Nutzeranforderungen
	
	//getter und setter
	public int getCounter();
	public void setCounter(int counter);
	public int getAnswers(int index);
	public void setAnswers(int index, int value);
	
	//Methoden
	public String selectQuestion();  //Ist daf�r da die Frage auszuw�hlen, intern ist Antwort von counter abh�ngig
	public String getLaptopFromAnswers(); //Wertet das Array "answers" aus, ausgabe ist intern von diesem abh�ngig
	
}