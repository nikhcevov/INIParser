package com.github.ovchingus;

public interface IniHandler {
    void startIni();

    void endIni();

    void startSection(String var1);

    void endSection();

    void handleOption(String var1, String var2);
}
