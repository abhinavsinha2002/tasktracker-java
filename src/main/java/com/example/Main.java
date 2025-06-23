package com.example;

import java.util.ArrayList;
import java.io.*;
import java.nio.file.Files;
import java.time.LocalDateTime;


import org.json.JSONArray;
import org.json.JSONObject;
import java.util.List;


public class Main {

    public static void main(String[] args) {

        switch (args[0]) {
            case "add":
                addTasks(args);
                break;
            case "update":
                updateTasks(args);
                break;
            case "delete":
                deleteTasks(args);
                break;
            case "mark-in-progress":
                markInProgress(args);
                break;
            case "mark-done":
                markDone(args);
                break;
            case "list":
                if(args.length==1){
                    listAll(args);
                }
                else if(args.length==2){
                    switch(args[1]){
                        case "done":
                            listDone(args);
                            break;
                        case "todo":
                            listToDo(args);
                            break;
                        case "in-progress":
                            listInProgress(args);
                            break;
                        default:
                            System.out.println("Wrong command.Type help to learn more about the commands.");
                        
                    }
                }
                break;
            
            case "help":
                System.out.println("The task-tracker supports CRUD operations");
                System.out.println("1) For Adding a new task, add \"Buy groceries\" ");
                System.out.println("2) For Updating a task, update 1 \"Buy groceries and cook dinner\" ");
                System.out.println("3) For Deleting tasks, delete 1");
                System.out.println("4) For Marking a task as in progress, mark-in-progress 1");
                System.out.println("5) For Marking a task as done, mark-done 1");
                System.out.println("6) For Listing all tasks, list");
                System.out.println("7) For Listing tasks by status, list todo/done/in-progress");
                break;
                
            default:
                System.out.println("Wrong command.Type help to learn more about the commands.");
        }
    }

    public static void listInProgress(String[] args){
        File file=new File("C:/Users/saura/OneDrive/Documents/Projects/tasktracker/src/main/java/com/example/store.json");
        try{
            String content=new String(Files.readAllBytes(file.toPath()));
            JSONArray data=new JSONArray(content);
            for(int i=0;i<data.length();i++){
                JSONObject obj=data.getJSONObject(i);
                if(obj.get("status").equals("in-progress")){
                    System.out.println(obj.toString(4));
                }
            }
        }
        catch(IOException e){
            e.printStackTrace();
        }
        
    }

    public static void listToDo(String[] args){
        File file=new File("C:/Users/saura/OneDrive/Documents/Projects/tasktracker/src/main/java/com/example/store.json");
        try{
            String content=new String(Files.readAllBytes(file.toPath()));
            JSONArray data=new JSONArray(content);
            for(int i=0;i<data.length();i++){
                JSONObject obj=data.getJSONObject(i);
                if(obj.get("status").equals("todo")){
                    System.out.println(obj.toString(4));
                }
            }
        }
        catch(FileNotFoundException e){
            e.printStackTrace();
        }
        catch(IOException e){
            e.printStackTrace();
        }
    }

    public static void listDone(String[] args){
        File file=new File("C:/Users/saura/OneDrive/Documents/Projects/tasktracker/src/main/java/com/example/store.json");
        try{
            String content=new String(Files.readAllBytes(file.toPath()));
            JSONArray data=new JSONArray(content);
            for(int i=0;i<data.length();i++){
                JSONObject obj=data.getJSONObject(i);
                if(obj.get("status").equals("done")){
                    System.out.println(obj.toString(4));
                }
            }
        }
        catch(IOException e){
            e.printStackTrace();
        }
    }

    public static void listAll(String[] args){
        File file=new File("C:/Users/saura/OneDrive/Documents/Projects/tasktracker/src/main/java/com/example/store.json");

        try{
            String content=new String(Files.readAllBytes(file.toPath()));
            JSONArray data=new JSONArray(content);
            for (int i = 0; i < data.length(); i++) {
                JSONObject jsonobj = data.getJSONObject(i);
                System.out.println(jsonobj.toString(4));
            }

        }
        catch(FileNotFoundException e){
            e.printStackTrace();
        }
        catch(IOException e){
            e.printStackTrace();
        }

    }

    public static void markDone(String[] args){
        if(args.length!=2){
            System.out.println("Error. Wrong command.");
        }
        else{
            String markId=args[1];
            File file=new File("C:/Users/saura/OneDrive/Documents/Projects/tasktracker/src/main/java/com/example/store.json");
            try(FileReader reader=new FileReader(file)){
                String content=new String(Files.readAllBytes(file.toPath()));
                JSONArray data=new JSONArray(content);
                boolean marked=false;
                for (int i = 0; i < data.length(); i++) {
                    JSONObject jsonobj = (JSONObject) data.get(i);
                    if (jsonobj.get("id").equals(markId)) {
                        marked = true;
                        jsonobj.put("status", "done");
                        break;
                    }
                }

                if(marked){
                    try(FileWriter writer=new FileWriter(file)){
                        writer.write(data.toString());
                        writer.flush();
                        System.out.printf("Task with ID:%s marked done",markId);
                    }
                }
                else{
                    System.out.println("No object found with ID: "+markId);
                }
            }
            catch(FileNotFoundException e){
                e.printStackTrace();
            }
            catch(IOException e){
                e.printStackTrace();
            }

        }
    }


