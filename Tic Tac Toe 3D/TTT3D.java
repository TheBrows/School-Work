/**
 * CMPS Assignment.
 * This program lets you play simulated 4x4x4 TicTacToe
 * With a computer. Four in a row in any direction wins.
 *
 * Author: Paige R.
 * 
 */
 
import java.util.*;

public class TTT3D
{
    public static void main(String[] args)
    {
        Scanner keyboard = new Scanner(System.in);
        
        int board[][][] = new int[4][4][4];
        
        int computerMove[] = new int[3];
        
        int playerMove[] = new int[3];
        
        //set board blank
        for( int i = 0; i < 4; i++ )
        {
            for( int j = 0; j < 4; j++ )
            {
                for( int k = 0; k < 4; k++ )
                {
                    board[i][j][k] = 0;
                }
            }
        }
        
        int sampleBoard = Integer.parseInt(args[0]);
        //sample boards to demonstrate that computer ai functions properly
        
        if( sampleBoard == 1 ) //move 021, comp blocks player fork
        {                          //move 020, comp ignores player 3 to get 4
            board[0][0][0] = 5;
            board[0][0][1] = 5;
            board[0][1][1] = 1;
            board[0][2][2] = 1;
            board[0][2][3] = 1;
            board[0][3][0] = 5;
            board[0][3][3] = 1;
            
            System.out.println("\nSample Board 1!");
            System.out.println("\nShows computer blocking a player fork" + 
                "\nand ignoring a player 3 in a row to get a 4 in a row.");  
        }
        else if( sampleBoard == 2 ) //move 020, comp blocks player 3 in a row 
        {                           //100, comp creates fork
            board[0][0][0] = 5;     //move 032, comp wins
            board[0][1][0] = 5;
            board[3][3][3] = 5;
            board[0][3][3] = 1;
            board[1][2][1] = 1;
            board[2][1][1] = 1;
            
            System.out.println("\nSample Board 2!");
            System.out.println("\nShows computer blocking a player 3 in a row"
                + "\nand creating a fork to win.");
        }
        else
        {
            System.out.println("\nNew Game!");
        }
        
        printArray(board);
        
        boolean win = false;
        
        //loop that stops if win detected
        while( !win )
        {
            //intake user move
            
            boolean properInput = false;
            
            while( !properInput )
            {
                System.out.print("Type your move as one 3 digit # (LRC): ");
                
                int input = keyboard.nextInt();
                
                playerMove = playerMove(input);
                
                if( board[playerMove[0]][playerMove[1]][playerMove[2]] == 0 )
                {
                    properInput = true;
                }
                else
                {
                    System.out.println("\nSpace already occupied! Try again!"
                        + "\n");
                }
            }
            
            properInput = false;
            
            //user moves
            board[playerMove[0]][playerMove[1]][playerMove[2]] = 5;
            
            //check win
            if( checkWin(board) != 0 )
            {
                win = true;
            }
            else
            {
                win = false;
            }
            
            //if no win
            if( !win )
            {
                //compute computer move
                computerMove = moveSearch(board);
                
                if( computerMove[0] < 100 )
                {    
                    //computer moves
                    board[computerMove[0]][computerMove[1]]
                        [computerMove[2]] = 1;
                    
                    //print board
                    printArray(board);
                    
                    //check win
                    if( checkWin(board) != 0 )
                    {
                        win = true;
                    }
                    else
                    {
                        win = false;
                    }
                }
                else
                {
                    win = true;
                }
            }
        }
        
        if( computerMove[0] >= 100 )
        {
            System.out.println("It's a draw!");
        }
        if( checkWin(board) == 1 ) //computer win
        {
            System.out.println("You lost! Better luck next time!");
        }
        else if( checkWin(board) == 2 ) //user win
        {
            //print board
            printArray(board);
            
            System.out.println("Congratulations! You won!");
        }
    }
    
    static int[] playerMove(int input)
    {
        int[] playerMove = new int[3];
        
        if( input < 100 )
        {
            playerMove[0] = 0;
            
            if( input < 10 )
            {
                playerMove[1] = 0;
                
                playerMove[2] = input;
            }
            else if( input < 20 )
            {
                playerMove[1] = 1;
                
                playerMove[2] = input - 10;
            }
            else if( input < 30 )
            {
                playerMove[1] = 2;
                
                playerMove[2] = input - 20;
            }
            else
            {
                playerMove[1] = 3;
                
                playerMove[2] = input - 30;
            }
            
        }
        else if( input < 200 )
        {
            playerMove[0] = 1;
            
            if( (input - 100) < 10 )
            {
                playerMove[1] = 0;
                
                playerMove[2] = input - 100;
            }
            else if( (input - 100) < 20 )
            {
                playerMove[1] = 1;
                
                playerMove[2] = input - 110;
            }
            else if( (input - 100) < 30 )
            {
                playerMove[1] = 2;
                
                playerMove[2] = input - 120;
            }
            else
            {
                playerMove[1] = 3;
                
                playerMove[2] = input - 130;
            }
        }
        else if( input < 300 )
        {
            playerMove[0] = 2;
            
            if( (input - 200) < 10 )
            {
                playerMove[1] = 0;
                
                playerMove[2] = input - 200;
            }
            else if( (input - 200) < 20 )
            {
                playerMove[1] = 1;
                
                playerMove[2] = input - 210;
            }
            else if( (input - 200) < 30 )
            {
                playerMove[1] = 2;
                
                playerMove[2] = input - 220;
            }
            else
            {
                playerMove[1] = 3;
                
                playerMove[2] = input - 230;
            }
        }
        else
        {
            playerMove[0] = 3;
            
            if( (input - 300) < 10 )
            {
                playerMove[1] = 0;
                
                playerMove[2] = input - 300;
            }
            else if( (input - 300) < 20 )
            {
                playerMove[1] = 1;
                
                playerMove[2] = input - 310;
            }
            else if( (input - 300) < 30 )
            {
                playerMove[1] = 2;
                
                playerMove[2] = input - 320;
            }
            else
            {
                playerMove[1] = 3;
                
                playerMove[2] = input - 330;
            }
        }
        
        return playerMove;
    }
    
