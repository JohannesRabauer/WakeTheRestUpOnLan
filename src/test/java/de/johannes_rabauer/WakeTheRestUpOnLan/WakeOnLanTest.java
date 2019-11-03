package de.johannes_rabauer.WakeTheRestUpOnLan;

import static org.junit.Assert.fail;

import java.io.IOException;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class WakeOnLanTest {

	@Rule
	public final ExpectedException exception = ExpectedException.none();

	@Test
	public void testWakeUpOverIpGood1() {
		WakeOnLan wake = new WakeOnLan();
		try {
			wake.wakeUpOverIp("192.168.0.255", "00-0E-62-09-23-4B");
		} catch (IOException e) {
			fail(e.getMessage());
		}
	}
	
	@Test
	public void testWakeUpOverIpGood2() {
		WakeOnLan wake = new WakeOnLan();
		try {
			wake.wakeUpOverIp("192.168.0.255", "00:0E:62:09:23:4B");
		} catch (IOException e) {
			fail(e.getMessage());
		}
	}
	
	@Test
	public void testWakeUpOverIpBad1() {
		WakeOnLan wake = new WakeOnLan();
		try {
		    exception.expect(IllegalArgumentException.class);
			wake.wakeUpOverIp("192.168.0.255", "00/0E/62/09/23/4B");
		} catch (IOException e) {
		}
	}
	
	@Test
	public void testWakeUpOverIpBad2() {
		WakeOnLan wake = new WakeOnLan();
		try {
		    exception.expect(NullPointerException.class);
			wake.wakeUpOverIp("192.168.0.255", null);
		} catch (IOException e) {
		}
	}
	
	@Test
	public void testWakeUpOverIpBad3() {
		WakeOnLan wake = new WakeOnLan();
		try {
		    exception.expect(NullPointerException.class);
			wake.wakeUpOverIp(null, "00-0E-62-09-23-4B");
		} catch (IOException e) {
		}
	}

}
