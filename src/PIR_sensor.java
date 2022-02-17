package project;

import java.util.List;

import org.ws4d.coap.core.enumerations.CoapMediaType;
import org.ws4d.coap.core.rest.BasicCoapResource;
import org.ws4d.coap.core.rest.CoapData;
import org.ws4d.coap.core.tools.Encoder;

import com.pi4j.io.gpio.GpioController;
import com.pi4j.io.gpio.GpioFactory;
import com.pi4j.io.gpio.GpioPinDigitalInput;
import com.pi4j.io.gpio.RaspiPin;

import project.I2CLCD;

public class PIR_sensor extends BasicCoapResource{
	private String state = "Not detected";
	GpioController gpio = GpioFactory.getInstance();
	GpioPinDigitalInput pir = gpio.provisionDigitalInputPin(RaspiPin.GPIO_00);
	

	
	private PIR_sensor(String path, String value, CoapMediaType mediaType) {
		super(path, value, mediaType);
	}

	public PIR_sensor() {
		this("/pir", "Not detected", CoapMediaType.text_plain);
	}

	@Override
	public synchronized CoapData get(List<String> query, List<CoapMediaType> mediaTypesAccepted) {
		return get(mediaTypesAccepted);
	}
	
	@Override
	public synchronized CoapData get(List<CoapMediaType> mediaTypesAccepted) {
		boolean temp_state = pir.isHigh();
		if (temp_state==true) {
			this.state = "Detected";
		}
		else {
			this.state = "Not detected";
		}
		return new CoapData(Encoder.StringToByte(this.state), CoapMediaType.text_plain);
	}

	public synchronized void optional_changed() {		
		boolean temp_state = pir.isHigh();
		if (temp_state==true) {
			if (this.state.equals("Detected")) {
				System.out.println("The state has not been changed(Detected)");
			}
			else {
				System.out.println("Detected!");
				this.state = "Detected";
				this.changed(this.state);
			}
		}
		else {
			if (this.state.equals("Not Detected")) {
				System.out.println("The state has not been changed (Not Detected)");
			}
			else {
				System.out.println("Not Detected!");
				this.state = "Not Detected";
				this.changed(this.state);
			}
		}
		
	}
	
	
	@Override
	public synchronized boolean setValue(byte[] value) {
		this.state = Encoder.ByteToString(value);
		return true;
	}	
	
	@Override
	public synchronized boolean post(byte[] data, CoapMediaType type) {
		return this.setValue(data);
	}

	@Override
	public synchronized boolean put(byte[] data, CoapMediaType type) {
		return this.setValue(data);
	}
	
	@Override
	public synchronized String getResourceType() {
		return "Raspberry pi 4 Temperature Sensor";
	}
}