    static int checkWin(int board[][][])
    {
        for( int i = 0; i < 4; i++ )
        {
            for( int j = 0; j < 4; j++ )
            {
                if( sumDiagonalLevel(board,i,0) == 4 || 
                sumDiagonalLevel(board,i,3) == 4 ||
                sumDiagonalRow(board,i,0) == 4 ||
                sumDiagonalRow(board,i,3) == 4 ||
                sumDiagonalColumn(board,i,0) == 4 ||
                sumDiagonalColumn(board,i,3) == 4 ||
                sumDiagonalCool(board,0) == 4 ||
                sumDiagonalCool(board,3) == 4 ||
                sumRow(board,i,j) == 4 ||
                sumColumn(board,i,j) == 4 ||
                sumHeight(board,i,j) == 4 )
                {
                    return 1; //computer win
                }
                else if( sumDiagonalLevel(board,i,0) == 20 || 
                sumDiagonalLevel(board,i,3) == 20 ||
                sumDiagonalRow(board,i,0) == 20 ||
                sumDiagonalRow(board,i,3) == 20 ||
                sumDiagonalColumn(board,i,0) == 20 ||
                sumDiagonalColumn(board,i,3) == 20 ||
                sumDiagonalCool(board,0) == 20 ||
                sumDiagonalCool(board,3) == 20 ||
                sumRow(board,i,j) == 20 ||
                  sumColumn(board,i,j) == 20 ||
                sumHeight(board,i,j) == 20 )
                {
                    return 2; //player win
                }
            }
        }
       
       return 0; //no win
    }
    
    static int[] moveSearch(int board[][][])
    {
        int[] computerMove = new int[3];
        
        int[] temporary = new int[3];

        //If there is a cell that wins immediately for the computer, 
        //play it and win!(game ends)
        //   find a line with a sum of 3, fill in final blank space
        
        temporary = findSum(board,3);
        
        if( temporary[0] < 100 )
        {
            computerMove = findSum(board,3);
            return computerMove;
        }
        
        //If there is a cell that wins immediately for the user, 
        //play it to block.
        //   find a line with a sum of 15, fill in final blank space
        
        temporary = findSum(board,15);
        
        if( temporary[0] < 100 )
        {
            computerMove = findSum(board,15);
            return computerMove;
        }
        
        //If there is a cell that when occupied would 
        //create a fork for the computer, 
        //i.e., two lines that would offer immediate wins 
        //on the next move, play it.
        //   sum of two lines each is 2 and both share a blank space, 
        //   occupy that blank space
        
        temporary = theForkOfDoom(board,2);
        
        if( temporary[0] < 100 )
        {
            computerMove = temporary;
            
            return computerMove;
        }
        
        //If there is a cell that when occupied would create a fork
        //for the user, play it to block.
        //   sum of two lines each is 10 and both share a blank space, 
        //   occupy that blank space
        
        temporary = theForkOfDoom(board,10);
        
        if( temporary[0] < 100 )
        {
            computerMove = temporary;
       
            return computerMove;
        }
        
        //Randomly play a cell that is in a non-dead line. 
        //Dead lines are those that have cells occupied by the computer 
        //and cells occupied by the user.
        //   random spot in line that has a sum of 0 or 1 or 2
        
        temporary = findSum(board,2);
        
        if( temporary[0] < 100 )
        {
            computerMove = findSum(board,2);
            return computerMove;
        }
        
        temporary = findSum(board,1);
        
        if( temporary[0] < 100 )
        {
            computerMove = findSum(board,1);
            return computerMove;
        }
        
        temporary = findSum(board,0);
        
        if( temporary[0] < 100 )
        {
            computerMove = findSum(board,0);
            return computerMove;
        }
        
        //Declare a draw. (game ends)
        //   there are no lines with sum less than 3 in the array
        
        computerMove[0] = 100;
        computerMove[1] = 100;
        computerMove[2] = 100;
        
        return computerMove;

        //If the game has not ended, go back to prompting the user for a move.
    }
    
