package com.example.gddcs.gddcsdemo.util;

import android.util.Log;

import com.example.gddcs.gddcsdemo.customview.FootView;
import com.example.gddcs.gddcsdemo.model.Line;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 等值线相关工具类
 */
public class ContourLineUtil {

    private static double  []  h   =  new double [5];
    private static int     []  sh  =  new int    [5];
    private static double  []  xh  =  new double [5];
    private static double  []  yh  =  new double [5];
    static long starttime = 0;

    static class Point {
        double row;
        double col;
        double value;

        public double getRow() {
            return row;
        }

        public void setRow(double row) {
            this.row = row;
        }

        public double getCol() {
            return col;
        }

        public void setCol(double col) {
            this.col = col;
        }

        public double getValue() {
            return value;
        }

        public void setValue(double value) {
            this.value = value;
        }
    }

    public static void test1(FootView cv) {
        starttime = System.currentTimeMillis();
        double[][] arr = {
                {0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 30, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0},
                {0, 80, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 30, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 30, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0}
        };
        double[][] endArr = insertValue(initData(),30,cv);
        printlnArray(endArr);
        Log.e("tag","运行花费时间：" + (System.currentTimeMillis() - starttime));
    }

    public static double[][] initData(){
        double[][] data = new double[178][55];
        for (int i = 0; i<178; i++){
            for (int j = 0; j<55; j++){
                data[i][j] = 0;
            }
        }
        data[17][55-37] = 200;
        data[17][55-26] = 150;
        data[17][55-16] = 150;
        data[17][55-5] = 150;
        data[47][55-48] = 100;
        data[55][55-35] = 100;
        data[55][55-22] = 40;
        data[47][55-8] = 80;
        data[74][55-48] = 50;
        data[103][55-48] = 30;
        data[97][55-35] = 20;
        data[97][55-22] = 10;
        data[131][55-44] = 0;
        data[131][55-26] = 0;
        data[160][55-44] = 100;
        data[160][55-26] = 100;
        return data;
    }

    /**
     * 打印数组
     */
    public static void printlnArray(double[][] arr) {
        for (double[] i : arr) {
            System.out.print("输出数组为：" + Arrays.toString(i) + "\n");
        }
    }

    public static double distance2(float x1, float y1, double x2, double y2) {
        double xd = Math.abs(x1 - x2);
        double yd = Math.abs(y1 - y2);
        return Math.sqrt(Math.pow(xd, 2) + Math.pow(yd, 2));
    }

    public static int getMinColFromArray(double[][] arrays) {
        int mMin = arrays[0].length;
        for (double[] f : arrays) {
            if (f.length < mMin) {
                mMin = f.length;
            }
        }
        return mMin;
    }

    public static double getMax(double[] arr)
    {
        double max = arr[0];
        for(int i=0;i<arr.length;i++)
        {
            if(arr[i]>max)
                max = arr[i];
        }
        return max;
    }

    public static double getMin(double[] arr)
    {
        double min = arr[0];
        for(int i=0;i<arr.length;i++)
        {
            if(arr[i]<min)
                min = arr[i];
        }
        return min;
    }

