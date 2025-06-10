package com.example;
import java.io.File;
import java.io.IOException;

/**
 * Hello world!
 *
 */
public class Main 
{
    public static void main(String[] args) {
         System.out.println("Hello All");
        // Create a new instance of the class
       switch (args[0]) {
           case "add":
                if(args.length<=1){
                    System.out.println("Error. Missing description after add command");
                }
                else{
                    //System.out.println("Inside");
                    String directory="C:/Users/saura/OneDrive/Documents/Projects/tasktracker/src/main/java/com/example/";
                    String filename="store.json";
                    try{
                        File file=new File(directory+filename);
                        file.createNewFile();

                        //System.out.println("File created successfully");
                    }
                    catch(IOException e){
                        e.printStackTrace();
                    }
                }
               
               break;
           case "update":
                System.out.println("This is a switch statement with a boolean expression.");
                break;
           case "delete":
               System.out.println("This is a switch statement with a boolean expression.");
               break;
           case "mark-in-progress":
               System.out.println("This is a switch statement with a boolean expression.");
               break;
           case "mark-done":
               System.out.println("This is a switch statement with a boolean expression.");
               break;
           case "list" :
                System.out.println("This is a switch statement with a boolean expression.");
                break;
           default:
               System.out.println("This will not be executed.");
       }
    }
}