    static int[] theForkOfDoom(int board[][][], int sum)
    {
        int[] computerMove = new int[3];
        
        //find a line with sum 2 or 15, 
        //get first blank coordinate and linetype
        if( sumDiagonalCool(board,0) == sum ) //DiagonalCool from back #1
        {
            computerMove[0] = findDiagonalCool(board,0);
            computerMove[1] = computerMove[0];
            computerMove[2] = computerMove[0];
            
            //run sum2 of all but that type of line
            if( findSum(board,sum,1,computerMove) )
            {
                return computerMove;
            }
            else //if no success, get second blank coordinate
            {
                computerMove[0] = findDiagonalCoolV2(board,0);
                computerMove[1] = computerMove[0];
                computerMove[2] = computerMove[0];
                
                //run sum2 of all but that type 
                //of line with second coordinate
                if( findSum(board,sum,1,computerMove) )
                {
                    return computerMove;
                }
            }
        }
        //if no succes, try a different line
        if( sumDiagonalCool(board,3) == sum ) //#2
        {
            computerMove[0] = findDiagonalCool(board,3);
            computerMove[1] = computerMove[0];
            if( computerMove[0] == 3 )
            {
                computerMove[2] = 0;
            }
            else if( computerMove[0] == 2 )
            {
                computerMove[2] = 1;
            }
            else if( computerMove[0] == 1 )
            {
                computerMove[2] = 2;
            }
            else if( computerMove[0] == 0 )
            {
                computerMove[2] = 3;
            }
            
            //run sum2 of all but that type of line
            if( findSum(board,sum,2,computerMove) )
            {
                return computerMove;
            }
            else //if no success, get second blank coordinate
            {
                computerMove[0] = findDiagonalCoolV2(board,3);
                computerMove[1] = computerMove[0];
                if( computerMove[0] == 3 )
                {
                    computerMove[2] = 0;
                }
                else if( computerMove[0] == 2 )
                {
                    computerMove[2] = 1;
                }
                else if( computerMove[0] == 1 )
                {
                    computerMove[2] = 2;
                }
                else if( computerMove[0] == 0 )
                {
                    computerMove[2] = 3;
                }
                
                //run sum2 of all but that type of line with second coordinate
                if( findSum(board,sum,2,computerMove) )
                {
                    return computerMove;
                }
            }
        }
        if( sumDiagonalCool2(board,0) == sum ) //DiagonalCool from front #3
        {
            computerMove[1] = findDiagonalCool2(board,0);
            if( computerMove[1] == 3 )
            {
                computerMove[0] = 0;
            }
            else if( computerMove[1] == 2 )
            {
                computerMove[0] = 1;
            }
            else if( computerMove[1] == 1 )
            {
                computerMove[0] = 2;
            }
            else if( computerMove[1] == 0 )
            {
                computerMove[0] = 3;
            }
            computerMove[2] = computerMove[0];
            
            if( findSum(board,sum,3,computerMove) )
            {
                return computerMove;
            }
            else
            {
                computerMove[1] = findDiagonalCool2V2(board,0);
                if( computerMove[1] == 3 )
                {
                    computerMove[0] = 0;
                }
                else if( computerMove[1] == 2 )
                {
                    computerMove[0] = 1;
                }
                else if( computerMove[1] == 1 )
                {
                    computerMove[0] = 2;
                }
                else if( computerMove[1] == 0 )
                {
                    computerMove[0] = 3;
                }
                computerMove[2] = computerMove[0];
                
                if( findSum(board,sum,3,computerMove) )
                {
                    return computerMove;
                }
            }
        }
        if( sumDiagonalCool2(board,3) == sum ) //#4
        {
            computerMove[0] = findDiagonalCool2(board,3);
            if( computerMove[0] == 3 )
            {
                computerMove[1] = 0;
            }
            else if( computerMove[0] == 2 )
            {
                computerMove[1] = 1;
            }
            else if( computerMove[0] == 1 )
            {
                computerMove[1] = 2;
            }
            else if( computerMove[0] == 0 )
            {
                computerMove[1] = 3;
            }
            computerMove[2] = computerMove[1];
            
            if( findSum(board,sum,4,computerMove) )
            {
                return computerMove;
            }
            else
            {
                computerMove[0] = findDiagonalCool2V2(board,3);
                if( computerMove[0] == 3 )
                {
                    computerMove[1] = 0;
                }
                else if( computerMove[0] == 2 )
                {
                    computerMove[1] = 1;
                }
                else if( computerMove[0] == 1 )
                {
                    computerMove[1] = 2;
                }
                else if( computerMove[0] == 0 )
                {
                    computerMove[1] = 3;
                }
                computerMove[2] = computerMove[1];
                    
                if( findSum(board,sum,4,computerMove) )
                {
                    return computerMove;
                }
            }
        }
        
        for( int i = 0; i < 4; i++ )
        {
            if( sumDiagonalLevel(board,i,0) == sum ) //DiagonalLevel #5
            {
                computerMove[0] = i;
                computerMove[1] = findDiagonalLevel(board,i,0);
                computerMove[2] = computerMove[1];
                
                if( findSum(board,sum,5,computerMove) )
                {
                    return computerMove;
                }
                else
                {
                    computerMove[0] = i;
                    computerMove[1] = findDiagonalLevelV2(board,i,0);
                    computerMove[2] = computerMove[1];
                    
                    if( findSum(board,sum,5,computerMove) )
                    {
                        return computerMove;
                    }
                }
                
            }
            if( sumDiagonalLevel(board,i,3) == sum ) //#6
            {
                computerMove[0] = i;
                computerMove[1] = findDiagonalLevel(board,i,3);
                if( computerMove[1] == 3 )
                {
                    computerMove[2] = 0;
                }
                else if( computerMove[1] == 2 )
                {
                    computerMove[2] = 1;
                }
                else if( computerMove[1] == 1 )
                {
                    computerMove[2] = 2;
                }
                else if( computerMove[1] == 0 )
                {
                    computerMove[2] = 3;
                }
                
                if( findSum(board,sum,6,computerMove) )
                {
                    return computerMove;
                }
                else
                {
                    computerMove[0] = i;
                    computerMove[1] = findDiagonalLevelV2(board,i,3);
                    if( computerMove[1] == 3 )
                    {
                        computerMove[2] = 0;
                    }
                    else if( computerMove[1] == 2 )
                    {
                        computerMove[2] = 1;
                    }
                    else if( computerMove[1] == 1 )
                    {
                        computerMove[2] = 2;
                    }
                    else if( computerMove[1] == 0 )
                    {
                        computerMove[2] = 3;
                    }
                    
                    if( findSum(board,sum,6,computerMove) )
                    {
                        return computerMove;
                    }
                }
            }
            if( sumDiagonalRow(board,i,0) == sum ) //DiagonalRow #7
            {
                computerMove[0] = findDiagonalRow(board,i,0);
                computerMove[1] = i;
                computerMove[2] = computerMove[0];
                
                if( findSum(board,sum,7,computerMove) )
                {
                    return computerMove;
                }
                else
                {
                    computerMove[0] = findDiagonalRowV2(board,i,0);
                    computerMove[1] = i;
                    computerMove[2] = computerMove[0];
                    
                    if( findSum(board,sum,7,computerMove) )
                    {
                        return computerMove;
                    }
                }
            }
            if( sumDiagonalRow(board,i,3) == sum ) //#8
            {
                computerMove[0] = findDiagonalRow(board,i,3);
                computerMove[1] = i;
                if( computerMove[0] == 3 )
                {
                    computerMove[2] = 0;
                }
                else if( computerMove[0] == 2 )
                {
                    computerMove[2] = 1;
                }
                else if( computerMove[0] == 1 )
                {
                    computerMove[2] = 2;
                }
                else if( computerMove[0] == 0 )
                {
                    computerMove[2] = 3;
                }
                
                if( findSum(board,sum,8,computerMove) )
                {
                    return computerMove;
                }
                else
                {
                    computerMove[0] = findDiagonalRowV2(board,i,3);
                    computerMove[1] = i;
                    if( computerMove[0] == 3 )
                    {
                        computerMove[2] = 0;
                    }
                    else if( computerMove[0] == 2 )
                    {
                        computerMove[2] = 1;
                    }
                    else if( computerMove[0] == 1 )
                    {
                        computerMove[2] = 2;
                    }
                    else if( computerMove[0] == 0 )
                    {
                        computerMove[2] = 3;
                    }
                    
                    if( findSum(board,sum,8,computerMove) )
                    {
                        return computerMove;
                    }
                }
            }
            if( sumDiagonalColumn(board,i,0) == sum ) //DiagonalColumn #9
            {
                computerMove[0] = findDiagonalColumn(board,i,0);
                computerMove[1] = computerMove[0];
                computerMove[2] = i;
                
                if( findSum(board,sum,9,computerMove) )
                {
                    return computerMove;
                }
                else
                {
                    computerMove[0] = findDiagonalColumnV2(board,i,0);
                    computerMove[1] = computerMove[0];
                    computerMove[2] = i;
                    
                    if( findSum(board,sum,9,computerMove) )
                    {
                        return computerMove;
                    }
                }
            }
            if( sumDiagonalColumn(board,i,3) == sum ) //#10
            {
                computerMove[0] = findDiagonalColumn(board,i,3);
                if( computerMove[0] == 3 )
                {
                    computerMove[1] = 0;
                }
                else if( computerMove[0] == 2 )
                {
                    computerMove[1] = 1;
                }
                else if( computerMove[0] == 1 )
                {
                    computerMove[1] = 2;
                }
                else if( computerMove[0] == 0 )
                {
                    computerMove[1] = 3;
                }
                computerMove[2] = i;
                
                if( findSum(board,sum,10,computerMove) )
                {
                    return computerMove;
                }
                else
                {
                    computerMove[0] = findDiagonalColumnV2(board,i,3);
                    if( computerMove[0] == 3 )
                    {
                        computerMove[1] = 0;
                    }
                    else if( computerMove[0] == 2 )
                    {
                        computerMove[1] = 1;
                    }
                    else if( computerMove[0] == 1 )
                    {
                        computerMove[1] = 2;
                    }
                    else if( computerMove[0] == 0 )
                    {
                        computerMove[1] = 3;
                    }
                    computerMove[2] = i;
                    
                    if( findSum(board,sum,10,computerMove) )
                    {
                        return computerMove;
                    }
                }
            }
            
            // Non Diagonals
            
            for( int j = 0; j < 4; j++ )
            {
                if( sumRow(board,i,j) == sum ) //Row #11
                {
                    computerMove[0] = i;
                    computerMove[1] = j;
                    computerMove[2] = findRow(board,i,j);
                    
                    if( findSum(board,sum,11,computerMove) )
                    {
                        return computerMove;
                    }
                    else
                    {
                        computerMove[0] = i;
                        computerMove[1] = j;
                        computerMove[2] = findRowV2(board,i,j);
                        
                        if( findSum(board,sum,11,computerMove) )
                        {
                            return computerMove;
                        }
                    }
                }
                if( sumColumn(board,i,j) == sum ) //Column #12
                {
                    computerMove[0] = i;
                    computerMove[1] = j;
                    computerMove[2] = findColumn(board,i,j);
                    
                    if( findSum(board,sum,12,computerMove) )
                    {
                        return computerMove;
                    }
                    else
                    {
                        computerMove[0] = i;
                        computerMove[1] = j;
                        computerMove[2] = findColumnV2(board,i,j);
                        
                        if( findSum(board,sum,12,computerMove) )
                        {
                            return computerMove;
                        }
                    }
                }
                if( sumHeight(board,i,j) == sum ) //Row #13
                {
                    computerMove[0] = i;
                    computerMove[1] = j;
                    computerMove[2] = findHeight(board,i,j);
                    
                    if( findSum(board,sum,13,computerMove) )
                    {
                        return computerMove;
                    }
                    else
                    {
                        computerMove[0] = i;
                        computerMove[1] = j;
                        computerMove[2] = findHeightV2(board,i,j);
                        
                        if( findSum(board,sum,13,computerMove) )
                        {
                            return computerMove;
                        }
                    }
                }
            }
        }
        
        computerMove[0] = 100;
        computerMove[1] = 100;
        computerMove[2] = 100;
        
        return computerMove;
    }
    
