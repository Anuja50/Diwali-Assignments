class BST {
    class Node {
        int data;
        Node left, right;
        Node(int d) { data = d; }
    }

    Node root;

    // Add node
    void insert(int data) {
        root = insertRec(root, data);
    }

    Node insertRec(Node root, int data) {
        if (root == null) return new Node(data);
        if (data < root.data) root.left = insertRec(root.left, data);
        else if (data > root.data) root.right = insertRec(root.right, data);
        return root;
    }

    // Delete node
    Node delete(Node root, int key) {
        if (root == null) return null;

        if (key < root.data) root.left = delete(root.left, key);
        else if (key > root.data) root.right = delete(root.right, key);
        else {
            if (root.left == null) return root.right;
            else if (root.right == null) return root.left;
            root.data = minValue(root.right);
            root.right = delete(root.right, root.data);
        }
        return root;
    }

    int minValue(Node root) {
        int minv = root.data;
        while (root.left != null) {
            minv = root.left.data;
            root = root.left;
        }
        return minv;
    }

    // Traversals
    void inorder(Node root) {
        if (root != null) {
            inorder(root.left);
            System.out.print(root.data + " ");
            inorder(root.right);
        }
    }

    void preorder(Node root) {
        if (root != null) {
            System.out.print(root.data + " ");
            preorder(root.left);
            preorder(root.right);
        }
    }

    void postorder(Node root) {
        if (root != null) {
            postorder(root.left);
            postorder(root.right);
            System.out.print(root.data + " ");
        }
    }

    public static void main(String[] args) {
        BST tree = new BST();
        tree.insert(50);
        tree.insert(30);
        tree.insert(20);
        tree.insert(40);
        tree.insert(70);
        tree.insert(60);
        tree.insert(80);

        System.out.print("Inorder: "); tree.inorder(tree.root); System.out.println();
        System.out.print("Preorder: "); tree.preorder(tree.root); System.out.println();
        System.out.print("Postorder: "); tree.postorder(tree.root); System.out.println();

        tree.root = tree.delete(tree.root, 20);
        System.out.print("After deleting 20 (Inorder): ");
        tree.inorder(tree.root);
    }
}
