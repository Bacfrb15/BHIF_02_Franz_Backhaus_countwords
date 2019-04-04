/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ex;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Franz
 */
public class WordsConsumer implements Runnable
{
    private final MyQueue<Book> queue;
    private final ThreadState status;
    
    public WordsConsumer(MyQueue<Book> queue, ThreadState status) 
    {
        this.queue = queue;
        this.status = status;
    }
    
    @Override
    public void run() 
    {
        status.setToRun();
        Book book = null;
        while (true) 
        {
            synchronized (queue) 
            {             
                try 
                {
                    book = queue.get();
                    queue.notifyAll();
                }catch (EmptyException ex) 
                {
                    try 
                    {
                        status.setToWait();
                        queue.wait();
                        status.setToRun();
                    }catch (Exception e) 
                    {
                        System.out.println("Error: " + e);
                    }
                    continue;
                }
            }
            HashMap<String, Integer> map = book.countWords();
            File f = new File("output/" + book.getFilename() + "-output.txt");
            try 
            {
                BufferedWriter bw = new BufferedWriter(new FileWriter(f));
                for (String str : map.keySet()) {
                    if (str.matches("^[a-zA-Z0-9]+$")) 
                    {
                        bw.write(String.format("%s: %d\n", str, map.get(str)));
                    }
                }
            }catch (IOException ex) 
            {
                Logger.getLogger(WordsConsumer.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
