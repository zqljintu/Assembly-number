package com.zql.app_ji.View.Fragments;

import android.app.Application;
import android.content.Context;

import com.zql.app_ji.Bean.InterfaceState;

public interface MovieFragmentImp{
    public Context getBasecontext();
    public Application getbaseapplication();
    public void setTheNightstatefromUserseting(InterfaceState interfaceState);
}