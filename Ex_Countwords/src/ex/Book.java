/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ex;

import java.util.HashMap;
import java.util.LinkedList;

/**
 *
 * @author Franz
 */
public class Book {

    private String text;
    private String inputfilename;

    public Book(String text, String inputfilename) {
        this.text = text;
        this.inputfilename = inputfilename;
    } 
    public String getFilename() 
    {
        return inputfilename;
    }


    public HashMap<String, Integer> countWords() 
    {
        String[] parts = text.split(" ");
        HashMap<String, Integer> map = new HashMap();
        for (String word : parts) {
            if (!map.containsKey(word)) 
            {
                map.put(word, 1);
            } else 
            {
                map.put(word, map.get(word) + 1);
            }
        }
        
        LinkedList<String> keys = new LinkedList<String>();
        for (String key : map.keySet()) 
        {
            if(map.get(key) < 2)
            {
                keys.add(key);
            }
        }
        for(String key : keys)
        {
            map.remove(key);
        }
        return map;
    }
}
