import java.util.Random;
public class KnightTourB {
    public static void main(String[] args) {
    	int [][] KMap = new int[8][8];            //Knight's Tour Map
    	int row;
    	int column;
    	int moveNumber;          //movement in the knight's Tour Map
    	int [] horizontal = {2, 1, -1, -2, -2, -1, 1, 2};
    	int [] vertical = {-1, -2, -2, -1, 1, 2, 2, 1};
    	int answerOne = 0;       //to test whether there is any valid empty point o move forward
    	int fakeRow;             //fake row for validation
    	int fakeColumn;			//fake column for validation
    	int fakeMove;			//fake move number for validation
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
    	
    	moveNumber = generator.nextInt(8);	
   
    	for(counter = 0; counter<64; counter++){
    		
    		answerOne = 0;  //ensure the answer is 0 when there is no any movement is valid
	    
	    	for(fakeMove = 0; fakeMove< 8; fakeMove++){    //is there any valid move left
	    		
	    		fakeRow = currentRow;
	    		fakeColumn = currentColumn;
	    	
	    		fakeRow += vertical[fakeMove];
	    		fakeColumn += horizontal[fakeMove];
	    		
	    		if(fakeRow>7 || fakeColumn > 7 || fakeRow<0 || fakeColumn<0){    //is the move out of the range  
		    		fakeRow -= vertical[fakeMove];
	    			fakeColumn -= horizontal[fakeMove];
		    	}else if(KMap[fakeRow][fakeColumn]== 0){      //is there any empty point in the map that can move forward
    				answerOne = 1;      //is there any empty space from the 8 possiblity type of move
    			}
    		}
	    	if(answerOne == 1){         //if there is an valid/empty point that can move toward
		    	currentRow += vertical[moveNumber];
	    		currentColumn += horizontal[moveNumber];
	    		
		    	if(currentRow>7 || currentColumn > 7 || currentRow<0 || currentColumn<0){   //is the move out of the range
		    		counter = counter - 1;
		    		currentRow -= vertical[moveNumber];
	    			currentColumn -= horizontal[moveNumber];
		    	}else if(KMap[currentRow][currentColumn] != 0){     //is the move invalid
		    		counter = counter - 1;
		    		currentRow -= vertical[moveNumber];
	    			currentColumn -= horizontal[moveNumber];
		    	}else if(currentRow<=7 && currentColumn<=7 && currentRow>=0 && currentColumn>=0 && KMap[currentRow][currentColumn] == 0){
		    		KMap[currentRow][currentColumn] = counter + 2;
		    	}
	    	}else{  			//no empty point that can move to it(if the answerOne is 0)
		    		break;      //break and go to the print statement
	    	}
	   		moveNumber = generator.nextInt(8);	//generate the movement number
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
}
