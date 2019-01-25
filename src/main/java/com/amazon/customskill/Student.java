package com.amazon.customskill;

public class Student implements Nutzer{
  
	private String[][] laptopDataBase = new String[37][2]  ;    // Tabelle von 37 Zeilen und 7 Spalte. Das ist unsere DataBase fÃ¼r den Laptop
    private int frageCounter  = 0 ;
    private boolean noMoreQuestions = false ; // Variable, die sagt wenn man die Methode fÃ¼r die Ausgabe 
    public boolean getNoMoreQuestions() {return noMoreQuestions;}
    
    
	public Student() {
		listAusfuellen();
	}


    ////Spezifikationen
    private String konfiguration = "";
    private String akkuLaufzeit = "";
    private String gewicht = "" ;
    private String convertible = "";
    private String touchScreen = "false";
    private String completteAnfrage = "";   
    
    public String selectQuestion () {
    	
    	String frage = "";
    	
    	switch (frageCounter) {
    		
    	case 0 : frage = "Studierst du Informatik ?" ;
    			 break;
    			 
    	case 1 : frage = "Bearbeitest du gerne Bilder auf deinem Laptop?" ;
		 		 break;
		 		 
    	case 2 : frage = "Arbeitest du viel am Laptop ohne eine Steckdose in der Nähe ?" ;
		 		 break;
		 		 
    	case 3 : frage = "Moechtest du einen leichten Laptop ?" ;
		 		 break;
		 		 
    	case 4 : frage = "Moechtest du ein Covertible haben, sprich soll man die Tastatur abnehmen koennen?" ;
		 		 break;
		 		 
    	case 5 : frage = "Moechtest du ein Touchscreen- Display ?" ;
		 		 break;
		 		 
    	default :  // Vorsicht 
    				frage = "stop" ;
    		break;
    	}
    	
    	return frage;
    }
    
    
    
