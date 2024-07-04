class Node {
    String data;
    Node next;

    public Node(String data) {
        this.data = data;
        this.next = null;
    }
}

public class Queue {
    private Node front; // início da fila
    private Node rear;  // fim da fila

    public Queue() {
        this.front = null;
        this.rear = null;
    }

    public void enqueue(String data) {

        Node newNode = new Node(data);

        if (rear == null) {
            front = rear = newNode;
            return;
        }
        rear.next = newNode;
        rear = newNode;
    }
    
    public String dequeueData(String data){
        Node x;
        x = front;
        while (x != null) {
            if (x.data == data) {
                front = front.next;
                return data;
            }
            x = x.next;
        }
        return data + " não está na fila.";

    }

    public String printQueue(){
        
        Node x = front;
        StringBuilder sb = new StringBuilder();

        while (x != null) {
            sb.append(x.data).append(" ");
            x = x.next;
        }

        return sb.toString().trim();
    }


}
