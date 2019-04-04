/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ex;

import java.util.LinkedList;

/**
 *
 * @author Franz
 */
public class MyQueue<Book> 
{
    private LinkedList<Book> data = new LinkedList<Book>();
    private int maxSize = 3;
    
    public MyQueue(int maxSize) 
    {
        this.maxSize = maxSize;
    }
    public void put(Book element) throws FullException
    {
        if(data.size() == maxSize)
        {
            throw new FullException();
        }
        else
        {
            data.add(element);
        }
    }
    
    public Book get() throws EmptyException
    {
        if(data.isEmpty())
        {
            throw new EmptyException();
        }
        else{
            return data.poll();
        }
    }
}

