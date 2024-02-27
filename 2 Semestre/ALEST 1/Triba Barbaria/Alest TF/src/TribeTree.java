import java.text.DecimalFormat;

public class TribeTree {
    private TreeNode firstWarrior;
    private TreeNode warriorWithMoreLands;

    public TribeTree(String warriorName, double lands) {
        this.firstWarrior = new TreeNode(warriorName, lands);
    }

    static class TreeNode {
        private Node firstChild;
        private String warriorName;
        private double lands;
        private int height;

        TreeNode() {
            this.height = 0;
            this.lands = 0;
        }

        TreeNode(String warriorName, double lands) {
            this.warriorName = warriorName;
            this.lands = lands;
        }

        public void setWarriorName(String warriorName) {
            this.warriorName = warriorName;
        }

        public Node getFirstChild() {
            return this.firstChild;
        }

        public String getWarriorName() {
            return this.warriorName;
        }
    }

    private static class Node {
        private TreeNode child;
        private Node next;

        Node(TreeNode node) {
            this.child = node;
            this.next = null;
        }
    }
    
    public TreeNode getFirstWarrior() {
        return this.firstWarrior;
    }

    public String getWarriorWithMoreLands() {
        if (this.firstWarrior == null)
            return null;
            
        this.warriorWithMoreLands = new TreeNode();

        calculateLandsHelper(this.firstWarrior, 1);

        DecimalFormat df = new DecimalFormat("#.00");

        return this.warriorWithMoreLands.warriorName
            .concat(" com ")
            .concat(df.format(this.warriorWithMoreLands.lands))
            .concat(" terras");
    }

    private void calculateLandsHelper(TreeNode treeNode, int height) {
        Node currentFatherChild = treeNode.getFirstChild();
        int fatherChildren = 0;

        while (currentFatherChild != null) {
            fatherChildren++;
            currentFatherChild = currentFatherChild.next;
        }

        currentFatherChild = treeNode.getFirstChild();

        while (currentFatherChild != null) {
            Node currentChild = currentFatherChild.child.getFirstChild();

            double inheritedLands = 0;
    
            if (fatherChildren != 0)
                inheritedLands = treeNode.lands / fatherChildren;
            
            currentFatherChild.child.lands += inheritedLands;

            int numberOfChildren = 0;
    
            while (currentChild != null) {
                numberOfChildren++;
                currentChild = currentChild.next;
            }

            if (numberOfChildren == 0) {
                if (height > warriorWithMoreLands.height || 
                    (
                        height == warriorWithMoreLands.height &&
                        currentFatherChild.child.lands > 
                        this.warriorWithMoreLands.lands
                    )
                ) {
                    warriorWithMoreLands = currentFatherChild.child;
                    warriorWithMoreLands.height = height;
                }
            }

            calculateLandsHelper(currentFatherChild.child, height + 1);

            currentFatherChild = currentFatherChild.next;
        }
    }

    private Node appendHelper(Node node, TreeNode treeNode) {
        if (node == null)
            return new Node(treeNode);

        node.next = appendHelper(node.next, treeNode);

        return node;
    }

    public TreeNode findHelper(TreeNode treeNode, String warriorName) {
        if (treeNode == null)
            return null;

        if (
            treeNode.warriorName != null && 
            treeNode.warriorName.equals(warriorName)
        ) {
            return treeNode;
        }

        Node currentChild = treeNode.getFirstChild();

        while (currentChild != null) {
            TreeNode aux = findHelper(currentChild.child, warriorName);

            if (aux != null)
                return aux;

            currentChild = currentChild.next;
        }

        return null;
    }

    public void insert(
        String fatherName, 
        String childName, 
        int childLands
    ) {
        TreeNode treeNode = findHelper(this.firstWarrior, fatherName);
        
        if (treeNode != null) 
            treeNode.firstChild = appendHelper(
                treeNode.firstChild, 
                new TreeNode(childName, childLands)
            );
    }

}