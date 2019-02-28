package com.amazon.customskill;

public class Student implements Nutzer {

	private String[][] laptopDataBase = new String[37][2]; // Tabelle von 37 Zeilen und 7 Spalte. Das ist unsere
															// DataBase fÃ¼r den Laptop
	private int frageCounter = 0;
	private boolean noMoreQuestions = false; // Variable, die sagt wenn man die Methode fÃ¼r die Ausgabe

	public boolean getNoMoreQuestions() {
		return noMoreQuestions;
	}

	public Student() {
		listAusfuellen();
	}

	//// Spezifikationen
	private String konfiguration = "1";
	private String akkuLaufzeit = ">= 7 Stunden";
	private String gewicht = "<= 2 Kg";
	private String convertible = "true";
	private String touchScreen = "false";
	private String kompletteAnfrage = konfiguration + "," + akkuLaufzeit + "," + gewicht + "," + convertible + "," + touchScreen;

	public String selectQuestion() {

		String frage = "";

		switch (frageCounter) {

		case 0:
			frage = "Studierst du Informatik ?";
			break;

		case 1:
			frage = "Bearbeitest du gerne Bilder auf deinem Laptop?";
			break;

		case 2:
			frage = "Arbeitest du viel am Laptop ohne eine Steckdose in der Naehe ?";
			break;

		case 3:
			frage = "Moechtest du einen leichten Laptop ?";
			break;

		case 4:
			frage = "Moechtest du ein Covertible haben, sprich soll man die Tastatur abnehmen koennen?";
			break;

		case 5:
			frage = "Moechtest du ein Touchscreen- Display ?";
			break;

		default: // Vorsicht
			frage = "stop";
			break;
		}

		return frage;
	}

	public void takeAnswer(int answerAsInt) { // Diese Methode nimmt die Antworten des nutzer und bildet eine
												// Suchanfrage damit.

		switch (frageCounter) {
		case 0:
			if (answerAsInt == 1) {
				konfiguration = "2";
				frageCounter++;
			} else if (answerAsInt == 0) {
				konfiguration = "1";
				frageCounter++;
			}
			break;
		case 1:
			if (answerAsInt == 1) {
				konfiguration = "4";
				frageCounter++;
			} else if (answerAsInt == 0) {
				frageCounter++;
			}
			break;

		case 2:
			if (answerAsInt == 1) {
				akkuLaufzeit = ">= 7 Stunden";
				frageCounter++;
			} else if (answerAsInt == 0) {
				akkuLaufzeit = "< 7 Stunden";
				frageCounter++;
			}
			break;

		case 3:
			if (answerAsInt == 1) {
				gewicht = "<= 2 Kg";
				frageCounter++;
			} else if (answerAsInt == 0) {
				gewicht = "> 2 Kg";
				frageCounter++;
			}
			break;

		case 4:
			if (answerAsInt == 1) {
				convertible = "true";
				noMoreQuestions = true;

			} else if(answerAsInt == 0) {
				convertible = "false";
				frageCounter++;
			}
			break;

		case 5:
			if (answerAsInt == 1) {
				touchScreen = "true";
				noMoreQuestions = true;
			} else if(answerAsInt == 0) {
				touchScreen = "false";
				noMoreQuestions = true;
			}
			break;

		default:
			break;
		}
		kompletteAnfrage = konfiguration + "," + akkuLaufzeit + "," + gewicht + "," + convertible + "," + touchScreen;
	}

