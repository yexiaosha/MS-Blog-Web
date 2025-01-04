package com.ms.blog.common;

import com.baomidou.mybatisplus.core.metadata.IPage;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.List;

/**
 * 分页封装类
 * @author wyh
 * @date 2022/12/01 11:08
 */
@Data
@Schema(name ="分页对象")
public class PageData<T> {

    /**
     * 总条数
     */
    @Schema(name ="总条数")
    private Long total;
    /**
     * 总页数
     */
    @Schema(name ="总页数")
    private Long totalPages;
    /**
     * 是否为最后一页
     */
    @Schema(name ="是否为最后一页")
    private Boolean isLastPage;
    /**
     * 数据
     */
    @Schema(name ="当前页面数据")
    private List<T> pageData;

    public PageData(IPage<T> pageData, Long page) {
        if (pageData == null) {
            return;
        }
        this.pageData = pageData.getRecords();
        this.total = pageData.getTotal();
        this.totalPages = pageData.getPages();
        if (page.equals(totalPages)) {
            this.isLastPage = true;
        }
    }

    public PageData(List<T> data) {
        this.total = 1L;
        this.totalPages = 1L;
        this.pageData = data;
        this.isLastPage = true;
    }

    public PageData(List<T> data, Long total, Long totalPages, Long page) {
        if (data == null) {
            return;
        }
        this.pageData = data;
        this.total = total;
        this.totalPages = totalPages;
        if (page.equals(totalPages)) {
            this.isLastPage = true;
        }
    }

    public void setDataList(List<T> data) {
        this.pageData = data;
    }
}
