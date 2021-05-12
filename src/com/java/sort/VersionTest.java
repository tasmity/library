package com.java.sort;

import java.util.ArrayList;
import java.util.List;

public class VersionTest {

    public static void main(String[] args) {
        final List<String> chapters = new ArrayList<>();

        chapters.add("1.1");
        chapters.add("1.2");
        chapters.add("1");
        chapters.add("1.3");
        chapters.add("1.1.1");
        chapters.add("5.6");
        chapters.add("1.1.10");
        chapters.add("4");
        chapters.add("1.1.9");
        chapters.add("1.2.1.10");
        chapters.add("2.1.1.4.5");
        chapters.add("1.2.1.9");
        chapters.add("1.2.1");
        chapters.add("2.2.2");
        chapters.add("1.2.1.11");

        System.out.println("UNSORTED: " + chapters.toString());

        chapters.sort(VersionNumberComparator.getInstance());
        System.out.println("SORTED:   " + chapters.toString());
    }
}
