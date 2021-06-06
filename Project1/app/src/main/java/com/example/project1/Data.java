package com.example.project1;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class Data {

    // A hashmap data structure for holding usernames and passwords pair
    HashMap <String, String> hmCredentials;

    public Data(){

        hmCredentials = new HashMap<>();

        // Adding some items into the hashmap table

        hmCredentials.put("AJ", "CoolDude1");
        hmCredentials.put("test", "1234");
    }

    // This method adds a new username and password to the hashmap
    public void AddCredential(String username, String password){
        hmCredentials.put(username, password);
    }

    // This method checks if username exists in the hashmap
    public Boolean CheckUsername(String username){
        Boolean  retval = false;

        // If the HashMap is empty... return the false
        if(hmCredentials.isEmpty()){

            return retval;

        } else {

            // Instantiate an Iterator for the hashmap
            Iterator<Map.Entry<String,String>> itr = hmCredentials.entrySet().iterator();

            // While there are still entry's...
            while(itr.hasNext()) {

                // Create a variable of the current entry being examined
                Map.Entry<String,String> entry = itr.next();

                // get the username entry
                String usernameEntry = entry.getKey();

                // If the username already exists... set return value to true and break loop
                if( username.compareTo(usernameEntry) == 0){

                    retval = true;
                    break;
                }

            }

       }

        return retval;
    }

    // This method checks a username and password combination is correct!
    public Boolean CheckCredentials(String username, String Password){
        Boolean  retval = false;


        // Instantiate an Iterator for the hashmap
        Iterator<Map.Entry<String,String>> itr = hmCredentials.entrySet().iterator();

        // While there are still entry's...
        while(itr.hasNext()) {

            // Create a variable of the current entry being examined
            Map.Entry<String,String> entry = itr.next();

            // get the username & password
            String usernameEntry = entry.getKey();
            String passwordEntry = entry.getValue();

            // If the username & password match... set return value to true and break loop
            if( username.compareTo(usernameEntry) == 0 && Password.compareTo(passwordEntry) == 0){

                retval = true;
                break;
            }

        }

        return retval;
    }

}
