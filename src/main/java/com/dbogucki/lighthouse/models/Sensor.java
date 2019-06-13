package com.dbogucki.lighthouse.models;

import com.dbogucki.lighthouse.enums.SensorType;

import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

import java.io.*;

public class Sensor {
    private static int seq = 0;

    private int sensorId;
    private String name;
    private SensorType type;
    private String[] connParam;
    private double value;

    public Sensor(String name, SensorType type, String... params) {
        this.sensorId = seq++;
        this.name = name;
        this.type = type;
        connParam = params;
        value = 0;
    }

    public Sensor(SensorType type, String... params) {
        this(type.toString(), type, params);
    }

    public double getValue() throws IOException, InterruptedException {

        Resource resource = new ClassPathResource(type.getPath());
        File file = resource.getFile();
        String path = file.getAbsolutePath();

        try {
            ProcessBuilder pb = new ProcessBuilder("sudo", "/usr/bin/python", path);
            pb.redirectErrorStream(true);
            Process proc = pb.start();
            int exitCode = proc.waitFor();
            if (exitCode == 0) {
                try (BufferedReader br = new BufferedReader(new InputStreamReader(proc.getInputStream()))) {
                    String line;
                    while ((line = br.readLine()) != null) {
                        value = Double.parseDouble(line);
                    }
                }
            }
        } catch (IOException e) {
        }

        return value;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public SensorType getType() {
        return type;
    }

    public void setType(SensorType type) {
        this.type = type;
    }

    public String[] getConnParam() {
        return connParam;
    }

    public void setConnParam(String[] connParam) {
        this.connParam = connParam;
    }

    public int getSensorId(){
        return sensorId;
    }
}
