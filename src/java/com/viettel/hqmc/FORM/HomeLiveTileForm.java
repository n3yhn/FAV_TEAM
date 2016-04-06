/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.viettel.hqmc.FORM;

/**
 *
 * @author vtit_havm2
 */
public class HomeLiveTileForm {
    
    private String url;
    private String iconUrl;
    private String tile;
    private String color;
    private Long count;
    private Long status;
    private Integer group;
    
    public HomeLiveTileForm(){
        
    }
    
    public HomeLiveTileForm(String url, String iconUrl, String tile, Long count, Long status, String color, Integer group){
        this.url = url;
        this.iconUrl = iconUrl;
        this.tile = tile;
        this.count = count;
        this.status = status;
        this.color = "#F0F0F0";
        this.group = group;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getIconUrl() {
        return iconUrl;
    }

    public void setIconUrl(String iconUrl) {
        this.iconUrl = iconUrl;
    }

    public String getTile() {
        return tile;
    }

    public void setTile(String tile) {
        this.tile = tile;
    }

    public Long getCount() {
        return count;
    }

    public void setCount(Long count) {
        this.count = count;
    }

    public Long getStatus() {
        return status;
    }

    public void setStatus(Long status) {
        this.status = status;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public Integer getGroup() {
        return group;
    }

    public void setGroup(Integer group) {
        this.group = group;
    }
    
}

