
import com.sun.source.tree.Tree;
import org.w3c.dom.css.Counter;

import java.util.*;

public class Solution {
    /* Leetcode剑指offer第三题*/
    /*查找数组中任意一个重复元素*/
        public int findRepeatNumber(int[] nums) {
            Set<Integer> set = new HashSet<>();
            int repeat = -1;
            for(int num : nums) {
                if(!set.add(num)) {
                    repeat = num;
                    break;
                }
            }
            return repeat;
        }
    /*LeetCode剑指offer第四题*/
    /*从有序二维矩阵中查找元素,判断是否含有该元素*/
    /*每一行都从左到右递增，每一列都从上到下递增*/
    public boolean findNumberIn2DArray(int[][] matrix, int target) {
        if(matrix.length == 0 || matrix[0].length == 0)
            return false;
        int column = matrix[0].length - 1;
        int row = 0;
        while(target != matrix[row][column]) {
            if(target < matrix[row][column])
                column--;
            else if(target > matrix[row][column])
                row++;
            else return true;
            if(row >= matrix.length || column < 0)
                return false;
        }
    return true;
    }
    /*LeetCode剑指offer第五题*/
    /*替换给定字符串中的所有空格为"%20"*/
    public String replaceSpace(String s) {
        StringBuilder res = new StringBuilder();
        for(int i = 0; i < s.length(); i++){
            char c = s.charAt(i);
            if(c == ' '){
                res.append("%20");
            }else{
                res.append(c);
            }
        }
        return res.toString();
    }
/*LeetCode剑指Offer第六题*/
    /*输入一个链表的头节点，从尾到头反过来返回每个节点的值（用数组返回）*/
    static class ListNode {
        int val;
        ListNode next;
        ListNode(int x) { val = x; }
        ListNode() {
            super();
        }
    }
    public int[] reversePrint(ListNode head) {
        Stack<ListNode> s = new Stack<>();
        ListNode temp = head;
        while(temp != null) {
            s.push(temp);
            temp = temp.next;
        }

        int size = s.size();
        int[] res = new int[size];
        int i = 0,j = 0;
        for(;i < size;i++) {
            res[i] = s.pop().val;
        }
        return res;
    }
/*LeetCode剑指Offer第七题*/
    /*根据前序遍历和中序遍历结果构造二叉树*/
    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }
    private Map<Integer,Integer> indexMap;
    //递归构建二叉树函数
    public TreeNode myBuildTree(int[] preorder,int[] inorder,int preorder_left,int preorder_right,int inorder_left,int inorder_right) {
        if (preorder_left > preorder_right) {
            return null;
        }
        //前序遍历中的第一个节点就是根节点
        //preorder_root是根节点在前序遍历数组中的下标
        int preorder_root = preorder_left;
        //在中序遍历中定位根节点的下标
        int inorder_root = indexMap.get(preorder[preorder_root]);
        //创建根节点
        TreeNode root = new TreeNode(preorder[preorder_root]);

        //得到左子树中的的节点数目
        int size_left_subtree = inorder_root - inorder_left;
        //递归构造左子树，并连接到根节点
        //先序遍历中【从 左边界+1 开始的 size_left_subtree】个元素就对应了中序遍历中【从 左边界 开始到根节点定位-1】的元素
        root.left = myBuildTree(preorder,inorder,preorder_left + 1,preorder_left+size_left_subtree,inorder_left,inorder_root - 1);
        //递归地构造右子树，并连接到根节点
        //先序遍历中【从 左边界+1+左子树节点数目 开始到 右边界】的元素就对应了中序遍历中【从 根节点定位+1 到 右边界】的元素
        root.right = myBuildTree(preorder,inorder,preorder_left + size_left_subtree+1,preorder_right,inorder_root + 1,inorder_right);
        return root;
    }
    public TreeNode buildTree(int[] preorder,int[] inorder) {
        int n = preorder.length;//
        indexMap = new HashMap<>();
        for(int i = 0; i < n; i++) {
            indexMap.put(inorder[i],i);//key是数组值，value是数组下标
        }
        return myBuildTree(preorder, inorder, 0, n - 1, 0, n - 1);
    }
/*LeetCode剑指offer第九题*/
    /*用两个栈实现一个队列*/
        class CQueue {
        Deque<Integer> stack1;
        Deque<Integer> stack2;

        public CQueue() {
            stack1 = new LinkedList<>();
            stack2  = new LinkedList<>();
        }

        public void appendTail(int value) {
            stack1.push(value);
        }
        //stack1可以看成是一个工具人
        //实际上存储队列的是stack2，但是进队需要先进
        public int deleteHead() {
            if(stack2.isEmpty()) {
                while (!stack1.isEmpty()) {
                    stack2.push(stack1.pop());
                }
            }
            if(stack2.isEmpty()) {
                return -1;
            }
            else {
                return stack2.pop();
            }
        }
    }
/*LeetCode剑指offer第十题*/
    /*斐波那契数列的求解*/
    int[] visit = new int[101];
    //Arrays.fill(visit,-1);为什么这里报错了？？？
    public int fib(int n) {
        if(visit[n] != -1)
            return visit[n];
        if(n == 0)
            visit[0] = 0;
        if(n == 1)
            visit[1] = 1;
        visit[n] =  (fib(n - 1) + fib( n - 2));
        return visit[n] % 1000000007;



    }
    /*numWays方法是和fib方法功能相同的方法*/
    public int numWays(int n) {
        int[] res = new int[101];
        res[0] = 1;
        res[1] = 1;
        if(n >= 2) {
            for(int i = 2;i <= n;i++) {
                res[i] = (res[i - 1] + res[i - 2])%1000000007;
            }
        }
        return res[n];
    }

/*2020.3.1.今日目标做完第十六题*/
/*LeetCode剑指offer第十一题*/
    /*寻找旋转过一次的数组的最小元素的值*/
    public int minArray(int[] numbers) {
        int i = 0, j = numbers.length - 1;
        while (i < j) {
            int m = (i + j) / 2;
            if (numbers[m] > numbers[j]) i = m + 1;
            else if (numbers[m] < numbers[j]) j = m;
            else j--;
        }
        return numbers[i];
    }
/*LeetCode剑指offer第十二题*/
    /*寻找字符矩阵中匹配所给字符串的路径*/
    public boolean exist(char[][] board,String word) {
        char[] words = word.toCharArray();
        for(int i = 0;i < board.length;i++) {
            for(int j = 0;j < board[0].length; j++) {
                if(dfs(board,words,i,j,0))
                    return true;
            }
        }
        return false;
    }

    boolean dfs(char[][] board,char[] word,int i,int j,int k) {
        if(i >= board.length || i < 0|| j >= board[0].length||j<0||board[i][j] != word[k])
            return false;
        if(k == word.length - 1) return true;
        board[i][j] = '\0';
        boolean res = dfs(board,word,i + 1,j,k+1) || dfs(board,word,i - 1,j,k+1) || dfs(board,word,i,j+1,k+1) || dfs(board,word,i,j - 1,k + 1);
        board[i][j] = word[k];
        return res;
    }
/*LeetCode剑指offer第十三题*/
    /*机器人的运动范围*/
    public int movingCount(int m, int n, int k) {
        int res = 0;
        int[][] visit = new int[m][n];
        res += dfs(0,0,m,n,k,visit);
        return res;
    }
    public int sumCount(int n) {
        int sum = 0;
        while(n / 10 >= 1) {
            sum += n % 10;
            n /= 10;
        }
        sum+= n;
        return sum;
    }
    public int dfs(int i,int j,int m,int n,int k,int[][] visit) {
        if( (sumCount(i) + sumCount(j)) > k || i < 0 || i >= n || j < 0 || j >= m )
            return 0;
        if(visit[j][i] == 1)
            return 0;
        visit[j][i] = 1;
        return (1+dfs(i+1,j,m,n,k,visit) + dfs(i-1,j,m,n,k,visit) +dfs(i,j+1,m,n,k,visit)+dfs(i,j-1,m,n,k,visit));
    }
/*剑指offer第十四题*/
    /*切割绳子*/
    boolean[] visited = new boolean[60];
    int[] visit2 = new int[60];
    public int cuttingRope(int n) {
        return maxCutting(n);
    }
    public int max(int a,int b) {
        return Math.max(a, b);
    }
    public int maxCutting(int n) {
        if(n == 1)
            return 1;
        if(n == 2)
            return 1;
        if(visited[n])
            return visit[n];
        int periodMax = 0;
        for(int i = 1;i <= n-1;i++) {
            int temp = max(i * maxCutting(n - i),i * (n - i));
            if(temp > periodMax)
                periodMax = temp;
        }
        visited[n] = true;
        visit[n] = periodMax;
        return periodMax;

    }
/*剑指offer第十五题*/
    /*输入一个无符号整数，输出该整数二进制表示中的1的个数*/
    public int hammingWeight(int n) {
        int res = 0;
        while(n != 0) {
            res++;
            n &= n - 1;
        }
        return res;
    }
/*剑指offer第十六题*/
    /*重写pow函数*/
    public double myPow(double x,int n) {
        if(x == 0) return 0;
        long b = n;
        double res = 1.0;
        if (b < 0) {
            x = 1 / x;
            b = -b;
        }
        while(b > 0) {
            if((b & 1) == 1)
                res *= x;
            x *=x;
            b >>= 1;
        }
        return res;
    }
/*剑指offer第十七题*/
    public int[] printNumbers(int n) {
        int end = (int)Math.pow(10,n) - 1;
        int[] res = new int[end];
        for(int i = 0;i < end;i++) {
            res[i] = i + 1;
        }
        return res;
    }
/*剑指offer第十八题*/
    /*从链表中删除节点*/
    public ListNode deleteNode(ListNode head, int val) {
        ListNode p1 = head,p2;
        if(head.val == val)
            return head.next;
        p2 = head.next;
        while(p2 != null && p2.val != val) {
            p1 = p1.next;
            p2 = p2.next;
        }
        p1.next = p2.next;
        return head;

    }
/*剑指offer第十九题*/

/*剑指offer第二十题*/
    /*判断字符串内容是否为合法数字*/
    public boolean isNumber(String s) {
        if(s == null || s.length() == 0)
            return false;
        boolean isNum = false,isDot = false,ise_or_E = false;//标记
        char[] str = s.trim().toCharArray();//删除字符串头尾的空格
        for(int i = 0;i < str.length;i++) {
            if(str[i] >= '0' && str[i] <= '9')
                isNum = true;
            else if(str[i] == '.') {
                if(isDot || ise_or_E)
                    return false;
                isDot = true;
            }
            else if(str[i] == 'e' || str[i] == 'E') {
                if(!isNum || ise_or_E)
                    return false;
                ise_or_E = true;
                isNum = false;//重置isNum，因为'e'或'E'之后也必须接整数

            }
            else if(str[i] == '-' || str[i] == '+') {
                if(i != 0 && str[i - 1] != 'e' && str[i-1] != 'E')
                    return false;//正负号只可能出现在第一个位置，或者出现在幂符号后面一个位置
            }
            else return false;//其他情况均为不合法
        }
        return isNum;
    }
    //有限状态自动机解法
    public boolean isNumber2(String s) {
        Map[] states = {
                new HashMap<>() {{ put(' ', 0); put('s', 1); put('d', 2); put('.', 4); }}, // 0.
                new HashMap<>() {{ put('d', 2); put('.', 4); }},                           // 1.
                new HashMap<>() {{ put('d', 2); put('.', 3); put('e', 5); put(' ', 8); }}, // 2.
                new HashMap<>() {{ put('d', 3); put('e', 5); put(' ', 8); }},              // 3.
                new HashMap<>() {{ put('d', 3); }},                                        // 4.
                new HashMap<>() {{ put('s', 6); put('d', 7); }},                           // 5.
                new HashMap<>() {{ put('d', 7); }},                                        // 6.
                new HashMap<>() {{ put('d', 7); put(' ', 8); }},                           // 7.
                new HashMap<>() {{ put(' ', 8); }}                                         // 8.
        };
        int p = 0;
        char t;
        for(char c : s.toCharArray()) {
            if(c >= '0' && c <= '9') t = 'd';
            else if(c == '+' || c == '-') t = 's';
            else if(c == 'e' || c == 'E') t = 'e';
            else if(c == '.' || c == ' ') t = c;
            else t = '?';
            if(!states[p].containsKey(t)) return false;
            p = (int)states[p].get(t);
        }
        return p == 2 || p == 3 || p == 7 || p == 8;
    }

/*剑指offer第二十一题*/
    /*改变数组元素顺序*/
    /*左右双指针*/
    public int[] exchange(int[] nums) {
        int left = 0,right = nums.length - 1,temp = 0;
        while(left < right) {
            while( left < right && (nums[left] % 2 == 1))
                left++;
            while( right > left && (nums[right] % 2 == 0))
                right--;
            temp = nums[right];
            nums[right] = nums[left];
            nums[left] = temp;
        }
        return nums;
    }
    /*快慢双指针*/
    public int[] exchange2(int[] nums) {
        int slow = 0,fast = 0;
        while( fast < nums.length){
            if((nums[fast]&1)==1)
                swap(nums,slow++,fast);
            fast++;
        }
        return nums;
    }
    public void swap(int[] nums,int a,int b){
        int temp = nums[a];
        nums[a] = nums[b];
        nums[b] =temp;
    }
/*剑指offer第二十二题*/
    /*返回链表中倒数第k个元素*/
    public ListNode getKthFromEnd(ListNode head, int k) {
        ListNode former = head, latter = head;
        for(int i = 0; i < k; i++)
            former = former.next;
        while(former != null) {
            former = former.next;
            latter = latter.next;
        }
        return latter;
    }
/*剑指offer第二十三题*/

/*剑指offer第二十四题*/
    /*反转链表*/
    public ListNode reverseList2(ListNode head) {
        ListNode cur = head, pre = null;
        while(cur != null) {
            ListNode tmp = cur.next; // 暂存后继节点 cur.next
            cur.next = pre;          // 修改 next 引用指向
            pre = cur;               // pre 暂存 cur
            cur = tmp;               // cur 访问下一节点
        }
        return pre;
    }
