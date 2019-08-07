package com.github.xenteros.hashset;

import org.junit.Test;

import java.util.Set;

import static org.junit.Assert.assertEquals;

public class StringHashSetTest {

    @Test
    public void shouldReturnCountOfElementsInserted() {

        Set<String> set = new StringHashSet();
        set.add("Adam");
        set.add("Asia");
        set.add("Aleks");
        set.add("Rafał");
        set.add("Łukasz");
        set.add("Adrian");

        assertEquals(6, set.size());

    }


    @Test
    public void givenEmptyList_WhenAskingForListSizeBeforeAddingAnything_ThenReturn0() {
        Set<String> set = new StringHashSet();
        assertEquals(0, set.size());
    }

    @Test
    public void shouldReturn0ForEmptiedSet(){
        Set<String> set = new StringHashSet();
        set.add("Adam");
        set.remove("Adam");
        assertEquals(0, set.size());
    }
}