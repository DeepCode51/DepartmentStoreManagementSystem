package com.hwua.entity;

import java.util.List;

public class Paging {
    private Integer topPage;//上一页
    private Integer nextPage;//下一页
    private Integer totalPage;//总页数
    private Long totalNumber;//总记录数
    private Integer currentPage;//当前页
    private Integer pageSize;//每一页的记录数
    private List<Product> list;//每一页的商品
    private List<Comment> cList;//每一页的留言

    /**
     * 获得上一页
     * @return
     */
    public Integer getTopPage() {
        //如果当前页已经是首页
        if (currentPage==1){
            return 1;
        }
        //上一页= 当前页-1
        topPage = currentPage - 1;
        return topPage;
    }

    public void setTopPage(Integer topPage) {
        this.topPage = topPage;
    }

    /**
     * 获得下一页
     * @return
     */
    public Integer getNextPage() {
        //如果当前页已经是最后页
        if (currentPage>=getTotalPage()){
            return currentPage;
        }else {
            //下一页 = 当前页+1
            nextPage = currentPage + 1;
        }
        return nextPage;
    }

    public void setNextPage(Integer nextPage) {
        this.nextPage = nextPage;
    }

    /**
     * 获得总页数
     * @return
     */
    public Integer getTotalPage() {
        //总页数= 总记录数/每一页的记录数
        totalPage = (int)(totalNumber/pageSize);
        if (totalNumber%pageSize>0){
            totalPage += 1;
        }
        return totalPage;
    }

    public void setTotalPage(Integer totalPage) {
        this.totalPage = totalPage;
    }

    public Long getTotalNumber() {
        return totalNumber;
    }

    public void setTotalNumber(Long totalNumber) {
        this.totalNumber = totalNumber;
    }

    public Integer getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(Integer currentPage) {
        this.currentPage = currentPage;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public List<Product> getList() {
        return list;
    }

    public void setList(List<Product> list) {
        this.list = list;
    }

    @Override
    public String toString() {
        return "Paging{" +
                "topPage=" + topPage +
                ", nextPage=" + nextPage +
                ", totalPage=" + totalPage +
                ", totalNumber=" + totalNumber +
                ", currentPage=" + currentPage +
                ", pageSize=" + pageSize +
                ", list=" + list +
                '}';
    }

    public List<Comment> getCList() {
        return cList;
    }

    public void setCList(List<Comment> cList) {
        this.cList = cList;
    }
}