	public void listAusfuellen() { // Diese Methode fÃ¼llt unsere Database einfach .

		/*
		 * laptopDataBase[i][0] = "Konfiguration,Akkulaufzeit,Gewicht,Konvertible,Touchscreen" ;
		 * //laptopDataBase[i][1] = "Notebook" ; Konfiguration 1 : 4 GB Ram, i3, 256 SSD, ab 370 Euro 
		 * Konfiguration 2 : 8 GB Ram, i5, 256 SSD, 1 TeraB HDD, ab 530 Euro 
		 * Konfiguration 3 : 8 GB Ram, i5, 256 SSD, 1 TeraBab HDD, ab 1000 Euro
		 * Konfiguration 4 : 16 GB Ram, i7, 256 SSD,2 TeraB, ab 1500 Euro
		 */

		laptopDataBase[1][0] = "1,>= 7 Stunden,<= 2 Kg,true,false";			laptopDataBase[1][1] = "Acer Spin 3 Convertible Notebook 14 Touch FHD IPS";
		laptopDataBase[2][0] = "1,< 7 Stunden,<= 2 Kg,true,false";			laptopDataBase[2][1] = "Lenovo Yoga 530-14IKB 81EK00XMGE 14";
		laptopDataBase[3][0] = "1,>= 7 Stunden,> 2 Kg,true,false";			laptopDataBase[3][1] = "LENOVO Miix 510 , Intel Core Silber";
		laptopDataBase[4][0] = "1,< 7 Stunden,> 2 Kg,true,false";			laptopDataBase[4][1] = "Lenovo super Yoga 530-14IKB 81EK00XQGE";
		laptopDataBase[5][0] = "1,>= 7 Stunden,<= 2 Kg,false,true";			laptopDataBase[5][1] = "HP 15-bs178ng, Notebook, Core";
		laptopDataBase[6][0] = "1,< 7 Stunden,> 2 Kg,false,true";			laptopDataBase[6][1] = "Acer Swift 3  Mega Thin 14";
		laptopDataBase[7][0] = "1,< 7 Stunden,<= 2 Kg,false,true";			laptopDataBase[7][1] = "Acer Swift 3 (SF315-52-37YA) Ultra Thin 15,6";
		laptopDataBase[8][0] = "1,>= 7 Stunden,<= 2 Kg,false,true";			laptopDataBase[8][1] = "Acer Swift 1 Ultra Thin (SF114-32-P7F1) 14";
		laptopDataBase[9][0] = "1,>= 7 Stunden,<= 2 Kg,false,false";		laptopDataBase[9][1] = "TREKSTOR PRIMEBOOK C11B-CO";
		laptopDataBase[10][0] = "1,< 7 Stunden,> 2 Kg,false,false,";		laptopDataBase[10][1] = "ASUS R702MA-BX089T";
		laptopDataBase[11][0] = "1,< 7 Stunden,<= 2 Kg,false,false";		laptopDataBase[11][1] = "MEDION MEDION® AKOYA® E6245";
		laptopDataBase[12][0] = "1,>= 7 Stunden,<= 2 Kg,false,false";		laptopDataBase[12][1] = "LENOVO IdeaPad 330";

		laptopDataBase[13][0] = "2,>= 7 Stunden,<= 2 Kg,true,false";		laptopDataBase[13][1] = "Acer Switch Alpha 12 Fit (SA5-271-FIT) + Acer Active Stift 12";
		laptopDataBase[14][0] = "2,< 7 Stunden,<= 2 Kg,true,false";			laptopDataBase[14][1] = "HP Envy x360  IPS Touch, AMD Ryzen";
		laptopDataBase[15][0] = "2,>= 7 Stunden,> 2 Kg,true,false";			laptopDataBase[15][1] = "HP ENVY x360 15-cp0002ng 15,6";
		laptopDataBase[16][0] = "2,< 7 Stunden,> 2 Kg,true,false";			laptopDataBase[16][1] = "HP ENVY cp0001ng 15";
		laptopDataBase[17][0] = "2,>= 7 Stunden,<= 2 Kg,false,true";		laptopDataBase[17][1] = "Apple MacBook Air 13\" MQD32D/A Intel Core i5 ";
		laptopDataBase[18][0] = "2,< 7 Stunden,> 2 Kg,false,true";			laptopDataBase[18][1] = "Asus Zenbook UX530UX-FY011T / 15,6";
		laptopDataBase[19][0] = "2,< 7 Stunden,<= 2 Kg,false,true";			laptopDataBase[19][1] = "MICROSOFT Surface Laptop 2, Notebook, Core";
		laptopDataBase[20][0] = "2,>= 7 Stunden,<= 2 Kg,false,true";		laptopDataBase[20][1] = "ACER Swift 5 (SF515-51T-7828), Notebook, Core";
		laptopDataBase[21][0] = "2,>= 7 Stunden,<= 2 Kg,false,false";		laptopDataBase[21][1] = "LENOVO IdeaPad 320, Notebook  15.6 ";
		laptopDataBase[22][0] = "2,< 7 Stunden,> 2 Kg,false,false,";		laptopDataBase[22][1] = "LENOVO IdeaPad 330, Notebook, A9";
		laptopDataBase[23][0] = "2,< 7 Stunden,<= 2 Kg,false,false";		laptopDataBase[23][1] = "TREKSTOR PRIMEBOOK P13 13,3";
		laptopDataBase[24][0] = "2,>= 7 Stunden,<= 2 Kg,false,false";		laptopDataBase[24][1] = "Asus Zenbook UX530UX-FY011T";

		laptopDataBase[25][0] = "4,>= 7 Stunden,<= 2 Kg,true,false";		laptopDataBase[25][1] = "ACER Swift 5 (SF515-51T-7828) Notebook Core";
		laptopDataBase[26][0] = "4,< 7 Stunden,<= 2 Kg,true,false";			laptopDataBase[26][1] = "Asus Zenbook Flip 15 Intel Core";
		laptopDataBase[27][0] = "4,>= 7 Stunden,> 2 Kg,true,false";			laptopDataBase[27][1] = "HP Spectre x360 UHD Touch";
		laptopDataBase[28][0] = "4,< 7 Stunden,> 2 Kg,true,false";			laptopDataBase[28][1] = "HP ENVY  15  Core GeForceMX Dark Ash ";
		laptopDataBase[29][0] = "4,>= 7 Stunden,<= 2 Kg,false,true";		laptopDataBase[29][1] = "DELL XPS 13 9370  Core Intel  Platinum";
		laptopDataBase[30][0] = "4,< 7 Stunden,> 2 Kg,false,true";			laptopDataBase[30][1] = "RAZER Blade Stealth 13  Notebook Core Intel UHD-Grafik";
		laptopDataBase[31][0] = "4,< 7 Stunden,<= 2 Kg,false,true";			laptopDataBase[31][1] = "Microsoft Surface Pro 6  Core platingrau Surface Pro Signature";
		laptopDataBase[32][0] = "4,>= 7 Stunden,<= 2 Kg,false,true";		laptopDataBase[32][1] = "Lenovo ThinkPad L580  Intel Core  LTE Windows 10 Pro";
		laptopDataBase[33][0] = "4,>= 7 Stunden,<= 2 Kg,false,false";		laptopDataBase[33][1] = "ACER Swift 5 (SF515-51T-7828), Notebook Core";
		laptopDataBase[34][0] = "4,< 7 Stunden,> 2 Kg,false,false,";		laptopDataBase[34][1] = "HP ProBook 450 G5 4QW89EA 15,6 FHD IPS Intel Core";
		laptopDataBase[35][0] = "4,< 7 Stunden,<= 2 Kg,false,false";		laptopDataBase[35][1] = "Lenovo ThinkPad L580 Intel Core  Windows 10 Pro";
		laptopDataBase[36][0] = "4,>= 7 Stunden,<= 2 Kg,false,false";		laptopDataBase[36][1] = "HP EliteBook 840 G5 4QZ38EA 14 SureView"; // die Tabelle ist komplett ausgefuellt

	}

	public String getLaptopFromAnswers() {
		// array durchgehen und mit anfrage vergleichen
		for (int i = 1; i <= laptopDataBase.length; i++)
			if (kompletteAnfrage.equals(laptopDataBase[i][0]))
				return laptopDataBase[i][1];
		return "error";
	}

}
