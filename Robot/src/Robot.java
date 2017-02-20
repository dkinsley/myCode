

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import java.util.StringTokenizer;

/**
 * Class representing a robot that lands on a planet.
 * robot takes orders from input file and moves accordingly. for example,
 * "2 20 1 12 L R L R 1 R 6 1" is the input that the robot computes
 * @author DavidKinsley
 *@version 0.1
 */

public class Robot 
{
    /**
     * Obtains the directions from an input file and outputs the results to another file
     * the robot is destroyed if it leaves the given array.  if the robot was not destroyed
     * then it outputs the time at which is finishes its orders and the coordinates.  if it is destroyed
     * then the output file says at what time it was destroyed
     * @param inputFile
     * @param outputFile the file that shows the results of the robots moves
     * @throws IOException if the input or output file is null
     */
    public static void  getMissionReport(File inputFile, File outputFile) throws IOException
    {
       
    	 if(inputFile == null)
         {
             throw new IllegalArgumentException();
         }
         if(outputFile == null)
         {
             throw new IllegalArgumentException();
         }
        
        int time = 0;
        try
        {
            PrintWriter pw = new PrintWriter(outputFile);
            Scanner sc = new Scanner(inputFile);
            
           
           
           sc.useDelimiter("\\n| ");
           
           ArrayList<String> input = new ArrayList<>();
           while(sc.hasNext())
           {
               input.add(sc.nextLine());    
           }
           
           
           int[] grid = new int[2];
           int beginX, beginY;
           
           
           for(int i = 0; i < input.size(); i++)
           {
               try
               {
               StringTokenizer toke = new StringTokenizer(input.get(i));
               String[] temp = new String[toke.countTokens()];
               for(int k = 0; k < input.get(i).length(); k++)
               {
                   if (toke.hasMoreTokens()) 
                   {
                       temp[k] = toke.nextToken();
                   }
               }
               
               grid[0] = Integer.parseInt(temp[0]);
               grid[1] = Integer.parseInt(temp[1]);
               
               if(grid[0] < 1 || grid[1] < 1)
               {
                   break;
               }
               
               beginX = Integer.parseInt(temp[2]);
               beginY = Integer.parseInt(temp[3]);
               
               int[][] gs = new int[grid[0]][grid[1]];
               for(int row = 0; row < grid.length; row++)
               {
                   for(int col = 0; col < gs[row].length; col++)
                   {
                       gs[row][col] = 0;
                   }
               }
               gs[beginX][beginY] = 1;
               
               time = 0;
               String pos = "north";
               for(int order = 4; order < temp.length; order++)
               {
                   if(temp[order].equals("L"))
                   {
                       time++;
                       if(pos.equals("north"))
                       {
                    	   pos = "left";
                       }
                       else if(pos.equals("right"))
                       {
                    	   pos = "north";
                       }
                       else if(pos.equals("left"))
                       {
                    	   pos = "south";
                       }
                       else
                       {
                    	   pos = "right";
                       }
                   }
                   else if(temp[order].equals("R"))
                   {
                       time++;
                       if(pos.equals("north"))
                       {
                           pos = "right";
                       }
                       else if(pos.equals("right"))
                       {
                    	   pos = "south";
                       }
                       else if(pos.equals("left"))
                       {
                    	   pos = "north";
                       }
                       else
                       {
                    	   pos = "left";
                       }
                   }
                   
                   else
                   {
                       time++;
                       if(pos.equals("north"))
                       {
                           gs[beginX][beginY] = 0;
                           gs[beginX - Integer.parseInt(temp[order])][beginY] = 1;
                           beginX = beginX - Integer.parseInt(temp[order]);
                       }
                       if(pos.equals("south"))
                       {
                           gs[beginX][beginY] = 0;
                           gs[beginX + Integer.parseInt(temp[order])][beginY] = 1;
                           beginX = beginX + Integer.parseInt(temp[order]);
                       }
                       if(pos.equals("left"))
                       {
                           gs[beginX][beginY] = 0;
                           gs[beginX][beginY - Integer.parseInt(temp[order])] = 1;
                           beginY = beginY - Integer.parseInt(temp[order]);
                       }
                       if(pos.equals("right"))
                       {
                           gs[beginX][beginY] = 0;
                           gs[beginX][beginY + Integer.parseInt(temp[order])] = 1;
                           beginY = beginY + Integer.parseInt(temp[order]);
                       }
                   }
                   
                   
                   
               }
               pw.println(beginX + " " + beginY + " @ time " + time);
               time = 0;
               }   
               catch(ArrayIndexOutOfBoundsException e)
               {
                   pw.println("destroyed @ time " + time);
                   time = 0;
                   
               }
           }
           
           pw.close();
           sc.close();
       }
       catch(IOException e)
       {
           
           
       }
       
        
    
    
    
    }   
}
