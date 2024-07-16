package com.egbas.job_board.service.impl;

import com.egbas.job_board.Utils.FileUtils;
import com.egbas.job_board.domain.entity.FileData;
import com.egbas.job_board.repository.FileRepository;
import com.egbas.job_board.service.StorageService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
@RequiredArgsConstructor
public class StorageServiceImpl implements StorageService {
    private final FileRepository fileRepository;
    @Override
    public String uploadFile(MultipartFile file) throws IOException {

        FileData fileData = FileData.builder()
                .name(file.getOriginalFilename())
                .type(file.getContentType())
                .imageData(FileUtils.compressFile(file.getBytes()) )
                .build();

        fileRepository.save(fileData);
        return "File uploaded successfully";
    }

    @Override
    public byte[] downloadFile(String filename) {
        FileData fileData = fileRepository.findByName(filename)
                .orElseThrow(() -> new RuntimeException("Filename "+ filename +" not found"));

        byte[] file = FileUtils.decompressFile(fileData.getImageData()) ;
        return file;
    }

}
