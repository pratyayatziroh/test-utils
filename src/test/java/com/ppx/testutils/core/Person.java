package com.ppx.testutils.core;

/**
 * @author Pratyay
 **/


class Person {
    private String name;
    private int age;
    private boolean isMale;
    private boolean isAdult;

    public Person() {
    }

    public Person(String name, int age, boolean isMale, boolean isAdult) {
        this.name = name;
        this.age = age;
        this.isMale = isMale;
        this.isAdult = isAdult;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public boolean isMale() {
        return isMale;
    }

    public void setMale(boolean male) {
        isMale = male;
    }

    public boolean isAdult() {
        return isAdult;
    }

    public void setAdult(boolean adult) {
        isAdult = adult;
    }
}