/*剑指offer第二十五题*/
    /*两个链表的合并，要求节点值递增*/
    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        ListNode dum = new ListNode(0), cur = dum;
        while(l1 != null && l2 != null) {
            if(l1.val < l2.val) {
                cur.next = l1;
                l1 = l1.next;
            }
            else {
                cur.next = l2;
                l2 = l2.next;
            }
            cur = cur.next;
        }
        cur.next = l1 != null ? l1 : l2;
        return dum.next;
    }
/*剑指offer第二十六题*/
    /*判断B是否是A的子结构树*/
    public boolean isSubStructure(TreeNode A, TreeNode B) {
        if(B == null || A == null)
            return false;
        return Trace(A,B,B,A);
    }
    public boolean Trace(TreeNode A, TreeNode B,TreeNode initial_B,TreeNode initial_A) {
        if(A == null && B == null)
            return true;
        else if(B == null)
            return true;
        if(A == null)
            return false;
        if(A.val == B.val) {
            return (Trace(A.left,B.left,initial_B,initial_A) && Trace(A.right,B.right,initial_B,initial_A));
        }
        else {
            return (Trace(initial_A.left,initial_B,initial_B,initial_A.left) || Trace(initial_A.right,initial_B,initial_B,initial_A.right));
        }
    }
/*剑指offer第二十七题*/
    /*构建镜像二叉树*/
    public TreeNode mirrorTree(TreeNode root) {
        if(root == null) return null;
        Stack<TreeNode> stack = new Stack<>() {{ add(root); }};
        while(!stack.isEmpty()) {
            TreeNode node = stack.pop();
            if(node.left != null) stack.add(node.left);
            if(node.right != null) stack.add(node.right);
            TreeNode tmp = node.left;
            node.left = node.right;
            node.right = tmp;
        }
        return root;
    }
/*剑指offer第二十八题*/
    /*判断树是否对称*/
    public boolean isSymmetric(TreeNode root) {
        if(root == null)
            return true;
        return preIterator(root.left,root.right);
    }
    public boolean preIterator(TreeNode root1,TreeNode root2) {
        if(root1 == null && root2 == null)
            return true;
        else if(root1 == null || root2 == null)
            return false;
        if(root1.val == root2.val)
            return preIterator(root1.right,root2.left) && preIterator(root1.left,root2.right);
        return false;
    }
/*剑指offer第二十九题*/
    /*顺时针打印矩阵*/
    public int[] spiralOrder(int[][] matrix) {
        if(matrix.length == 0)
            return new int[0];
        int l = 0,r = matrix[0].length - 1,t = 0,b = matrix.length - 1,x = 0;
        int[] res = new int[(r + 1) * (b + 1)];
        while(true) {
            for(int i = 1;i <= r;i++)
                res[x++] = matrix[t][i];//left to right
            if(++t > b)
                break;
            for(int i = t;i <= b;i++)
                res[x++] = matrix[i][r];
            if(l > --r)
                break;
            for(int i = r;i >= 1;i--)
                res[x++] = matrix[b][i];//right to left
            if(t > --b)
                break;
            for(int i = b;i >= t;i--)
                res[x++] = matrix[i][l];
            if(++l > r)
                break;
        }
        return res;
    }
/*剑指offer第三十题*/
    /**/
    class MinStack {
        Stack<Integer> A,B;
        public MinStack() {
            A = new Stack<>();
            B = new Stack<>();
        }
        public void push(int x) {
            A.add(x);
            if(B.empty() || B.peek() >= x)
                B.add(x);
        }
        public void pop() {
            if(A.pop().equals(B.peek()))
                B.pop();
        }
        public int top() {
            return A.peek();
        }
        public int min() {
            return B.peek();
        }
    }
/*剑指offer第三十一题*/
    public boolean validateStackSequences(int[] pushed,int[] popped) {
        Stack<Integer> stack = new Stack<>();
        int i = 0;
        for(int num: pushed) {
            stack.push(num);
            while(!stack.isEmpty() && stack.peek() == popped[i]) {
                stack.pop();
                i++;
            }
        }
        return stack.isEmpty();
    }
/*剑指offer第三十二题*/
//二叉树的层序遍历
    public int[] levelOrder2(TreeNode root) {
    if(root == null)
        return new int[0];
    Queue<TreeNode> queue = new LinkedList<>();
    ArrayList<Integer> list = new ArrayList<>();
    queue.offer(root);
    while(!queue.isEmpty()) {
        TreeNode temp = queue.poll();
        list.add(temp.val);
        if(temp.left != null)
            queue.offer(temp.left);
        if(temp.right != null)
            queue.offer(temp.right);
    }
    int[] res = new int[list.size()];
    int j = 0;
    for(int i = 0;i < list.size();i++)
        res[i] = list.get(i);
    return res;
}

/*剑指offer第三十三题*/
//    1、递归方法，判断序列是否为二叉搜索树的后序遍历结果
//    开始我想用copyOfRange方法生成新数组作为参数传递，但是这种做法没有把上下界作为参数来得简单
//    另外注意copyOfRange方法是包含下界不包含上界的
    public boolean verifyPstorder(int[] postorder) {
        return recur(postorder,0,postorder.length - 1);
    }
    boolean recur(int[] postorder,int i,int j) {
        if(i >= j)
            return true;
        int p = i;
        while(postorder[p++] < postorder[j]);
        p--;
        int m = p;
        while(postorder[p] > postorder[j])
            p++;
        return p==j && recur(postorder,i,m - 1) && recur(postorder,m,j - 1);
    }
//    2、辅助单调栈解法，很巧妙的思路
//  我们从后往前遍历该序列，如果序列正确的话，那么我们就是按根，右，左的顺序遍历某二叉搜索树
//    当ri > ri+1时，ri一定是节点ri+1的右子节点；当ri < ri+1时，ri一定是某节点root的左子节点
//    且root为节点ri+1,ri+2,...,rn中值大于ri且最接近ri的节点
//    当遍历时遇到递减节点ri<ri+1，如果是二叉搜索树，必有0< x < i，rx < root
//    rx有两种可能：1、是ri左或右子树。2、是root 的父节点或更高层父节点的左子树的各节点
//    好难理解，放弃，mark
/*剑指offer第三十四题*/

/*剑指offer第三十五题*/
//class Node {
//    int val;
//    Node next;
//    Node random;
//
//    public Node(int val) {
//        this.val = val;
//        this.next = null;
//        this.random = null;
//    }
//}
//    public Node copyRandomList(Node head) {
//        if(head == null)
//            return null;
//        Node cur = head;
//        Map<Node,Node> map = new HashMap<Node,Node>();
//        while(cur != null) {
//            map.put(cur,new Node(cur.val));
//            cur = cur.next;
//        }
//        cur = head;
//        while(cur != null) {
//            map.get(cur).next = map.get(cur.next);
//            map.get(cur).random = map.get(cur.random);
//            cur = cur.next;
//        }
//        return map.get(head);
//    }
//    //方法二：拼接拆分
//    public Node copyRandomList2(Node head) {
//        if(head == null)
//            return null;
//        Node cur = head;
//        //构建拼接链表
//        while(cur != null) {
//            Node tmp = new Node(cur.val);
//            tmp.next = cur.next;
//            cur.next = tmp;
//            cur = tmp.next;
//        }
//        //构建各新节点的random指向
//        cur = head;
//        while(cur != null) {
//            if(cur.random != null)
//                cur.next.random = cur.random.next;
//            cur = cur.next.next;
//        }
//        //拆分两链表
//        cur = head.next;
//        Node pre = head,res = head.next;
//        while(cur.next != null) {
//            pre.next = pre.next.next;
//            cur.next = cur.next.next;
//            pre = pre.next;
//            cur = cur.next;
//        }
//        pre.next = null;
//        return res;
//    }
/*剑指offer第三十六题*/
class Node {
    public int val;
    public Node left;
    public Node right;

    public Node() {}

    public Node(int _val) {
        val = _val;
    }

    public Node(int _val,Node _left,Node _right) {
        val = _val;
        left = _left;
        right = _right;
    }
}
    Node pre,head;
    public Node treeToDoublyList(Node root) {
        if(root == null)
            return null;
        dfs(root);
        head.left = pre;
        pre.right = head;
        return head;
    }
    /*每次都是增加pre.right和cur.left，如果pre == null 说明当前是最左节点*/
    void dfs(Node cur) {
        if(cur == null)
            return;
        dfs(cur.left);
        if(pre != null)
            pre.right = cur;
        else
            head = cur;
        cur.left = pre;
        pre = cur;dfs(cur.right);
    }

    /*剑指offer第三十七题*/
        public String serialize(TreeNode root) {
            if(root == null)
                return "[]";
            StringBuilder res = new StringBuilder("[");
            Queue<TreeNode> queue = new LinkedList<>()  {{add(root);}};
            while(!queue.isEmpty()) {
                TreeNode node = queue.poll();
                if(node != null) {
                    res.append(node.val + ',');
                    queue.add(node.right);
                    queue.add(node.right);
                }
                else res.append("null,");
            }
            res.deleteCharAt(res.length() - 1);
            res.append("]");
            return res.toString();
        }
        public TreeNode deserialize(String data) {
            if(data.equals("[]"))
                return null;
            String[] vals = data.substring(1,data.length() - 1).split(",");
            TreeNode root = new TreeNode(Integer.parseInt(vals[0]));
            Queue<TreeNode> queue = new LinkedList<>() {{add(root);}};
            int i = 1;
            while(! queue.isEmpty()) {
                TreeNode node = queue.poll();
                if(! vals[i].equals("null")) {
                    node.left = new TreeNode(Integer.parseInt(vals[i++]));
                    queue.add(node.left);
                }
                if(! vals[i].equals("null")) {
                    node.right = new TreeNode(Integer.parseInt(vals[i++]));
                    queue.add(node.left);
                }
            }
            return root;
        }
    /*剑指offer第三十八题*/
