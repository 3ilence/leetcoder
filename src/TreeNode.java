import java.sql.SQLOutput;
import java.util.Stack;
//树的遍历
public class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode() {}
    TreeNode(int val) { this.val = val; }
    TreeNode(int val, TreeNode left, TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }

    /*前序遍历  中左右*/
    public static void preOrderIteration(TreeNode head) {
        if (head == null) {
            return;
        }
        Stack<TreeNode> stack = new Stack<>();
        stack.push(head);
        while (!stack.isEmpty()) {
            TreeNode node = stack.pop();
            System.out.println(node.val + " ");;
            if(node.right != null)
                stack.push(node.right);
            if(node.left != null) {
                stack.push(node.left);
            }
        }
        return;
    }
    /*中序遍历  左中右*/
    public static void inOrderIteration(TreeNode head) {
        if (head == null) {
            return;
        }
        TreeNode cur = head;
        Stack<TreeNode> stack = new Stack<>();
        while (!stack.isEmpty() || cur != null) {
            //每次都会遍历直到到达最左子树
            while (cur != null) {
                stack.push(cur);//左子树根节点入栈
                cur = cur.left;//不断深入遍历左节点
            }
            //此时栈顶前几个是左节点序列
            TreeNode node = stack.pop();
            System.out.println(node.val + " ");
            //打印当前出栈节点的右子树，这样就是左中右的顺序
            if(node.right != null) {
                cur = node.right;
            }
        }
    }
    /*后序遍历*/
    /*双栈实现*/
    public static void postOrderIteration(TreeNode head) {
        if (head == null) {
            return;
        }

        Stack<TreeNode> stack1 = new Stack<>();
        Stack<TreeNode> stack2 = new Stack<>();
        stack1.push(head);
        while (!stack1.isEmpty()) {
            TreeNode node = stack1.pop();
            stack2.push(node);
            if (node.left != null) {
                stack1.push(node.left);
            }
            if (node.right != null) {
                stack1.push(node.right);
            }
        }
        while (!stack2.isEmpty()) {
            System.out.println(stack2.pop().val + "");
        }
    }
    /*单栈实现*/
    public static void postOrderIteration2(TreeNode head) {
        if (head == null) {
            return;
        }
        TreeNode cur = head;
        Stack<TreeNode> stack = new Stack<>();
        stack.push(head);
        while (!stack.isEmpty()) {
            TreeNode peek = stack.peek();
            if (peek.left != null && peek.left != cur && peek.right != cur) {
                stack.push(peek.left);
            } else if (peek.right != null && peek.right != cur) {
                stack.push(peek.right);
            } else {
                System.out.print(stack.pop().val + " ");
                cur = peek;
            }
        }
    }

}


