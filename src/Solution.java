import org.w3c.dom.Node;

import java.util.*;

public class Solution {
/* Leetcode剑指offer第三题*/
    /*查找数组中任意一个重复元素*/
        public int findRepeatNumber(int[] nums) {
            Set<Integer> set = new HashSet<Integer>();
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
    class ListNode {
        int val;
        ListNode next;
        ListNode(int x) { val = x; }
    }
    public int[] reversePrint(ListNode head) {
        Stack<ListNode> s = new Stack<ListNode>();
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
        indexMap = new HashMap<Integer,Integer>();
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
            stack1 = new LinkedList<Integer>();
            stack2  = new LinkedList<Integer>();
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
                int deleteItem = stack2.pop();
                return deleteItem;
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
        if(a > b)
            return a;
        else
            return b;
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
        ListNode p1 = head,p2 = head;
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
    public ListNode reverseList(ListNode head) {
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
    public int[] spiralOreder(int[][] matrix) {
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
    public int[] levelOrder(TreeNode root) {
    if(root == null)
        return new int[0];
    Queue<TreeNode> queue = new LinkedList<TreeNode>();
    ArrayList<Integer> list = new ArrayList<Integer>();
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
//public List<List<Integer>> levelOrder(TreeNode root) {
//    Queue<TreeNode> queue = new LinkedList<>();
//    List<List<Integer>> res = new LinkedList<>();
//    if(root != null)
//        queue.add(root);
//    while(!queue.isEmpty()) {
//        List<Integer> tmp = new ArrayList<>();
//        for(int i = queue.size();i > 0;i--) {
//            TreeNode node = queue.poll();
//            tmp.add(node.val);
//            if(node.left != null)
//                queue.add(node.left);
//            if(node.right != null)
//                queue.add(node.right);
//        }
//        res.add(tmp);
//    }
//    return res;
//}
//    public List<List<Integer>> levelOrder(TreeNode root) {
//        Queue<TreeNode> queue = new LinkedList<>();
//        List<List<Integer>> res = new ArrayList<>();
//        if(root != null)
//            queue.add(root);
//        while(!queue.isEmpty()) {
//            LinkedList<Integer> tmp = new LinkedList<>();
//            for(int i = queue.size();i > 0;i--) {
//                TreeNode node  =queue.poll();
//                if(res.size() % 2 == 0)
//                    tmp.addLast(node.val);
//                else tmp.addFirst(node.val);
//                if(node.left != null)
//                    queue.add(node.left);
//                if(node.right != null)
//                    queue.add(node.right);
//            }
//            res.add(tmp);
//        }
//        return res;
//    }
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
};
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
            return a < c?a:c;
        } else {
            return b < c?b:c;
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
    public int[] twoSum(int[] nums, int target) {
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
     * @param s
     * @return
     */
    public static String reverseWords(String s) {
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
        if (n >= s.length()) {
            return s;
        }
        return s.substring(n,s.length()) + s.substring(0,n);
    }

    /**
     * 剑指offer第59题其1：求滑动窗口内的最大值
     * @param nums 数组
     * @param k 窗口大小
     * @return 移动过程中的最大值构成的数组
     */

    /**
     * 剑指offer第61题
     * @param nums
     * @return
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
        while (i < nums.length && nums[i] != 0) {
            if (pre == nums[i])
                return false;
            if (pre != 0 && pre + 1 != nums[i]) {
                flag--;
            }
            if (flag < 0)
                return false;
        }
        return true;
    }
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
    /*20.有效的括号，括号匹配*/
    public boolean isValid(String s) {
        if (s.isEmpty())
            return true;
        Stack<Character> stack = new Stack<Character>();
        for (char c : s.toCharArray()) {
            if (c == '(')
                stack.push(')');
            else if (c == '{')
                stack.push('}');
            else if (c == '[')
                stack.push(']');
            else if (stack.empty() || c != stack.pop())
                return false;
        }
        if (stack.empty())
            return true;
        return false;
    }
    /*145.二叉树的后序遍历*/
    public List<Integer> postorderTraversal(TreeNode root) {
        List<Integer> res = new ArrayList<Integer>();
        if (root == null) {
            return res;
        }

        Deque<TreeNode> stack = new LinkedList<TreeNode>();
        TreeNode prev = null;
        while (root != null || !stack.isEmpty()) {
            while (root != null) {
                stack.push(root);
                root = root.left;
            }
            root = stack.pop();
            /*如果右子树为空或者已经访问过了才访问根结点
            否则，需要将该结点再次压回栈中，去访问其右子树*/
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
    /*1190.反转每对括号间的子串*/

    /**
     * 反转每对括号间的子串
     * @param s
     * @return
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
    /**/
    /**/
    /**/
    /**/
    /**/
    public static void main(String[] args) {
        System.out.println("hello");
    }



}


