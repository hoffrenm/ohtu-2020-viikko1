package ohtu.ohtuvarasto;

import org.junit.*;
import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class VarastoTest {

    Varasto varasto;
    double vertailuTarkkuus = 0.0001;

    @Before
    public void setUp() {
        varasto = new Varasto(10);
    }

    @Test
    public void konstruktoriLuoTyhjanVaraston() {
        assertEquals(0, varasto.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void uudellaVarastollaOikeaTilavuus() {
        assertEquals(10, varasto.getTilavuus(), vertailuTarkkuus);
    }

    @Test
    public void lisaysLisaaSaldoa() {
        varasto.lisaaVarastoon(8);

        // saldon pitäisi olla sama kun lisätty määrä
        assertEquals(8, varasto.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void lisaysLisaaPienentaaVapaataTilaa() {
        varasto.lisaaVarastoon(8);

        // vapaata tilaa pitäisi vielä olla tilavuus-lisättävä määrä eli 2
        assertEquals(2, varasto.paljonkoMahtuu(), vertailuTarkkuus);
    }

    @Test
    public void ottaminenPalauttaaOikeanMaaran() {
        varasto.lisaaVarastoon(8);
        
        double saatuMaara = varasto.otaVarastosta(2);

        assertEquals(2, saatuMaara, vertailuTarkkuus);
    }

    @Test
    public void ottaminenLisaaTilaa() {
        varasto.lisaaVarastoon(8);

        varasto.otaVarastosta(2);

        // varastossa pitäisi olla tilaa 10 - 8 + 2 eli 4
        assertEquals(4, varasto.paljonkoMahtuu(), vertailuTarkkuus);
    }
    
    @Test
    public void negatiivinenVarastoOnTyhja() {
        varasto = new Varasto(-10, 0);
        
        assertEquals(0, varasto.getTilavuus(), vertailuTarkkuus);
        assertEquals(-10, varasto.getSaldo(), vertailuTarkkuus);
    }
    
    @Test
    public void varastolleVoiAsettaaAlkusaldon() {
        varasto = new Varasto(15, 8);
        
        assertEquals(15, varasto.getTilavuus(), vertailuTarkkuus);
        assertEquals(8, varasto.getSaldo(), vertailuTarkkuus);
    }
    
    @Test
    public void negatiivinenTilavuusOnNolla() {
        varasto = new Varasto(-10);
        
        assertEquals(0, varasto.getTilavuus(), vertailuTarkkuus);
    }
    
    @Test
    public void negatiivinenAlkusadoOnNolla() {
        varasto = new Varasto(10, -10);
        
        assertEquals(10, varasto.getTilavuus(), vertailuTarkkuus);
        assertEquals(0, varasto.getSaldo(), vertailuTarkkuus);
    }
    
    @Test
    public void luodunVarastonSaldoOnMaxTilavuus() {
        varasto = new Varasto(15, 23);
        
        assertEquals(15, varasto.getTilavuus(), vertailuTarkkuus);
        assertEquals(15, varasto.getSaldo(), vertailuTarkkuus);
    }
    
    @Test
    public void negatiivinenOttoPalauttaaNollan() {
        assertEquals(0, varasto.otaVarastosta(-10), vertailuTarkkuus);
    }
    
    @Test
    public void voiOttaaMaxSaldonVerran() {
        varasto.lisaaVarastoon(10);
        
        assertEquals(10, varasto.otaVarastosta(25), vertailuTarkkuus);
    }
    
    @Test
    public void ylimaarainenLisaysOnMaxTilavuus() {
        varasto.lisaaVarastoon(34);
        
        assertEquals(10, varasto.getSaldo(), vertailuTarkkuus);
    }
    
    @Test
    public void negatiivinenLisaysEiMuutaSaldoa() {
        varasto.lisaaVarastoon(-6);
        
        assertEquals(0, varasto.getSaldo(), vertailuTarkkuus);
    }
    
    @Test
    public void merkkijonoesitysOnOikein() {
        varasto = new Varasto(12, 8);
        
        assertEquals(varasto.toString(), "saldo = 8.0, vielä tilaa 4.0");
    }
}