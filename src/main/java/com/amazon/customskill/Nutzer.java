package com.amazon.customskill;

public interface Nutzer {

	// Informationen und Statuswerte für Nutzergruppe
	int frageCounter = 0; // "Zählt" die Fragen, gibt an welche Frage als nächstes dran ist
	int minimalPrice = 0; // Gibt mindestpreis für Laptopkategorie an
	boolean noMoreQuestions = false;

	// getter und setter
	public boolean getNoMoreQuestions();

	// Methoden
	public String selectQuestion(); // Ist dafür da die Frage auszuwählen, intern ist Antwort von counter abhängig

	public String getLaptopFromAnswers(); // Wertet das Array "answers" aus, ausgabe ist intern von diesem abhängig

	public void listAusfuellen(); //erstellt datenbank	

	public void takeAnswer(int answerAsInt); //nimmt analysierte antworten entgegen und setzt entsprehchend die konfiguration
}