    public static double[][] insertValue(double[][] arrays, int n, FootView cv) {
        int row = arrays.length;
        int col = getMinColFromArray(arrays);
        double[] insert_longitude = new double[col];
        double[] insert_latitude = new double[row];
        double insert_value[][] = new double[row][col];
        List<Point> mPoints = getPointsFromArray(arrays);
        double max_longitude=getMax(insert_longitude);
        double min_longitude=getMin(insert_longitude);
        double max_latitude=getMax(insert_latitude);
        double min_latitude=getMin(insert_latitude);
        double add_longitude=(max_longitude-min_longitude)/col;
        double add_latitude=(max_latitude-min_latitude)/row;

        for(int i=0;i<col;i++) {
//            insert_longitude[i]=min_longitude+add_longitude*i;
            insert_longitude[i] = i*5;
        }
        for(int j=0;j<row;j++) {
//            insert_latitude[j]=min_latitude+add_latitude*j;
            insert_latitude[j] = j*5;
        }

        Log.e("tag","insertValue运行花费时间：" + (System.currentTimeMillis() - starttime));

        double max_distance = distance2(col, row, 0, 0);
        if (mPoints.size() != 0) {
            for (int i = 0; i < row; i++) {
                for (int j = 0; j < col; j++) {
                    float sum_distance = 0, sum_arg = 0;
                    for (int k = 0; k < mPoints.size(); k++) {
                        double DIS = distance2(j, i, mPoints.get(k).getCol(), mPoints.get(k).getRow());
                        double arg = 1 - DIS / max_distance;
                        sum_arg += Math.pow(arg, 10);
                    }
                    for (int k = 0; k < mPoints.size(); k++) {
                        double DIS = distance2(j, i, mPoints.get(k).getCol(), mPoints.get(k).getRow());
                        double arg = 1 - DIS / max_distance;
                        arg =  (Math.pow(arg, 10) / sum_arg);
                        insert_value[i][j] += mPoints.get(k).getValue() * arg;
                    }
                    Log.e("tag","for (int j = 0; j < col; j++) {运行花费时间：" + (System.currentTimeMillis() - starttime));
                }
                Log.e("tag"," for (int i = 0; i < row; i++) {运行花费时间：" + (System.currentTimeMillis() - starttime));
            }

            Log.e("tag","生成数组运行花费时间：" + (System.currentTimeMillis() - starttime));

            double max=0,min=insert_value[0][0];
            for(int i=0;i<row;i++) {
                for(int j=0;j<col;j++) {
                    if(insert_value[i][j]<min) {
                        min=insert_value[i][j];
                    }
                    else if(insert_value[i][j]>max) {
                        max=insert_value[i][j];
                    }

                }}
            double z[]=new double[n];
            double add_z=(max-min)/n;
            for(int i=0;i<n;i++) {
                z[i]=add_z*i+min;
                System.out.println("z="+z[i]+"\n");
            }

            Log.e("tag","计算z【】数组运行花费时间：" + (System.currentTimeMillis() - starttime));

            contour(insert_value, 0,row-1,0,col-1,insert_latitude, insert_longitude, n ,z,cv);
        } else {
            System.out.println("没有离散点,请检查数据");
        }
        return insert_value;
    }


