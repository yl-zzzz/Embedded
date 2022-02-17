package project;

import org.ws4d.coap.core.rest.CoapResourceServer;


public class Mini_server {
	private static Mini_server coapServer;
	private CoapResourceServer resourceServer;
	
	public static void main(String[] args) {
		coapServer = new Mini_server();
		coapServer.start();
	}

	public void start() {
		System.out.println("===Run Test Server ===");

		// create server
		if (this.resourceServer != null)	this.resourceServer.stop();
		this.resourceServer = new CoapResourceServer();


		// initialize resource
		LCD_display lcd = new LCD_display();
		PIR_sensor pir = new PIR_sensor();
		Temp_sensor tmp = new Temp_sensor();
		pir.setObservable(true);
		
		// add resource to server
		this.resourceServer.createResource(lcd);
		this.resourceServer.createResource(pir);
		this.resourceServer.createResource(tmp);
		pir.registerServerListener(resourceServer);
		
		
		// run the server
		try {
			this.resourceServer.start();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		//Observe 한 클라이언트에게 1초에 한번씩 센서 값을 전달.
		while(true) {
			try {
				Thread.sleep(1000);
			}catch (Exception e) {
				// TODO: handle exception
			}
			pir.changed();			
		}
	}
}
