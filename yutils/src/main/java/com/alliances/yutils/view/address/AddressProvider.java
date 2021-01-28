package com.alliances.yutils.view.address;

import com.alliances.yutils.view.address.model.City;
import com.alliances.yutils.view.address.model.County;
import com.alliances.yutils.view.address.model.Province;
import com.alliances.yutils.view.address.model.Street;

import java.util.List;


public interface AddressProvider {
    void provideProvinces(AddressReceiver<Province> addressReceiver);
    void provideCitiesWith(int provinceId, AddressReceiver<City> addressReceiver);
    void provideCountiesWith(int cityId, AddressReceiver<County> addressReceiver);
    void provideStreetsWith(int countyId, AddressReceiver<Street> addressReceiver);

    interface AddressReceiver<T> {
        void send(List<T> data);
    }
}