import java.util.Scanner;

class CircularLinkedList {
    class Node {
        int data;
        Node next;
        Node(int data) { this.data = data; }
    }

    private Node head = null;

    // Insert node at nth position
    public void insertAtPosition(int data, int pos) {
        Node newNode = new Node(data);
        if (head == null) {
            newNode.next = newNode;
            head = newNode;
            return;
        }
        if (pos == 1) {
            Node temp = head;
            while (temp.next != head) temp = temp.next;
            newNode.next = head;
            temp.next = newNode;
            head = newNode;
            return;
        }

        Node temp = head;
        for (int i = 1; i < pos - 1 && temp.next != head; i++)
            temp = temp.next;
        newNode.next = temp.next;
        temp.next = newNode;
    }

    // Delete by data
    public void deleteByData(int key) {
        if (head == null) return;

        Node curr = head, prev = null;
        while (curr.data != key && curr.next != head) {
            prev = curr;
            curr = curr.next;
        }

        if (curr.data != key) {
            System.out.println("Data not found!");
            return;
        }

        if (curr == head && curr.next == head) {
            head = null;
            return;
        }

        if (curr == head) {
            Node temp = head;
            while (temp.next != head)
                temp = temp.next;
            temp.next = head.next;
            head = head.next;
        } else {
            prev.next = curr.next;
        }
    }

    // Modify node data
    public void modifyNode(int oldData, int newData) {
        if (head == null) return;
        Node temp = head;
        do {
            if (temp.data == oldData) {
                temp.data = newData;
                return;
            }
            temp = temp.next;
        } while (temp != head);
        System.out.println("Data not found!");
    }

    // Display
    public void display() {
        if (head == null) {
            System.out.println("List is empty");
            return;
        }
        Node temp = head;
        do {
            System.out.print(temp.data + " ");
            temp = temp.next;
        } while (temp != head);
        System.out.println();
    }

    // Main for testing
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        CircularLinkedList list = new CircularLinkedList();
        int choice;
        do {
            System.out.println("\n1.Insert 2.Delete 3.Modify 4.Display 5.Exit");
            choice = sc.nextInt();
            switch (choice) {
                case 1:
                    System.out.print("Enter data & position: ");
                    int d = sc.nextInt();
                    int p = sc.nextInt();
                    list.insertAtPosition(d, p);
                    break;
                case 2:
                    System.out.print("Enter data to delete: ");
                    list.deleteByData(sc.nextInt());
                    break;
                case 3:
                    System.out.print("Enter old data and new data: ");
                    list.modifyNode(sc.nextInt(), sc.nextInt());
                    break;
                case 4:
                    list.display();
                    break;
            }
        } while (choice != 5);
        sc.close();
    }
}
