package com.example;

import java.util.ArrayList;
import java.io.*;
import java.time.LocalDateTime;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import org.json.simple.parser.JSONParser;
import java.util.List;


public class Main {

    public static void main(String[] args) {
        //System.out.println("Hello All");
        // Create a new instance of the class
        switch (args[0]) {
            case "add":
                addTasks(args);
                break;
            case "update":
                updateTasks(args);
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
            case "list":
                System.out.println("This is a switch statement with a boolean expression.");
                break;
            default:
                System.out.println("This will not be executed.");
        }
    }

    public static boolean isStringInt(String s){
        try{
            Integer.parseInt(s);
            return true;
        }
        catch(NumberFormatException ex){
            return false;
        }
    }

    @SuppressWarnings("unchecked")
    public static void updateTasks(String[] args){
        if(args.length != 3 || !isStringInt(args[1]) || !(args[2] instanceof String)){
            //System.out.println(args.length);
            //System.out.println(args[2] instanceof String);
            //System.out.println(isStringInt(args[1]));
            System.out.println("Error. Wrong update command. Type help to learn more about the commands.");
        }
        else{
            String updateId=args[1];
            File file=new File("C:/Users/saura/OneDrive/Documents/Projects/tasktracker/src/main/java/com/example/store.json");

            try(FileReader reader=new FileReader(file)) {
                JSONParser parser=new JSONParser();
                JSONArray data = (JSONArray) parser.parse(reader);
                boolean ifUpdate=false;

                for(Object obj:data){
                    JSONObject jsonobj=(JSONObject)obj;
                    if((jsonobj).get("id").equals(updateId)){
                        (jsonobj).put("description",args[2]);
                        (jsonobj).put("updatedAt",String.valueOf(LocalDateTime.now()));
                        System.out.println(obj.toString());
                        ifUpdate=true;
                        break;
                    }
                }

                if(ifUpdate){
                    try(FileWriter fileWriter=new FileWriter(file)){
                        fileWriter.write(data.toJSONString());
                        fileWriter.flush();
                        System.out.println("Description updated successfully with ID: "+updateId);
                    }
                }
                else{
                    System.out.println("No object found with ID: "+updateId);
                }
                

            } catch (FileNotFoundException e) {
                //System.out.println("File not found : "+e.getMessage());
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (org.json.simple.parser.ParseException e) {
                e.printStackTrace();
            }
        }
    }


    @SuppressWarnings("unchecked")
    public static void addTasks(String[] args) {
        if (args.length <= 1) {
            System.out.println("Error. Missing description after add command");
        } 
        else {
            // System.out.println("Inside");
            String directory = "C:/Users/saura/OneDrive/Documents/Projects/tasktracker/src/main/java/com/example/";
            String filename = "store.json";
            File file=new File(directory+filename);

            JSONArray tasks;
            JSONParser parser=new JSONParser();

            if(file.exists()){
                try(Reader reader=new FileReader(file)){
                    Object obj=parser.parse(reader);
                    if(obj instanceof JSONArray){
                        tasks=(JSONArray)obj;
                    }
                    else{
                        tasks=new JSONArray();
                        tasks.add(obj);
                    }
                    
                }
                catch(Exception e){
                    e.printStackTrace();
                    tasks=new JSONArray();
                }
            }
            else{
                tasks=new JSONArray();
            }

            JSONObject task=new JSONObject();
            List<JSONObject> list=new ArrayList<>();
            for(Object o:tasks){
                list.add((JSONObject)o);
            }

            int new_id = list.stream()
            .mapToInt(o->Integer.parseInt(o.get("id").toString()))
            .max()
            .orElse(0)+1;

            Start.description=args[1];
            Start.status="todo";
            task.put("id", String.valueOf(new_id));
            task.put("description",Start.description); 
            task.put("status",Start.status);
            task.put("createdAt",String.valueOf(Start.createdAt));
            task.put("updatedAt",String.valueOf(Start.updatedAt));
            tasks.add(task);
            
            try(Writer writer=new FileWriter(file)){
                writer.write(tasks.toJSONString());
                writer.flush();
                System.out.printf("Task added successfully (ID:%d)",new_id);
            }
            catch(IOException e){
                e.printStackTrace();
            }
           
        }
    }
}