    //finds a line with the specific sum and returns a blank spot on that line
    //for the computer to make a move on
    //returns 100 if no good line found
    
    static int[] findSum(int board[][][], int sum)
    {
        int[] computerMove = new int[3];
        
        if( sumDiagonalCool(board,0) == sum ) //DiagonalCool from back
        {
            computerMove[0] = findDiagonalCool(board,0);
            computerMove[1] = computerMove[0];
            computerMove[2] = computerMove[0];
            
            return computerMove;
        }
        if( sumDiagonalCool(board,3) == sum )
        {
            computerMove[0] = findDiagonalCool(board,3);
            computerMove[1] = computerMove[0];
            if( computerMove[0] == 3 )
            {
                computerMove[2] = 0;
            }
            else if( computerMove[0] == 2 )
            {
                computerMove[2] = 1;
            }
            else if( computerMove[0] == 1 )
            {
                computerMove[2] = 2;
            }
            else if( computerMove[0] == 0 )
            {
                computerMove[2] = 3;
            }
            
            return computerMove;
        }
        if( sumDiagonalCool2(board,0) == sum ) //DiagonalCool from front
        {
            computerMove[1] = findDiagonalCool2(board,0);
            if( computerMove[1] == 3 )
            {
                computerMove[0] = 0;
            }
            else if( computerMove[1] == 2 )
            {
                computerMove[0] = 1;
            }
            else if( computerMove[1] == 1 )
            {
                computerMove[0] = 2;
            }
            else if( computerMove[1] == 0 )
            {
                computerMove[0] = 3;
            }
            computerMove[2] = computerMove[0];
            
            return computerMove;
        }
        if( sumDiagonalCool2(board,3) == sum )
        {
            computerMove[0] = findDiagonalCool2(board,3);
            if( computerMove[0] == 3 )
            {
                computerMove[1] = 0;
            }
            else if( computerMove[0] == 2 )
            {
                computerMove[1] = 1;
            }
            else if( computerMove[0] == 1 )
            {
                computerMove[1] = 2;
            }
            else if( computerMove[0] == 0 )
            {
                computerMove[1] = 3;
            }
            computerMove[2] = computerMove[1];
            
            return computerMove;
        }
        
        for( int i = 0; i < 4; i++ )
        {
            if( sumDiagonalLevel(board,i,0) == sum ) //DiagonalLevel
            {
                computerMove[0] = i;
                computerMove[1] = findDiagonalLevel(board,i,0);
                computerMove[2] = computerMove[1];
                
                return computerMove;
                
            }
            if( sumDiagonalLevel(board,i,3) == sum )
            {
                computerMove[0] = i;
                computerMove[1] = findDiagonalLevel(board,i,3);
                if( computerMove[1] == 3 )
                {
                    computerMove[2] = 0;
                }
                else if( computerMove[1] == 2 )
                {
                    computerMove[2] = 1;
                }
                else if( computerMove[1] == 1 )
                {
                    computerMove[2] = 2;
                }
                else if( computerMove[1] == 0 )
                {
                    computerMove[2] = 3;
                }
                
                return computerMove;
                
            }
            if( sumDiagonalRow(board,i,0) == sum ) //DiagonalRow
            {
                computerMove[0] = findDiagonalRow(board,i,0);
                computerMove[1] = i;
                computerMove[2] = computerMove[0];
                
                return computerMove;
            }
            if( sumDiagonalRow(board,i,3) == sum )
            {
                computerMove[0] = findDiagonalRow(board,i,3);
                computerMove[1] = i;
                if( computerMove[0] == 3 )
                {
                    computerMove[2] = 0;
                }
                else if( computerMove[0] == 2 )
                {
                    computerMove[2] = 1;
                }
                else if( computerMove[0] == 1 )
                {
                    computerMove[2] = 2;
                }
                else if( computerMove[0] == 0 )
                {
                    computerMove[2] = 3;
                }
                
                return computerMove;
            }
            if( sumDiagonalColumn(board,i,0) == sum ) //DiagonalColumn
            {
                computerMove[0] = findDiagonalColumn(board,i,0);
                computerMove[1] = computerMove[0];
                computerMove[2] = i;
                
                return computerMove;
            }
            if( sumDiagonalColumn(board,i,3) == sum )
            {
                computerMove[0] = findDiagonalColumn(board,i,3);
                if( computerMove[0] == 3 )
                {
                    computerMove[1] = 0;
                }
                else if( computerMove[0] == 2 )
                {
                    computerMove[1] = 1;
                }
                else if( computerMove[0] == 1 )
                {
                    computerMove[1] = 2;
                }
                else if( computerMove[0] == 0 )
                {
                    computerMove[1] = 3;
                }
                computerMove[2] = i;
                
                return computerMove;
            }
            
            // Non Diagonals
            
            for( int j = 0; j < 4; j++ )
            {
                if( sumRow(board,i,j) == sum ) //Row
                {
                    computerMove[0] = i;
                    computerMove[1] = j;
                    computerMove[2] = findRow(board,i,j);
                    
                    return computerMove;
                }
                if( sumColumn(board,i,j) == sum ) //Column
                {
                    computerMove[0] = i;
                    computerMove[1] = findColumn(board,i,j);
                    computerMove[2] = j;
                    
                    return computerMove;
                }
                if( sumHeight(board,i,j) == sum ) //Height
                {
                    computerMove[0] = findHeight(board,i,j);
                    computerMove[1] = i;
                    computerMove[2] = j;
                    
                    return computerMove;
                }
            }
        }
        
        computerMove[0] = 100;
        computerMove[1] = 100;
        computerMove[2] = 100;
        
        return computerMove;
    }
    
