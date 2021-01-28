package com.alliances.yutils.view.address.model;

import com.alliances.yutils.view.address.global.AppDatabase;
import com.raizlabs.android.dbflow.annotation.Column;
import com.raizlabs.android.dbflow.annotation.PrimaryKey;
import com.raizlabs.android.dbflow.annotation.Table;
import com.raizlabs.android.dbflow.structure.BaseModel;

@Table(database = AppDatabase.class)
public class Province extends BaseModel {

    @PrimaryKey(autoincrement = true)
    public int id;
    @Column
    public String name;
}