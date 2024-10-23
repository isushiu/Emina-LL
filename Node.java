
public class Node
{
    Customer data;
    Node next; //reference to the next node
    
    Node(Customer c)
    {
        this(c,null);
    }
    
    Node(Customer c, Node nextNode)
    {
        data = c;
        next = nextNode;
    }
    
    Customer getCustomer()
    {
        return data;
    }
    
    Node getLink()
    {
        return next;
    }
}
