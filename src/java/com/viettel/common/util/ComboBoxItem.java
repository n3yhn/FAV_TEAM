/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.viettel.common.util;

/**
 *
 * @author Ebaymark
 */
public class ComboBoxItem {
    private Long id;
    private String name;
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    public ComboBoxItem(Long id, String name) {
        this.id = id;
        this.name = name;
    }
}
