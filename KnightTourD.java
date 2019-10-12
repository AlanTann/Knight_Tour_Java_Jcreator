import java.util.Random;
public class KnightTourD {
    public static void main(String[] args) {
    	int [][] KMap = new int[8][8];            //Knight's Tour Map
    	int [][] accessMap ={{2,3,4,4,4,4,3,2},   //Map of the access value
    						 {3,4,6,6,6,6,4,3},
    						 {4,6,8,8,8,8,6,4},
    						 {4,6,8,8,8,8,6,4},
    						 {4,6,8,8,8,8,6,4},
    						 {4,6,8,8,8,8,6,4},
    						 {3,4,6,6,6,6,4,3},
    						 {2,3,4,4,4,4,3,2}};
    	int row;
    	int column;
    	int [] horizontal = {2, 1, -1, -2, -2, -1, 1, 2};
    	int [] vertical = {-1, -2, -2, -1, 1, 2, 2, 1};
    	int answerOne = 0;        //to test whether there is any valid empty point o move forward
    	int answerTwo = 0;		  //another access value 
    	int nAnswerOne = 0;       // acccess value of the answerOne next move
    	int nAnswerTwo = 0;		  //accessnext move of the answerTwo next move
    	int fakeRow = 0;             //fake row for validation
    	int fakeColumn = 0;			//fake column for validation
    	int fakeMove = 0;			//fake move number for validation
    	int realMove = 0;          //move that had been confirm to apply 
    	int counter = 0;           //counter for the for looping of every point in the map
    	int currentRow;            //current row in the Knight's Tour Map
    	int currentColumn;		   //current column in the Knight's Tour Map
    	
    	Random generator = new Random();

		//set the element of all Knight's Tour Map/array to 0
    	for(row = 0; row<KMap.length; row++){		//each row
    		for(column = 0; column<KMap[row].length; column++){    //each column
    			KMap[row][column]= 0;
    		}
    	}
    	//random generate the 1st point in the map
    	currentRow = generator.nextInt(8);
    	currentColumn = generator.nextInt(8);
    	KMap[currentRow][currentColumn] = 1;
		
    	for(counter = 0; counter<64; counter++){
    		
    		answerOne = 0;   //ensure the answer is 0 when there is no any movement is valid
	    
	    	for(fakeMove = 0; fakeMove< 8; fakeMove++){    //is there any valid move left
	    		
	    		fakeRow = currentRow;
	    		fakeColumn = currentColumn;
	    	
	    		fakeRow += vertical[fakeMove];
	    		fakeColumn += horizontal[fakeMove];
	    		if(fakeRow>7 || fakeColumn > 7 || fakeRow<0 || fakeColumn<0){   //is the move out of the range
		    		fakeRow -= vertical[fakeMove];
	    			fakeColumn -= horizontal[fakeMove];
		    	}else if(KMap[fakeRow][fakeColumn]== 0){     //is there any empty point in the map that can move forward
    				answerOne = 7;                          //when there is an empty or valid point
    				answerTwo = 8;                          //so that the starting would be answerTwo more than answerOne
    			}
    		}
			if(answerOne== 7){        //if there is an empty or valid point 
	    		for(fakeMove = 0; fakeMove< 8; fakeMove++){
	    			fakeRow = currentRow;
		    		fakeColumn = currentColumn;
		    	
		    		fakeRow += vertical[fakeMove];
		    		fakeColumn += horizontal[fakeMove];
	    			if(fakeRow>7 || fakeColumn > 7 || fakeRow<0 || fakeColumn<0){   // is the move of the range
			    		fakeRow -= vertical[fakeMove];
		    			fakeColumn -= horizontal[fakeMove];
		    			answerTwo = 8;
			    	}else if(KMap[fakeRow][fakeColumn]!=0){           //is the point empty/valid
			    		fakeRow -= vertical[fakeMove];
		    			fakeColumn -= horizontal[fakeMove];
		    			answerTwo = 8;
	    			}else if(accessMap[fakeRow][fakeColumn]== 2 && KMap[fakeRow][fakeColumn]==0){  //is the move valid in the point(Knight tours's Map) where access value is 2
	    				answerTwo = 2;                            
	    			}else if(accessMap[fakeRow][fakeColumn]== 3 && KMap[fakeRow][fakeColumn]==0){  //is the move valid in the point(Knight tours's Map) where access value is 3
	    				answerTwo = 3;;                            
	    			}else if(accessMap[fakeRow][fakeColumn]== 4 && KMap[fakeRow][fakeColumn]==0){  //is the move valid in the point(Knight tours's Map) where access value is 4
	    				answerTwo = 4;                            
	    			}else if(accessMap[fakeRow][fakeColumn]== 6 && KMap[fakeRow][fakeColumn]==0){  //is the move valid in the point(Knight tours's Map) where access value is 6
	    				answerTwo = 5;                            
	    			}else if(accessMap[fakeRow][fakeColumn]== 8 && KMap[fakeRow][fakeColumn]==0){  //is the move valid in the point(Knight tours's Map) where access value is 8
	    				answerTwo = 6;                          
	    			}
	    			if(answerTwo<answerOne){    //if the current access value is smaller than the previous access value
	    				answerOne=answerTwo;    //then swap the access value with each other
	    				realMove= fakeMove;     //then swap the fake movement(answerTwo) to realMove
	    			}
	    			//if answerTwo more than answerOne, then dont change/swap, do nothing
	    				
	    			if(answerTwo == answerOne){    //if the current access value is tie/same with the previouse access value
	    				nAnswerOne=BetterNextMove(realMove, currentRow, currentColumn, KMap, accessMap, horizontal, vertical);  //get the acceess value of the next move(AnswerOne)
	    				nAnswerTwo=BetterNextMove(fakeMove, currentRow, currentColumn, KMap, accessMap, horizontal, vertical);  //get the access value of the next move(AnswerTw0)
	    				
	    				//compare the access value of next move with each other 
	    				if(nAnswerTwo < nAnswerOne){    //if the access value of the next move(AnswerTwo) is smaller than access value of the next move(AnswerOne)
	    					answerOne=answerTwo;        //then swap
	    					realMove=fakeMove;          // then swap 
	    				}
	    			}
	    		}
			}
			
	    	if(answerOne != 0){         //if the point is valid & empty & smallest access value
		    	currentRow += vertical[realMove];                   
	    		currentColumn += horizontal[realMove];
		    	KMap[currentRow][currentColumn] = counter + 2;     
    		}else{  				 //no more empty/valid point in Knight Tour's Map(if asnwerOne is 0)
		    		break;           //break and go to the print statement
	    	}	
    	}
    	System.out.print("\n\n");
    	//print the Knight's Tour Map
    	for(row = 0; row<KMap.length; row++){		//each row
	    	for(column = 0; column<KMap[row].length; column++){     //each column
	    		System.out.printf("%d\t",KMap[row][column]);
	    	}
	    	System.out.print("\n");		
	    }
	    //count the Knight's Tour Movement
	    System.out.printf("Number of Move= %d", counter+1);
    }
	public static int BetterNextMove(int RMove,int currentRow, int currentColumn, int [][]KMap, int [][]accessMap, int []horizontal, int []vertical){
		int tMove;
		int tRow = 0;            //temporary Row     /fakeRow
		int tColumn = 0;         //temporary column / fakeColumn
		int tAnswerOne = 0;      //temporary answer one
		int tAnswerTwo = 0;      //temporary answer two
		
		tAnswerOne = 7;			   //Temporary answer one
		tAnswerTwo = 8;            //Temporary answer two, ensure AnswerTwo is bigger than AnswerOne at the first loop
		
		//add the movement of answerOne/answerTwo
		tRow = currentRow + vertical[RMove];
		tColumn = currentColumn + horizontal[RMove];
		
		currentRow = tRow;                          //swap the currentRow  with tRow
		currentColumn = tColumn;                    //swap the currentColumn with tColumn
		
		for(tMove = 0; tMove< 8; tMove++){
			tRow = currentRow;
			tColumn = currentColumn;
				    	
			tRow += vertical[tMove];
			tColumn += horizontal[tMove];
			if(tRow>7 || tColumn > 7 || tRow<0 || tColumn<0){   // is the move out of the range  
				tRow -= vertical[tMove];
				tColumn -= horizontal[tMove];
			}else if(KMap[tRow][tColumn]!=0){                  //is the point empty/valid
				tRow -= vertical[tMove];
				tColumn -= horizontal[tMove];
			}else if(accessMap[tRow][tColumn]== 2 && KMap[tRow][tColumn]==0){  //is the next move valid in the point(Knight Tour's Map) where access value is 2
			    	tAnswerTwo = 2;                            
			}else if(accessMap[tRow][tColumn]== 3 && KMap[tRow][tColumn]==0){  //is the next move valid in the point(Knight Tour's Map) where access value is 3
					tAnswerTwo = 3;;                            
			}else if(accessMap[tRow][tColumn]== 4 && KMap[tRow][tColumn]==0){  //is the next move valid in the point(Knight Tour's Map) where access value is 4
			    	tAnswerTwo = 4;                            
			}else if(accessMap[tRow][tColumn]== 6 && KMap[tRow][tColumn]==0){  //is the next move valid in the point(Knight Tour's Map) where access value is 6
			    	tAnswerTwo = 5;                            
			}else if(accessMap[tRow][tColumn]== 8 && KMap[tRow][tColumn]==0){  //is the next move valid in the point(Knight Tour's Map) where access value is 8
			    	tAnswerTwo = 6;                          
			}
			if(tAnswerTwo<tAnswerOne){      //if the current access value is smaller than previous access value
			    tAnswerOne=tAnswerTwo;      //then swap the access value(tAswerTwo) to tAnswerOne
			}
		}
		return tAnswerOne; //return the final smaller access value that could be in the answerOne/answerTwo
	}   
}
