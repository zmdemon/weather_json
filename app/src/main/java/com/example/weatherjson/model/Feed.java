package com.example.weatherjson.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class Feed {

        @SerializedName("weather")
        @Expose

        private ArrayList<Weather> weather;

        @SerializedName("main")
        @Expose
        private Main main;

        public ArrayList<Weather> getWeather() {
            return weather;
        }

        public void setWeather(ArrayList<Weather> weather) {
            this.weather = weather;
        }

        public Main getMain() {
            return main;
        }

        public void setMain(Main main) {
            this.main = main;
        }
}
