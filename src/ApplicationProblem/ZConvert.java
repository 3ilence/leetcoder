package ApplicationProblem;

/**
 * @ClassName : ZConvert
 * @Author : Silence
 * @Date: 2022/12/19 11:23
 * @Description : 6. Z 字形变换
 */
public class ZConvert {

    /**
     * 采用模拟的办法，先用StringBuilder数组把Z模拟出来，然后每一次循环从每个StringBuilder取出第一行的元素，第二次循环从每个StringBuilder中取出第二行元素
     * 问题就在于一次可能会从同一个StringBuilder取出两个数，比如i = 1，要取出第二行数，sb.charAt(x - i)也是第二行的数
     * 此题的问题在于边界判断比较多
     * @param s
     * @param numRows
     * @return
     */
    public String convert(String s, int numRows) {
        if (numRows == 1) {
            return s;//避免x < 0 的情况
        }
        int len = s.length();
        int x = numRows * 2 - 2;
        int l = len / x;
        if (len % x != 0) {
            l += 1;
        }
        StringBuilder[] sb = new StringBuilder[l];
        int index = 0;
        for (int i = 0; i < sb.length; ) {
            sb[i] = new StringBuilder();
            for (int j = 0; j < x; j++) {

                if (index < len) {
                    sb[i].append(s.charAt(index));
                    index++;
                }
            }
            i++;
        }
        StringBuilder res = new StringBuilder();
        for (int i = 0; i < numRows; i++) {
            for (int j = 0; j < sb.length; j++) {
                if (sb[j].length() > i) {
                    res.append(sb[j].charAt(i));
                    if (x - i < sb[j].length() && x - i != i) {
                        res.append(sb[j].charAt(x - i ));
                    }
                } else {
                    break;
                }

            }
        }
        return res.toString();
    }

    public static void main(String[] args) {
        System.out.println(new ZConvert().convert("A", 1));
        System.out.println(new ZConvert().convert("PAYPALISHIRING", 4));
    }
}
