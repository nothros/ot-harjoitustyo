package com.mycompany.unicafe;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;


public class KassapaateTest {
    Kassapaate kassa;

    
    @Before
    public void setUp() {
        kassa = new Kassapaate();

    }

    // Kun ohjelma avataan
    @Test
    public void kassassaOikeaMaaraRahaaAlussa(){   
        assertEquals(100000, kassa.kassassaRahaa());
    }
    
    @Test
    public void lounaitaSyotyNollaAlussa(){
        assertEquals(0,kassa.maukkaitaLounaitaMyyty()+kassa.edullisiaLounaitaMyyty());
    }
    
    @Test
    public void luotuKassaOnOlemassa() {
        assertTrue(kassa!=null);      
    }
    
    //Korttiostoon liittyvät laskut kun maksu onnistuu
    @Test
    public void maukkaanLounaanMyyntiOnnistuuKortilla(){
        Maksukortti kortti = new Maksukortti(400);
        boolean onnistui=kassa.syoMaukkaasti(kortti);
        assertEquals(100000,kassa.kassassaRahaa());
        assertTrue(onnistui);
        assertEquals(1,kassa.maukkaitaLounaitaMyyty());
        assertEquals(0, kortti.saldo());
    }
    
    @Test
    public void edullisenLounaanMyyntiOnnistuuKortilla(){
        Maksukortti kortti = new Maksukortti(240);
        boolean onnistui=kassa.syoEdullisesti(kortti);
        assertEquals(100000,kassa.kassassaRahaa());
        assertTrue(onnistui);
        assertEquals(1,kassa.edullisiaLounaitaMyyty());
        assertEquals(0, kortti.saldo());
    }
    
        //Korttiostoon liittyvät testi kun maksu epäonnistuu
    @Test
    public void maukkaanLounaanMyyntiEpaonnistuuKortilla(){
        Maksukortti kortti = new Maksukortti(300);
        boolean epaonnistui=kassa.syoMaukkaasti(kortti);
        assertEquals(100000,kassa.kassassaRahaa());
        assertFalse(epaonnistui);
        assertEquals(0,kassa.maukkaitaLounaitaMyyty());
        assertEquals(300, kortti.saldo());
    }
    
    @Test
    public void edullisenLounaanMyyntiEpaonnistuuKortilla(){
        Maksukortti kortti = new Maksukortti(140);
        boolean epaonnistui=kassa.syoEdullisesti(kortti);
        assertEquals(100000,kassa.kassassaRahaa());
        assertFalse(epaonnistui);
        assertEquals(0,kassa.edullisiaLounaitaMyyty());
        assertEquals(140, kortti.saldo());
    }
    
    @Test
    public void lataaRahaaOnnistuu(){
        Maksukortti kortti = new Maksukortti(0);
        kassa.lataaRahaaKortille(kortti, 1);
        assertEquals(100001,kassa.kassassaRahaa());
        assertEquals(1,kortti.saldo());
  
    }
    
     @Test
    public void lataaRahaaepaOnnistuu(){
        Maksukortti kortti = new Maksukortti(0);
        kassa.lataaRahaaKortille(kortti, -1);
        assertEquals(100000,kassa.kassassaRahaa());
        assertEquals(0,kortti.saldo());
  
    }
    
    
    


}
