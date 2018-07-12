package com.zsol.brewer.storage;

import org.springframework.web.context.request.async.DeferredResult;
import org.springframework.web.multipart.MultipartFile;

import com.zsol.brewer.dto.FotoDTO;

public class FotoStorageRunnable implements Runnable {

	private MultipartFile[] file;
	private DeferredResult<FotoDTO> resultado;
	private FotoStorage fotoStorage;
	
	public FotoStorageRunnable(MultipartFile[] file, DeferredResult<FotoDTO> resultado, FotoStorage fotoStorage) {
		super();
		this.file = file;
		this.resultado = resultado;
		this.fotoStorage = fotoStorage;
	}



	@Override
	public void run() {
			
		String nomeFoto = this.fotoStorage.salvarTemporariamente(file);	
		String contentType = file[0].getContentType();
		resultado.setResult(new FotoDTO(nomeFoto, contentType));
	}

}
