package com.ljuslin.view;

import javafx.scene.control.Tab;

public class MemberView implements TabView{
    private Tab memberTab;
    public MemberView(){
        memberTab = new  Tab("Member");
    }
    //lägg all koden i en konstruktor eller i en getTab
    public Tab getTab() {
        return memberTab;
    }
}
