package org.geo;

import java.util.ArrayList;

public class Geometry {

    /**
     * 点到直线的最短距离的判断 点（x0,y0） 到由两点组成的线段（x1,y1） ,( x2,y2 )
     * @param x1
     * @param y1
     * @param x2
     * @param y2
     * @param x0
     * @param y0
     * @return
     */
    private double minDistanceBetweenPoint2Segment(double x1, double y1, double x2, double y2,
    		double x0, double y0) {
        double space = 0;

        double a, b, c;

        a = lineSpace(x1, y1, x2, y2);// 线段的长度

        b = lineSpace(x1, y1, x0, y0);// (x1,y1)到点的距离

        c = lineSpace(x2, y2, x0, y0);// (x2,y2)到点的距离

        if (c+b == a) {//点在线段上

           space = 0;

           return space;

        }

        if (a <= 0.000001) {//不是线段，是一个点

           space = b;

           return space;

        }

        if (c * c >= a * a + b * b) { //组成直角三角形或钝角三角形，(x1,y1)为直角或钝角

           space = b;

           return space;

        }

        if (b * b >= a * a + c * c) {//组成直角三角形或钝角三角形，(x2,y2)为直角或钝角

           space = c;

           return space;

        }
        //组成锐角三角形，则求三角形的高
        double p = (a + b + c) / 2;// 半周长

        double s = Math.sqrt(p * (p - a) * (p - b) * (p - c));// 海伦公式求面积

        space = 2 * s / a;// 返回点到线的距离（利用三角形面积公式求高）

        return space;

    }
    /**
     * 计算两点之间的距离
     * @param x1
     * @param y1
     * @param x2
     * @param y2
     * @return
     */
    private double lineSpace(double x1, double y1, double x2, double y2) {

        double lineLength = 0;

        lineLength = Math.sqrt((x1 - x2) * (x1 - x2) + (y1 - y2)

               * (y1 - y2));

        return lineLength;

    }
    /**
     * 判断点是否是在线的2个端点内
     * 线上2端点(x1,y1,x2,y2).点(x0,y0)
     * @param x1
     * @param y1
     * @param x2
     * @param y2
     * @param x0
     * @param y0
     * @return
     */
    private Boolean pointBetweenTwoPoint(double x1, double y1, double x2, double y2, 
    		double x0, double y0) {
        return (Math.abs(x0 - x1) <= Math.abs(x1 - x2) && Math.abs(x0 - x2) <= Math.abs(x1 - x2)
      && Math.abs(y0 - y1) <= Math.abs(y1 - y2) && Math.abs(y0 - y2) <= Math.abs(y1 - y2));
    }
    /**
     * 计算一个点到线上最近的拐点的索引号
     * @param lnglatstr 经纬度坐标字符串(如:123,23;122,32),x:经度,y纬度
     * @param x
     * @param y
     */
    private MPoint getNeastPointBetweenPoint2Polyline(String lineStr, double x, double y) {
      String xyarr[] = lineStr.split(";");
      double mindis = -1;
      MPoint p1 = new MPoint(0, 0);
      MPoint p2 = new MPoint(0, 0);
      for (int i = 1; i < xyarr.length; i++) {
          double x1 = Double.valueOf(xyarr[i - 1].split(",")[0]);
          double y1 = Double.valueOf(xyarr[i - 1].split(",")[1]);
          double x2 = Double.valueOf(xyarr[i].split(",")[0]);
          double y2 = Double.valueOf(xyarr[i].split(",")[1]);
          
		  double dis = minDistanceBetweenPoint2Segment(x1, y1, x2, y2, x, y);
	      if (mindis == -1 || dis < mindis) {
	          mindis = dis;
	          p1.setX(x1);
	          p1.setY(y1);
	          p2.setX(x2);
	          p2.setY(y2);
	      }
      }

      return minReachPointBetweenPoint2Segment(p1.getX(),p1.getY(),p2.getX(),p2.getY(),x,y);
      
    }
    /**
     * 计算点到线段上最近的点坐标，垂足或者端点
     * @param x1
     * @param y1
     * @param x2
     * @param y2
     * @param x
     * @param y
     * @return
     */
    private MPoint minReachPointBetweenPoint2Segment(double x1, double y1, double x2, double y2, 
    		double x, double y) {
        if(pointBetweenTwoPoint(x1,y1,x2,y2,x,y)){
        	double a = (y1 - y2) / (x1 - x2);
            double b = y1 - a * x1;
            double m = x + a * y;

            double x0 = (m - a * b) / (a * a + 1);
            double y0 = a * x0 + b;
        	return new MPoint(x0,y0);
        }else{
        	double d1 = lineSpace(x,y,x1,y1);
        	double d2 = lineSpace(x,y,x2,y2);
        	
        	return d1<d2?new MPoint(x1,y1):new MPoint(x2,y2);
        }
        
    }
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