    static boolean findSum(int board[][][], int sum, int lineType, 
        int[] moveCheck)
    {
        //if sum2 has coordinate, return true
        //check all sum2s until success
        
        if( sumDiagonalCool(board,0,moveCheck) == sum && lineType != 1 )
        {
            return true;
        }
        if( sumDiagonalCool(board,3,moveCheck) == sum && lineType != 2 )
        {
            return true;
        }
        if( sumDiagonalCool2(board,0,moveCheck) == sum && lineType != 3 )
        {
            return true;
        }
        if( sumDiagonalCool2(board,3,moveCheck) == sum && lineType != 4 )
        {
            return true;
        }
        
        for( int i = 0; i < 4; i++ )
        {
            if( sumDiagonalLevel(board,i,0,moveCheck) == sum && lineType != 5 )
            {
                return true;
                
            }
            if( sumDiagonalLevel(board,i,3,moveCheck) == sum && lineType != 6 )
            {
                return true;
                
            }
            if( sumDiagonalRow(board,i,0,moveCheck) == sum && lineType != 7 )
            {
                return true;
            }
            if( sumDiagonalRow(board,i,3,moveCheck) == sum && lineType != 8 )
            {
                return true;
            }
            if( sumDiagonalColumn(board,i,0,moveCheck) == sum && 
                lineType != 9 )
            {
                return true;
            }
            if( sumDiagonalColumn(board,i,3,moveCheck) == sum && 
                lineType != 10 )
            {
                return true;
            }
            
            // Non Diagonals
            
            for( int j = 0; j < 4; j++ )
            {
                if( sumRow(board,i,j,moveCheck) == sum && lineType != 11 )
                {
                    return true;
                }
                if( sumColumn(board,i,j,moveCheck) == sum && lineType != 12 )
                {
                    return true;
                }
                if( sumHeight(board,i,j,moveCheck) == sum && lineType != 13 )
                {
                    return true;
                }
            }
        }
        
        return false;
    }
    
