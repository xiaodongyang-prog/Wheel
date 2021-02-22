package com.alliances.yutils.view.address;

import android.content.Context;
import android.util.Log;


import com.alliances.yutils.view.address.model.City;
import com.alliances.yutils.view.address.model.County;
import com.alliances.yutils.view.address.model.Province;
import com.alliances.yutils.view.address.model.Street;


public class DefaultAddressProvider implements AddressProvider {
    public DefaultAddressProvider(Context context) {
    }

    @Override
    public void provideProvinces(final AddressReceiver<Province> addressReceiver) {
        addressReceiver.send(null);
    }

    @Override
    public void provideCitiesWith(int provinceId, final AddressReceiver<City> addressReceiver) {
        addressReceiver.send(null);
    }

    @Override
    public void provideCountiesWith(int cityId, final AddressReceiver<County> addressReceiver) {
        addressReceiver.send(null);
    }

    @Override
    public void provideStreetsWith(int countyId, final AddressReceiver<Street> addressReceiver) {
        addressReceiver.send(null);
    }
}
