package com.company;

public class Smartphone implements Callable, Browsable{
    public Smartphone() {
    }

    @Override
    public String browsing(String number) {
        return String.format("Browsing: %s!", number);
    }

    @Override
    public String calling(String webSite) {
        return String.format("Calling... %s", webSite);
    }
}
