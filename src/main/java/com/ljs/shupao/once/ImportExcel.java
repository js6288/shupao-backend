package com.ljs.shupao.once;

import com.alibaba.excel.EasyExcel;

import java.util.List;

/**
 * 导入Excel
 */
public class ImportExcel {
    public static void main(String[] args) {
        //写法1
        String fileName = "D:\\ideaProjects\\用户中心\\shupao-backend\\excel\\testExcel.xlsx";
        // 这里 需要指定读用哪个class去读，然后读取第一个sheet 文件流会自动关闭
        // 这里每次会读取100条数据 然后返回过来 直接调用使用数据就行
        synchronousRead(fileName);

    }

    /**
     * 监听器读
     * @param fileName
     */
    public static void listenerRead(String fileName) {
        // 这里 需要指定读用哪个class去读，然后读取第一个sheet 文件流会自动关闭
        EasyExcel.read(fileName, XingQiuTableUserInfo.class, new TableListener()).sheet().doRead();
    }

    /**
     * 同步读
     * @param fileName
     */
    public static void synchronousRead(String fileName) {
        // 这里 需要指定读用哪个class去读，然后读取第一个sheet 同步读取会自动finish
        List<XingQiuTableUserInfo> list = EasyExcel.read(fileName).head(XingQiuTableUserInfo.class).sheet().doReadSync();
        for (XingQiuTableUserInfo data : list) {
            System.out.println(data);
        }
    }


}
