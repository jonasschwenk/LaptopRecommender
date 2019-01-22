package com.amazon.customskill;

public class Gamer implements Nutzer{
	protected final int minimalPrice = 899;
	protected final int NumberOfQuestions = 3;
	
	
	private int counter = 0;
	
	private int[] answers = new int[NumberOfQuestions];
	
	public int getCounter() {
		return counter;
	}
	public void setCounter(int counter) {
		this.counter = counter;
	}
	public int getAnswers (int index) {
		return answers[index];
	}
	public void setAnswers (int index, int value) {
		answers[index] = value;
	}
	
	public String getLaptopFromAnswers() {
		switch (answerstoNumber(answers)) {
		//noch alles Platzhalter
		case 0: //nein, nein
			return "0";
		case 1: //nein, ja
			return "1";
		case 2: //ja, nein
			return "2";
		case 3: //ja, ja
			return "3";
		default:
			return "fehler";
		}
		
	}
	
	public String selectQuestion() {
	        String question;
	        switch(counter){
	            case 1:
	                question ="M�chtest du eine beleuchtete Tastatur haben?";
	                counter ++;
	                break;
	            case 2:
	                question = "M�chtest du auch aktuelle Spiele spielen?";
	                counter ++;
	                break;
	        
	            default:
	                question = "Der Counter ist kleiner 1 oder gr��er 2!";
	                //counter = 0;
	                //The program doesn't stop at the moment rather it starts at question 1

	        }
	        return question;
	}
	
	//macht aus bin�ren antworten eine Nummer, um caseswitch verwenden zu k�nnen
	public int answerstoNumber (int[] input) {
		int output = 0;
		int j = 1;
		for (int i = input.length - 1; i<= 0 ; i--) {
			output += input[i] * j;
			j += 2;
		}
		return output;
			
	}
	public Gamer() {}
}
