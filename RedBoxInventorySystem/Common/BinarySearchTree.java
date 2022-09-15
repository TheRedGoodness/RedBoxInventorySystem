package RedBoxInventorySystem.Common;

//My own version of BinarySearchTree
public class BinarySearchTree<E extends Comparable<E>>  
{
    //local variables 
    Node<E> root; 
    int size;

    //constructor
    public BinarySearchTree()
    {
        root = null;
        size = 0;
    }
    //calls the recursive method 
    public void insert(E object)
    { 
        root = insert(root, object);
    }

    //return the size of the tree
    public int getSize()
    {
        return size;
    }

    //uses recursion to insert a node 
    public Node<E> insert(Node<E> root, E object)
    {
        if(root == null)
        {
            size = size + 1;
            return new Node<>(object); 
        }
        if(object.compareTo(root.element) < 0 )
        {
            root.leftChild = insert(root.leftChild, object);
        }
        else
        {
            root.rightChild = insert(root.rightChild, object);
        }
        return root; 
    }
    //removes a node 
    public boolean remove(E object)
    {
        // Start at the top of the tree
        Node<E> focusNode = root; 
        Node<E> parent = root; 
        // When searching for a Node this will
        // tell us whether to search to the
        // right or left
        boolean isItALeftChild = true; 
        // While we haven't found the Node we will keep looking 
        while(!(focusNode.element.equals(object)))
        {
            parent = focusNode; 
            //sees if we should search left 
            if(object.compareTo(focusNode.element) < 0 )
            {
                //shift to the left 
                isItALeftChild = true; 
                focusNode = focusNode.leftChild; 
            }
            else
            {
                //shift to the right 
                isItALeftChild = false; 
                focusNode = focusNode.rightChild; 

            }
            //node was not found 
            if(focusNode == null)
            {
                return false; 
            }
        }
        //if node does not have a child will delete it 
        if(focusNode.leftChild == null && focusNode.rightChild == null)
        {
            //if root delete it 
            if(focusNode == root)
            {
                root = null; 
            }  
            else if(isItALeftChild)
            {
                parent.leftChild = null; 
            }
            else
            {
                parent.rightChild = null;
            }
          
        }
        // If no right child

        else if ( focusNode.rightChild == null )
        {
            // If focus Node was on the left of parent
            // move the focus Nodes left child up to the
            // parent node
            if(focusNode == root)
            {
                root = focusNode.leftChild;
            }
            else if(isItALeftChild)
            {
                parent.leftChild = focusNode.leftChild;
            }
            // Vice versa for the right child
            else 
            {
                parent.rightChild = focusNode.leftChild;
            }
        }
        else if (focusNode.leftChild == null)
        {
            // If focus Node was on the right of parent
            // move the focus Nodes right child up to the
            // parent node
            if(focusNode == root)
            {
                root = focusNode.rightChild; 
            }
            else if( isItALeftChild)
            {
                parent.leftChild = focusNode.rightChild; 
            }
            // Vice versa for the left child
            else
            {
                parent.rightChild = focusNode.rightChild;
            }
        }
        else
        {
            Node<E> replacementNode = getReplacementNode(focusNode);

            // If the focusNode is root replace root with the replacement
            if(focusNode == root)
            {
                root = replacementNode;
            }
            else if( isItALeftChild)
            {
                parent.leftChild = replacementNode; 
            }
            // Vice versa if it was a right child
            else
            {
                parent.rightChild = replacementNode;  
            }
            replacementNode.leftChild = focusNode.leftChild;
        }
        size = size - 1;
        return true;
    }
    //gets the replacement node and returns it to the remove method to be then removed 
    public Node<E> getReplacementNode(Node<E> replacedNode)
    {
        Node<E> replacementParent = replacedNode; 
        Node<E> replacement = replacedNode; 
        
        Node<E> focusNode = replacedNode.rightChild;

        // While there are no more left children

        while (focusNode != null)
        {
            replacementParent  = replacement; 

            replacement = focusNode; 
            
            focusNode = focusNode.leftChild; 
        }
        // If the replacement isn't the right child
        // move the replacement into the parents
        // leftChild slot and move the replaced nodes
        // right child into the replacements rightChild

        if(replacement != replacedNode.rightChild)
        {
            replacementParent.leftChild = replacement.rightChild; 
            replacement.rightChild = replacedNode.rightChild;    
        }

        return replacement;

    }
    //will call the tree in-order 
    public void inOrderTraverseTree()
    {
        inOrderTraverseTree(root);
    }
    //will call the tree in-order  
    public void inOrderTraverseTree(Node<E> focusNode)
    {
        if(focusNode != null)
        {
            inOrderTraverseTree(focusNode.leftChild);
            System.out.print(focusNode.element.toString());
            inOrderTraverseTree(focusNode.rightChild);
        }
    }

    //search call for the node 
    public E search(E object)
    {
        return search(root, object);
    }
    
    //search for the node and return it 
    public E search(Node<E> root, E object)
    {
        if(root == null )
        {
            return null;
        }
        if(root.element.compareTo(object) == 0)
        {
            return root.element;
        }
        if(object.compareTo(root.element) < 0)
        {
            return search(root.leftChild, object);
        }
        return search(root.rightChild, object);
    }

    //returns the elements in an array 
    public E[] getElements(E[] elements)
    {
        int[] current ={0} ;
        return getElements(elements, root, current);
    }
    //returns the elements in an array 
    public E[] getElements(E[] elements, Node<E> focusNode, int[] current)
    {
        if(!(current[0] < size && current[0] >= 0))
        {
            return elements; 
        }
        
        if(current[0] < size && current[0] >= 0)
        {
            if(focusNode != null)
            {
                getElements(elements, focusNode.leftChild, current);
                current[0] = current[0] + 1;
                elements[current[0]-1] = focusNode.element;
                getElements(elements, focusNode.rightChild, current);
            }
        }
        return elements;
    }

}

//node class 
class Node<E> 
{
    E element;

    Node<E> leftChild;
    Node<E> rightChild;

    Node(E element)
    {
        this.element = element;
    }


}

