package com.article.recommend.constant;

import java.util.List;

public class PageModel {
    private int rowCount;
    private List<?> records;
    private int limit;
    private int pageSize;
    private int start;
    private String countSql;//查询总记录数的属性
    
    public String getCountSql() {
        return countSql;
    }
    public void setCountSql(String countSql) {
        this.countSql = countSql;
    }
    public int getStart() {
        return start;
    }
    public void setStart(int start) {
        this.start = start;
    }
    public int getRowCount() {
        return rowCount;
    }
    public void setRowCount(int rowCount) {
        this.rowCount = rowCount;
    }
   
    public List<?> getRecords() {
        return records;
    }
    public void setRecords(List<?> records) {
        this.records = records;
    }
    public int getLimit() {
        return limit;
    }
    public void setLimit(int limit) {
        this.limit = limit;
    }
    public int getPageSize() {
        return pageSize;
    }
    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }
    
}
