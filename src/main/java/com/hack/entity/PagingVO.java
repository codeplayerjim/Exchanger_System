package com.hack.entity;

public class PagingVO {
    //当前页码
    private int currentPageNo=1;
    //总页数
    private int totalCount;
    //页面容量
    private int pageSize=5;
    //上一页
    private int upPageNo;
    //下一页
    private int nextPageNo;

    public int getCurrentPageNo() {
        return currentPageNo;
    }

    public int getToPageNo() {
        return toPageNo;
    }

    public void setToPageNo(int toPageNo) {
        this.toPageNo = toPageNo;
    }

    //前往页码
    private int toPageNo=0;

    public void setToPageNo(Integer toPageNo){
        //新一页
        this.toPageNo=(toPageNo-1)*pageSize;
        //设置跳转后当前的页码
        setCurrentPageNo(toPageNo);
    }
    public void setCurrentPageNo(int currentPageNo){
        if(currentPageNo!=1){
            this.upPageNo=upPageNo-1;

        }
            this.nextPageNo=nextPageNo+1;

            this.currentPageNo=currentPageNo;

    }

    public int getTotalCount(){
        return totalCount;
    }

    public void setTotalCount(int totalCount){
        if(totalCount%pageSize>0){
            this.totalCount=(totalCount/pageSize)+1;
        }else {
            this.totalCount=totalCount/pageSize;
        }
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getUpPageNo() {
        return upPageNo;
    }

    public void setUpPageNo(int upPageNo) {
        this.upPageNo = upPageNo;
    }

    public int getNextPageNo() {
        return nextPageNo;
    }

    public void setNextPageNo(int nextPageNo) {
        this.nextPageNo = nextPageNo;
    }



}
