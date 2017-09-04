import java.io.Serializable;
import java.lang.reflect.Array;
//Name     : Minjie Fan
//Class    : CSCI 1620-001
//Program #    : 2
//Due Date : May 9th 2014
//
//Honor Pledge :  On my honor as a student of the University of Nebraska at Omaha, I have neither given nor received unauthorized help on this homework assignment.
//
//NAME     : Minjie Fan
//NUID     : 738
//EMAIL    : mfan@unomaha.edu
//
//Partners : none
//
//
//Description  : This can also be used for communicating over a network. A Server that owns a Hash Table (from the previous part) of Account Records, and a Client that makes requests to manipulate the Hash Table owned by the Server.
public class HashTable<E extends hasKey & Serializable> {
    private E[] ht;
    private int numElements;
    private Class<E> elementType;


    public HashTable(Class<E> t) {
        elementType = t;
        numElements = 0;
        ht = (E[]) Array.newInstance(t, 7);
    }

    private int hash(int key) {
        return key % ht.length;
    }


    public void put(int key, E item) {

        if (numElements > ht.length / 2) {
            enlarge();
        }

        int t = findPosition(key);
        // System.out.println(t);
        //   System.out.println(t);
        if (ht[t] != null) {
            System.out.println("Duplicate not inserted");
            return;
        }

        ht[t] = item;
        numElements++;
    }

    public void remove(int key) {
        int t = findPosition(key);
        if (ht[t] == null) {
            System.out.println("Item not found");
            return;
        }

        ht[t] = null;
        numElements--;

    }

    public E get(int key) {
        int t = findPosition(key);
        if (ht[t] == null) {
            System.out.println("Item not found");
            return null;
        }

        return ht[t];
    }

    private int findPosition(int key) {
        int i = hash(key);
        //  System.out.println("----------");
        for (int j = 0; j < ht.length; j++) {
            int u = (i + j * j) % ht.length;

            //   System.out.println(u);
            if (ht[u] == null) {
                return u;
            }
            if (ht[u].getKey() == key) {
                return u;
            }

        }
        //  print();
        return -1;
    }

    private void enlarge() {
        int nextPrime = ht.length + 1;

        while (true) {
            boolean found = true;
            for (int i = 2; i < nextPrime; i++) {
                if (nextPrime % i == 0) {
                    found = false;
                    break;
                }


            }
            if (found) {
                break;
            }

            nextPrime++;
        }


        E[] nt = (E[]) Array.newInstance(elementType, nextPrime);
        E[] temp = ht;
        ht = nt;
        int old = numElements;
        for (int i = 0; i < temp.length; i++) {
            if (temp[i] != null) {
                numElements = 0;
                put(temp[i].getKey(), temp[i]);
            }
        }

        numElements = old;
    }


    public void empty() {
        numElements = 0;
        ht = (E[]) Array.newInstance(elementType, 7);
    }

    public void print() {
        System.out.println("Size:" + ht.length);
        for (int i = 0; i < ht.length; i++) {
            System.out.println(ht[i]);
            System.out.println();
        }
    }

}

