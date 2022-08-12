package com.assignment.dbclm.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Table(name = "nace", uniqueConstraints = @UniqueConstraint(columnNames = { "order_no" }))
@Entity
public class NACEDao {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "order_no")
    private Long order;
    private Integer level;
    private String code;
    private String parent;
    @Column(length=100000)
    private String description;
    @Column(length=100000)
    private String includes;
    @Column(length=100000)
    private String alsoIncludes;
    @Column(length=100000)
    private String rulings;
    @Column(length=100000)
    private String excludes;
    private String refISICrev;

    public NACEDao(){

    }
    public NACEDao(Long order, Integer level, String code, String parent, String description, String includes,
            String alsoIncludes, String rulings, String excludes, String refISICrev) {
        this.order = order;
        this.level = level;
        this.code = code;
        this.parent = parent;
        this.description = description;
        this.includes = includes;
        this.alsoIncludes = alsoIncludes;
        this.rulings = rulings;
        this.excludes = excludes;
        this.refISICrev = refISICrev;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getOrder() {
        return order;
    }

    public void setOrder(Long order) {
        this.order = order;
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getParent() {
        return parent;
    }

    public void setParent(String parent) {
        this.parent = parent;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getIncludes() {
        return includes;
    }

    public void setIncludes(String includes) {
        this.includes = includes;
    }

    public String getAlsoIncludes() {
        return alsoIncludes;
    }

    public void setAlsoIncludes(String alsoIncludes) {
        this.alsoIncludes = alsoIncludes;
    }

    public String getRulings() {
        return rulings;
    }

    public void setRulings(String rulings) {
        this.rulings = rulings;
    }

    public String getExcludes() {
        return excludes;
    }

    public void setExcludes(String excludes) {
        this.excludes = excludes;
    }

    public String getRefISICrev() {
        return refISICrev;
    }

    public void setRefISICrev(String refISICrev) {
        this.refISICrev = refISICrev;
    }

}
