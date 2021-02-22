package com.alliances.yutils.utils;

import android.content.Context;

import com.alibaba.fastjson.JSON;
import com.alliances.yutils.view.address.model.City;
import com.alliances.yutils.view.address.model.County;
import com.alliances.yutils.view.address.model.Province;
import com.alliances.yutils.view.address.model.Street;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class FileUtils {
    public static final String DB_NAME = "Area.db";

    /**
     *
     * @param context
     */
    public static void copyDataBase(Context context) {
        try {
            InputStream myInput = context.getAssets().open(DB_NAME);
            File outFileName = context.getDatabasePath(DB_NAME);

            outFileName.getParentFile().mkdirs();
            OutputStream myOutput = new FileOutputStream(outFileName);
            byte[] buffer = new byte[1024];
            int length;
            while ((length = myInput.read(buffer)) > 0) {
                myOutput.write(buffer, 0, length);
            }
            myOutput.flush();
            myOutput.close();
            myInput.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    /**
     * @param context
     * @return
     */
    public static List<Province> getAddress(Context context) {
        try {
            InputStream inputStream = context.getAssets().open("provinces.json");
            ByteArrayOutputStream arrayOutputStream = new ByteArrayOutputStream();
            byte[] buffer = new byte[4096];
            int len;
            while ((len = inputStream.read(buffer)) != -1) {
                arrayOutputStream.write(buffer, 0, len);
            }
            arrayOutputStream.flush();
            arrayOutputStream.close();
            inputStream.close();

            String json = new String(arrayOutputStream.toByteArray());
            return JSON.parseArray(json, Province.class);
        } catch (Exception e) {
            return Collections.emptyList();
        }
    }

    /**
     * @param context
     * @return
     */
    public static List<City> getCity(Context context, String provinceId) {
        try {
            InputStream inputStream = context.getAssets().open("city.json");
            ByteArrayOutputStream arrayOutputStream = new ByteArrayOutputStream();
            byte[] buffer = new byte[4096];
            int len;
            while ((len = inputStream.read(buffer)) != -1) {
                arrayOutputStream.write(buffer, 0, len);
            }
            arrayOutputStream.flush();
            arrayOutputStream.close();
            inputStream.close();
            List<City> cities = new ArrayList<>();
            String json = new String(arrayOutputStream.toByteArray());
            List<City> provinces = JSON.parseArray(json, City.class);
//            for (int i = 0; i < provinces.size(); i++) {
//                if (provinceId.equals(String.valueOf(provinces.get(i).province_id))) {
//                    cities.add(provinces.get(i));
//                }
//            }
            return provinces;
        } catch (Exception e) {
            return Collections.emptyList();
        }
    }

    /**
     * @param context
     * @return
     */
    public static List<County> getCounty(Context context, String cityId) {
        try {
            InputStream inputStream = context.getAssets().open("counties.json");
            ByteArrayOutputStream arrayOutputStream = new ByteArrayOutputStream();
            byte[] buffer = new byte[4096];
            int len;
            while ((len = inputStream.read(buffer)) != -1) {
                arrayOutputStream.write(buffer, 0, len);
            }
            arrayOutputStream.flush();
            arrayOutputStream.close();
            inputStream.close();
            List<County> counties = new ArrayList<>();
            String json = new String(arrayOutputStream.toByteArray());
            List<County> cities = JSON.parseArray(json, County.class);
//            for (int i = 0; i < cities.size(); i++) {
//                if (cityId.equals(String.valueOf(cities.get(i).city_id))) {
//                    counties.add(cities.get(i));
//                }
//            }
            return cities;
        } catch (Exception e) {
            return Collections.emptyList();
        }
    }

    /**
     * @param context
     * @param countyId
     * @return
     */
    public static List<Street> getStreet(Context context, String countyId) {
        try {
            InputStream inputStream = context.getAssets().open("streets.json");
            ByteArrayOutputStream arrayOutputStream = new ByteArrayOutputStream();
            byte[] buffer = new byte[4096];
            int len;
            while ((len = inputStream.read(buffer)) != -1) {
                arrayOutputStream.write(buffer, 0, len);
            }
            arrayOutputStream.flush();
            arrayOutputStream.close();
            inputStream.close();
            List<Street> streets = new ArrayList<>();
            String json = new String(arrayOutputStream.toByteArray());
            List<Street> cities = JSON.parseArray(json, Street.class);
//            for (int i = 0; i < cities.size(); i++) {
//                if (countyId.equals(String.valueOf(cities.get(i).county_id))) {
//                    streets.add(cities.get(i));
//                }
//            }
            return cities;
        } catch (Exception e) {
            return Collections.emptyList();
        }
    }
}