    public static void markInProgress(String[] args){
        if(args.length!=2){
            System.out.println("Error.Wrong command.Type help to learn more about the commands");
        }
        else{
            String markId=args[1];
            File file=new File("C:/Users/saura/OneDrive/Documents/Projects/tasktracker/src/main/java/com/example/store.json");
            try(FileReader reader=new FileReader(file)){
                String content=new String(Files.readAllBytes(file.toPath()));
                JSONArray data=new JSONArray(content);
                boolean marked=false;

                for (int i = 0; i < data.length(); i++) {
                    JSONObject jsonobj = (JSONObject) data.get(i);
                    if (jsonobj.get("id").equals(markId)) {
                        jsonobj.put("status", "in-progress");
                        marked = true;
                        break;
                    }
                }

                if(marked){
                    try(FileWriter writer=new FileWriter(file)){
                        writer.write(data.toString());
                        writer.flush();
                        System.out.printf("Task with ID:%s marked in progress",markId);
                    }
                }
                else{
                    System.out.println("No object found with ID: "+markId);
                }
            }
            catch(FileNotFoundException e){
                e.printStackTrace();
            }
            catch(IOException e){
                e.printStackTrace();
            }

        }
    }

    public static void deleteTasks(String[] args){
        if(args.length!=2){
            System.out.println("Error. Wrong delete command. Type help to learn more about the commands");
        }
        else{
            String deleteId=args[1];
            File file=new File("C:/Users/saura/OneDrive/Documents/Projects/tasktracker/src/main/java/com/example/store.json");

            try(FileReader reader=new FileReader(file)){
                String content=new String(Files.readAllBytes(file.toPath()));
                JSONArray data=new JSONArray(content);
                boolean deleted=false;
                for(int i=0;i<data.length();i++){
                    JSONObject jsonobj=data.getJSONObject(i);
                    if(jsonobj.get("id").equals(deleteId)){
                        data.remove(i);
                        deleted=true;
                        break;
                    }
                    //System.out.println(jsonobj.toJSONString());
                }

                if(deleted){
                    try(FileWriter writer=new FileWriter(file)){
                        writer.write(data.toString());
                        writer.flush();
                        System.out.println("Task deleted with ID: "+deleteId);
                    }
                }
                else{
                    System.out.println("No object found with ID: "+deleteId);
                }
                
            }
            catch(FileNotFoundException e){
                e.printStackTrace();
            }
            catch(IOException e){
                e.printStackTrace();
            }

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
                String content=new String(Files.readAllBytes(file.toPath()));
                JSONArray data = new JSONArray(content);
                boolean ifUpdate=false;

                for(int i=0;i<data.length();i++){
                    JSONObject jsonobj=data.getJSONObject(i);
                    if((jsonobj).get("id").equals(updateId)){
                        (jsonobj).put("description",args[2]);
                        (jsonobj).put("updatedAt",String.valueOf(LocalDateTime.now()));
                        //System.out.println(obj.toString());
                        ifUpdate=true;
                        break;
                    }
                }

                if(ifUpdate){
                    try(FileWriter fileWriter=new FileWriter(file)){
                        fileWriter.write(data.toString());
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
            }

        }
    }



    public static void addTasks(String[] args) {
        if (args.length <= 1) {
            System.out.println("Error. Missing description after add command. Type help to get the list of commands.");
        } 
        else {
            // System.out.println("Inside");
            String directory = "C:/Users/saura/OneDrive/Documents/Projects/tasktracker/src/main/java/com/example/";
            String filename = "store.json";
            File file=new File(directory+filename);

            JSONArray tasks;

            if(file.exists()){
                try{
                    String content=new String(Files.readAllBytes(file.toPath()));
                    if(content.trim().startsWith("[")){
                        tasks=new JSONArray(content);
                    }
                    else{
                        JSONObject obj=new JSONObject(content);
                        tasks=new JSONArray();
                        tasks.put(obj);
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

            for(int i=0;i<tasks.length();i++){
                list.add(tasks.getJSONObject(i));
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
            tasks.put(task);
            
            try(Writer writer=new FileWriter(file)){
                writer.write(tasks.toString());
                writer.flush();
                System.out.printf("Task added successfully (ID:%d)",new_id);
            }
            catch(IOException e){
                e.printStackTrace();
            }
           
        }
    }
}
