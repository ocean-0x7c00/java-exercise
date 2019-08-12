package ocean.kmp;

import static java.lang.Math.max;

/**
 * 字符串匹配
 * 字符串
 * 两个字符串的最长公共子序列
 * 两个字符串的最长公共字符串
 * 子序列
 * <p>
 * <p>
 * <p>
 * 1.什么是KMP算法
 * 2.实现KMP算法
 * 3.KMP算法复杂度分析
 * <p>
 * 1.什么是动态规划
 * 2.动态规划的算法如何实现
 * 3.时间复杂度分析
 * <p>
 * <p>
 * https://zhuanlan.zhihu.com/p/28815523
 * https://blog.csdn.net/v_JULY_v/article/details/6110269
 * [字符串匹配的KMP算法](http://www.ruanyifeng.com/blog/2013/05/Knuth%E2%80%93Morris%E2%80%93Pratt_algorithm.html)
 * [The Knuth-Morris-Pratt Algorithm in my own words](http://jakeboxer.com/blog/2009/12/13/the-knuth-morris-pratt-algorithm-in-my-own-words/)
 * https://zhuanlan.zhihu.com/p/43824258
 * https://blog.csdn.net/u013309870/article/details/75193592
 * https://zhuanlan.zhihu.com/p/28815523
 * https://blog.csdn.net/hrn1216/article/details/51534607
 * https://www.cnblogs.com/CQzhangyu/category/927326.html
 * https://zhuanlan.zhihu.com/p/28934358
 *
 * @author ocean
 * @date 2019/8/12
 */
public class Main {
    static int count = 0;

    public static void main(String[] args) {
//        String target = "BBC ABCDAB ABCDABCDABDE";
//        String source = "ABCDABD";
        String target = "root.data.sensorId";
        String source = "root.data.records[0].points[0].sensorId";
//        String source = "root.data.records[0].points[1].sensorType";

//        判断target是不是source的子串
        //子串问题,子列和的问题，引出KMP算法
        //给定两个字符串A和B，返回两个字符串的最长公共子序列的长度
        //动态规划

//        brutalAlgorithm(target, source);
//        System.out.println(fib(6));
//        System.out.println(count);
        String result = lcs(target, source);
        //如果最长公共子序列存在且和target相等，则返回使用source查询。否则不存在
//        if (target.equals(result)) {
//            System.out.println("存在  " + source);
//        } else {
//            System.out.println("不存在  ");
//        }

    }

    public static void brutalAlgorithm(String target, String source) {
        char[] srcChar = source.toCharArray();
        char[] tgrChar = target.toCharArray();

        int count = 0;
        boolean flag = true;
        for (int i = 0; i < tgrChar.length; i++) {
            for (int j = 0; j < srcChar.length; j++) {
                if (tgrChar[i] != srcChar[j]) {
                    flag = false;
                } else {
                    flag = true;
                }
                count++;
            }
        }
        if (flag) {
            System.out.println("存在");
        } else {
            System.out.println("不存在");
        }

        //时间复杂度O(m*n)
        System.out.println(count);
    }


    public static int fib(int n) {
        count++;
        if (n <= 0) {
            return 0;
        } else if (n == 1) {
            return 1;
        }
        return fib(n - 1) + fib(n - 2);
    }


    public static String lcs(String target, String source) {
        int size1 = target.length();
        int size2 = source.length();
        int chess[][] = new int[target.length() + 1][source.length() + 1];
        for (int i = 1; i <= size1; i++) {
            //根据上面提到的公式计算矩阵
            for (int j = 1; j <= size2; j++) {
                if (target.charAt(i - 1) == source.charAt(j - 1)) {
                    chess[i][j] = chess[i - 1][j - 1] + 1;
                } else {
                    chess[i][j] = max(chess[i][j - 1], chess[i - 1][j]);
                }
            }
        }
        int i = size1;
        int j = size2;
        StringBuffer sb = new StringBuffer();
        while ((i != 0) && (j != 0)) {
            //利用上面得到的矩阵计算子序列，从最右下角往左上走
            if (target.charAt(i - 1) == source.charAt(j - 1)) {
                sb.append(target.charAt(i - 1));
                //相同时即为相同的子串
                i--;
                j--;
            } else {
                if (chess[i][j - 1] > chess[i - 1][j]) {
                    j--;
                } else {
                    i--;
                }
            }
        }
//        System.out.println((double) sb.length() / s2.length() + "," + (double) sb.length() / s1.length());
        String result = sb.reverse().toString();
        if (target.equals(result)) {
            System.out.println("存在  " + source);
        } else {
            System.out.println("不存在  ");
        }
        return result;

    }
}
