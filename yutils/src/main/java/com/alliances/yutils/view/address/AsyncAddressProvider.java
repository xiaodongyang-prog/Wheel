package com.alliances.yutils.view.address;

import android.content.Context;
import android.util.Log;


import com.alliances.yutils.utils.FileUtils;
import com.alliances.yutils.view.address.model.City;
import com.alliances.yutils.view.address.model.City_Table;
import com.alliances.yutils.view.address.model.County;
import com.alliances.yutils.view.address.model.County_Table;
import com.alliances.yutils.view.address.model.Province;
import com.alliances.yutils.view.address.model.Street;
import com.alliances.yutils.view.address.model.Street_Table;
import com.raizlabs.android.dbflow.config.FlowConfig;
import com.raizlabs.android.dbflow.config.FlowManager;
import com.raizlabs.android.dbflow.list.FlowQueryList;
import com.raizlabs.android.dbflow.sql.language.SQLite;
import com.raizlabs.android.dbflow.structure.database.transaction.Transaction;

import org.json.JSONArray;

import java.util.ArrayList;
import java.util.List;

public class AsyncAddressProvider implements AddressProvider {
    private Context context;
    public AsyncAddressProvider(Context context) {
        this.context = context;
        FlowManager.init(new FlowConfig.Builder(context.getApplicationContext()).build());
    }

    @Override
    public void provideProvinces(final AddressReceiver<Province> addressReceiver) {
        List<Province> addresses = FileUtils.getAddress(context);
        addressReceiver.send(new ArrayList<>(addresses));
    }

    @Override
    public void provideCitiesWith(int provinceId, final AddressReceiver<City> addressReceiver) {
        List<City> citys = FileUtils.getCity(context,String.valueOf(provinceId));
        addressReceiver.send(new ArrayList<>(citys));
    }

    @Override
    public void provideCountiesWith(int cityId, final AddressReceiver<County> addressReceiver) {
        List<County> counties = FileUtils.getCounty(context,String.valueOf(cityId));
        addressReceiver.send(new ArrayList<>(counties));
    }

    @Override
    public void provideStreetsWith(int countyId, final AddressReceiver<Street> addressReceiver) {
        List<Street> streets = FileUtils.getStreet(context,String.valueOf(countyId));
        addressReceiver.send(new ArrayList<>(streets));
    }
}
