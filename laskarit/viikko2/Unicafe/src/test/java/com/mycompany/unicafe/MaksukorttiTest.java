package com.mycompany.unicafe;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

public class MaksukorttiTest {

    Maksukortti kortti;

    @Before
    public void setUp() {
        kortti = new Maksukortti(1000);
    }

    @Test
    public void kostruktoriAsettaaSaldonOikein() {
        assertEquals("saldo: 10.0", kortti.toString());
    }

    @Test
    public void kortilleVoiLadataRahaa() {
        kortti.lataaRahaa(2500);
        assertEquals("saldo: 35.0", kortti.toString());
    }

    @Test
    public void kortinSaldoEiMuutuJosRahaEiRiita() {
        kortti.otaRahaa(1100);
        assertEquals("saldo: 10.0", kortti.toString());
    }

    @Test
    public void saldoPalauttaaSaldonTuhat() {
        assertEquals(1000, kortti.saldo());
    }

    @Test
    public void otaRahaaPalauttaaTrue() {
        assertTrue(kortti.otaRahaa(100));

    }

    @Test
    public void otaRahaaPalauttaaFalse() {
        assertFalse(kortti.otaRahaa(1100));

    }

    @Test
    public void luotuKorttiOlemassa() {
        assertTrue(kortti != null);
    }
}
