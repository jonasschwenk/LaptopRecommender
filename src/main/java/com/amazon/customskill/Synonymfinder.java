package com.amazon.customskill;

public class Synonymfinder{
	
	//Standartkonstruktor
	public Synonymfinder() {}
	
	//Arrays mit Synonymen, erstes Element: wort was wir brauchen
	private String JaSynonyms[] = {"ja", "jawohl", "sicherlich","freilich"};
	private String NeinSynonyms[] = {"nein", "nicht", "keinen", "niemals"};
	private String GamerSynonyms[] = {"gamer", "zocker"};
	private String StudentSynonyms[] = {"student", "studentin"};
	private String SeniorSynonyms[] = {"senior" , "rentner" , "oma" , "opa" , "alter"};
	private String PrivatnutzerSynonyms[] = {"privatnutzer"};
	
	
	//übers alle arrays der reihe nach drüberiterieren bis passendes gefunden wurde, dann erstes element ausgeben (das was wir haben wollen)
	public String erkenneSynonym(String input) {	
		for (int i = 0 ; i < JaSynonyms.length ; i++) 
			if (input.equalsIgnoreCase(JaSynonyms[i]))
				return JaSynonyms[0];	
		for (int i = 0 ; i < NeinSynonyms.length ; i++) 
			if (input.equalsIgnoreCase(NeinSynonyms[i]))
				return NeinSynonyms[0];			
		for (int i = 0 ; i < GamerSynonyms.length ; i++) 
			if (input.equalsIgnoreCase(GamerSynonyms[i]))
				return GamerSynonyms[0];			
		for (int i = 0 ; i < StudentSynonyms.length ; i++) 
			if (input.equalsIgnoreCase(StudentSynonyms[i]))
				return StudentSynonyms[0];			
		for (int i = 0 ; i < SeniorSynonyms.length ; i++) 
			if (input.equalsIgnoreCase(SeniorSynonyms[i]))
				return SeniorSynonyms[0];		
		for (int i = 0 ; i < PrivatnutzerSynonyms.length ; i++)
			if (input.equalsIgnoreCase(PrivatnutzerSynonyms[i]))
				return PrivatnutzerSynonyms[0];	
		//und so weiter für alles was wir erkennen wollen
		
		return "nichts gefunden"; //catchAll
	}
	
	
	
	
	
}