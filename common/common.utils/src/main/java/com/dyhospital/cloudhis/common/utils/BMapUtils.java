package com.dyhospital.cloudhis.common.utils;

import org.gavaghan.geodesy.Ellipsoid;
import org.gavaghan.geodesy.GeodeticCalculator;
import org.gavaghan.geodesy.GlobalPosition;

public class BMapUtils {
    /**
     * @param longitude1 经度1
     * @param latitude1  纬度1
     * @param longitude2 经度2
     * @param latitude2  纬度2
     * @return 两点之间的距离
     */
    public static double getInstance(double longitude1, double latitude1, double longitude2, double latitude2) {
        // 经度
        double lon1 = (Math.PI / 180) * longitude1;
        double lon2 = (Math.PI / 180) * longitude2;
        // 纬度
        double lat1 = (Math.PI / 180) * latitude1;
        double lat2 = (Math.PI / 180) * latitude2;
        // 地球半径
        double r = 6371;
        // 返回的单位是km
        double d = Math.acos(Math.sin(lat1) * Math.sin(lat2) + Math.cos(lat1) * Math.cos(lat2) * Math.cos(lon2 - lon1)) * r;
        // 返回米
        return d * 1000;
    }

    /**
     * Haversine公式 formula 维基百科
     *
     * @param longitude1
     * @param latitude1
     * @param longitude2
     * @param latitude2
     * @return
     */
    public static double getInstanceByHaversine(double longitude1, double latitude1, double longitude2, double latitude2) {
        double earthRadius = 6378.137;
        // 纬度
        double lat1 = Math.toRadians(latitude1);
        double lat2 = Math.toRadians(latitude2);
        // 经度
        double lng1 = Math.toRadians(longitude1);
        double lng2 = Math.toRadians(longitude2);
        // 纬度之差
        double a = lat1 - lat2;
        // 经度之差
        double b = lng1 - lng2;
        // 计算两点距离的公式
        double s = 2 * Math.asin(Math.sqrt(Math.pow(Math.sin(a / 2), 2) +
                Math.cos(lat1) * Math.cos(lat2) * Math.pow(Math.sin(b / 2), 2)));
        // 弧长乘地球半径, 返回单位: 千米
        s = s * earthRadius;
        return s;
    }

    /**
     * 使用考虑到地球表面曲率的Vincenty公式
     *
     * @param longitude1
     * @param latitude1
     * @param longitude2
     * @param latitude2
     * @return
     */
    public static double getInstanceByVincenty(double longitude1, double latitude1, double longitude2, double latitude2) {
        GeodeticCalculator geoCalc = new GeodeticCalculator();
        Ellipsoid reference = Ellipsoid.WGS84;
        GlobalPosition pointA = new GlobalPosition(latitude2, longitude2, 0.0); // Point A
        GlobalPosition userPos = new GlobalPosition(latitude1, longitude1, 0.0); // Point B
        double distance = geoCalc.calculateGeodeticCurve(reference, userPos, pointA).getEllipsoidalDistance(); // Distance between Point A and Point B
        return distance;
    }

    public static void main(String[] args) {
        double getInstance = getInstance(106.486654, 29.490295, 106.581515, 29.615467);
        double getInstanceByHaversine = getInstanceByHaversine(106.486654, 29.490295, 106.581515, 29.615467);
        double getInstanceByVincenty = getInstanceByVincenty(106.486654, 29.490295, 106.581515, 29.615467);
        System.out.println("getInstance距离" + getInstance / 1000 + "km");
        System.out.println("getInstanceByHaversine距离" + getInstanceByHaversine + "km");
        System.out.println("getInstanceByVincenty距离" + getInstanceByVincenty / 1000 + "km");
    }
}
