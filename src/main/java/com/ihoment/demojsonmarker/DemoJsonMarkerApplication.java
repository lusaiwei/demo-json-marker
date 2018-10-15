package com.ihoment.demojsonmarker;

import com.ihoment.demojsonmarker.app.MainClient;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DemoJsonMarkerApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoJsonMarkerApplication.class, args);
		run();
	}

	private static void run() {
		MainClient client = MainClient.init();
		client.start();

	}


}
