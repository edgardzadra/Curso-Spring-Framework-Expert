package com.zsol.brewer.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import com.zsol.brewer.service.CadastroCervejaService;
import com.zsol.brewer.storage.FotoStorage;
import com.zsol.brewer.storage.local.FotoStorageLocal;

@Configuration
@ComponentScan(basePackageClasses = CadastroCervejaService.class)
public class ServiceConfig {
	
	@Bean
	public FotoStorage fotoStorage(){
		return new FotoStorageLocal();
	}

}
