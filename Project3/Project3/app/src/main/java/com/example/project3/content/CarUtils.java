package com.example.project3.content;

import java.util.ArrayList;
import java.util.List;

public class CarUtils {

    // An ArrayList of Cars
    public static final List<Car> CAR_ITEMS = new ArrayList<>();

    // The ID for the index into song titles.
    public static final String SONG_ID_KEY = "item_id";

    // The number of songs.
    private static int COUNT = 0;

    /**
     * A Car item represents a cars make and model
     */
    public static class Car {
        public final String car_make;
        public final String car_model;
        public final String car_price;
        public final String car_details;
        public final String car_image_url;
        public final String car_update;

        private Car(String content, String details, String car_price, String car_details, String car_image_url, String car_update) {
            this.car_make = content;
            this.car_model = details;
            this.car_price = car_price;
            this.car_details = car_details;
            this.car_image_url = car_image_url;
            this.car_update = car_update;
        }
    }

    private static void addItem(Car item) {
        CAR_ITEMS.add(item);
        COUNT++;
    }


}
