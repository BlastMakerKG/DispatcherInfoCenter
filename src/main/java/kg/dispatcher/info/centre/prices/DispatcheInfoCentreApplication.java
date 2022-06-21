package kg.dispatcher.info.centre.prices;

import kg.dispatcher.info.centre.prices.UI.Frame;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

import java.awt.*;

@SpringBootApplication
public class DispatcheInfoCentreApplication implements CommandLineRunner {

	@Autowired
	Frame frame;

	public static void main(String[] args) {
		new SpringApplicationBuilder(DispatcheInfoCentreApplication.class).headless(false).run(args);
	}

	@Override
	public void run(String... args) {
		frame.start();
	}
}
