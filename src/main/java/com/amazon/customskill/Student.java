package com.amazon.customskill;

public class Student {
  
  private String[][] laptopDataBase = new String[37][2]  ;    // Tabelle von 37 Zeilen und 7 Spalte. Das ist unsere DataBase für den Laptop
    private int frageCounter  = 0 ;
    private String completteAnfrage = "";   
    private Boolean ausgabe = false ; // Variable, die sagt wenn man die Methode für die Ausgabe 
    
    public String selectQuestion (String antwort) {
    	
    	String frage = "";
    	
    	/*if ((frageCounter-1)==1 && antwort.equals("ya")) {
    		
    		frageCounter =-1 ;
    	}
    	
    	if ((frageCounter-1)==4 && antwort.equals("ya")) {
    		
    		frageCounter =-1 ;
    	}*/
    	
    	switch (frageCounter) {
    		
    	case 0 : frage = "Studiert diese Person Informatik ?" ;
    			 break;
    			 
    	case 1 : frage = "Hat die Person was mit Bildbearbeitung zu tun?" ;
		 		 ++frageCounter ;
		 		 break;
		 		 
    	case 2 : frage = "Wird sie lange ohne Kabelanschluss mit dem Laptop arbeiten ?" ;
		 		 ++frageCounter ;
		 		 break;
		 		 
    	case 3 : frage = "Möchtet sie ein leichter Laptop ?" ;
		 		 ++frageCounter ;
		 		 break;
		 		 
    	case 4 : frage = "Hättet si ein Konvertible  ?" ;
		 		 break;
		 		 
    	case 5 : frage = "Möchtet sie ein Touchscreen- Display ?" ;
		 		 ++frageCounter ;
		 		 break;
		 		 
    	default :  // Vorsicht 
    				frage = "stop" ;
    		break;
    	}
    	
    	return frage;
    }
    
    
    
    public void takeAnswer (String antwort) {  // Diese Methode nimmt die Antworten des nutzer und bildet ei SuchAnfrage damit.
    	String konfiguration = "";
        String akkuLaufzeit = "";
        String gewicht = "" ;
        String konvertible = "";
        String touchScreem = "false";
    	
    	switch (frageCounter ) {
    	
    	case 0 : if (antwort.equals("Ya")) {
    				konfiguration = "2";
    				++ frageCounter;
    			}else {
    				konfiguration = "1" ;
    				frageCounter=+2 ;
    			}
    			break;
    			
    	case 1 :  if (antwort.equals("Ya")) {
					konfiguration = "4";
    			  }
    			  break;
    			  
    	case 2 : if (antwort.equals("Ya")) {
    				akkuLaufzeit = ">= 7 Stunden";
				 }else {
					akkuLaufzeit = "< 7 Stunden" ;
				 }
    			 break;
    			 
    	case 3 : if (antwort.equals("Ya")) {
    				gewicht = "<= 2 Kg";
		 		 }else {
		 			gewicht = "> 2 Kg" ;
		 		 }
    			 break;
    			 
    	case 4 :   if (antwort.equals("Ya")) {
    					konvertible = "true";
    					ausgabe = true;
    					
		 		   }else {
		 			   	konvertible = "false" ;
		 			    ++ frageCounter;
		 		   }
    			   break;
    			   
    	case 5 : if (antwort.equals("Ya")) {
    				 touchScreem = "true";
    				 ausgabe = true;
		 		 }else {
		 			 touchScreem = "false" ;
		 			ausgabe = true;
		 		 }
    			 break;
    			 
    	default : break;
    	
    	}
    	
    	completteAnfrage = konfiguration+","+akkuLaufzeit+","+gewicht+","+konvertible+","+touchScreem ;
    }
    

    
    public void ListAusfüllen() { // Diese Methode füllt unsere Database  einfach . 
    	
    	/*laptopDataBase[i][0] = "Konfiguration,Akkulaufzeit,Gewicht,Konvertible,Touchscreem" ;       //laptopDataBase[i][1] = "Notebook" ;
    	 * Konfiguration 1 : 4 GB Ram, i3, 256 SSD, ab 370 Euro
         * Konfiguration 2 : 8 GB Ram, i5, 256 SSD, 1 TeraB HDD, ab 530 Euro
         * Konfiguration 1 : 8 GB Ram, i5, 256 SSD, 1 TeraBab  HDD, ab 1000 Euro
         * Konfiguration 1 : 16 GB Ram, i7, 256 SSD,2 TeraB, ab 1500 Euro */
    	
    laptopDataBase[1][0] = "1,>= 7 Stunden,<= 2 Kg,true,false"  ;     laptopDataBase[1][1] = "..."  ;
    laptopDataBase[2][0] = "1,< 7 Stunden,<= 2 Kg,true,false"  ;      laptopDataBase[2][1] = "..."  ;
    laptopDataBase[3][0] = "1,>= 7 Stunden,> 2 Kg,true,false" ;		  laptopDataBase[3][1] = "..." ;
    laptopDataBase[4][0] = "1,< 7 Stunden,> 2 Kg,true,false" ;	      laptopDataBase[4][1] = "..." ;
    laptopDataBase[5][0] = "1,>= 7 Stunden,<= 2 Kg,false,true" ;      laptopDataBase[5][1] = "..." ;
    laptopDataBase[6][0] = "1,< 7 Stunden,> 2 Kg,false,true" ;        laptopDataBase[6][1] = "..." ;
    laptopDataBase[7][0] = "1,< 7 Stunden,<= 2 Kg,false,true"  ;      laptopDataBase[7][1] = "..."  ;
    laptopDataBase[8][0] = "1,>= 7 Stunden,<= 2 Kg,false,true";       laptopDataBase[8][1] = "...";
    laptopDataBase[9][0] = "1,>= 7 Stunden,<= 2 Kg,false,false" ;     laptopDataBase[9][1] = "..." ;
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
    laptopDataBase[36][0] = "4,>= 7 Stunden,<= 2 Kg,false,false" ;    laptopDataBase[36][1] = "..."  ;      // die Tabelle ist komplett ausgefüllt
    }
    
     
     public String ausgabeAlexa() {
     	
     	String antwort = "";
     	for (int i=1; i < laptopDataBase.length; i++) {
     		if (laptopDataBase[i][0].equals(completteAnfrage)) {
     			antwort = laptopDataBase[i][1];
     			break;
     		}
     	}
     	
     	String aussage1= "Der "+antwort+ " ist für sie ein guter Auswahl. Außerdem emphele ich Ihnen auch ein Notebook Tasche zu kaufen" ;
     	return aussage1;
     }

}
