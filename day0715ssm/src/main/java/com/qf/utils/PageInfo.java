package com.qf.utils;

import lombok.Data;

import java.util.List;

@Data
public class PageInfo<Item> {


        private Integer page;
        private Integer size;
        private Long total;
        private Integer pages;
        private Integer offset;
        private List<Item> list;

        public PageInfo(Integer page,Integer size,Long total){
            this.page=page<1?1:page;
            this.size=size<1?5:size;
            this.total=total<0?0:total;

            this.pages=(int)(this.total%this.size==0?this.total/this.size:this.total/this.size+1);
            this.offset=(this.page-1)*this.size;

    }


}
