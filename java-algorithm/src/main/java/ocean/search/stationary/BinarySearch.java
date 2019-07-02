package ocean.search.stationary;

/**
 * 二分查找的基本思想：
 * 有一个有序的集合，想判断关键字key是否在集合中。集合的中间元素将集合分成两部分A、B，将key与集合的中间元素作比较，
 * 如果相等，则查找成功。如果key比中间的元素大，则key在后一半集合B中，反之在集合A中。重复上述过程
 * <p>
 * <p>
 * 前提：有一个有序集合n
 * 要求：查找key是否在集合n中
 * 1.置low=0, high=n.length-1
 * 2.计算mid=low+(high-low)/2
 * 3.当low<=high,比较key是否与a[mid]相等，若相等，查找成功，返回mid值.
 * 否则转向4
 * 4.如果a[mid]大于key，置high=mid-1；如果a[mid]小于key，置low=mid+1
 * <p>
 * 5.当low>high时，查找失败
 * <p>
 * ASL约等于log(n+1)-1
 * <p>
 * 困惑
 * 1.如何求中位数
 * 2.如何计算二分查找的ASL
 * 3.int mid=(low+high)/2 求mid的方式有什么问题 A:low+high可能会溢出
 * 4.jdk中 int mid = (low + high) >>> 1 的好处是什么？
 * jdk6 求mid的方式是int mid = (low + high) >> 1，这样做有什么问题？
 * 5.关于移位运算和溢出的基础知识详见《计算机组成原理》，并结合https://coolshell.cn/articles/11466.html
 * <p>
 * <p>
 * 适用情况
 * 表中数据已读操作为主，很少进行写操作
 *
 * @author yancy
 * @date 2019/7/2
 */
public class BinarySearch {
    public int binarySearch(int[] num, int key) {
        int low = 0;
        int high = num.length - 1;
        int mid = low + (high - low) / 2;
        while (low <= high) {
            if (key == num[mid]) {
                return mid;
            } else if (key > num[mid]) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }
        return -1;
    }

    /**
     * 用来说明无符号右移(>>>)和有符号右移(>>)
     */
    public static void bitOperation() {
        int low = Integer.MAX_VALUE;
        int high = Integer.MAX_VALUE;
        System.out.println("low+high 溢出: " + (low + high));
        System.out.println("除/: " + (low + high) / 2);
        System.out.println("无溢出 除/: " + (low + (high - low) / 2));

        //无符号数的溢出原因
        System.out.println("有符号右移 >> : " + ((low + high) >> 1));
        System.out.println("无符号右移 >>> :" + ((low + high) >>> 1));


    }

    public static void main(String[] args) {
        bitOperation();

        int n = Integer.MAX_VALUE;
        System.out.println(n);
    }
}
