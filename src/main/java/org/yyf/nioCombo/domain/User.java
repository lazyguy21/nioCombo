package org.yyf.nioCombo.domain;

/**
 * Created by tobi on 16-11-3.
 */
public class User {
    private Long id;
    private String name;
    private Boolean isBeauty;

    public User() {
    }

    public User(Long id, Boolean isBeauty, String name) {
        this.id = id;
        this.isBeauty = isBeauty;
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public User setId(Long id) {
        this.id = id;
        return this;
    }

    public Boolean getBeauty() {
        return isBeauty;
    }

    public User setBeauty(Boolean beauty) {
        isBeauty = beauty;
        return this;
    }

    public String getName() {
        return name;
    }

    public User setName(String name) {
        this.name = name;
        return this;
    }
}
