package com.paupupu.common.inet;

import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Enumeration;

public class Inet {
    static public String getHostAddr(String networkInterfaceName) throws SocketException {
        Enumeration<NetworkInterface> networkInterfaces = NetworkInterface.getNetworkInterfaces();
        while (networkInterfaces.hasMoreElements()){
            NetworkInterface networkInterface = networkInterfaces.nextElement();
            if(networkInterface.getName().equals(networkInterfaceName)){
                Enumeration<InetAddress> inetAddresses = networkInterface.getInetAddresses();
                while (inetAddresses.hasMoreElements()){
                    InetAddress inetAddresse = inetAddresses.nextElement();
                    if(inetAddresse instanceof Inet4Address){
//                        System.out.println(inetAddresse.getHostAddress());
                        return inetAddresse.getHostAddress();
                    }
                }
                break;
            }
        }
        return null;
    }
}
