package de.johannes_rabauer.WakeTheRestUpOnLan;

import static org.junit.Assert.*;

import java.io.IOException;

import org.junit.Test;
import org.junit.runners.JUnit4;

public class WakeOnLanTest {

	@Test
	public void testWakeUpOverIp() {
		WakeOnLan wake = new WakeOnLan();
		try {
			wake.wakeUpOverIp("192.168.0.255", "00-0E-62-09-23-4B");
		} catch (IOException e) {
			fail(e.getMessage());
		}
	}

}
