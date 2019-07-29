package com.dyhospital.cloudhis.common.utils;

import java.util.ArrayList;
import java.util.List;

public class PagingUtil {
    private final static int PAGE_SIZE = Integer.MAX_VALUE;
    /**
     * 
     * @param list
     * @param start 从1开始
     * @param pageSize 
     * @return
     */
    public static <T> List<T> paging(List<T> list, int start, int pageSize){
        if(list == null || list.size() == 0){
            return new ArrayList<T>();
        }
        if(start <= 0){
            start = 1;
        }
        if(pageSize <= 0){
            pageSize = PAGE_SIZE;
        }
        int all = list.size();
        if((start - 1) * pageSize > all){
            // 没有数据
            return new ArrayList<T>();
        }
        int end = 0;
        if(start * pageSize > all){
            end = all;
        }else{
            end = start * pageSize;
        }
        return list.subList((start - 1) * pageSize, end);
    }

    public static long getPageCount(long count, int pageSize){
        if(count <= 0){
            return 0;
        }
        if(pageSize <= 0){
            pageSize = PAGE_SIZE;
        }
        if( count / pageSize > 0){
            if(count % pageSize == 0){
                return count / pageSize;
            }
        }
        return count / pageSize + 1;
    }


    public static boolean hasNext(long count, int pageSize, int pageNum){
        return pageNum < getPageCount(count, pageSize)?true:false;
    }
}
