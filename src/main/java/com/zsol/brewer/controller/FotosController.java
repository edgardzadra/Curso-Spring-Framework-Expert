package com.zsol.brewer.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.async.DeferredResult;
import org.springframework.web.multipart.MultipartFile;

import com.zsol.brewer.dto.FotoDTO;
import com.zsol.brewer.storage.FotoStorage;
import com.zsol.brewer.storage.FotoStorageRunnable;

@RestController
@RequestMapping("/fotos")
public class FotosController {
	
	@Autowired
	private FotoStorage fotoStorage;
	
	@PostMapping
	public DeferredResult<FotoDTO> upload(@RequestParam("files[]") MultipartFile[] file){
		DeferredResult<FotoDTO> resultado = new DeferredResult<>();
		
		Thread thread = new Thread(new FotoStorageRunnable(file, resultado, fotoStorage));
		thread.start();

		return resultado;
	}
	
	@GetMapping("/temp/{nome:.*}")
	public byte[] recuperarFoto(@PathVariable String nome){
		return fotoStorage.recuperarFotoTemporaria(nome);
	}
	
	@GetMapping("/{nome:.*}")
	public byte[] recuperar(@PathVariable String nome){
		return fotoStorage.recuperarFoto(nome);
	}

}