    public void takeAnswer (int answerAsInt) {  // Diese Methode nimmt die Antworten des nutzer und bildet eine Suchanfrage damit.

       	switch (frageCounter) {
       		case 0 :
    			if (answerAsInt == 1) {
    				konfiguration = "2";
    				++ frageCounter;
    			}else {
    				konfiguration = "1" ;
    				frageCounter=+2 ;
    			}
    			break;
       		case 1 :
    			if (answerAsInt == 1)
					konfiguration = "4"; 
    			frageCounter++;
    			  break;
    			  
       		case 2 :
    			if (answerAsInt == 1) 
    				akkuLaufzeit = ">= 7 Stunden";
				else 
					akkuLaufzeit = "< 7 Stunden" ;
    			frageCounter++;
    			 break;
    			 
       		case 3 :
    			if (answerAsInt == 1) 
    				gewicht = "<= 2 Kg";
		 		else 
		 			gewicht = "> 2 Kg" ;
    			frageCounter++;
    			break;
    			 
       		case 4 :   
    			if (answerAsInt == 1) {
    					convertible = "true";
    					noMoreQuestions = true;
    					
		 		}else {
		 			convertible = "false" ;
		 			frageCounter++;
		 		}
    			break;
    			   
       		case 5 : 
    			if (answerAsInt == 1) {
    				 touchScreen = "true";
    				 noMoreQuestions = true;
    			}else {
		 			touchScreen = "false" ;
		 			noMoreQuestions = true;
    			}
    			 break;
    			 
       		default : break;
    	}	
    	completteAnfrage = konfiguration+","+akkuLaufzeit+","+gewicht+","+convertible+","+touchScreen ;
    }
    

    
    public void listAusfuellen() { // Diese Methode fÃ¼llt unsere Database  einfach . 
    	
    	/*laptopDataBase[i][0] = "Konfiguration,Akkulaufzeit,Gewicht,Konvertible,Touchscreem" ;       //laptopDataBase[i][1] = "Notebook" ;
    	 * Konfiguration 1 : 4 GB Ram, i3, 256 SSD, ab 370 Euro
         * Konfiguration 2 : 8 GB Ram, i5, 256 SSD, 1 TeraB HDD, ab 530 Euro
         * Konfiguration 1 : 8 GB Ram, i5, 256 SSD, 1 TeraBab  HDD, ab 1000 Euro
         * Konfiguration 1 : 16 GB Ram, i7, 256 SSD,2 TeraB, ab 1500 Euro */
    	
    laptopDataBase[1][0] = "1,>= 7 Stunden,<= 2 Kg,true,false"  ;     laptopDataBase[1][1] =  "..." ;
    laptopDataBase[2][0] = "1,< 7 Stunden,<= 2 Kg,true,false"  ;      laptopDataBase[2][1] =  "..." ;
    laptopDataBase[3][0] = "1,>= 7 Stunden,> 2 Kg,true,false" ;		  laptopDataBase[3][1] =  "..." ;
    laptopDataBase[4][0] = "1,< 7 Stunden,> 2 Kg,true,false" ;	      laptopDataBase[4][1] =  "..." ;
    laptopDataBase[5][0] = "1,>= 7 Stunden,<= 2 Kg,false,true" ;      laptopDataBase[5][1] =  "..." ;
    laptopDataBase[6][0] = "1,< 7 Stunden,> 2 Kg,false,true" ;        laptopDataBase[6][1] =  "..." ;
    laptopDataBase[7][0] = "1,< 7 Stunden,<= 2 Kg,false,true"  ;      laptopDataBase[7][1] =  "..." ;
    laptopDataBase[8][0] = "1,>= 7 Stunden,<= 2 Kg,false,true";       laptopDataBase[8][1] =  "..."	;
    laptopDataBase[9][0] = "1,>= 7 Stunden,<= 2 Kg,false,false" ;     laptopDataBase[9][1] =  "..." ;
    laptopDataBase[10][0] = "1,< 7 Stunden,> 2 Kg,false,false," ;     laptopDataBase[10][1] = "..." ;
    laptopDataBase[11][0] = "1,< 7 Stunden,<= 2 Kg,false,false" ;     laptopDataBase[11][1] = "..." ;
    laptopDataBase[12][0] = "1,>= 7 Stunden,<= 2 Kg,false,false" ;    laptopDataBase[12][1] = "..." ;
    
    
    laptopDataBase[13][0] = "2,>= 7 Stunden,<= 2 Kg,true,false"  ;    laptopDataBase[13][1] = "..."  ;
    laptopDataBase[14][0] = "2,< 7 Stunden,<= 2 Kg,true,false"  ;     laptopDataBase[14][1] = "..."  ;
    laptopDataBase[15][0] = "2,>= 7 Stunden,> 2 Kg,true,false" ;      laptopDataBase[15][1] = "..."  ;
    laptopDataBase[16][0] = "2,< 7 Stunden,> 2 Kg,true,false" ;       laptopDataBase[16][1] = "..."  ;
    laptopDataBase[17][0] = "2,>= 7 Stunden,<= 2 Kg,false,true" ;     laptopDataBase[17][1] = "..."  ;
    laptopDataBase[18][0] = "2,< 7 Stunden,> 2 Kg,false,true" ;       laptopDataBase[18][1] = "..."  ;
    laptopDataBase[19][0] = "2,< 7 Stunden,<= 2 Kg,false,true"  ;     laptopDataBase[19][1] = "..."  ;
    laptopDataBase[20][0] = "2,>= 7 Stunden,<= 2 Kg,false,true";      laptopDataBase[20][1] = "..."  ;
    laptopDataBase[21][0] = "2,>= 7 Stunden,<= 2 Kg,false,false" ;    laptopDataBase[21][1] = "..."  ;
    laptopDataBase[22][0] = "2,< 7 Stunden,> 2 Kg,false,false," ;     laptopDataBase[22][1] = "..."  ;
    laptopDataBase[23][0] = "2,< 7 Stunden,<= 2 Kg,false,false" ;     laptopDataBase[23][1] = "..."  ;
    laptopDataBase[24][0] = "2,>= 7 Stunden,<= 2 Kg,false,false" ;    laptopDataBase[24][1] = "..."  ;
    
    
    laptopDataBase[25][0] = "4,>= 7 Stunden,<= 2 Kg,true,false"  ;    laptopDataBase[25][1] = "..."  ;
    laptopDataBase[26][0] = "4,< 7 Stunden,<= 2 Kg,true,false"  ;     laptopDataBase[26][1] = "..."  ;
    laptopDataBase[27][0] = "4,>= 7 Stunden,> 2 Kg,true,false" ;      laptopDataBase[27][1] = "..."  ;
    laptopDataBase[28][0] = "4,< 7 Stunden,> 2 Kg,true,false" ;       laptopDataBase[28][1] = "..."  ;
    laptopDataBase[29][0] = "4,>= 7 Stunden,<= 2 Kg,false,true" ;     laptopDataBase[29][1] = "..."  ;
    laptopDataBase[30][0] = "4,< 7 Stunden,> 2 Kg,false,true" ;       laptopDataBase[30][1] = "..."  ;
    laptopDataBase[31][0] = "4,< 7 Stunden,<= 2 Kg,false,true"  ;     laptopDataBase[31][1] = "..."  ;
    laptopDataBase[32][0] = "4,>= 7 Stunden,<= 2 Kg,false,true";      laptopDataBase[32][1] = "..."  ;
    laptopDataBase[33][0] = "4,>= 7 Stunden,<= 2 Kg,false,false" ;    laptopDataBase[33][1] = "..."  ;
    laptopDataBase[34][0] = "4,< 7 Stunden,> 2 Kg,false,false," ;     laptopDataBase[34][1] = "..."  ;
    laptopDataBase[35][0] = "4,< 7 Stunden,<= 2 Kg,false,false" ;     laptopDataBase[35][1] = "..."  ;
    laptopDataBase[36][0] = "4,>= 7 Stunden,<= 2 Kg,false,false" ;    laptopDataBase[36][1] = "..."  ;      // die Tabelle ist komplett ausgefuellt
    }
    
     
	public String getLaptopFromAnswers() {
		//array durchgehen und mit anfrage vergleichen
		for (int i = 0 ; i <= laptopDataBase.length ; i++ ) 
			if (completteAnfrage.equals(laptopDataBase[i][0]))
				return laptopDataBase[i][1];		
		return "error";
	}

}
