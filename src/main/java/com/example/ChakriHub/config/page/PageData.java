package com.example.ChakriHub.config.page;

import java.util.Collection;

public class PageData {

    private Collection<?> model;
    private int totalPages;
    private int currentPage;
    private long totalElements;

    // No-args constructor
    public PageData() {
    }

    // All-args constructor
    public PageData(Collection<?> model, int totalPages, int currentPage, long totalElements) {
        this.model = model;
        this.totalPages = totalPages;
        this.currentPage = currentPage;
        this.totalElements = totalElements;
    }

    // Getter for model
    public Collection<?> getModel() {
        return model;
    }

    // Setter for model
    public void setModel(Collection<?> model) {
        this.model = model;
    }

    // Getter for totalPages
    public int getTotalPages() {
        return totalPages;
    }

    // Setter for totalPages
    public void setTotalPages(int totalPages) {
        this.totalPages = totalPages;
    }

    // Getter for currentPage
    public int getCurrentPage() {
        return currentPage;
    }

    // Setter for currentPage
    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    // Getter for totalElements
    public long getTotalElements() {
        return totalElements;
    }

    // Setter for totalElements
    public void setTotalElements(long totalElements) {
        this.totalElements = totalElements;
    }
}