    /**
     * 获取特征点
     */
    public static List<Point> getPointsFromArray(double[][] array) {
        List<Point> mListPoints = new ArrayList<>();
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array[i].length; j++) {
                if (array[i][j] != 0) {
                    Point mp = new Point();
                    mp.setValue(array[i][j]);
                    mp.setRow(i);
                    mp.setCol(j);
                    mListPoints.add(mp);
                }
            }
        }
        return mListPoints;
    }

    public static void contour(double[][] d, int ilb, int iub, int jlb, int jub, double[] x, double[] y, int nc, double[] z, FootView cv) {
        int         m1;
        int         m2;
        int         m3;
        int         case_value;
        double      dmin;
        double      dmax;
        double      x1 = 0.0;
        double      x2 = 0.0;
        double      y1 = 0.0;
        double      y2 = 0.0;
        int         i,j,k,m;

        // The indexing of im and jm should be noted as it has to start from zero
        // unlike the fortran counter part
        int     [] im   = {0,1,1,0};
        int     [] jm   = {0,0,1,1};

        // Note that castab is arranged differently from the FORTRAN code because
        // Fortran and C/C++ arrays are transposed of each other, in this case
        // it is more tricky as castab is in 3 dimension
        int [][][] castab=
                {
                        {
                                {0,0,8},{0,2,5},{7,6,9}
                        },
                        {
                                {0,3,4},{1,3,1},{4,3,0}
                        },
                        {
                                {9,6,7},{5,2,0},{8,0,0}
                        }
                };

        for (j=(jub-1);j>=jlb;j--) {
            for (i=ilb;i<=iub-1;i++) {
                double temp1,temp2;
                temp1 = Math.min(d[i][j],d[i][j+1]);
                temp2 = Math.min(d[i+1][j],d[i+1][j+1]);
                dmin  = Math.min(temp1,temp2);
                temp1 = Math.max(d[i][j],d[i][j+1]);
                temp2 = Math.max(d[i+1][j],d[i+1][j+1]);
                dmax  = Math.max(temp1,temp2);

                if (dmax>=z[0]&&dmin<=z[nc-1]) {
                    for (k=0;k<nc;k++) {
                        if (z[k]>=dmin&&z[k]<=dmax) {
                            for (m=4;m>=0;m--) {
                                if (m>0) {
                                    // The indexing of im and jm should be noted as it has to
                                    // start from zero
                                    h[m] = d[i+im[m-1]][j+jm[m-1]]-z[k];
                                    xh[m] = x[i+im[m-1]];
                                    yh[m] = y[j+jm[m-1]];
                                } else {
                                    h[0] = 0.25*(h[1]+h[2]+h[3]+h[4]);
                                    xh[0]=0.5*(x[i]+x[i+1]);
                                    yh[0]=0.5*(y[j]+y[j+1]);
                                }
                                if (h[m]>0.0) {
                                    sh[m] = 1;
                                } else if (h[m]<0.0) {
                                    sh[m] = -1;
                                } else
                                    sh[m] = 0;
                            }
                            //
                            // Note: at this stage the relative heights of the corners and the
                            // centre are in the h array, and the corresponding coordinates are
                            // in the xh and yh arrays. The centre of the box is indexed by 0
                            // and the 4 corners by 1 to 4 as shown below.
                            // Each triangle is then indexed by the parameter m, and the 3
                            // vertices of each triangle are indexed by parameters m1,m2,and
                            // m3.
                            // It is assumed that the centre of the box is always vertex 2
                            // though this isimportant only when all 3 vertices lie exactly on
                            // the same contour level, in which case only the side of the box
                            // is drawn.
                            //
                            //
                            //      vertex 4 +-------------------+ vertex 3
                            //               |                / |
                            //               |       m-3    /   |
                            //               |            /     |
                            //               |          /       |
                            //               |  m=2    X   m=2   |       the centre is vertex 0
                            //               |       /          |
                            //               |     /            |
                            //               |   /    m=1       |
                            //               | /               |
                            //      vertex 1 +-------------------+ vertex 2
                            //
                            //
                            //
                            //               Scan each triangle in the box
                            //
                            for (m=1;m<=4;m++) {
                                m1 = m;
                                m2 = 0;
                                if (m!=4) {
                                    m3 = m+1;
                                } else {
                                    m3 = 1;
                                }
                                case_value = castab[sh[m1]+1][sh[m2]+1][sh[m3]+1];
                                if (case_value!=0) {
                                    switch (case_value) {
                                        case 1: // Line between vertices 1 and 2
                                            x1=xh[m1];
                                            y1=yh[m1];
                                            x2=xh[m2];
                                            y2=yh[m2];
                                            break;
                                        case 2: // Line between vertices 2 and 3
                                            x1=xh[m2];
                                            y1=yh[m2];
                                            x2=xh[m3];
                                            y2=yh[m3];
                                            break;
                                        case 3: // Line between vertices 3 and 1
                                            x1=xh[m3];
                                            y1=yh[m3];
                                            x2=xh[m1];
                                            y2=yh[m1];
                                            break;
                                        case 4: // Line between vertex 1 and side 2-3
                                            x1=xh[m1];
                                            y1=yh[m1];
                                            x2=xsect(m2,m3);
                                            y2=ysect(m2,m3);
                                            break;
                                        case 5: // Line between vertex 2 and side 3-1
                                            x1=xh[m2];
                                            y1=yh[m2];
                                            x2=xsect(m3,m1);
                                            y2=ysect(m3,m1);
                                            break;
                                        case 6: //  Line between vertex 3 and side 1-2
                                            x1=xh[m3];
                                            y1=yh[m3];
                                            x2=xsect(m1,m2);
                                            y2=ysect(m1,m2);
                                            break;
                                        case 7: // Line between sides 1-2 and 2-3
                                            x1=xsect(m1,m2);
                                            y1=ysect(m1,m2);
                                            x2=xsect(m2,m3);
                                            y2=ysect(m2,m3);
                                            break;
                                        case 8: // Line between sides 2-3 and 3-1
                                            x1=xsect(m2,m3);
                                            y1=ysect(m2,m3);
                                            x2=xsect(m3,m1);
                                            y2=ysect(m3,m1);
                                            break;
                                        case 9: // Line between sides 3-1 and 1-2
                                            x1=xsect(m3,m1);
                                            y1=ysect(m3,m1);
                                            x2=xsect(m1,m2);
                                            y2=ysect(m1,m2);
                                            break;
                                        default:
                                            break;
                                    }
//                                    if (x1 != 0) {
                                    System.out.println("x1-x2-y1-y2:"+x1+"-"+x2+"-"+y1+"-"+y2+"---------z[k]:"+z[k] + "\n");

//                                    Line l = new Line();
//                                    l.setSx(y1);
//                                    l.setSy(x1);
//                                    l.setEx(y2);
//                                    l.setEy(x2);
//
//                                    cv.addPoint(l);
//                                    }
                                    // Put your processing code here and comment out the printf
                                    //printf("%f %f %f %f %fn",x1,y1,x2,y2,z[k]);
//                                    render.drawContour(x1,y1,x2,y2,z[k]);
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    private static double xsect(int p1, int p2){
        return    (h[p2]*xh[p1]-h[p1]*xh[p2])/(h[p2]-h[p1]);
    }

    private static double ysect(int p1, int p2){
        return (h[p2]*yh[p1]-h[p1]*yh[p2])/(h[p2]-h[p1]);
    }

}
