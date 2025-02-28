package com.seares.crud_spring.enums;

public enum Category {
    
    BACK_END("Back-end"), FRONT_END("Front-end");
    
    private String category;
    
    private Category(String category) {
        this.category = category;
    }
    
    public String getCategory() {
        return category;
    }
    
    @Override
    public String toString() {
        return category;
    }
}
