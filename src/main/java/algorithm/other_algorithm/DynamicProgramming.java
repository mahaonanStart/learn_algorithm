package main.java.algorithm.other_algorithm;

/**
 * @Author: M˚Haonan
 * @Date: 2019-05-08 15:39
 * @Description: 动态规划范例
 * 给定一个网格地图，中间有一些石头，一个人从起点(0,0)走到终点(m - 1,n - 1)，求出路径的所有情况
 */
public class DynamicProgramming {

    /**
     * 使用动态规划,即递推的方式
     * @param grip
     * @return
     */
    public int dynamic(int [][] grip){
        //总行数
        int rows = grip.length;
        //总列数
        int cols = grip[0].length;
        //遍历行
        for (int row = rows - 1; row >= 0; row--) {
            //遍历列
            for (int col = cols - 1; col >= 0 ; col--) {
                //如果不是障碍，该位置的情况等于右边+下边
                if (grip[row][col] != -1){
                    //处理最右边和最下边的情况,即递推的初始条件
                    if (col + 1 == cols || row + 1 == cols){
                        grip[row][col] = 1;
                    }else{
                        //这个公式即为动态规划的递推公式
                        grip[row][col] = grip[row + 1][col] + grip[row][col + 1];
                    }
                //如果为障碍，设置为0，表示此路不通
                }else{
                    grip[row][col] = 0;
                }
            }
        }
        return grip[0][0];
    }


    public static void main(String[] args) {
        //8*8的一个网格地图
        int[][] grip = new int[8][8];
        //设置障碍,-1代表障碍
        grip[1][2] = -1;
        grip[1][6] = -1;
        grip[2][4] = -1;
        grip[3][0] = -1;
        grip[3][2] = -1;
        grip[3][5] = -1;
        grip[4][2] = -1;
        grip[5][3] = -1;
        grip[5][4] = -1;
        grip[5][6] = -1;
        grip[6][1] = -1;
        grip[6][5] = -1;
        DynamicProgramming dynamicProgramming = new DynamicProgramming();
        int all = dynamicProgramming.dynamic(grip);
        System.out.println(all); //27
    }
}
