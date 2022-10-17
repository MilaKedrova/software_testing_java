package ru.stqa.pft.soap;

import com.lavasoft.GeoIPService;
import org.testng.annotations.Test;

import static org.testng.AssertJUnit.assertEquals;

public class GeoIpServiceTests {

    @Test
    public void testMyIp() {
        String geoIp = new GeoIPService().getGeoIPServiceSoap12().getIpLocation("62.217.188.64");
        assertEquals(geoIp, "RUS");
        System.out.println(geoIp);
    }

    @Test
    public void testInvalidIp() {
        String geoIp = new GeoIPService().getGeoIPServiceSoap12().getIpLocation("62.217.188.xxx");
        assertEquals(geoIp, "RUS");
        System.out.println(geoIp);
    }
}
