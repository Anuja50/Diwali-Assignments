class CircularQueue {
    private int[] queue;
    private int front, rear, size, capacity;

    CircularQueue(int cap) {
        capacity = cap;
        queue = new int[capacity];
        front = rear = size = 0;
    }

    public void enqueue(int data) {
        if (size == capacity) {
            expandCapacity();
        }
        queue[rear] = data;
        rear = (rear + 1) % capacity;
        size++;
    }

    public void dequeue() {
        if (size == 0) {
            System.out.println("Queue is empty!");
            return;
        }
        System.out.println("Dequeued: " + queue[front]);
        front = (front + 1) % capacity;
        size--;
    }

    private void expandCapacity() {
        int[] newQueue = new int[capacity * 2];
        for (int i = 0; i < size; i++)
            newQueue[i] = queue[(front + i) % capacity];
        queue = newQueue;
        front = 0;
        rear = size;
        capacity *= 2;
    }

    public void display() {
        if (size == 0) {
            System.out.println("Queue empty!");
            return;
        }
        System.out.print("Queue: ");
        for (int i = 0; i < size; i++)
            System.out.print(queue[(front + i) % capacity] + " ");
        System.out.println();
    }

    public static void main(String[] args) {
        CircularQueue cq = new CircularQueue(3);
        cq.enqueue(10);
        cq.enqueue(20);
        cq.enqueue(30);
        cq.display();
        cq.enqueue(40); // expands capacity
        cq.display();
        cq.dequeue();
        cq.display();
    }
}

