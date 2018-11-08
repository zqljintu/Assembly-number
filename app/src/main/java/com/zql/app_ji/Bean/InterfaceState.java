package com.zql.app_ji.Bean;

public class InterfaceState {
    private Integer backgroundcolor;
    private Integer itemcolor;
    private Integer textcolor;
    private Integer pagecolor;
    private boolean night;

    public Integer getBackgroundcolor() {
        return backgroundcolor;
    }

    public Integer getItemcolor() {
        return itemcolor;
    }

    public Integer getTextcolor() {
        return textcolor;
    }

    public Integer getPagecolor() {
        return pagecolor;
    }

    public boolean isNight() {
        return night;
    }

    public void setBackgroundcolor(Integer backgroundcolor) {
        this.backgroundcolor = backgroundcolor;
    }

    public void setNight(boolean night) {
        this.night = night;
    }

    public void setItemcolor(Integer itemcolor) {
        this.itemcolor = itemcolor;
    }

    public void setTextcolor(Integer textcolor) {
        this.textcolor = textcolor;
    }

    public void setPagecolor(Integer pagecolor) {
        this.pagecolor = pagecolor;
    }
}