    //gets sum method for each line type
    
    static int sumRow(int board[][][], int level, int row)
    {
        int sum = 0;
        
        for( int i = 0; i < 4; i++ )
        {
            sum += board[level][row][i];
        }
        
        return sum;
    }
    
    static int sumColumn(int board[][][], int level, int column)
    {
        int sum = 0;
        
        for( int i = 0; i < 4; i++ )
        {
            sum += board[level][i][column];
        }
        
        return sum;
    }
    
    static int sumDiagonalLevel(int board[][][], int level, int first)
    {
        int sum = 0;
        
        if( first == 0 )
        {
            for( int i = 0; i < 4; i++ )
            {
                sum += board[level][i][i];
            }
        }
        else
        {
            int j = 0;
            
            for( int i = 3; i >= 0; i-- )
            {
                sum += board[level][j][i];
                
                j++;
            }
        }
        
        return sum;
    }
    
    static int sumHeight(int board[][][], int row, int column)
    {
        int sum = 0;
        
        for( int i = 0; i < 4; i++ )
        {
            sum += board[i][row][column];
        }
        
        return sum;
    }
    
    static int sumDiagonalRow(int[][][] board, int row, int first)
    {
        int sum = 0;
        
        if( first == 0 )
        {
            for( int i = 0; i < 4; i++ )
            {
                sum += board[i][row][i];
            }
        }
        else
        {
            int j = 0;
            
        	for( int i = 3; i >= 0; i-- )
            {
                sum += board[j][row][i];
                
                j++;
            }
        }
        
        return sum;
    }
    
    static int sumDiagonalColumn(int board[][][], int column, int first)
    {
        int sum = 0;
        
        if( first == 0 )
        {
            for( int i = 0; i < 4; i++ )
            {
                sum += board[i][i][column];
            }
        }
        else
        {
            int j = 0;
        	
        	for( int i = 3; i >= 0; i-- )
            {
                sum += board[i][j][column];
                
                j++;
            }
        }
        
        return sum;
    }
    
    static int sumDiagonalCool(int board[][][], int first)
    {
        int sum = 0;
        
        if( first == 0 )
        {
            for( int i = 0; i < 4; i++ )
            {
                sum += board[i][i][i];
            }
        }
        else
        {
            int j = 0;
            
            for( int i = 3; i >= 0; i-- )
            {
                sum += board[j][j][i];
                
                j++;
            }
        }
        
        return sum;
    }
    
    static int sumDiagonalCool2(int board[][][], int first)
    {
        int sum = 0;
        int j;
        
        if( first == 0 )
        {
            j = 3;
            
            for( int i = 0; i < 4; i++ )
            {
                sum += board[i][j][i];
                j--;
            }
        }
        else
        {
            j = 0;
            
            for( int i = 3; i >= 0; i-- )
            {
                sum += board[j][i][i];
                j++;
            }
        }
        
        return sum;
    }
    
    static int sumRow(int board[][][], int level, int row, int moveCheck[])
    {
        int sum = 0;
        
        boolean found = false;
        
        for( int i = 0; i < 4; i++ )
        {
            sum += board[level][row][i];
            
            if( level == moveCheck[0] && row == moveCheck[1] && 
                i == moveCheck[2] )
            {
                found = true;
            }
        }
        
        if( found )
        {
            return sum;
        }
        else
        {
            return sum + 100;
        }
    }
    
    static int sumColumn(int board[][][], int level, int column, 
        int moveCheck[])
    {
        int sum = 0;
        
        boolean found = false;
        
        for( int i = 0; i < 4; i++ )
        {
            sum += board[level][i][column];
            
            if( level == moveCheck[0] && i == moveCheck[1] && 
                column == moveCheck[2] )
            {
                found = true;
            }
        }
        
        if( found )
        {
            return sum;
        }
        else
        {
            return sum + 100;
        }
    }
    
    static int sumDiagonalLevel(int board[][][], int level, int first, 
        int moveCheck[])
    {
        int sum = 0;
        
        boolean found = false;
        
        if( first == 0 )
        {
            for( int i = 0; i < 4; i++ )
            {
                sum += board[level][i][i];
                
                if( level == moveCheck[0] && i == moveCheck[1] && 
                    i == moveCheck[2] )
                {
                    found = true;
                }
            }
        }
        else
        {
            int j = 0;
            
            for( int i = 3; i >= 0; i-- )
            {
                sum += board[level][j][i];
                
                if( level == moveCheck[0] && j == moveCheck[1] && 
                    i == moveCheck[2] )
                {
                    found = true;
                }
                
                j++;
            }
        }
        
        if( found )
        {
            return sum;
        }
        else
        {
            return sum + 100;
        }
    }
    
    static int sumHeight(int board[][][], int row, int column, int moveCheck[])
    {
        int sum = 0;
        
        boolean found = false;
        
        for( int i = 0; i < 4; i++ )
        {
            sum += board[i][row][column];
            
            if( i == moveCheck[0] && i == moveCheck[1] && i == moveCheck[2] )
            {
                found = true;
            }
        }
        
        if( found )
        {
            return sum;
        }
        else
        {
            return sum + 100;
        }
    }
    
    static int sumDiagonalRow(int[][][] board, int row, int first, 
        int moveCheck[])
    {
        int sum = 0;
        
        boolean found = false;
        
        if( first == 0 )
        {
            for( int i = 0; i < 4; i++ )
            {
                sum = sum + board[i][row][i];
                
                if( i == moveCheck[0] && row == moveCheck[1] && 
                    i == moveCheck[2] )
                {
                    found = true;
                }
            }
        }
        else
        {
            int j = 0;
        	
        	for( int i = 3; i >= 0; i-- )
            {
                sum = sum + board[j][row][i];
                
                if( j == moveCheck[0] && row == moveCheck[1] && 
                    i == moveCheck[2] )
                {
                    found = true;
                }
                
                j++;
            }
        }
        
        if( found )
        {
            return sum;
        }
        else
        {
            return sum + 100;
        }
    }
    
