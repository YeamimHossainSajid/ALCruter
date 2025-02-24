package com.example.ChakriHub.config.page;

import java.util.Collection;

public class PageData {

    private Collection<?> model;
    private int totalPages;
    private int currentPage;
    private long totalElements;

    public PageData() {
    }

    public PageData(Collection<?> model, int totalPages, int currentPage, long totalElements) {
        this.model = model;
        this.totalPages = totalPages;
        this.currentPage = currentPage;
        this.totalElements = totalElements;
    }

    public Collection<?> getModel() {
        return model;
    }


    public void setModel(Collection<?> model) {
        this.model = model;
    }

    public int getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(int totalPages) {
        this.totalPages = totalPages;
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    public long getTotalElements() {
        return totalElements;
    }

    public void setTotalElements(long totalElements) {
        this.totalElements = totalElements;
    }
}

