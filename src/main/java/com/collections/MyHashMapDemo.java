package com.collections;

public class MyHashMapDemo {
    public static void main(String[] args) {
        MyHashMap<Integer, Integer> myHashMap = new MyHashMap<>();

         myHashMap.remove(27);
         myHashMap.put(65, 65);
         myHashMap.remove(19);
         myHashMap.remove(0);
         myHashMap.get(18);
         myHashMap.remove(3);
         myHashMap.put(42, 0);
         myHashMap.get(19);
         myHashMap.remove(42);
         myHashMap.put(17, 90);
         myHashMap.put(31, 76);
         myHashMap.put(48, 71);
         myHashMap.put(5, 50);
         myHashMap.put(7, 68);
         myHashMap.put(73, 74);
         System.out.println("Before adding 85 "+myHashMap.size());
         myHashMap.put(85, 18);
         System.out.println("After adding 85 "+myHashMap.size());
         myHashMap.put(74, 95);
         myHashMap.put(84, 82);
         myHashMap.put(59, 29);
         myHashMap.put(71, 71);
         myHashMap.remove(42);
         myHashMap.put(51, 40);
         myHashMap.put(33, 76);
         System.out.println(myHashMap.get(17));
         myHashMap.put(89, 95);
         myHashMap.get(95);
         myHashMap.put(30,31);
         myHashMap.put(37,99);
         System.out.println(myHashMap.get(51));
         myHashMap.put(95, 35);
         myHashMap.remove(65);
         myHashMap.remove(81);
         myHashMap.put(61, 46);
         myHashMap.put(50, 33);
         myHashMap.get(59);
         myHashMap.remove(5);
         myHashMap.put(75, 89);
         myHashMap.put(80, 17);
         myHashMap.put(35, 94);
         myHashMap.get(80);
         myHashMap.put(19, 68);
         myHashMap.put(13, 17);
         myHashMap.remove(70);
         myHashMap.put(28, 35);
         myHashMap.remove(99);
         myHashMap.remove(37);
         myHashMap.remove(13);
         myHashMap.put(90, 83);
         myHashMap.remove(41);
         myHashMap.get(50);
         myHashMap.put(29, 48);
         myHashMap.put(54, 72);
         myHashMap.put(6, 8);
         myHashMap.put(51, 88);
         myHashMap.remove(13);
         myHashMap.put(8, 22);
         System.out.println(myHashMap.get(85));
    }
}
