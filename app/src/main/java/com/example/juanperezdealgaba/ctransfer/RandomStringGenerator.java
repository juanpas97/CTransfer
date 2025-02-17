package com.example.juanperezdealgaba.ctransfer;

import java.util.Random;

public class RandomStringGenerator {

    public static String generateRandomString() {
        Random rand = new Random();
        StringBuilder stringBuilder = new StringBuilder();
        char[] chars = {'a','b','c','d','e','f','g','h','i','j','k','l','m','n','o','p',
                'q','r','s','t','u','v','w','x','y','z','1','2','3','4','5','6','7','8','9','0'};
        for (int i = 0; i < 128; ++i) {
            char selectedChar = chars[rand.nextInt(chars.length)];
            stringBuilder.append(selectedChar);
        }

        final String randomstring = stringBuilder.toString();

        return randomstring;
    }

    public static String generateRandomString(int blocksize) {
        Random rand = new Random();
        StringBuilder stringBuilder = new StringBuilder();
        char[] chars = {'a','b','c','d','e','f','g','h','i','j','k','l','m','n','o','p',
                'q','r','s','t','u','v','w','x','y','z','1','2','3','4','5','6','7','8','9','0'};
        for (int i = 0; i < blocksize; ++i) {
            char selectedChar = chars[rand.nextInt(chars.length)];
            stringBuilder.append(selectedChar);
        }

        final String randomstring = stringBuilder.toString();

        return randomstring;
    }

    /**
     *
     * @param args
     */
    public  void main (String [ ] args){

        String pene = generateRandomString();
        System.out.println(pene);
    }
}
