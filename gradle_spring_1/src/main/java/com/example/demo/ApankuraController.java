package com.example.demo;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

@RestController()
@RequestMapping("/apankura")
public class ApankuraController {

    @RequestMapping("/info")
    public Map<String, String> Information() throws SocketException {
        HashMap<String, String> map = new HashMap<>();

        Enumeration e = NetworkInterface.getNetworkInterfaces();
        while(e.hasMoreElements())
        {
            NetworkInterface n = (NetworkInterface) e.nextElement();
            Enumeration ee = n.getInetAddresses();
            while (ee.hasMoreElements())
            {
                InetAddress i = (InetAddress) ee.nextElement();
                System.out.println(i.getHostAddress());
            }
        }

        return map;
    }

    @RequestMapping("/liveness")
    public Map<String, String> Liveness() throws SocketException {
        Date date = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
        HashMap<String, String> map = new HashMap<>();
        map.put("date", formatter.format(date));
        return map;
    }
}
