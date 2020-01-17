package telran.m2m.service;

import java.io.IOException;

import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Sink;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import telran.m2m.dto.Sensor;

@EnableBinding(Sink.class)
public class SensorAbnormalBloodPressureSink {
	ObjectMapper mapper = new ObjectMapper();

	@StreamListener(Sink.INPUT)
	public void recieveRensorData(String sensorData) throws JsonParseException, JsonMappingException, IOException {
		Sensor sensor = mapper.readValue(sensorData, Sensor.class);
		printJsonSensor(sensor);
	}

	private void printJsonSensor(Sensor sensor) {
		System.out.println("sensor number : " + sensor.id);
		System.out.println("data time : " + sensor.time);
		System.out.println("UPPER BLOOD PRESSURE: " + sensor.dataUBP);
		System.out.println("LOWER BLOOD PRESSURE: " + sensor.dataLBP);
		System.out.println("puls : " + sensor.dataPuls);
		System.out.println("sugar in blood : " + sensor.dataSugar);
		System.out.println("*********************************************");

	}
}
