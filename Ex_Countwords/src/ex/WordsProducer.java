/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ex;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Franz
 */
public class WordsProducer implements Runnable
{
    private final MyQueue<Book> queue;
    private final ThreadState status;

    public WordsProducer(MyQueue<Book> queue, ThreadState status) 
    {
        this.queue = queue;
        this.status = status;
    }

    @Override
    public void run() 
    {
        status.setToRun();
        File[] files = new File("files/").listFiles();
        for (File file : files) 
        {
            try 
            {
                BufferedReader br = new BufferedReader(new FileReader(file));
                String text = "";
                String line = "";
                while ((line = br.readLine()) != null) 
                {
                    text += line;
                }
                Book book = new Book(text, file.getName());
                synchronized (queue) 
                {
                    try 
                    {
                        queue.put(book);
                        queue.notifyAll();
                    }catch (FullException ex) 
                    {
                        try
                        {
                            status.setToWait();
                            queue.wait();
                            status.setToRun();
                        }catch(Exception e)
                        {
                            System.out.println("Error: " + e);
                        }
                    }
                }
            }catch (IOException ex) 
            {
                Logger.getLogger(WordsProducer.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