    static int sumDiagonalColumn(int board[][][], int column, int first, 
        int moveCheck[])
    {
        int sum = 0;
        
        boolean found = false;
        
        if( first == 0 )
        {
            for( int i = 0; i < 4; i++ )
            {
                sum += board[i][i][column];
                
                if( i == moveCheck[0] && i == moveCheck[1] && 
                    column == moveCheck[2] )
                {
                    found = true;
                }
            }
        }
        else
        {
            int j = 0;
        	
        	for( int i = 3; i >= 0; i-- )
            {
                sum += board[i][j][column];
                
                if( i == moveCheck[0] && i == moveCheck[1] && 
                    column == moveCheck[2] )
                {
                    found = true;
                }
                
                j++;
            }
        }
        
        if( found )
        {
            return sum;
        }
        else
        {
            return sum + 100;
        }
    }
    
    static int sumDiagonalCool(int board[][][], int first, int moveCheck[])
    {
        int sum = 0;
        
        boolean found = false;
        
        if( first == 0 )
        {
            for( int i = 0; i < 4; i++ )
            {
                sum = sum + board[i][i][i];
                
                if( i == moveCheck[0] && i == moveCheck[1] && 
                    i == moveCheck[2] )
                {
                    found = true;
                }
            }
        }
        else
        {
            int j = 0;
            
            for( int i = 3; i >= 0; i-- )
            {
                sum = sum + board[j][j][i];
                
                if( j == moveCheck[0] && j == moveCheck[1] && 
                    i == moveCheck[2] )
                {
                    found = true;
                }
                
                j++;
            }
        }
        
        if( found )
        {
            return sum;
        }
        else
        {
            return sum + 100;
        }
    }
    
    static int sumDiagonalCool2(int board[][][], int first, int moveCheck[])
    {
        int sum = 0;
        int j;
        
        boolean found = false;
        
        if( first == 0 )
        {
            j = 3;
            
            for( int i = 0; i < 4; i++ )
            {
                sum = sum + board[i][j][i];
                j--;
                
                if( i == moveCheck[0] && j == moveCheck[1] && 
                    i == moveCheck[2] )
                {
                    found = true;
                }
            }
        }
        else
        {
            j = 0;
            
            for( int i = 3; i >= 0; i-- )
            {
                sum = sum + board[j][i][i];
                j++;
                
                if( j == moveCheck[0] && i == moveCheck[1] && 
                    i == moveCheck[2] )
                {
                    found = true;
                }
            }
        }
        
        if( found )
        {
            return sum;
        }
        else
        {
            return sum + 100;
        }
    }
    
    //find blank spot in line for each line type
    
    static int findRow(int board[][][], int level, int row)
    {
        for( int i = 0; i < 4; i++ )
        {
            if( board[level][row][i] == 0 )
            {
                return i;
            }
        }
        
        return 100;
    }
    
    static int findColumn(int board[][][], int level, int column)
    {
        for( int i = 0; i < 4; i++ )
        {
            if( board[level][i][column] == 0 )
            {
                return i;
            }
        }
        
        return 100;
    }
    
    static int findDiagonalLevel(int board[][][], int level, int first)
    {
        if( first == 0 )
        {
            for( int i = 0; i < 4; i++ )
            {
                if( board[level][i][i] == 0 )
                {
                    return i;
                }
            }
        }
        else
        {
            int j = 0;
            
            for( int i = 3; i >= 0; i-- )
            {
                if( board[level][j][i] == 0 )
                {
                    return j;
                }
                
                j++;
            }
        }
        
        return 100;
    }
    
    static int findHeight(int board[][][], int row, int column)
    {
        for( int i = 0; i < 4; i++ )
        {
            if( board[i][row][column] == 0 )
            {
                return i;
            }
        }
        
        return 100;
    }
    
    static int findDiagonalColumn(int board[][][], int column, int first)
    {
        if( first == 0 )
        {
            for( int i = 0; i < 4; i++ )
            {
                if( board[i][i][column] == 0 )
                {
                    return i;
                }
            }
        }
        else
        {
            int j = 0;
        	
        	for( int i = 3; i >= 0; i-- )
            {
                if( board[i][j][column] == 0 )
                {
                    return i;
                }
                
                j++;
            }
        }
        
        return 100;
    }
    
    static int findDiagonalRow(int board[][][], int row, int first)
    {
        if( first == 0 )
        {
            for( int i = 0; i < 4; i++ )
            {
                if( board[i][row][i] == 0 )
                {
                    return i;
                }
            }
        }
        else
        {
            int j = 0;
            
        	for( int i = 3; i >= 0; i-- )
            {
                if( board[j][row][i] == 0 )
                {
                    return j;
                }
                
                j++;
            }
        }
        
        return 100;
    }
    
    static int findDiagonalCool(int board[][][], int first) //based on column
    {
        if( first == 0 )
        {
            for( int i = 0; i < 4; i++ )
            {
                if( board[i][i][i] == 0 )
                {
                    return i;
                }
            }
        }
        else
        {
            int j = 0;
            
            for( int i = 3; i >= 0; i-- )
            {
                if( board[j][j][i] == 0 )
                {
                    return j;
                }
                
                j++;
            }
        }
        
        return 100;
    }
    
    static int findDiagonalCool2(int board[][][], int first) //based on column
    {
        int j;
        
        if( first == 0 )
        {
            j = 3;
            
            for( int i = 0; i < 4; i++ )
            {
                if( board[i][j][i] == 0 )
                {
                    return j;
                }
                
                j--;
            }
        }
        else
        {
            j = 0;
            
            for( int i = 3; i >= 0; i-- )
            {
                if( board[j][i][i] == 0 )
                {
                    return j;
                }
                
                j++;
            }
        }
        
        return 100;
    }
    
