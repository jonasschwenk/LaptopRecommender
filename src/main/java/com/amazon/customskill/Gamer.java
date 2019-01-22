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
		return "Test";
	}
	
	public String selectQuestion() {
	        String question;
	        switch(counter){
	            case 1:
	                question ="Möchtest du eine beleuchtete Tastatur haben?";
	                counter ++;
	                break;
	            case 2:
	                question = "Möchtest du auch aktuelle Spiele spielen?";
	                counter ++;
	                break;
	        
	            default:
	                question = "Der Counter ist kleiner 1 oder größer 2!";
	                //counter = 0;
	                //The program doesn't stop at the moment rather it starts at question 1

	        }
	        return question;
	}
	public Gamer() {}
}
