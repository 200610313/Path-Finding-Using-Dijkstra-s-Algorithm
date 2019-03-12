/**
 * Array implementation of the stack solely to used in the path retrieval algorithm
 */
public class stack {
    private int top;
    private int capacity;
    private int[] stack;

    /**
     * Initializes the the stack array
     */
    public stack(){
        top = -1;
        capacity = 20;
        stack = new int[capacity];
    }

    /**
     * Check if the stack is Empty
     * @return return true if it is empty, else false
     */
    public boolean isEmpty(){
        return top == -1;
    }

    /**
     * Checks if the stack is full
     * @return true if the stack is full
     */
    public boolean isFull(){
        return  top == capacity - 1;
    }

    /**
     * Add an element in to the stack
     * @param d element to be added
     */
    public void push(int d){
        if (isFull()){
            System.out.println("Full stack.");
        }
        else{
            top++;
            stack[top]=d;
        }
    }

    /**
     * Removes an element from top of stack
     */
    public void pop(){
        if (isEmpty()){
            System.out.println("Empty stack.");
        }
        top--;
    }

    /**
     * Peeks the top element of stack
     * @return returns the top element of the stack
     */
    public int peek(){
        return stack[top];
    }

    /**
     * Clears the stack
     */
    public void clear(){
        top = -1;
    }
}