    static int findRowV2(int board[][][], int level, int row)
    {
        int count = 0;
        
        for( int i = 0; i < 4; i++ )
        {
            if( board[level][row][i] == 0 && count == 1 )
            {
                return i;
            }
            else if( board[level][row][i] == 0 && count == 0 )
            {
                count = 1;
            }
        }
        
        return 100;
    }
    
    static int findColumnV2(int board[][][], int level, int column)
    {
        int count = 0;
        
        for( int i = 0; i < 4; i++ )
        {
            if( board[level][i][column] == 0 && count == 1 )
            {
                return i;
            }
            else if( board[level][i][column] == 0 && count == 0 )
            {
                count = 1;
            }
        }
        
        return 100;
    }
    
    static int findDiagonalLevelV2(int board[][][], int level, int first)
    {
        int count = 0;
        
        if( first == 0 )
        {
            for( int i = 0; i < 4; i++ )
            {
                if( board[level][i][i] == 0 && count == 1 )
                {
                    return i;
                }
                else if( board[level][i][i] == 0 && count == 0 )
                {
                    count = 1;
                }
            }
        }
        else
        {
            int j = 0;
            
            for( int i = 3; i >= 0; i-- )
            {
                if( board[level][j][i] == 0 && count == 1 )
                {
                    return j;
                }
                else if( board[level][j][i] == 0 && count == 0 )
                {
                    count = 1;
                }
                
                j++;
            }
        }
        
        return 100;
    }
    
    static int findHeightV2(int board[][][], int row, int column)
    {
        int count = 0;
        
        for( int i = 0; i < 4; i++ )
        {
            if( board[i][row][column] == 0 && count == 1 )
            {
                return i;
            }
            else if( board[i][row][column] == 0 && count == 0 )
            {
                count = 1;
            }
        }
        
        return 100;
    }
    
    static int findDiagonalColumnV2(int board[][][], int column, int first)
    {
        int count = 0;
        
        if( first == 0 )
        {
            for( int i = 0; i < 4; i++ )
            {
                if( board[i][i][column] == 0 && count == 1 )
                {
                    return i;
                }
                else if( board[i][i][column] == 0 && count == 0 )
                {
                    count = 1;
                }
            }
        }
        else
        {
            int j = 0;
        	
        	for( int i = 3; i >= 0; i-- )
            {
                if( board[i][j][column] == 0 && count == 1 )
                {
                    return i;
                }
                else if( board[i][j][column] == 0 && count == 0 )
                {
                    count = 1;
                }
                
                j++;
            }
        }
        
        return 100;
    }
    
    static int findDiagonalRowV2(int board[][][], int row, int first)
    {
        int count = 0;
        
        if( first == 0 )
        {
            for( int i = 0; i < 4; i++ )
            {
                if( board[i][row][i] == 0 && count == 1 )
                {
                    return i;
                }
                else if( board[i][row][i] == 0 && count == 0 )
                {
                    count = 1;
                }
            }
        }
        else
        {
            int j = 0;
        	
        	for( int i = 3; i >= 0; i-- )
            {
                if( board[j][row][i] == 0 && count == 1 )
                {
                    return j;
                }
                else if( board[j][row][i] == 0 && count == 0 )
                {
                    count = 1;
                }
                
                j++;
            }
        }
        
        return 100;
    }
    
    static int findDiagonalCoolV2(int board[][][], int first) //based on column
    {
        int count = 0;
        
        if( first == 0 )
        {
            for( int i = 0; i < 4; i++ )
            {
                if( board[i][i][i] == 0 && count == 1 )
                {
                    return i;
                }
                else if( board[i][i][i] == 0 && count == 0 )
                {
                    count = 1;
                }
            }
        }
        else
        {
            int j = 0;
            
            for( int i = 3; i >= 0; i-- )
            {
                if( board[j][j][i] == 0 && count == 1 )
                {
                    return j;
                }
                else if( board[j][j][i] == 0 && count == 0 )
                {
                    count = 1;
                }
                
                j++;
            }
        }
        
        return 100;
    }
    
    //all coordinate changing diagonal starting from top front
    static int findDiagonalCool2V2(int board[][][], int first)
    {
        int j;
        int count = 0;
        
        if( first == 0 )
        {
            j = 3;
            
            for( int i = 0; i < 4; i++ )
            {
                if( board[i][j][i] == 0 && count == 1 )
                {
                    return j;
                }
                else if( board[i][j][i] == 0 && count == 0 )
                {
                    count = 1;
                }
                
                j--;
            }
        }
        else
        {
            j = 0;
            
            for( int i = 3; i >= 0; i-- )
            {
                if( board[j][i][i] == 0 && count == 1 )
                {
                    return j;
                }
                if( board[j][i][i] == 0 && count == 0 )
                {
                    count = 1;
                }
                
                j++;
            }
        }
        
        return 100;
    }
    
    static void printArray(int[][][] board)
    {
        String zero = "_"; //blank
        String one = "O"; //comp
        String five = "X"; //user
        
        for( int i = 0; i < 4; i++ )
        { 
            //level
            
            System.out.println("\n    #" + i + " 0 1 2 3");
            
            for( int j = 0; j < 4; j++ )
            {
                //row
                if( j == 0 )
                {
                    System.out.print("   " + j + "  ");
                }
                else if( j == 1 )
                {
                    System.out.print("  " + j + "  ");
                }
                else if( j == 2 )
                {
                    System.out.print(" " + j + "  ");
                }
                else if( j == 3 )
                {
                    System.out.print(j + "  ");
                }
                
                for( int k = 0; k < 4; k++ )
                {
                    //column
                    
                    if( board[i][j][k] == 0 )
                    {
                        System.out.print(zero + " ");
                    }
                    else if( board[i][j][k] == 1 )
                    {
                        System.out.print(one + " ");
                    }
                    else if( board[i][j][k] == 5 )
                    {
                        System.out.print(five + " ");
                    }
                }
                
                System.out.print("\n");
            }
        }
        
        System.out.print("\n");
    }
}