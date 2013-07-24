package com.xinyuan.haze.core.jpa.search;

public enum SearchMode {
 
    EQUALS("eq"),
  
    ANYWHERE("any"),
   
    STARTING_LIKE("sl"),
   
    LIKE("li"),
   
    ENDING_LIKE("el");

    private final String code;

    SearchMode(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }

    public static final SearchMode convert(String code) {
        for (SearchMode searchMode : SearchMode.values()) {
            if (searchMode.getCode().equals(code)) {
                return searchMode;
            }
        }

        return EQUALS; // default
    }
}