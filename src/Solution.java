
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
    public int[] levelOrder(TreeNode root) {
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
     * @param s s
     * @return res
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
     * 20.有效的括号，括号匹配
     * @param s 带判断的括号字符串
     * @return 有效则返回true
     */
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
    /**
     * 20.有效的括号，这是过了很久自己独立写的
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
    /**/
    /**/
    /**/
    /**/
    /**/

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
        PriorityQueue<Integer> priorityQueue = new PriorityQueue<Integer>(new Comparator<Integer>() {
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
        System.out.println(new Solution().closestToTarget(new int[] {
                9,12,3,7,15}, 5));
    }



}


