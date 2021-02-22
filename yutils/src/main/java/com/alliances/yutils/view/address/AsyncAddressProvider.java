package com.alliances.yutils.view.address;

import android.content.Context;
import android.util.Log;


import com.alliances.yutils.utils.FileUtils;
import com.alliances.yutils.view.address.model.City;
import com.alliances.yutils.view.address.model.County;
import com.alliances.yutils.view.address.model.Province;
import com.alliances.yutils.view.address.model.Street;

import org.json.JSONArray;

import java.util.ArrayList;
import java.util.List;

public class AsyncAddressProvider implements AddressProvider {
    private Context context;
    public AsyncAddressProvider(Context context) {
        this.context = context;
    }

    @Override
    public void provideProvinces(final AddressReceiver<Province> addressReceiver) {
        Log.i("province","start" + System.currentTimeMillis());
        List<Province> addresses = FileUtils.getAddress(context);
        addressReceiver.send(new ArrayList<>(addresses));
        Log.i("province","end" + System.currentTimeMillis());
    }

    @Override
    public void provideCitiesWith(int provinceId, final AddressReceiver<City> addressReceiver) {
        Log.i("city","start" + System.currentTimeMillis());
        List<City> citys = FileUtils.getCity(context,String.valueOf(provinceId));
        Log.i("city","end" + System.currentTimeMillis());
        addressReceiver.send(new ArrayList<>(citys));
    }

    @Override
    public void provideCountiesWith(int cityId, final AddressReceiver<County> addressReceiver) {
        Log.i("county","start" + System.currentTimeMillis());
        List<County> counties = FileUtils.getCounty(context,String.valueOf(cityId));
        addressReceiver.send(new ArrayList<>(counties));
        Log.i("county","end" + System.currentTimeMillis());
    }

    @Override
    public void provideStreetsWith(int countyId, final AddressReceiver<Street> addressReceiver) {
        Log.i("street","start" + System.currentTimeMillis());
        List<Street> streets = FileUtils.getStreet(context,String.valueOf(countyId));
        addressReceiver.send(new ArrayList<>(streets));
        Log.i("street","end" + System.currentTimeMillis());
    }
}