//    字符串的全排列：dfs+剪枝
    List<String> res = new LinkedList<>();
    char[] c;
    public String[] permutation(String s) {
        c = s.toCharArray();
        dfs(0);
        return res.toArray(new String[res.size()]);
    }
    void dfs(int x) {
        if(x == c.length - 1) {
            res.add(String.valueOf(c));
            return;
        }
        HashSet<Character> set = new HashSet<>();
        for(int i = x;i < c.length;i++) {
            //剪枝
            //为排除重复方案，需在固定某位字符时，保证 “每种字符只在此位固定一次” ，即遇到重复字符时不交换，直接跳过
            //这一步非常重要，也很难理解
            if(set.contains(c[i]))
                continue;
            set.add(c[i]);
            swap(i,x);
            dfs(x+1);
            //记得交换回去
            swap(i,x);
        }
    }
    void swap(int a,int b) {
        char tmp = c[a];
        c[a] = c [b];
        c[b] = tmp;
    }
    /*剑指offer第三十九题*/
    /*剑指offer第四十题*/
    //返回数组最小的k个数
    //这是普通的快排
    void quickSort(int[] arr,int leftIndex,int rightIndex) {
        if(leftIndex >= rightIndex)
            return;
        int key = arr[leftIndex];
        int left = leftIndex;
        int right = rightIndex;
        while(left < right) {
            while(right > left && arr[right] >= key) {
                right--;
            }
            arr[left] = arr[right];
            while(left < right && arr[left] <= key) {
                left++;
            }

            arr[right] = arr[left];
        }
        arr[left] = key;
        quickSort(arr,leftIndex,left - 1);
        quickSort(arr,left+1,rightIndex);
    }
    //这是基于快排的数组划分
    private int[] quickSort(int[] arr,int k, int leftIndex,int rightIndex) {
        int left = leftIndex, right = rightIndex;
        int key = arr[leftIndex];
        while(left < right) {
            while (left < right && arr[right] >= key)
                right--;
            while (left < right && arr[left] <= key)
                left++;
            swap(arr,left,right);
        }
        swap(arr,left,leftIndex);
        if(left > k)
            return quickSort(arr,k,leftIndex,left - 1);
        if(left < k)
            return quickSort(arr,k,left+1,rightIndex);
        return Arrays.copyOf(arr,k);
    }

    public int[] getLeastNumbers(int[] arr, int k) {
        if(k >= arr.length)
            return arr;
        return quickSort(arr,k,0,arr.length - 1);
    }
    /*剑指offer第四十题*/
    /*剑指offer第四十二题*/



    /*剑指offer第四十四题*/
        public int findNthDigit(int n) {
            int digit = 1;
            long start = 1;
            long count = 9;
            while (n > count) {
                n -= count;
                start *= 10;
                digit++;
                count = digit * start * 9;
            }
            long num = start + (n - 1) / digit;//FIXME 这里的n-1很重要
            //如果不-1，一旦n整除了digit，说明要找的那一位正是上个数的最后一位，所以需要-1
            //如果n除digit余1，说明是当前数的第一位
            return Long.toString(num).charAt((n - 1) % digit) - '0';
        }
    /*剑指offer第四十六题*/
    //动态规划或者递归
    public int translateNum(int num) {
        String str = String.valueOf(num);
        int[] visit = new int[str.length() + 1];
        for (int i = 1; i <= str.length(); i++) {
            if (i == 1) {
                visit[i] = 1;
                continue;
            }
            if (i == 2) {
                if (str.substring(0,2).compareTo("10") >= 0 && str.substring(0,2).compareTo("25") <= 0)
                    visit[i] = 2;
                else
                    visit[i] = 1;
                continue;
            }
            if(str.substring(i - 2,i).compareTo("25") <= 0 && str.charAt(i - 2 ) != '0')
                visit[i] = visit[i - 1] + visit[i - 2];
            else
                visit[i] = visit[i - 1];
        }
        return visit[str.length()];
    }

    /*剑指offer第四十七题*/
    //向右或向下走：动态规划
    public int maxValue(int[][] grid) {
        int res[][] = new int[grid.length][grid[0].length];
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++  ) {
                if (i == 0 && j == 0) {
                    res[i][j] = grid[0][0];
                } else if (i == 0) {
                    res[i][j] = res[i][j-1] + grid[i][j];
                } else if (j == 0) {
                    res[i][j] = res[i-1][j] + grid[i][j];
                } else {
                    res[i][j] = res[i-1][j] >= res[i][j-1] ?
                            res[i-1][j] + grid[i][j]:res[i][j-1] + grid[i][j];
                }
            }
        }
        return res[grid.length - 1][grid[0].length -1];
    }
    /*剑指offer第四十八题*/
    //字符串最长子字符串的长度：动态规划
    public int lengthOfLongestSubstring(String s) {
        Map<Character,Integer> map = new HashMap<>();
        //res[i]表示以第i个字符结尾的最长子字符串的长度
        int max = 0;
        int tmp = 0;
        for (int i = 0; i < s.length(); i++) {
            //如果没找到Key就会返回default = -1
            int j = map.getOrDefault(s.charAt(i),-1);
            map.put(s.charAt(i),i);
            tmp = tmp < i - j?tmp + 1:i - j;
            if (tmp > max)
                max = tmp;
        }
        return max;
    }
    /*剑指offer第四十九题*/
    //求第n个丑数
    public int nthUglyNumber(int n) {
        int[] dp = new int[1700];
        dp[0] = 1;
        int pos = 1;
        int a = 0,b = 0,c = 0;
        int nexta = dp[0]*2;
        int nextb = dp[0]*3;
        int nextc = dp[0]*5;
        for (;pos <= n - 1; pos++) {
            dp[pos] = min(nexta,nextb,nextc);
            if (dp[pos] == nexta) {
                a++;
                nexta = dp[a] * 2;
            }
            if (dp[pos] == nextb) {
                b++;
                nextb = dp[b] * 3;
            }
            if(dp[pos] == nextc){
                c++;
                nextc = dp[c] * 5;
            }
        }
        return dp[n-1];
    }
    public int min(int a,int b,int c) {
        if (a < b) {
            return Math.min(a,c);
        } else {
            return Math.min(b,c);
        }
    }
    /*剑指offer第五十题*/

    public char firstUniqChar(String s) {
        Map<Character, Boolean> map = new LinkedHashMap<>();
        char[] arr = s.toCharArray();
        for (char c : arr) {
            map.put(c, !map.containsKey(c));
        }
        for (Map.Entry<Character,Boolean> entry : map.entrySet()) {
            if (entry.getValue() == true)
                return entry.getKey();
        }
        return ' ';
    }
    /*剑指offer第五十一题*/
    /**
     * 求数组中逆序对的个数
     * @param nums 数组
     * @return 逆序对个数
     */
    int[] nums, tmp;
    public int reversePairs(int[] nums) {
        this.nums = nums;
        tmp = new int[nums.length];
        return mergeSort(0, nums.length - 1);
    }

    private int mergeSort(int l, int r) {
        //终止条件
        if (l >= r)
            return 0;
        //递归划分
        int m = (l + r) / 2;
        int res = mergeSort(l, m) + mergeSort(m + 1, r);
        //合并阶段
        int i = l, j = m + 1;
        for (int k = l; k <= r; k++)
            tmp[k] = nums[k];
        for (int k = l; k <= r; k++) {
            if (i == m + 1)
                nums[k] = tmp[j++];
            else if (j == r + 1 || tmp[i] <= tmp[j])
                nums[k] = tmp[i++];
            else {
                nums[k] = tmp[j++];
                res += m - i + 1;
            }
        }
        return res;
    }
    /*剑指offer第五十五-1题*/
    /*求树深度：层序遍历*/
    public int maxDepth(TreeNode root) {
        if (root == null)
            return 0;
        Queue<TreeNode> queue = new LinkedList<>();
        Queue<TreeNode> tmp;
        queue.offer(root);
        int depth = 0;
        while (!queue.isEmpty()) {
            tmp = new LinkedList<>();
            for (TreeNode node : queue) {
                if (node.left != null)
                    tmp.add(node.left);
                if (node.right != null)
                    tmp.add(node.right);
            }
            queue = tmp;
            depth++;
        }
        return depth;
    }
    /*剑指offer第五十五-2题*/
    /*判断一棵树是否是平衡树*/
    public boolean isBalanced(TreeNode root) {
        return recur(root) != -1;
    }

    /**
     * 通过后序遍历判断一棵树是否是平衡树
     * @param root 树的根节点
     * @return 平衡树返回0，否则返回-1
     */
    private int recur(TreeNode root) {
        if (root == null)
            return 0;
        int left = recur(root.left);
        if (left == -1)
            return -1;
        int right = recur(root.right);
        if (right == -1)
            return -1;
        return Math.abs(left - right) < 2 ? Math.max(left, right) + 1 : -1;
    }

    /**
     * 剑指offer第56-1题
     * 一个整型数组 nums 里除两个数字之外，其他数字都出现了两次
     * 解法为将数组分为两组分别异或运算，分组依据为x和y中有某一位不相同
     * 数组nums中该位和x相同的分一组，和y不同的分一组
     * @param nums 输入数组
     * @return 找到那两个只出现了一次的数字
     */
    public int[] singleNumbers(int[] nums) {
        int x = 0, y = 0, n = 0, m = 1;
        /*首先对整个数组作异或，得到的结果就是这两个数的异或结果*/
        for (int num : nums)
            n ^= num;
        /*因为这两个数不等，所以n的二进制表示中必然有一位为1*/
        while ((n & m) == 0)
            m <<= 1;
        /*我们通过m来找到某一位为1，则有a & m == 1, b & m != 1*/
        /*其中a，b是我们要得到的答案*/
        /*举个例子，a=1001和b=0001，那么m=1000，当然a和b可能有多位不同，但是我们只需要根据其中的一位来对原数组进行划分即可*/
        /*通过m将原数组分为两组，那么问题就转换为找到数组中唯一一个只出现一次的数*/
        /*再对每一组分别异或，得到的就是答案*/
        for (int num : nums) {
            if ((num & m) != 0)
                x ^= num;
            else
                y ^= num;
        }
        return new int[] {x, y};
    }

    /**
     * 剑指offer第56-2题
     * 在一个数组 nums 中除一个数字只出现一次之外，其他数字都出现了三次。请找出那个只出现一次的数字。
     * 解法为计算二进制每一位上的数字和，并对该数字和对3取余，得到的就是那个只出现一次的数
     * @param nums 输入数组
     * @return 数组中只出现了一次的那个数
     */
    public int singleNumber(int[] nums) {
       /*int[] counts = new int[32];
        for (int num : nums) {
            for (int j = 0; j < 32; j++) {
                counts[j] += num & 1;
                num >>>= 1;
            }
        }
        int res = 0, m = 3;
        for (int i = 0; i < 32; i++) {
            res <<= 1;
            res |= counts[31 - i] % m;
        }
        return res;*/
        /*有限状态自动机*/
        /*感觉题解在用数电知识写代码*/
        int ones = 0, twos = 0;
        for (int num : nums) {
            ones = ones ^ num & ~twos;
            twos = twos ^ num & ~ones;
        }
        return ones;
    }

    /**
     * 剑指offer第57题其1
     * 对撞双指针解法，注意这里使用双指针是可行的，不会遗漏正确解
     * @param nums 递增数组
     * @param target 目标和
     * @return
     */
    public int[] twoSum2(int[] nums, int target) {
        int res = 0;
        int left = 0, right = nums.length - 1;
        while (left < right) {
            if (nums[left] + nums[right] > target)
                right--;
            else if (nums[left] + nums[right] < target)
                left++;
            else
                return new int[]{nums[left], nums[right]};
        }
        return null;
    }

    /**
     * 剑指offer第57题其二，解法滑动窗口，注意点是有参toArray()方法的使用
     * @param target
     * @return
     */
    public int[][] findContinuousSequence(int target) {
        int i = 1, j = 2, s = 3;
        List<int[]> res = new ArrayList<>();
        while(i < j) {
            if(s == target) {
                int[] ans = new int[j - i + 1];
                for(int k = i; k <= j; k++)
                    ans[k - i] = k;
                res.add(ans);
            }
            if(s >= target) {
                s -= i;
                i++;
            } else {
                j++;
                s += j;
            }
        }
        return res.toArray(new int[0][]);
    }

    /**
     * 剑指offer第58题其1，解法使用滑动窗口或者使用正则表达式"[\\s]"进行单词的分割
     * @param s s
     * @return res
     */
    public static String reverseWords2(String s) {
        Stack<String> stack = new Stack<>();
        int last = -1;//上一个空格的位置
        int i = 0;
        while(i < s.length()) {
            if (s.charAt(i) == ' ') {
                if ((i - last) != 1) {
                    stack.push(s.substring(last + 1,i));
                    //System.out.println("上一个空格位置：" + last + "，这一个空格位置：" + i + "，结果字符串为：" + s.substring(last,i));
                }
                last = i;
            }
            i++;
        }
        //System.out.println("上一个空格位置：" + last + "，字符串尾位置：" + i + "，结果字符串为：" + s.substring(last,i));
        if ((i - last) != 1)
            stack.push(s.substring(last+1, i));
        StringBuilder res = new StringBuilder();
        //System.out.println(stack.size());
        while (!stack.isEmpty()) {
            res.append(stack.pop());
            if (!stack.isEmpty())
                res.append(" ");
        }
        return res.toString();
    }

    /**
     * 剑指offer第58题其2
     * @param s 待处理的字符串
     * @param n 需要移到末尾的串长度
     * @return 处理结果字符串
     */
    public String reverseLeftWords(String s, int n) {
        char[] ch = s.toCharArray();
        reverse(ch, 0, n - 1);
        reverse(ch, n, ch.length - 1);
        reverse(ch, 0, ch.length - 1);
        return new String(ch);
    }

    /**
     * 剑指offer第59题其1：求滑动窗口内的最大值
     * @param nums 数组
     * @param k 窗口大小
     * @return 移动过程中的最大值构成的数组
     */
    public int[] maxSlidingWindow(int[] nums, int k) {
        if(nums.length == 0 || k == 0) return new int[0];
        int[] res = new int[nums.length + 1 - k];
        Deque<Integer> deque = new LinkedList<>();
        for (int i = 0; i < k; i++) {
            while(!deque.isEmpty() && deque.peekLast() < nums[i])
                deque.removeLast();
            deque.addLast(nums[i]);
        }
        res[0] = deque.peekFirst();
        for (int i = k; i < nums.length; i++) {
            //如果滑动窗口第一个就是最大值的话，那么在窗口变动前只需要移除窗口首部元素
            if (deque.peekFirst() == nums[i - k])
                deque.removeFirst();
            while (!deque.isEmpty() && deque.peekLast() < nums[i])
                deque.removeLast();
            deque.addLast(nums[i]);
            res[i - k + 1] = deque.peekFirst();
        }
        return res;
    }

    /**
     * 剑指offer第60题：n个骰子的点数的概率
     * @param n n
     * @return 概率序列
     */
    public double[] dicesProbability(int n) {
        //（5n + 1)
        double[][] dp = new double[n+1][6 * n];
        //dp[1][0] = 0.0 / 6;
        dp[1][0] = 1.0 / 6;
        dp[1][1] = 1.0 / 6;
        dp[1][2] = 1.0 / 6;
        dp[1][3] = 1.0 / 6;
        dp[1][4] = 1.0 / 6;
        dp[1][5] = 1.0 / 6;
        for (int i = 1; i < n; i++) {
            for (int j = i - 1; j < 6 * i; j++) {
                dp[i + 1][j + 1] += dp[i][j]  / 6;
                dp[i + 1][j + 2] += dp[i][j]  / 6;
                dp[i + 1][j + 3] += dp[i][j]  / 6;
                dp[i + 1][j + 4] += dp[i][j]  / 6;
                dp[i + 1][j + 5] += dp[i][j]  / 6;
                dp[i + 1][j + 6] += dp[i][j]  / 6;
            }
        }
        double[] res = new double[5 * n + 1];
        int j = n;
        for (int i = 0; i < 5 * n + 1; i++) {
            res[i] = dp[n][j - 1];
            j++;
        }
        return res;

    }
    /**
     * 剑指offer第61题：扑克牌中的顺子
     * @param nums 序号序列
     * @return 如果是顺子则返回true，否则返回false
     */
    public static boolean isStraight(int[] nums) {
        Arrays.sort(nums);
        int flag = 0;
        if (nums[0] == 0) {
            if (nums.length >= 2 && nums[1] == 0) {
                flag = 2;
            } else {
                flag = 1;
            }
        }
        int i = 0,pre = 0;
        return true;
    }

    /**
     * 剑指offer第62题：圆圈中最后剩下的数字：约瑟夫环问题，动态规划
     * @param n 环的长度
     * @param m 计数
     * @return 返回最后剩下的那个数字
     */
    public int lastRemaining(int n, int m) {
        int[] dp = new int[n];
        dp[0] = 0;
        for (int i = 0; i < n - 1; i++) {
            dp[i+1] = (m-1 + dp[i] + 1) % (i + 2);
        }
        return dp[n - 1];
    }

    /**
     * 剑指offer第65题：不使用加减乘除实现加法运算
     * @param a 加数
     * @param b 加数
     * @return 和
     */
    public int add(int a, int b) {
        while(b != 0) {// 当进位为0的时候跳出
            //一开始的时候是没有进位的，我们把a+b分解为无进位结果+进位，无进位结果用异或运算求出
            //进位由与运算并算数左移一位求出
            //循环求解直至进位为0，此时a即结果
            int c = (a & b) << 1;
            a ^= b;
            b = c;
        }
        return a;
    }

    /**
     * 剑指offer第66题：不使用除法求乘积数组
     * @param a
     * @return
     */
    public int[] constructArr(int[] a) {
        if (a == null || a.length == 0)
            return a;
        int len = a.length;
        int[] left = new int[len];
        int[] right = new int[len];
        left[0] = right[len - 1] = 1;

        for (int i = 1; i < len; i++) {
            left[i] = left[i - 1] * a[i -1];
        }
        for (int i = len - 2; i >= 0; i--) {
            right[i] = right[i + 1] * a[i + 1];
        }

        int[] ans = new int[len];
        for (int i = 0; i < len; i++) {
            ans[i] = left[i] * right[i];
        }
        return ans;
    }


    /*1143.最长公共子序列*/
    public int longestCommonSubsequence(String text1, String text2) {
        int[][] res = new int[text1.length()+1][text2.length()+1];
        //res[i][j]代表长度为i的A串和长度为j的B串的最长公共子序列的长度
        for (int i = 0; i <= text1.length(); i++) {
            res[i][0] = 0;
        }
        for (int j = 0; j < text2.length(); j++) {
            res[0][j] = 0;
        }

        for (int i = 1; i <= text1.length(); i++) {
            for (int j = 1; j <= text2.length(); j++) {
                if (text1.charAt(i - 1) == text2.charAt(j - 1)) {
                    res[i][j] = res[i - 1][j - 1] + 1;
                } else {
                    res[i][j] = Math.max(res[i-1][j],res[i][j-1]);
                }
            }
        }
        return res[text1.length()][text2.length()];
    }
    /*最长公共子串*/
    /*public int longestCommonSubstring(String text1, String text2) {
        int[][] res = new int[text1.length()+1][text2.length()+1];
        //每一个位置表示第一个字符串的第i个字符是否和第二个字符串的第j个位置元素相同，
        // 如果相同，则在res[i-1][j-1]位置上加1
        for (int i = 0; i <= text1.length(); i++) {
            res[i][0] = 0;
        }
        for (int j = 0; j < text2.length(); j++) {
            res[0][j] = 0;
        }
        int max = 0;
        for (int i = 1; i <= text1.length(); i++) {
            for (int j = 1; j <= text2.length(); j++) {
                if (text1.charAt(i - 1) == text2.charAt(j - 1)) {
                    res[i][j] = 1 + res[i - 1][j - 1];
                    if (max < res[i][j])
                        max = res[i][j];
                } else {
                    res[i][j] = 0;
                }
            }
        }
        return max;
    }*/
    public static String longestCommonSubstring(String text1, String text2) {
        int[][] res = new int[text1.length()+1][text2.length()+1];
        //每一个位置表示第一个字符串的第i个字符是否和第二个字符串的第j个位置元素相同，
        // 如果相同，则在res[i-1][j-1]位置上加1
        for (int i = 0; i <= text1.length(); i++) {
            res[i][0] = 0;
        }
        for (int j = 0; j < text2.length(); j++) {
            res[0][j] = 0;
        }
        int max = 0;
        //start和end是最长子串的起始和结束位置下标
        int start = 0, end = 0;
        for (int i = 1; i <= text1.length(); i++) {
            for (int j = 1; j <= text2.length(); j++) {
                if (text1.charAt(i - 1) == text2.charAt(j - 1)) {
                    res[i][j] = 1 + res[i - 1][j - 1];
                    if (max < res[i][j]) {
                        max = res[i][j];
                        end =  i ;
                        start = i - res[i][j];
                    }
                } else {
                    res[i][j] = 0;
                }
            }
        }
        return text1.substring(start, end);
    }

    /**
     *1. 两数之和。哈希表。
     * @param nums 数组
     * @param target 目标和
     * @return 和为target的两元素的下标组成的数组
     */
    public int[] twoSum(int[] nums, int target) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            if (map.containsKey(target - nums[i])) {
                return new int[]{map.get(target - nums[i]), i};
            }
            if (!map.containsKey(nums[i])) {
                map.put(nums[i], i);
            }
        }
        return new int[]{0,0};
    }

    /**
     * 15. 三数之和
     * @param nums nums
     * @return 三元组列表
     */
    public List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        Arrays.sort(nums);

        for (int i = 0; i < nums.length; i++) {
            if (nums[i] > 0) {
                return result;
            }

            if (i > 0 && nums[i] == nums[i - 1]) {
                continue;
            }

            int left = i + 1;
            int right = nums.length - 1;
            while (right > left) {
                int sum = nums[i] + nums[left] + nums[right];
                if (sum > 0) {
                    right--;
                } else if (sum < 0) {
                    left++;
                } else {
                    result.add(Arrays.asList(nums[i], nums[left], nums[right]));

                    while (right > left && nums[right] == nums[right - 1]) right--;
                    while (right > left && nums[left] == nums[left + 1]) left++;

                    right--;
                    left++;
                }
            }
        }
        return result;
    }

    /**
     * 18. 四数之和
     * @param nums nums
     * @param target target
     * @return 四元组列表
     */
    public List<List<Integer>> fourSum(int[] nums, int target) {
        List<List<Integer>> res = new ArrayList<>();
        int len = nums.length;
        if (len < 4) {
            return res;
        }
        Arrays.sort(nums);
        int i = 0, j, left, right;
        for (; i < len - 3;i++) {
            if (i > 0 && nums[i] == nums[i - 1]) {
                continue;//去重
            }
            for (j = i + 1; j < len - 2; j++) {
                left = j + 1;
                right = len - 1;
                int tmp = nums[i] + nums[j];
                if (j-1 > i && j > 1 && nums[j] == nums[j - 1]) {
                    continue;//去重
                }
                while (left < right) {
                    int sum = nums[left] + nums[right];
                    if (sum + tmp < target) {
                        left++;
                    } else if (sum + tmp > target) {
                        right--;
                    } else {
                        res.add(Arrays.asList(nums[i], nums[j], nums[left], nums[right]));
                        while (left < right && nums[left] == nums[left+1] ) {
                            left++;
                        }
                        while (left < right && nums[right] == nums[right - 1]) {
                            right--;
                        }
                        left++;
                        right--;
                    }
                }
            }
        }
        return res;
    }

    /**
     * 19. 删除链表的倒数第 N 个结点。要求只用一趟扫描。
     * @param head 表头
     * @param n n
     * @return 返回新链表表头
     */
    public ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode fast = head, slow = head;
        int i = 0;
        for (; fast != null && i < n; i++) {
            fast = fast.next;
        }
        if (i == n) {//i != n表示链表长度不足n，也就没有所谓的倒数第n个节点
            if (fast == null) {
                return head.next;
            } else {
                while (fast.next != null) {
                    slow = slow.next;
                    fast = fast.next;
                }
                slow.next = slow.next.next;
            }
        }
        return head;
    }

    /**
     * 20.有效的括号
     * @param s 括号字符串
     * @return 是否有效
     */
    public boolean isValid2(String s) {
        if (s.length() == 0)
            return true;
        Stack<Character> stack = new Stack<>();
        int start = 0;
        while (start < s.length() ) {
            if (s.charAt(start) == '(') {
                stack.push(')');
            } else if (s.charAt(start) == '[') {
                stack.push(']');
            } else if (s.charAt(start) == '{') {
                stack.push('}');
            } else {
                if (!stack.isEmpty() && stack.peek() == s.charAt(start)) {
                    stack.pop();
                } else {
                    return false;
                }
            }
            start++;
        }
        return stack.empty();
    }

    /**
     * 21.合并有序链表，学习答案的简洁的代码
     * @param l1 链表1
     * @param l2 链表2
     * @return 合并后的有序链表
     */
    public ListNode mergeTwoLists2(ListNode l1, ListNode l2) {
        ListNode prehead = new ListNode(-1);

        ListNode prev = prehead;
        while (l1 != null && l2 != null) {
            if (l1.val <= l2.val) {
                prev.next = l1;
                l1 = l1.next;
            } else {
                prev.next = l2;
                l2 = l2.next;
            }
            prev = prev.next;
        }

        // 合并后 l1 和 l2 最多只有一个还未被合并完，我们直接将链表末尾指向未合并完的链表即可
        prev.next = l1 == null ? l2 : l1;

        return prehead.next;
    }


    /**
     * 24. 两两交换链表中的节点
     * @param head 表头
     * @return 新表表头
     */
    public ListNode swapPairs(ListNode head) {
        if (head == null) {
            return head;
        }
        ListNode pre = null, tmp, cur = head;
        if (head.next != null) {
            head = head.next;
        }
        while (cur != null && cur.next != null) {
            tmp = cur.next.next;
            if (pre == null) {
                cur.next.next = cur;
                cur.next = tmp;
            } else {
                pre.next = cur.next;
                cur.next.next = cur;
                cur.next = tmp;
            }
            pre = cur;
            cur = tmp;
        }
        return head;
    }

    /**
     * 26. 删除有序数组中的重复项。删除重复项同时保持元素相对顺序不变。
     * @param nums nums
     * @return 最终得到的数组长度
     */
    public int removeDuplicates(int[] nums) {
        int index = 0;//左右分界线
        for (int i = 0; i < nums.length;i++) {
            while (i < nums.length - 1 && nums[i] == nums[i + 1]) {
                i++;
            }
            nums[index++] = nums[i];
        }
        return index;
    }

    /**
     * 27. 移除元素。移除数组指定元素同时保持其余元素相对顺序不变
     * @param nums nums
     * @param val val
     * @return 最终得到的数组长度
     */
    public int removeElement(int[] nums, int val) {
        int slow = 0, fast = 0;//fast指向最近一个非val，slow指向非val的尾部
        int len = nums.length;
        while (fast < len) {
            while (fast < len && nums[fast] == val) {
                fast++;
            }
            if (fast < len) {
                nums[slow++] = nums[fast];
                fast++;
            }
        }
        return slow;
    }

    /**
     * 34.在排序数组中查找元素的第一个和最后一个位置
     * @param nums 数组
     * @param target 目标值
     * @return target在数组中的起始下标组成的长为2的数组
     */
    public int[] searchRange(int[] nums, int target) {
        int length = nums.length;
        if (length == 1 ) {
            return target == nums[0] ? new int[]{0,0} : new int[]{-1,-1};
        }
        int l = 0, r = length - 1;
        int mid = 0;
        int leftBound = -2;//-1是有可能被leftBound取到的，比如[2,2],2，它的左边界就是-1
        //求上边界
        while (l <= r) {
            mid = l + (r - l ) / 2;
            if (nums[mid] < target) {
                l = mid + 1;//对于不等于的情况和一般二分法处理类似
            } else if (nums[mid] > target) {
                r = mid - 1;//对于不等于的情况和一般二分法处理类似
            } else if (nums[mid] == target) {
                //关键在相等的时候更新左边界为当前mid-1，并且缩小范围：r = mid - 1，进一步在mid左边去找target，如果mid已经是最左的target了，那么leftBound也不会在变动
                leftBound = mid - 1;
                r = mid - 1;
            }
        }
        if (leftBound == -2) {
            //说明数组中有target
            return new int[] {-1, -1};
        }
        int rightBound = 0;
        l = 0;
        r = length - 1;
        // 找右边界与找左边界十分类似，唯一不同地方在于nums[mid] == target的时候我们向右缩小范围，因为找最右边的target
        while (l <= r) {
            mid = l + (r - l) / 2;
            if (nums[mid] < target) {
                l = mid + 1;
            } else if (nums[mid] > target) {
                r = mid - 1;
            } else {
                rightBound = mid + 1;
                l = mid + 1;
            }
        }
        return new int[] {leftBound+1, rightBound - 1};
    }

    /**
     * 54. 螺旋矩阵
     * @param matrix
     * @return
     */
    public List<Integer> spiralOrder2(int[][] matrix) {
        int m = matrix.length, n = matrix[0].length;
        List<Integer> res = new ArrayList<>();
        int index = 0;
        int l = 0,r = n - 1,t = 1,d = m - 1;//左右上下边界
        int row = 0, column = 0;
        while (index < m * n) {
            //右
            while (column <= r) {
                res.add(matrix[row][column++]);
                index++;
            }
            column--;row++;
            r--;
            while (row <= d) {
                res.add(matrix[row++][column]);
                index++;
            }
            row--;column--;
            d--;
            while (column >= l) {
                res.add(matrix[row][column--]);
                index++;
            }
            column++;row--;
            l++;
            while (row >= t) {
                res.add(matrix[row--][column]);
                index++;
            }
            row++;column++;
            t++;
        }
        while (index > m * n) {
            res.remove(index - 1);
            index--;
        }
        return res;
    }

    /**
     * 59. 螺旋矩阵 II
     * @param n n
     * @return 返回一个n维矩阵，该矩阵元素为1->n的平方
     */
    public int[][] generateMatrix(int n) {
        int l = 0, r = n - 1, t = 0, b = n - 1;//左右上下边界
        int[][] mat = new int[n][n];
        int num = 1, tar = n * n;
        while(num <= tar){
            for(int i = l; i <= r; i++) mat[t][i] = num++; // left to right.
            t++;
            for(int i = t; i <= b; i++) mat[i][r] = num++; // top to bottom.
            r--;
            for(int i = r; i >= l; i--) mat[b][i] = num++; // right to left.
            b--;
            for(int i = b; i >= t; i--) mat[i][l] = num++; // bottom to top.
            l++;
        }
        return mat;
    }

    /**
     * 59. 螺旋矩阵 II
     * @param n n
     * @return 返回一个n维矩阵，该矩阵元素为1->n的平方
     */
    public int[][] generateMatrix2(int n) {
        int maxNum = n * n;
        int curNum = 1;
        int[][] matrix = new int[n][n];
        int row = 0, column = 0;
        int[][] directions = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}}; // 右下左上
        int directionIndex = 0;
        while (curNum <= maxNum) {
            matrix[row][column] = curNum;
            curNum++;
            // nextRow和nextColumn是如果按照之前方向的下一格。通过下一个格是否符合要求来判断是否需要改变方向
            int nextRow = row + directions[directionIndex][0], nextColumn = column + directions[directionIndex][1];
            if (nextRow < 0 || nextRow >= n || nextColumn < 0 || nextColumn >= n || matrix[nextRow][nextColumn] != 0) {
                directionIndex = (directionIndex + 1) % 4; // 顺时针旋转至下一个方向
            }//这种比我简洁多了
            row = row + directions[directionIndex][0];
            column = column + directions[directionIndex][1];
        }
        return matrix;
    }

    /**
     * 69. x 的平方根
     * @param x
     * @return x的平方根下取整
     */
    public int mySqrt(int x) {
        int l = 0, r = x;
        int mid = 0;
        int ans = -1;
        while (l <= r) {
            mid = l + (r - l) / 2;
            if ((long) mid * mid <= x) {    //这里要将第一个mid强转为long，不然会出现int越界
                ans = mid;
                l = mid + 1;
            } else {
                r = mid - 1;
            }
        }
        return ans;
    }

    /**
     * 76. 最小覆盖子串。滑动窗口+哈希表
     * @param s s
     * @param t t
     * @return 找出s中包含了t中所有字母的最短字串，没有返回""
     */
    public String minWindow(String s, String t) {
        if (s == null || s == "" || t == null || t == "" || s.length() < t.length()) {
            return "";
        }
        // 滑动窗口，固定右边界，收缩时移动左边界
        int len = s.length();
        int l = 0, r = 0;
        int sum = t.length();
        int res = Integer.MAX_VALUE;
        int[] index = new int[2];
        Map<Character, Integer> map = new HashMap<>();
        Set<Character> set = new HashSet<>();
        for (int i = 0; i < t.length(); i++) {
            char a = t.charAt(i);
            set.add(a);
            if (map.containsKey(a)) {
                map.put(a,map.get(a) + 1);
            } else {
                map.put(a, 1);
            }
        }
        //窗口滑动的过程中，如果map的size归0，表示符合
        while (r < len) {
            char a = s.charAt(r);
            if (set.contains(a)) {
                // 对于非目标的字符我们不予处理
                map.put(a, map.get(a) - 1);
                sum--;//sum大于0的时候不可能满足条件
            }
            r++;
            if (sum <= 0) {
                boolean is = true;
                for (Character c : map.keySet()) {
                    if (map.get(c) > 0) {
                        is = false;
                        break;
                    }
                }
                while(is &&l <= r) {
                    a = s.charAt(l);
                    if (set.contains(a) && map.get(a) != 0) {
                        map.put(a, map.get(a) + 1);
                        sum++;
                    } else if (set.contains(a) && map.get(a) == 0) {
                        map.put(a, map.get(a) + 1);
                        sum++;
                        if (res > r - l) {
                            res = r - l;
                            index[0] = l;
                            index[1] = r;
                        }
                        l++;//因为此时该字串的最短字串已经得到了，要去找下一个字串
                        break;
                    }
                    l++;
                }
                //寻找下一个满足条件的字串
            }
        }
        return  res == Integer.MAX_VALUE ? "" : s.substring(index[0], index[1]);
    }

    /**
     * 76. 最小覆盖子串
     * @param s s
     * @param t t
     * @return 找出s中包含了t中所有字母的最短字串，没有返回""
     */
    public String minWindow2(String s, String t) {
        if (s == null || s == "" || t == null || t == "" || s.length() < t.length()) {
            return "";
        }
        //维护两个数组，记录已有字符串指定字符的出现次数，和目标字符串指定字符的出现次数
        //ASCII表总长128
        int[] need = new int[128];
        int[] have = new int[128];

        //将目标字符串指定字符的出现次数记录
        for (int i = 0; i < t.length(); i++) {
            need[t.charAt(i)]++;
        }

        //分别为左指针，右指针，最小长度(初始值为一定不可达到的长度)
        //已有字符串中目标字符串指定字符的出现总频次以及最小覆盖子串在原字符串中的起始位置
        int left = 0, right = 0, min = s.length() + 1, count = 0, start = 0;
        while (right < s.length()) {
            char r = s.charAt(right);
            // 对于t中没有的字符不予处理
            if (need[r] == 0) {
                right++;
                continue;
            }
            //当且仅当已有字符串目标字符出现的次数小于目标字符串字符的出现次数时，count才会+1
            //是为了后续能直接判断已有字符串是否已经包含了目标字符串的所有字符，不需要挨个比对字符出现的次数
            if (have[r] < need[r]) {
                count++;
            }
            //已有字符串中目标字符出现的次数+1
            have[r]++;
            //移动右指针
            right++;
            //当且仅当已有字符串已经包含了所有目标字符串的字符，且出现频次一定大于或等于指定频次
            while (count == t.length()) {
                //挡窗口的长度比已有的最短值小时，更改最小值，并记录起始位置
                if (right - left < min) {
                    min = right - left;
                    start = left;
                }
                char l = s.charAt(left);
                //如果左边即将要去掉的字符不被目标字符串需要，那么不需要多余判断，直接可以移动左指针
                if (need[l] == 0) {
                    left++;
                    continue;
                }
                //如果左边即将要去掉的字符被目标字符串需要，且出现的频次正好等于指定频次，那么如果去掉了这个字符，
                //就不满足覆盖子串的条件，此时要破坏循环条件跳出循环，即控制目标字符串指定字符的出现总频次(count）-1
                if (have[l] == need[l]) {
                    count--;
                }
                //已有字符串中目标字符出现的次数-1
                have[l]--;
                //移动左指针
                left++;
            }
        }
        //如果最小长度还为初始值，说明没有符合条件的子串
        if (min == s.length() + 1) {
            return "";
        }
        //返回的为以记录的起始位置为起点，记录的最短长度为距离的指定字符串中截取的子串
        return s.substring(start, start + min);
    }

    /**
     * 94. 二叉树的中序遍历
     * @param root 根节点
     * @return 中序遍历序列
     */
    public List<Integer> inorderTraversal(TreeNode root) {
        //左根右
        List<Integer> res = new ArrayList<>();
        if (root == null) {
            return res;
        }
        Deque<TreeNode> stack = new ArrayDeque<>();
        while (!stack.isEmpty() || root != null) {
            // 找到最左节点
            while (root != null) {
                stack.push(root);
                root = root.left;
            }
            root = stack.pop();
            res.add(root.val);//root，根记为a。遍历完根下一个是右子树
            //遍历根的右子树时只需直接赋就行，不用管右子树是否为空
            // 假如为空，那么在下一轮迭代中就会直接用栈顶的树，即该根的根，记为b。a是b的左子树，b是a的根
            // 假如不为空，那么在下一轮迭代中会寻找a的右子树的最左节点。重复操作
            root = root.right;
        }
        return res;
    }

    /**
     * 102. 二叉树的层序遍历
     * @param root
     * @return
     */
    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        if (root == null) {
            return res;
        }
        List<TreeNode> list = new ArrayList<>();
        while (list.isEmpty()) {
            List<Integer> tmp = new ArrayList<>();
            List<TreeNode> tmpList = new ArrayList<>();
            while (list.isEmpty()) {
                root = list.get(list.size() - 1);
                list.remove(list.size() - 1);
                tmp.add(root.val);
                if (root.left != null) {
                    tmpList.add(root.left);
                }
                if (root.right != null) {
                    tmpList.add(root.right);
                }

            }
            list = tmpList;
            res.add(tmp);
        }
        return res;
    }

    /**
     * 142. 环形链表 II。约瑟夫环问题。
     * @param head 头指针
     * @return 链表中环的入口
     */
    public ListNode detectCycle(ListNode head) {
        ListNode fast = head, slow = head;//一次两步和一次一步
        while (true) {
            if (fast != null) {
                fast = fast.next;
            }
            if (fast != null) {
                fast = fast.next;
            }
            if (slow != null) {
                slow = slow.next;
            }
            if (fast == null || slow == null) {
                return null;
            }
            if (fast == slow) {
                break;
            }
        }
        fast = head;
        while (fast != slow) {
            fast = fast.next;
            slow = slow.next;
        }
        return slow;
    }

    /**
     * 144. 二叉树的前序遍历
     * @param root 根节点
     * @return 前序遍历序列
     */
    public List<Integer> preorderTraversal(TreeNode root) {
        Deque<TreeNode> stack = new ArrayDeque<>();
        List<Integer> res = new ArrayList<>();
        if (root == null) {
            return res;
        }
        stack.add(root);
        while (!stack.isEmpty()) {
            root = stack.pop();
            res.add(root.val);
            // 右子树先进栈，左子树后进。这样才能左子树先出栈，先处理。最终根左右
            if (root.right != null) {
                stack.push(root.right);
            }
            if (root.left != null) {
                stack.push(root.left);
            }
        }
        return res;
    }

    /**
     *145. 二叉树的后序遍历。左右根，根右左翻转后就是左右根。即将前序稍微变动，最后将列表翻转即可。
     * @param root 根节点
     * @return 后序遍历序列
     */
    public List<Integer> postorderTraversal(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        if (root == null) {
            return res;
        }
        //左右中
        Deque<TreeNode> stack = new ArrayDeque<>();
        stack.push(root);
        while (!stack.isEmpty()) {
            root = stack.pop();
            res.add(root.val);
            if (root.left != null) {
                stack.push(root.left);
            }
            if (root.right != null) {
                stack.push(root.right);
            }
        }
        Collections.reverse(res);
        return res;
    }

    /**
     * 145. 二叉树的后序遍历。左右根，正经流程遍历，非投机取巧，个人觉得有点复杂。
     * @param root 根节点
     * @return 后序遍历序列
     */
    public List<Integer> postorderTraversal2(TreeNode root) {
        List<Integer> res = new ArrayList<Integer>();
        if (root == null) {
            return res;
        }
        Deque<TreeNode> stack = new LinkedList<>();
        TreeNode prev = null;
        while (root != null || !stack.isEmpty()) {
            //和中根类似，还是先找到最左根节点
            while (root != null) {
                stack.push(root);
                root = root.left;
            }
            root = stack.pop();//root为根，因为是左右根，root左子树为空，或者root左子树已经遍历过。
            //如果右子树为空或者已经访问过了才访问根结点否则，需要将该结点再次压回栈中，去访问其右子树
            if (root.right == null || root.right == prev) {
                res.add(root.val);
                prev = root;
                root = null;
            } else {
                stack.push(root);
                root = root.right;
            }
        }
        return res;
    }

    /**
     * 151. 翻转字符串里的单词。正则表达式替换是replaceAll，replace是普通字符序列的替换
     * @param s s
     * @return 翻转后的字符串
     */
    public String reverseWords(String s) {
        s.trim();//先去除首尾空格
        s = s.replaceAll("\\s+", " ");//再将连续的多个空格转换为一个
        char[] ch = s.toCharArray();
        reverse(ch, 0, ch.length - 1);
        for (int i = 0; i < ch.length;) {
            while ( i < ch.length && ch[i] == ' ') {
                i++;
            }
            int j = i;
            while (j < ch.length && ch[j] != ' ') {
                j++;
            }
            reverse(ch, i, j - 1);
            i = j + 1;
        }
        return new String(ch).trim();
    }

    /**
     * 160. 相交链表
     * @param headA headA
     * @param headB headB
     * @return 相交节点
     */
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        int la = 0, lb = 0;
        la = getListLength(headA);
        lb = getListLength(headB);
        if (la < lb) {
            for (int i = 0; i < lb - la; i++) {
                headB = headB.next;
            }
        } else {
            for (int i = 0; i < la - lb; i++) {
                headA = headA.next;
            }
        }
        while (headA != null && headA != headB ) {
            headA = headA.next;
            headB = headB.next;
        }
        return headA == headB ? headA : null;

    }

    /**
     *求链表长度
     * @param node 表头
     * @return 链表长度
     */
    public int getListLength(ListNode node) {
        int l = 0;
        while (node != null) {
            node = node.next;
            l++;
        }
        return l;
    }

    /**
     *160. 相交链表
     * @param headA headA
     * @param headB headB
     * @return 相交节点
     */
    public ListNode getIntersectionNode2(ListNode headA, ListNode headB) {
        if (headA == null || headB == null) {
            return null;
        }
        ListNode A = headA, B = headB;//因为headA和headB后面需要用，所以需要分配临时节点
        while (A != B) {
            A = A == null ? headB : A.next;
            B = B == null ? headA : B.next;
        }
        return A;
    }

    /**
     * 200.岛屿数量
     *
     * @param grid 地形矩阵
     * @return 岛屿数量
     */
    public int numIslands(char[][] grid) {
        int index = 2;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == '1') {
                    traverseIsland(grid, i, j, index++);
                }
            }
        }
        return (index - 2);
    }

    /**
     * 遍历地形矩阵，对属于同一个岛屿的陆地进行标记
     *
     * @param grid 地形矩阵
     * @param i 行号
     * @param j 列号
     * @param index 当前岛屿标记
     */
    public void traverseIsland(char[][] grid, int i, int j, int index) {
        grid[i][j] = (char)('0' + index);
        if (i - 1 >= 0 && grid[i - 1][j] == '1') {
            traverseIsland(grid, i - 1, j, index);
        }
        if (i + 1 < grid.length && grid[i + 1][j] == '1') {
            traverseIsland(grid, i + 1, j, index);
        }
        if (j - 1 >= 0 && grid[i][j - 1] == '1') {
            traverseIsland(grid, i, j - 1, index);
        }
        if (j + 1 < grid[0].length && grid[i][j + 1] == '1') {
            traverseIsland(grid, i, j + 1, index);
        }
    }


    /**
     *202. 快乐数。使用哈希表来判断是否有循环
     * @param n n
     * @return true or false
     */
    public boolean isHappy(int n) {
        Set<Integer> set = new HashSet<>();
        while (n != 1) {
            n = calHappy(n);
            if (!set.add(n)) {
                return false;
            }
        }
        return true;
    }

    /**
     *计算n的各位数的平方和。比如19 = 1*1 + 9*9
     * @param n n
     * @return res
     */
    public int calHappy(int n) {
        int sum = 0;
        while (n > 0) {
            sum += Math.pow(n % 10, 2);
            n = n / 10;
        }
        return sum;
    }

    /**
     * 202. 快乐数。双指针法。快慢双指针终会相遇，如果相遇位置是1，表明符合要求，如果不为1，表明不是。
     * @param n n
     * @return true or false
     */
    public boolean isHappy2(int n) {
        int slowRunner = n;
        int fastRunner = calHappy(n);
        while (fastRunner != 1 && slowRunner != fastRunner) {
            slowRunner = calHappy(slowRunner);
            fastRunner = calHappy(calHappy(fastRunner));
        }
        return fastRunner == 1;
    }

    /**
     * 203. 移除链表元素
     * @param head 表头
     * @param val 将被移除的元素
     * @return 移除完成后链表新表头
     */
    public ListNode removeElements(ListNode head, int val) {
        while (head != null && head.val == val) {
            head = head.next;
        }
        if (head == null) {
            return head;
        }
        ListNode tmp = head;
        while (head.next != null) {
            if (head.next.val == val) {
                head.next = head.next.next;
            } else {
                head = head.next;
            }
        }
        return tmp;
    }

    /**
     * 206. 反转链表
     * @param head 表头
     * @return 反转后的链表表头
     */
    public ListNode reverseList(ListNode head) {
        if (head == null) {
            return null;
        }
        ListNode next = head.next;
        head.next = null;
        ListNode tmp;
        while (next != null) {
            tmp = next.next;
            next.next = head;
            head = next;
            next = tmp;
        }
        return head;
    }

    /**
     * 209. 长度最小的子数组。双指针解法。
     * @param target 目标和
     * @param nums nums
     * @return 连续的和不小于target的子数组长度
     */
    public int minSubArrayLen(int target, int[] nums) {
        int len = nums.length;
        int res = Integer.MAX_VALUE;
        int l = 0, r = 0;
        int sum = nums[0];
        while (l < len) {
            if (sum < target) {
                r++;
                if (r < len)
                    sum += nums[r];
                else
                    break;//从l到后面所有数和都小于target

            } else if (sum >= target) {
                if (res > r - l + 1)
                    res = r - l + 1;
                if (res == 1)
                    return res;//没有比1更小的正数了
                sum -= nums[l];
                l++;
                while (sum >= target) {
                    sum -= nums[r];
                    r--;
                }
            }
        }
        return res == Integer.MAX_VALUE ? 0 : res;

    }

    /**
     * 209. 长度最小的子数组。前缀和+二分。O(nlog(n))
     * @param s 目标和
     * @param nums nums
     * @return 连续的和不小于target的子数组长度
     */
    public int minSubArrayLen2(int s, int[] nums) {
        int n = nums.length;
        if (n == 0) {
            return 0;
        }
        int ans = Integer.MAX_VALUE;
        int[] sums = new int[n + 1];
        // 为了方便计算，令 size = n + 1
        // sums[0] = 0 意味着前 0 个元素的前缀和为 0
        // sums[1] = A[0] 前 1 个元素的前缀和为 A[0]
        // 以此类推
        for (int i = 1; i <= n; i++) {
            sums[i] = sums[i - 1] + nums[i - 1];
        }
        for (int i = 1; i <= n; i++) {
            int target = s + sums[i - 1];//寻找sums[j]，使得sums[j] - sums[i - 1] >= s。
            int bound = Arrays.binarySearch(sums, target);
            if (bound < 0) {
                // 前缀和数组是单调递增的，如果找到了的话肯定是唯一的，返回值也为正。如果返回值为负，也能反推出前缀和数组中第一个大于target的下标
                bound = -bound - 1;
            }
            if (bound <= n) {
                ans = Math.min(ans, bound - (i - 1));
            }
        }
        return ans == Integer.MAX_VALUE ? 0 : ans;
    }

    /**
     * 234.回文链表。要求时间O(n)，空间O(1)
     *
     * @param head 表头，此表头为val不为空的表头
     * @return 如果是回文链表返回true
     */
    public boolean isPalindrome(ListNode head) {
        ListNode Head = new ListNode();
        Head.next = head;
        ListNode slow = Head, fast = Head;
        ListNode pre = Head;
        boolean flag = false;//单数则为true
        while (fast.next != null) {

            fast = fast.next;
            if (fast.next != null) {
                //链表长为双数，且slow位于[left,right]的left
                fast = fast.next;
            } else {
                flag = true;
            }
            //否则说明链表长为单数，且slow刚好位于中点
            //反转链表
            ListNode tmp = slow.next;
            slow.next = pre;
            pre = slow;
            slow = tmp;
        }
        if (flag) {
            slow = slow.next;
            while (slow != null && slow.val == pre.val) {
                slow = slow.next;
                pre = pre.next;
            }
        } else {
            if (slow.val != slow.next.val)
                return false;
            slow = slow.next.next;

            while (slow != null && slow.val == pre.val) {
                slow = slow.next;
                pre = pre.next;
            }
        }
        return slow == null ;
    }

    /**
     * 236.二叉树的最近公共祖先，自己也是自己的祖先
     *
     * @param root 根节点
     * @param p 指定节点p
     * @param q 指定节点q
     * @return p和q的最近公共祖先
     */
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null) {
            return null;
        }
        TreeNode left = lowestCommonAncestor(root.left,p,q);
        TreeNode right = lowestCommonAncestor(root.right,p,q);
        if (root.val == p.val || root.val == q.val) {
            //有几种情况，一是left和right为null，还有就是left和right有一个不返回null，返回结果一样
            //这是最关键的，不管root子树下是不是有另一个节点，都会返回root，那何必还要分类讨论
            return root;
        } else if (left != null && right != null) {
            return root;
        } else if (left != null) {
            return left;
        } else if (right != null) {
            return right;
        } else {
            return null;
        }
    }

    /**
     * 239.滑动窗口最大值，解法双端队列，类似于单调栈
     *
     * @param nums 数组
     * @param k 窗口大小
     * @return 数组，res[i]表示第i个窗口内的最大值
     */
    public int[] maxSlidingWindow2(int[] nums, int k) {
        Deque<Integer> window = new ArrayDeque<>();
        int len = nums.length;
        int[] res = new int[len - k + 1];
        for (int i = 0; i < len; i++) {
            while (!window.isEmpty() && nums[window.peekLast()] < nums[i]) {
                window.pollLast();
            }
            window.offerLast(i);
            while (!window.isEmpty() && window.peekFirst() < i - k + 1) {
                window.pollFirst();
            }
            if (i >= k - 1 && !window.isEmpty()) {
                res[i-k+1] = nums[window.peekFirst()];
            }
        }
        return res;
    }

    /**
     * 242. 有效的字母异位词
     * @param s
     * @param t
     * @return
     */
    public boolean isAnagram(String s, String t) {
        if (s.length() != t.length()) {
            return false;
        }
        int[] hash = new int[128];
        int count = 0;
        for (int i = 0; i < s.length(); i++) {
            if (hash[s.charAt(i)] == 0 ) {
                count++;
            }
            hash[s.charAt(i)]++;
        }
        for (int i = 0; i < t.length(); i++) {
            if (hash[t.charAt(i)] == 1) {
                count--;
            } else if (hash[t.charAt(i)] == 0 ) {
                return false;
            }
            hash[t.charAt(i)]--;
        }
        return count == 0;
    }

    /**
     * 283.移动零。将数组中的0都移动到末尾，并保持其余元素相对位置不变。双指针
     * @param nums nums
     */
    public void moveZeroes(int[] nums) {
        int len = nums.length;
        int fast = 0;//i左边全部非0（不包括i)，fast是下标i右边第一个非0
        for (int i = 0; fast < len; i++,fast++) {
            // 每次先寻找i右边第一个非0，如果fast>=len表明i右边全是0，即移动完成
            while (fast < len && nums[fast] == 0) {
                fast++;
            }
            if (fast < len && nums[i] == 0) {
                nums[i] = nums[fast];
                nums[fast] = 0;
            }
        }
    }

    /**
     * 349. 两个数组的交集
     * @param nums1 nums1
     * @param nums2 nums2
     * @return 交集
     */
    public int[] intersection(int[] nums1, int[] nums2) {
        Set<Integer> set = new HashSet<>();
        Set<Integer> res = new HashSet<>();
        for (int i : nums1) {
            set.add(i);
        }
        for (int i : nums2) {
            if (set.contains(i)) {
                res.add(i);
            }
        }
        int[] n = new int[res.size()];
        int index = 0;
        for (int a : res) {
            n[index++] = a;
        }
        return n;
    }

    /**
     * 367. 有效的完全平方数。二分
     * @param num num
     * @return 如果是完全平方数返回true，否则返回false
     */
    public boolean isPerfectSquare(int num) {
        int l = 1, r = num, mid = 0;
        while (l <= r) {
            mid = l + (r - l) / 2;
            if ((long) mid * mid > num) {
                r = mid - 1;
            } else if ((long) mid * mid < num) {
                l = mid + 1;
            } else {
                return true;
            }
        }
        return false;
    }

    /**
     *383. 赎金信
     * @param ransomNote
     * @param magazine
     * @return
     */
    public boolean canConstruct(String ransomNote, String magazine) {
        if (ransomNote.length() > magazine.length()) {
            return false;
        }
        int[] cnt = new int[26];
        for (char c : magazine.toCharArray()) {
            cnt[c - 'a']++;
        }
        for (char c : ransomNote.toCharArray()) {
            cnt[c - 'a']--;
            if(cnt[c - 'a'] < 0) {
                return false;
            }
        }
        return true;
    }

    /**
     * 437.路径总和 III。求路径和为target的路径个数。这是题解里的，自己写的又长又没过。泪目了。题解代码真的很清晰。
     *
     * @param root 树的根节点
     * @param targetSum 目标和
     * @return 路径数目
     */
    public int pathSum(TreeNode root, int targetSum) {
        if (root == null) {
            return 0;
        }

        int ret = rootSum(root, targetSum);
        ret += pathSum(root.left, targetSum);
        ret += pathSum(root.right, targetSum);
        return ret;
    }

    /**
     * 包含root的路径和为target的路径个数
     *
     * @param root 根节点
     * @param targetSum 目标和
     * @return 路径个数
     */
    public int rootSum(TreeNode root, int targetSum) {
        int ret = 0;

        if (root == null) {
            return 0;
        }
        int val = root.val;
        if (val == targetSum) {
            ret++;
        }

        ret += rootSum(root.left, targetSum - val);
        ret += rootSum(root.right, targetSum - val);
        return ret;
    }

    /**
     * 438.找到字符串中所有字母异位词。方法是滑动窗口，用数组记录状态，时间复杂度O(n)，空间复杂度O(n)
     *
     * @param s 字符串
     * @param p 字符串
     * @return 异位词构成的列表
     */
    public List<Integer> findAnagrams(String s, String p) {
        if (s.length() < p.length()) {
            return new ArrayList<Integer>();
        }
        Set<Character> set = new HashSet<>();//set包含了设计的字符，对于不涉及的字符，我们不对相应下标的reference做改变
        List<Integer> res = new ArrayList<>();
        int[] reference = new int[26];//如果reference数组不全为0，说明不是字母异位词
        int sum = 0;//sum是reference中所有元素和
        int num = 0;//reference中负数个数
        // 当reference数组全都是0的时候说明此时滑动窗口中是异位词，但是不可能每次都遍历数组来判断，借助sum+num来判断是否满足
        for (int i = 0; i < p.length(); i++) {
            reference[p.charAt(i) - 'a']++;
            set.add(p.charAt(i));
            sum++;
        }
        int len = p.length();
        int index = 0;
        for (int i = 0; i < len; i++) {
            index = s.charAt(i) - 'a';
            if (set.contains(s.charAt(i))) {
                reference[index]--;
                if (reference[index] == -1 ) {
                    num++;
                }
                sum--;
            }
        }
        if (sum == 0 && num == 0) {
            res.add(0);
        }
        for (int i = 1; i <= s.length() - len; i++) {
            if (set.contains(s.charAt(i - 1))) {
                sum++;
                if (reference[s.charAt(i - 1) - 'a'] == -1)
                    num--;
                reference[s.charAt(i - 1) - 'a']++;
            }
            index = s.charAt(i + len - 1) - 'a';
            if (set.contains(s.charAt(i + len - 1))) {
                reference[index]--;
                if (reference[index] == -1 ) {
                    num++;
                }
                sum--;
            }
            if (sum == 0 && num == 0) {
                res.add(i);
            }
        }
        return res;
    }

    /**
     * 454. 四数相加 II
     * @param nums1 nums1
     * @param nums2 nums2
     * @param nums3 nums3
     * @param nums4 nums4
     * @return 和等于0的4元组个数
     */
    public int fourSumCount(int[] nums1, int[] nums2, int[] nums3, int[] nums4) {
        Map<Integer, Integer> map = new HashMap<>();
        int res = 0;
        for (int a : nums1) {
            for (int b : nums2) {
                if (map.containsKey(a + b)) {
                    map.put(a+b, map.get(a+b) + 1);
                } else {
                    map.put(a+b, 1);
                }
            }
        }
        for (int a : nums3) {
            for (int b : nums4) {
                res += map.getOrDefault(-a-b, 0);
            }
        }
        return res;
    }

    /**
     * 459. 重复的子字符串
     * @param s
     * @return
     */
    public boolean repeatedSubstringPattern(String s) {
        if (s.equals("")) return false;

        int len = s.length();
        // 原串加个空格(哨兵)，使下标从1开始，这样j从0开始，也不用初始化了
        s = " " + s;
        char[] chars = s.toCharArray();
        int[] next = new int[len + 1];

        // 构造 next 数组过程，j从0开始(空格)，i从2开始
        for (int i = 2, j = 0; i <= len; i++) {
            // 匹配不成功，j回到前一位置 next 数组所对应的值
            while (j > 0 && chars[i] != chars[j + 1]) j = next[j];
            // 匹配成功，j往后移
            if (chars[i] == chars[j + 1]) j++;
            // 更新 next 数组的值
            next[i] = j;
        }

        // 最后判断是否是重复的子字符串，这里 next[len] 即代表next数组末尾的值
        if (next[len] > 0 && len % (len - next[len]) == 0) {
            return true;
        }
        return false;
    }

    /**
     * 494.目标和。很巧妙把问题转换为在数组里取n个数使得和为target'
     *
     * @param nums 源数组
     * @param target 目标
     * @return 满足条件的结果个数
     */
    public int findTargetSumWays(int[] nums, int target) {
        int len = nums.length;
        int sum = 0;
        for (int i = 0; i < len; i++) {
            sum += nums[i];
        }
        if (sum - target < 0 || (sum - target) % 2 != 0) {
            return 0;
        }

        int n = (sum - target) / 2;
        int[][] dp = new int[len+1][n+1];//dp[i][j]，表示前i个数里选取的负数和为j
        dp[0][0] = 1;
        for (int i = 1; i <= len; i++ ) {
            for (int j = 0; j <= n; j++) {
                if (j >= nums[i - 1])
                    dp[i][j] = dp[i-1][j - nums[i-1]] + dp[i-1][j];
                else
                    dp[i][j] = dp[i-1][j];
            }
        }
        return dp[len][n];
    }

    /**
     * 538.把二叉搜索树转换为累加树。方法一是反中序遍历，即右中左，用递归实现，很好实现。第二种就是这种笨方法。
     *
     * @param root 根节点
     * @return
     */
    public TreeNode convertBST(TreeNode root) {
        if (root == null || (root.left == null && root.right == null)) {
            return root;
        }
        List<Integer> right = new ArrayList<>();
        List<Integer> left = new ArrayList<>();
        TreeNode cur = root.right;
        inOrderIteration(cur, right);
        inOrderIteration(root.left, left);
        //left和right分别是左右子树的中序遍历结果：左根右，符合从小到大顺序
        List<Integer> list = new ArrayList<>(left);
        list.add(root.val);
        list.addAll(right);
        int[] res = new int[list.size()];
        int tmp = 0;
        for (int i = list.size() - 1; i >= 0; i --) {
            tmp += list.get(i);
            res[i] = tmp;
        }
        // res[i] = list[i] + list[i + 1] + ...+ list[size-1]
        // 最后以先序遍历的方式一个一个地修改节点的val
        Deque<TreeNode> stack = new ArrayDeque<>();
        stack.push(root);
        while (!stack.isEmpty()) {
            cur = stack.pop();
            cur.val = res[list.indexOf(cur.val)];
            if (cur.right != null) {
                stack.push(cur.right);
            }
            if (cur.left != null) {
                stack.push(cur.left);
            }
        }
        return root;
    }

    /**
     * 二叉树中序遍历结果存到入参List中
     *
     * @param cur 根节点
     * @param list
     */
    void inOrderIteration(TreeNode cur, List<Integer> list)  {
        Deque<TreeNode> stack = new ArrayDeque<>();
        while (!stack.isEmpty() || cur != null) {
            while (cur != null) {
                stack.push(cur);
                cur = cur.left;
            }
            cur = stack.pop();
            list.add(cur.val);
            cur = cur.right;
        }
    }

    /**
     * 541. 反转字符串 II
     * @param s s
     * @param k k
     * @return 反转完成的字符串
     */
    public String reverseStr(String s, int k) {
        int n = s.length();
        char[] arr = s.toCharArray();
        for (int i = 0; i < n; i += 2 * k) {
            reverse(arr, i, Math.min(i + k, n) - 1);
        }
        return new String(arr);
    }

    /**
     * 621.任务调度器。思路是没问题的，但是有一个用例过不了，主要这个用例数据很大，不好调。
     *
     * @param tasks 任务列表
     * @param n 同种任务之间的执行间隔
     * @return 执行完所有任务需要的最短时间
     */
    public int leastInterval(char[] tasks, int n) {
        int interval = n;
        //当前可执行的任务队列
        PriorityQueue<Task> execuable = new PriorityQueue<>();//某种任务剩余得越多优先级越高
        Set<Character> allTasks = new HashSet<>();
        Map<Character, Task> map = new HashMap<>();
        initExecuable(tasks, allTasks, execuable, map);
        int res = 0;
        while (!allTasks.isEmpty()) {
            res++;
            if (!execuable.isEmpty()) {
                Task tmp = execuable.peek();
                tmp.cooldown = n+1;//执行后有冷却
                execuable.remove(tmp);
                tmp.num--;//执行完任务数-1
                if (tmp.num == 0) {
                    allTasks.remove(tmp.task);
                }
            }

            //执行完一个任务之后更新其他种类任务得状态
            for (Character a : allTasks) {
                Task task = map.get(a);
                if (task.cooldown != 0 ) {
                    task.cooldown--;
                    if (task.cooldown == 0) {
                        execuable.add(task);
                    }
                }
            }
        }
        return res;
    }

    /**
     * 初始化剩余任务集合allTasks，当前可执行任务优先队列execuable，char->Task映射的map
     *
     * @param tasks 输入的字符数组
     * @param allTasks 还剩的任务种类
     * @param execuable 当前可执行任务队列
     * @param map char->Task映射
     */
    public void initExecuable(char[] tasks, Set<Character> allTasks, PriorityQueue<Task> execuable, Map<Character, Task> map) {
        for (char a : tasks) {
            if (allTasks.add(a)) {
                map.put(a, new Task(a, 1, 0));
                execuable.add(map.get(a));
            } else {
                map.get(a).num++;
            }
        }
    }

    class Task implements Comparable{
        char task;
        public int cooldown;//该种任务的剩余冷却时间冷却
        public int num;//该种任务还剩多少个

        public Task(char task, int num, int cooldown) {
            this.task = task;
            this.num = num;
            this.cooldown = cooldown;
        }

        @Override
        public int compareTo(Object o) {
            if (o instanceof Task) {
                return  ((Task) o).num - this.num;
            } else {
                throw new RuntimeException();
            }
        }
    }

    /**
     * 632.最小区间，给出的是贪心加堆的做法，还可以用滑动窗口，滑动窗口更加巧妙
     *
     * @param nums 二维列表
     * @return 区间上下界
     */
    public int[] smallestRange(List<List<Integer>> nums) {
        //区间的左边和右边
        int rangeLeft = 0, rangeRight = Integer.MAX_VALUE;
        //最小范围
        int minRange = rangeRight - rangeLeft;
        //区间的左边最大值
        int max = Integer.MIN_VALUE;
        int size = nums.size();
        //由于 k 个列表都是升序排列的，因此对每个列表维护一个指针，
        //通过指针得到列表中的元素，指针右移之后指向的元素一定大于或等于之前的元素。
        int[] next = new int[size];
        //使用最小堆维护 k 个指针指向的元素中的最小值
        PriorityQueue<Integer> priorityQueue = new PriorityQueue<>(new Comparator<Integer>() {
            public int compare(Integer index1, Integer index2) {
                //第index个列表的next[index1]指针指向的元素
                return nums.get(index1).get(next[index1]) - nums.get(index2).get(next[index2]);
            }
        });

        for (int i = 0; i < size; i++) {
            //初始时，i 个指针都指向下标 0，因为next[i]=0
            priorityQueue.offer(i);
            //最大元素即为所有列表的下标 0 位置的元素中的最大值
            max = Math.max(max, nums.get(i).get(0));
        }

        while (true) {
            //每次从堆中取出最小值，minIndex是指第几个列表，也代表指针数组next的下标
            int minIndex = priorityQueue.poll();
            //根据最大值和最小值计算当前区间
            int curRange = max - nums.get(minIndex).get(next[minIndex]);
            //如果当前区间小于最小区间则用当前区间更新最小区间
            if (curRange < minRange) {
                minRange = curRange;
                rangeLeft = nums.get(minIndex).get(next[minIndex]);
                rangeRight = max;
            }
            //然后将对应列表的指针右移
            next[minIndex]++;
            //如果一个列表的指针超出该列表的下标范围，则说明该列表中的所有元素都被遍历过，
            //堆中不会再有该列表中的元素，因此退出循环。
            if (next[minIndex] == nums.get(minIndex).size()) {
                break;
            }
            //将新元素加入堆中
            priorityQueue.offer(minIndex);
            //并更新堆中元素的最大值
            max = Math.max(max, nums.get(minIndex).get(next[minIndex]));
        }
        return new int[]{rangeLeft, rangeRight};
    }

    /**
     * 685.冗余连接II
     *
     * @param edges
     * @return
     */
    public int[] findRedundantDirectedConnection(int[][] edges) {
        int n = edges.length;
        UnionFind uf = new UnionFind(n + 1);
        int[] parent = new int[n + 1];
        for (int i = 1; i <= n; ++i) {
            parent[i] = i;
        }
        int conflict = -1;
        int cycle = -1;//是否成环
        for (int i = 0; i < n; ++i) {
            int[] edge = edges[i];
            int node1 = edge[0], node2 = edge[1];
            // 如果不是初始状态，即已经有了父节点，说明node2已经有了两个父节点，记录冲突
            if (parent[node2] != node2) {
                conflict = i;
            } else {
                parent[node2] = node1;
                // 如果在(node1,node2)的关系union之前他俩的祖先就相同的话，加上这条边就成环了
                if (uf.find(node1) == uf.find(node2)) {
                    cycle = i;
                } else {
                    // 否则将两个连通分支进行合并
                    uf.union(node1, node2);
                }
            }
        }
        if (conflict < 0) {
            int[] redundant = {edges[cycle][0], edges[cycle][1]};
            return redundant;
        } else {
            int[] conflictEdge = edges[conflict];
            if (cycle >= 0) {
                int[] redundant = {parent[conflictEdge[1]], conflictEdge[1]};
                return  redundant;
            } else {
                int[] redudant = {conflictEdge[0], conflictEdge[1]};
                return redudant;
            }
        }
    }

    /**
     * 695.岛屿的最大面积，方法dfs，关键在于序号标记，因为联通的陆地一定是同一个岛屿，我们用序号标记出来，表示它们被计算过了，方便算出面积
     *
     * @param grid 地形矩阵，0为海洋，1为陆地
     * @return 最大面积
     */
    public int maxAreaOfIsland(int[][] grid) {
        //题目有给矩阵最少为1x1
        int row = grid.length;//矩阵行数
        int col = grid[0].length;//矩阵列数
        int res = 0, index = 2;
        int area = 0;
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (grid[i][j] == 1) {
                    area = area(grid, i, j, index++);
                }
                res = Math.max(area, res);
            }
        }
        return res;
    }

    /**
     * 749.隔离病毒
     *
     * @param isInfected 感染矩阵，1为已感染，0为未感染
     * @return 返回最终安装的防火墙数目
     */
    public int containVirus(int[][] isInfected) {
        int row = isInfected.length;
        int col = isInfected[0].length;
        int index = 2;//给每个病毒区的编号
        // FIXME 写错了，本来想用list下标表示区域编号，值表示防火墙数目，但是后面发现不行，感觉要这种写法需要用map
        List<Integer> transmittedNums = new ArrayList<>();
        transmittedNums.add(0);
        transmittedNums.add(1);
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (isInfected[i][j] == 1) {
                    transmittedNums.add(index, transmittedNum(isInfected, i, j, index++));
                }
            }
        }
        // 假设-1是指原来是病毒区域，但是现在被隔离
        for (int i = 0; i < transmittedNums.size() ; i++) {
            int targetIndex = Util.leastOfList(transmittedNums);//下标即区域编号
            int target = transmittedNums.get(targetIndex);
            transmittedNums.remove(targetIndex);
        }
        return 0;
    }

    /**
     * 病毒下一回合扩散数，病毒区域的轮廓下一回合会被感染病毒
     *
     * @param isInfected 感染矩阵，1为已感染，0为未感染
     * @param i row
     * @param j col
     * @param index 病毒区域编号
     * @return 该病毒区域下一回合扩散面积
     */
    public int transmittedNum(int[][] isInfected, int i, int j, int index ) {
        int res = 0;
        int[] row = new int[]{i + 1, i - 1, i, i};
        int[] col = new int[]{j, j, j +1, j - 1};
        isInfected[i][j] = index;
        for (int k = 0; k < 4; k++) {
            if (inArea(isInfected, row[k], col[k])) {
                if (isInfected[row[k]][col[k]] == 0) {
                    res++;
                } else if (isInfected[row[k]][col[k]] == 1){
                    res += transmittedNum(isInfected, row[k], col[k], index);
                }
            }
        }
        return res;
    }

    /**
     *
     * 除了编号为index的病毒区域，其他区域均向外扩散
     * @param isInfected 感染矩阵
     * @param index 这回合将被隔离的区域
     * @param transmittedNum 剩余病毒区域
     * @return 如果下一回合地图全被感染，返回false
     */
    public boolean virusTransmitted(int[][] isInfected, int index, List<Integer> transmittedNum) {
        int row = isInfected.length;
        int col = isInfected[0].length;
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (isInfected[i][j] == index) {
                    isInfected[i][j] = -1;
                } else if (isInfected[i][j] == 0 || isInfected[i][j] == -1) {
                    continue;
                }
            }
        }
        return false;
    }

    /**
     * 827.最大人工岛，获取海洋格子相邻的岛屿，如果相邻的岛屿有两个，那么当这个海洋格子变成陆地岛屿就会合并
     *
     * @param grid 地形矩阵
     * @return 最多将一个0变成1，最终最大岛屿的面积
     */
    public int largestIsland(int[][] grid) {
        if (grid == null || grid.length == 0) return 1;
        int res = 0;
        int index = 2;
        HashMap<Integer, Integer> indexAndAreas = new HashMap<>();
        for (int r = 0; r < grid.length; r++) {
            for (int c = 0; c < grid[0].length; c++) {
                if (grid[r][c] == 1) {
                    int area = area(grid, r, c, index);
                    indexAndAreas.put(index, area);
                    index++;
                    res = Math.max(res, area);
                }
            }
        }
        //System.out.println(res);
        if (res == 0) return 1;
        for (int r = 0; r < grid.length; r++) {
            for (int c = 0; c < grid[0].length; c++) {
                if (grid[r][c] == 0) {//遍历海洋格子
                    HashSet<Integer> hashSet = findNeighbour(grid, r, c);
                    if (hashSet.size() < 1) continue;
                    int twoIsland = 1;
                    for (Integer i : hashSet) twoIsland += indexAndAreas.get(i);
                    res = Math.max(res, twoIsland);
                }
            }
        }
        return res;
    }

    /**
     * 获取海洋格子的邻接岛屿
     *
     * @param grid 地形矩阵
     * @param r row，行
     * @param c col，列
     * @return 临界岛屿的序号的集合
     */
    private HashSet<Integer> findNeighbour(int[][] grid, int r, int c) {
        HashSet<Integer> hashSet = new HashSet<>();
        if (inArea(grid, r - 1, c) && grid[r - 1][c] != 0)
            hashSet.add(grid[r - 1][c]);
        if (inArea(grid, r + 1, c) && grid[r + 1][c] != 0)
            hashSet.add(grid[r + 1][c]);
        if (inArea(grid, r, c - 1) && grid[r][c - 1] != 0)
            hashSet.add(grid[r][c - 1]);
        if (inArea(grid, r, c + 1) && grid[r][c + 1] != 0)
            hashSet.add(grid[r][c + 1]);
        return hashSet;
    }

    /**
     * 求格子所属岛屿面积
     *
     * @param grid 地形矩阵
     * @param r row，行
     * @param c col，列
     * @param index 岛屿编号
     * @return 返回此格子所属岛屿的面积
     */
    private int area(int[][] grid, int r, int c, int index) {
        if (!inArea(grid, r, c)) return 0;
        if (grid[r][c] != 1) return 0;
        grid[r][c] = index;
        return 1 + area(grid, r - 1, c, index) + area(grid, r + 1, c, index) + area(grid, r, c - 1, index) + area(grid, r, c + 1, index);
    }

    private boolean inArea(int[][] grid, int r, int c) {
        return r >= 0 && r < grid.length && c >= 0 && c < grid[0].length;
    }

    /**
     * 844. 比较含退格的字符串。#表示退格，即delete。比较两个可能包含#的字符串s，t是否相等。从后往前遍历的双指针，因为#删除的是#左边的字母，从右往左才能知道最终有效的是哪个字符
     * @param s s
     * @param t t
     * @return 如果内容相等返回true
     */
    public boolean backspaceCompare(String s, String t) {
        int p1 = s.length() - 1, p2 = t.length() - 1;
        char a , b;
        while (p1 >= 0 || p2 >= 0) {
            p1 = effectiveChar(s, p1) ;
            if (p1 != -2)
                a = s.charAt(p1 + 1);
            else
                a = 'A';
            p2 = effectiveChar(t, p2);
            if (p2 != -2)
                b = t.charAt(p2 + 1);
            else
                b = 'A';
            if ( a != b) {
                return false;
            }
        }
        return true;
    }

    /**
     * 返回可能包含退格符#的字符串下标index左边最近的字符的下标，如果为空白返回-2
     * @param s s
     * @param index index
     * @return 从右向左遍历，返回下标index左边第一个有效字符的下标。所谓有效就是该字母最终没有被退格。
     */
    public int effectiveChar(String s, int index) {
        int count1 = 0, count2 = 0;
        int p = index;//指针位置
        while (p >= 0 && count1 >= count2) {
            if (s.charAt(p) == '#') {
                count1++;
            } else {
                count2++;
            }
            p--;
        }
        if (count1 < count2 ) {
             return p;
        } else {
            return  -2;//表明为空
        }
    }

    /**
     * 862.和至少为k的最短子数组
     *
     * @param nums
     * @param k
     * @return
     */
    public int shortestSubarray(int[] nums, int k) {
        int left = 0, right = 0;
        int res = -1;
        int current = nums[0];
        if (nums[0] >= k) {
            return 1;
        }
        while (right < nums.length - 1) {
            right++;
            current += nums[right];
            if (current < k) {
                continue;
            } else {
                /*while (current - nums[left] >= k) {
                    current -= nums[left];
                    left++;
                }
                上面这种写法不行对于用例：[84,-37,32,40,95] 167
                我的想法是需要存取以下标i开始的最小连续和*/
                if (res == -1) {
                    res = right - left + 1;
                } else if (right - left + 1 < res) {
                    res = right - left + 1;
                }
            }
        }
        return res;
    }

    /**
     * 904. 水果成篮。fruits代表一排果树的种类。你只有两个篮子，每个篮子装同种水果，求最终能采摘水果数量最大值。
     * @param fruits fruits
     * @return 最多采摘的水果数量。
     */
    public int totalFruit(int[] fruits) {
        Set<Integer> set = new HashSet<>();
        int res = 0;
        int l = 0, r = 0;//从第l个树开始摘
        int len = fruits.length;
        while (l < len && res < len - l + 1) {
            set.clear();
            int tmp = 0;
            r = l;
            while (r < len) {
                if (set.size() < 2 && !set.contains(fruits[r])) {
                    set.add(fruits[r]);
                }
                if (set.contains(fruits[r])) {
                    tmp++;
                    res = Math.max(res, tmp);
                }
                if (!set.contains(fruits[r]) && set.size() == 2) {
                    break;
                }
                r++;
            }
            while (fruits[l] == fruits[l+1]) {
                l++;
            }//这个循环是必要的，能够提高这种暴力解法的效率
            l++;
        }
        return res;
    }

    /**
     * 904. 水果成篮。前面的解答其实就是普通的暴力，我一开始还以为那也是滑动窗口。
     * @param fruits fruits
     * @return 最多采摘的水果数量。
     */
    public int totalFruit2(int[] fruits) {
        // 滑动窗口解这题应该是这样的，维护一个窗口，窗口内应该只有两种数字，目的是使得这个窗口最大
        // 一个比较关键点就是同种类的数字不一定连续，左边移动的时候不好移动
        // 窗口扩大的时候肯定会遇到窗口内数字超过三种，这时候就要从右边界往左数，第三种数字要被去掉，这样保证新窗口最大，那么这时候左边界怎么变动
        // 假设要除去的数字为s，一个想法是左边界变为最后一个s+1，毕竟是左闭右闭的窗口，但是实际写起来并不好处理
        // 最终采用了题解解法
        int ans = 0, i = 0;
        Counter count = new Counter();
        for (int j = 0; j < fruits.length; ++j) {
            count.add(fruits[j], 1);
            while (count.size() >= 3) {
                count.add(fruits[i], -1);
                if (count.get(fruits[i]) == 0)
                    count.remove(fruits[i]);
                i++;
            }
            ans = Math.max(ans, j - i + 1);
        }
        return ans;
    }

    class Counter extends HashMap<Integer, Integer> {
        public int get(int k) {
            return containsKey(k) ? super.get(k) : 0;
        }

        public void add(int k, int v) {
            put(k, get(k) + v);
        }
    }

    /**
     * 977. 有序数组的平方。一个可能包含负数的非递减数组，返回其对应的非递减成员的平方组成的数组。要求时间复杂度O(n)。脑残了，找中心点是不必要的，直接从最左和最右开始，从大往小填，反正数组长度是已知的。
     * @param nums nums
     * @return [-2,1,3] -> [1,4,9]
     */
    public int[] sortedSquares(int[] nums) {
        //首先找到中点，再从中点向左右沿申
        int left = findMid(nums);
        int[] res = new int[nums.length];
        if (left < 0) {// left小于0表明nums中没有小于0的元素
            for (int i = 0; i < nums.length; i++) {
                res[i] = nums[i] * nums[i];
            }
        } else {
            int i = 0;
            int a , b ;
            int right = left + 1;//左右双指针，right向右，left向左
            while (left >= 0 || right < nums.length) {
                if (left >= 0)
                    a = nums[left] * nums[left];
                else
                    a = Integer.MAX_VALUE;
                if (right < nums.length)
                    b = nums[right] * nums[right];
                else
                    b = Integer.MAX_VALUE;
                if (a < b) {
                    res[i] = a;
                    left--;
                } else {
                    res[i] = b;
                    right++;
                }
                i++;
            }
        }
        return res;
    }


    /**
     * 返回非递减数组中第一个小于0成员的下标
     * @param nums nums
     * @return
     */
    public int findMid(int[] nums) {
        //找第一个小于0的，如果没有就应该是-1了
        int l = 0, r = nums.length - 1;
        int mid;
        int leftBound = -1;
        while (l <= r) {
            mid = l + (r - l) / 2;
            if (nums[mid] < 0) {
                l = mid + 1;
                leftBound = mid;//之前少了这个，导致错误。我们是要找第一个小于0，所以只要遇到小于0的时候就能更新leftBound，毕竟mid是我们目前知道的最接近0的负数，之后再往mid的右边去找
            } else if (nums[mid] > 0) {
                r = mid - 1;
            } else {
                leftBound = mid - 1;
                r = mid - 1;
            }
        }
        return leftBound;//如果返回负数表示数组非负
    }

    /**
     * 1190.反转每对括号间的子串
     *
     * @param s 初始字符串
     * @return 反转后字符串
     */
    public String reverseParentheses(String s) {
        int right = 0,left = 0;//左右双指针
        char[] arr = s.toCharArray();
        for (; right < s.length(); right++) {
            if(arr[right] == ')') {
                left = right;
                for (;left >= 0; left--) {
                    if (arr[left] == '(') {
                        reverse(arr,left + 1,right - 1);
                        arr[left] = '1';
                        arr[right] = '1';
                        break;
                    }
                }
            }
        }
        StringBuilder sb = new StringBuilder();
        for (char c : arr) {
            if (c != '1')
                sb.append(c);
        }
        return sb.toString();
    }

    /**
     * 反转字符数组arr从start到end位置的字符
     *
     * @param arr 字符数组
     * @param start 起始位置，闭区间
     * @param end 结束位置，闭区间
     */
    public void reverse(char[] arr, int start, int end) {
        char tmp;
        while (start < end) {
            tmp = arr[start];
            arr[start] = arr[end];
            arr[end] = tmp;
            start++;
            end--;
        }
    }

    /**
     * 1283.使结果不超过阈值的最小除数，二分查找
     *
     * @param nums 被除数数组
     * @param threshold 阈值
     * @return 除数
     */
    public int smallestDivisor(int[] nums, int threshold) {
        //查找符合条件区间的最左端
        int length = nums.length;
        Arrays.sort(nums);
        int left = 1;
        int right = nums[length - 1];
        int mid;
        while (left < right) {
            mid = (left + right) / 2;
            int midR = tool(nums, threshold, mid);
            if (midR <= threshold) {
                right = mid;// 商的和小了，除数偏大，所以将区间缩小，注意必须确保正确答案仍然在缩小后的区间中
            } else {
                left = mid + 1;// midR是除的商的和，商大了，说明除数小了，自己感觉一直没有把题意理清楚，逻辑很混乱
            }
        }
        return left;
    }

    public int tool(int[] nums, int threshold, int divisor) {
        int res = 0;
        for (int i : nums) {
            res += i % divisor == 0 ? i / divisor : i / divisor + 1;
        }
        return res;
    }

    /**
     * 1438.绝对差不超过限制的最长连续子数组，滑动窗口+有序集合
     *
     * @param nums 整型数组
     * @param limit 限制值
     * @return 满足条件子数组最大长度
     */
    public int longestSubarray(int[] nums, int limit) {
        if (limit < 0) {
            return 0;
        }
        TreeMap<Integer, Integer> window = new TreeMap<>();
        int length = nums.length;
        int left = 0, right = 0;
        int res = 0;
        while (right < nums.length) {
            window.put(nums[right], window.getOrDefault(nums[right], 0) + 1);
            while (window.lastKey() - window.firstKey() > limit) {
                window.put(nums[left], window.get(nums[left]) - 1);
                if (window.get(nums[left]) == 0) {
                    window.remove(nums[left]);
                }
                left++;
            }
            res = Math.max(res, right - left + 1);
            right++;
        }
        return res;
    }

    /**
     * 1438.绝对差不超过限制的最长连续子数组，滑动窗口+双端队列，基本思想就是维护最大值和最小值，队列中只保留当前窗口的最值，如果不在窗口内就移除
     *
     * @param nums
     * @param limit
     * @return
     */
    public int longestSubarray2(int[] nums, int limit) {
        Deque<Integer> maxQueue = new ArrayDeque<>();
        Deque<Integer> minQueue = new ArrayDeque<>();
        int l = 0, r = 0, res = 0;
        while (r < nums.length) {
            while (!maxQueue.isEmpty() && nums[r] > maxQueue.peekLast())
                maxQueue.removeLast();
            while (!minQueue.isEmpty() && nums[r] < minQueue.peekLast())
                minQueue.removeLast();
            maxQueue.add(nums[r]);
            minQueue.add(nums[r]);
            r++;
            while (maxQueue.peek() - minQueue.peek() > limit) {
                if (maxQueue.peek() == nums[l]) maxQueue.remove();
                if (minQueue.peek() == nums[l]) minQueue.remove();
                l += 1;
            }
            res = Math.max(res, r - l);
        }
        return res;
    }

    /**
     * 1521. 找到最接近目标值的函数值，与运算的特点
     *
     * @param arr arr
     * @param target target
     * @return abs(res - target)
     */
    public int closestToTarget(int[] arr, int target) {
        int ans = Math.abs(arr[0] - target);
        List<Integer> valid = new ArrayList<>();
        valid.add(arr[0]);
        for (int num : arr) {
            List<Integer> validNew = new ArrayList<>();
            validNew.add(num);
            int last = num;
            ans = Math.min(ans, Math.abs(num - target));
            for (int prev : valid) {
                int curr = prev & num;//如果与上这个数之后结果与上次没有变化，那么这个数现在和将来是不被需要的，舍弃，所以其实validNew最终长度只有20不到
                // 按位与运算中，0 不能变回 1，1可能变为0，所以prev二进制表示中，只有0的位是有效的，如果curr == last代表prev所有的0的位置都被curr包含了
                if (curr != last) {
                    validNew.add(curr);
                    ans = Math.min(ans, Math.abs(curr - target));
                    last = curr;
                }
            }
            valid = validNew;
        }
        return ans;
    }

    /**
     * 1477.找两个和为目标值且不重叠的子数组
     *
     * @param arr arr
     * @param target target
     * @return  res
     */
    public int minSumOfLengths(int[] arr, int target) {
        return 0;
    }

    public boolean subArrayIsCoincide(int startA, int lengthA, int startB, int lengthB) {
        if ((startA - startB) * (lengthA - lengthB) < 0 && (startA + lengthA - startB - lengthB >= 0)) {
            return true;
        }
        return false;
    }

    public int sumOfSubArray(int[] arr, int start, int end) {
        int sum = 0;
        for (int i = start; i < Math.min(arr.length, end); i++) {
            sum += arr[i];
        }
        return sum;
    }

    static class Util {
        public static Integer leastOfList(List<Integer> list) {
            if (list == null || list.size() == 0) {
                return null;
            }
            int res = 0;
            for (int i = 1; i < list.size(); i++) {
                res = list.get(i) < list.get(res) ? i : res;
            }
            return res;
        }
    }

    public static void main(String[] args) {
        //System.out.println(new Solution().shortestSubarray(new int[]{2,-1,2}, 3));
        //System.out.println(new Solution().maxSlidingWindow2(new int[] {1,3,1,2,0,5}, 3));
        new Solution().minSubArrayLen(6, new int[] {10,2,3});
        for (int a : new Solution().sortedSquares(new int[]{-7,-3,2,3,11})) {
            System.out.println(a);
        }
        System.out.println(new Solution().minWindow("ab", "a"));
        new Solution().intersection(new int[]{2,3,3,4}, new int[]{1,3});
        new Solution().reverseWords("  the     sky is    blue");
        new Solution().fourSum(new int[]{-3,-2,-1,0,0,1,2,3},0 );
    }



}


