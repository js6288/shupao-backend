package com.ljs.shupao.once;

import com.alibaba.excel.EasyExcel;
import org.apache.commons.lang3.StringUtils;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class ImportXingQiuUser {
    public static void main(String[] args) {
        String fileName = "D:\\ideaProjects\\用户中心\\shupao-backend\\excel\\testExcel.xlsx";
        List<XingQiuTableUserInfo> userInfoList = EasyExcel.read(fileName).head(XingQiuTableUserInfo.class).sheet().doReadSync();
        System.out.println("总数 = "+userInfoList.size());
        Map<String, List<XingQiuTableUserInfo>> listMap = userInfoList.stream().filter(userInfo -> StringUtils.isNotEmpty(userInfo.getUserName())).collect(Collectors.groupingBy(XingQiuTableUserInfo::getUserName));
        System.out.println("不重复的昵称数="+listMap.keySet().size());
    }
}
