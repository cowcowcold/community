package com.niuniu.community.dto;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;


@Data
public class PageDTO {
    private List<QuestionDTO> questions;
    private boolean showPrevious;
    private boolean showFirstPage;
    private boolean showEndPage;
    private boolean showNext;
    private Integer page;
    private List<Integer> pages=new ArrayList<>();
    private Integer totalPage;


    public void setPagination(Integer totalCount, Integer page, Integer size) {
        Integer totalPage = (totalCount+size-1)/size;
        this.page = page;
        for(int i=-3;i<=3;i++){
            if((page+i)>0&&(page+i)<=totalPage ){
                pages.add(page+i);
            }
        }
        //是否展示上一页
        if(page==1) {
            showPrevious = false;
        }else {
            showPrevious = true;
        }
        //是否展示下一页
        if(page==totalPage){
            showNext = false;
        }else {
            showNext = true;
        }
        if(pages.contains(1)){
            showFirstPage = false;
        }else {
            showFirstPage = true;
        }
        if(pages.contains(totalPage)){
            showEndPage = false;
        }else {
            showEndPage = true;
        }
    }

}
