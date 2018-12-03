package com.analistas.proyectoCAFD;

import com.analistas.proyectoCAFD.service.IUploadFileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ProyectoCafdApplication {
    
        @Autowired
	IUploadFileService uploadFileService;

	public static void main(String[] args) {
		SpringApplication.run(ProyectoCafdApplication.class, args);
	}
        
	public void run(String... args) throws Exception {
		// TODO Auto-generated method stub
		uploadFileService.deleteAll();
		uploadFileService.init();
	}
}
