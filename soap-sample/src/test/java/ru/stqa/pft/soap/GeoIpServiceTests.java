package ru.stqa.pft.soap;

import com.lavasoft.GeoIPService;
import org.testng.annotations.Test;


public class GeoIpServiceTests {

  @Test
  public void testMyIp() {
    String ipLocation = new GeoIPService().getGeoIPServiceSoap12().getIpLocation("46.146.16.99");
    System.out.println("ipLocation " + ipLocation);
    //String ipLocation = new GeoIPService().getGeoIPServiceSoap12().getIpLocation("46.146.16.99");
    //assertEquals(ipLocation,"RUS");
 //MatcherAssert.(ipLocation, hasItem("RU"));
   // assertThat(ipLocation, "RU");

  }
}
