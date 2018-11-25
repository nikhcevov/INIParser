package com.github.ovchingus;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.LinkedHashMap;


public class Ini extends LinkedHashMap<String, Ini.Section> {
    private static final String OPERATOR = " = ";

    Ini() {
    }

    Ini(Reader input) throws IOException {
        this();
        this.load(input);
    }

    Ini(InputStream input) throws IOException {
        this();
        this.load(input);
    }

    private Ini.Section add(String name) {
        Ini.Section s = new Ini.Section(name);
        this.put(name, s);
        return s;
    }


    @Override
    public Section get(Object key) {
        if (!this.containsKey(key))
            throw new NullSectionException("Section \"" + key + "\" isn`t exist in .ini file.");
        return super.get(key);
    }

    public Ini.Section remove(Ini.Section section) {
        return this.remove(section.getName());
    }

    void load(InputStream input) throws IOException {
        this.load((new InputStreamReader(input)));
    }

    void load(Reader input) throws IOException {
        //лучше сделать анонимный а не локальный
        class Handler implements IniHandler {
            private Ini.Section currentSection;

            private Handler() {
            }

            public void startIni() {
            }

            public void endIni() {
            }

            public void startSection(String sectionName) {
                // я использую немного другую реализацию get() в Section
                Ini.Section s = Ini.super.get(sectionName);
                //Ini.Section s = (Ini.Section) Ini.this.get(sectionName);
                this.currentSection = s != null ? s : Ini.this.add(sectionName);
            }

            public void endSection() {
                this.currentSection = null;
            }

            public void handleOption(String name, String value) {
                this.currentSection.put(name, value);
            }
        }

        Handler handler = new Handler();
        (new IniParser()).parse(input, handler);
    }

    public static class Section extends LinkedHashMap<String, String> {
        private String _name;

        Section(String name) {
            this._name = name;
        }

        String getName() {
            return this._name;
        }

        @Override
        public String get(Object key) {
            if (!this.containsKey(key))
                throw new NullParameterException("Parameter \"" + key + "\" isn`t exist in " + this.getName() + " section.");
            return super.get(key);
        }

        public Integer getInt(Object key) {
            if (!this.containsKey(key))
                throw new NullParameterException("Parameter \"" + key + "\" isn`t exist in " + this.getName() + " section.");
            return Integer.parseInt(super.get(key.toString()));
        }

        public Double getDouble(Object key) {
            if (!this.containsKey(key))
                throw new NullParameterException("Parameter \"" + key + "\" isn`t exist in " + this.getName() + " section.");
            return Double.parseDouble(super.get(key.toString()));
        }
    }
}
