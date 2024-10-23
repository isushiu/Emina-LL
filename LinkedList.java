
public class LinkedList
{
    private Node first; //first Node
    private Node last; //last Node
    private Node current; //current Node
    
    //default construtor for LinkedList
    public LinkedList()
    {
        first = last = current = null;
    }
    
    //insert at front
    public void insertAtFront(Customer insertItem)
    {
        if(isEmpty())
        {
            first = last = new Node(insertItem);
        }
        else
        {
            first = new Node(insertItem, first);
        }
    }
    
    //insert at back
    public void insertAtBack(Customer insertItem)
    {
        if(isEmpty())
        {
            first = last = new Node(insertItem);
        }
        else
        {
            last = last.next  = new Node(insertItem);
        }
    }
    
    //remove From front
    public Customer removeFromFront() throws EmptyListException
    {
        Customer removeItem = null;
        
        if(isEmpty())
        {
            throw new EmptyListException();
        }
        removeItem = first.data;
        
        if(first.equals(last))
        {
            first = last = null;
        }
        else
        {
            first = first.next;
        }
        return removeItem;
    }
    
    //remove From back
    public Customer removeFromBack() throws EmptyListException
    {
        Customer removeItem = null;
        
        if(isEmpty())
        {
            throw new EmptyListException();
        }
        removeItem = last.data;
        
        if(first.equals(last))
        {
            first = last = null;
        }
        else
        {
            while(current.next != first)
            {
                current = current.next;
            }
            last = current;
            current.next = null;
        }
        return removeItem;
    }
    
    //getFirst
    public Customer getFirst()
    {
        if(isEmpty())
        {
            return null;
        }
        else
        {
            current = first;
            return current.data;
        }
    }
    
    //getNext
    public Customer getNext()
    {
        if(current != last)
        {
            current = current.next;
            return current.data;
        }
        else
        {
            return null;
        }
    }
    
    //getLast
    public Customer getLast()
    {
        if(isEmpty())
        {
            return null;
        }
        else
        {
            current = last;
            return current.data;
        }
    }
    
    //size
    public int size()
    {
        int size = 0;
        Node current = first;
        
        while(current != null)
        {
            current = current.next;
            size++;
        }
        return size;
    }
    
    //display details all elements
    public void displayElement()
    {
        Node current = first;
        
        while(current != null)
        {
            current = current.next;
            System.out.println(current);
        }
    }
    
    //isEmpty()
    public boolean isEmpty()
    {
        return first == null;
    }
}
