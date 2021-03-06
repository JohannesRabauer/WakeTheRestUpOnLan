package de.johannes_rabauer.WakeTheRestUpOnLan;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.logging.Logger;

public class WakeOnLan {
	public static final int PORT = 9;

	public void wakeUpOverIp(String ipAddress, String macAddress) throws IOException {
		if (ipAddress == null) 
		{
			throw new NullPointerException("IP-Address may not be null.");
		}
		if (macAddress == null) 
		{
			throw new NullPointerException("MAC-Address may not be null.");
		}
		byte[] macBytes = getMacBytes(macAddress);
		byte[] bytes = new byte[6 + 16 * macBytes.length];
		for (int i = 0; i < 6; i++) {
			bytes[i] = (byte) 0xff;
		}
		for (int i = 6; i < bytes.length; i += macBytes.length) {
			System.arraycopy(macBytes, 0, bytes, i, macBytes.length);
		}
		InetAddress address = InetAddress.getByName(ipAddress);
		DatagramPacket packet = new DatagramPacket(bytes, bytes.length, address, PORT);
		try (DatagramSocket socket = new DatagramSocket()) {
			socket.send(packet);
			socket.close();
			Logger.getLogger(WakeOnLan.class.getName()).info("Wake-on-LAN packet sent to ip " + address.toString()
					+ " on port " + PORT + " with MAC " + macAddress);
		}
	}

	private byte[] getMacBytes(String macStr) throws IllegalArgumentException {
		byte[] bytes = new byte[6];
		String[] hex = macStr.split("(\\:|\\-)");
		if (hex.length != 6) {
			throw new IllegalArgumentException("Invalid MAC address.");
		}
		try {
			for (int i = 0; i < 6; i++) {
				bytes[i] = (byte) Integer.parseInt(hex[i], 16);
			}
		} catch (NumberFormatException e) {
			throw new IllegalArgumentException("Invalid hex digit in MAC address.");
		}
		return bytes;
	}
}
