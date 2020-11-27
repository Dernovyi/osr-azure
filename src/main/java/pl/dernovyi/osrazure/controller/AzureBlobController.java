package pl.dernovyi.osrazure.controller;


import com.microsoft.azure.storage.StorageException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import pl.dernovyi.osrazure.model.ImageDto;
import pl.dernovyi.osrazure.model.RequestPicture;
import pl.dernovyi.osrazure.service.ServiceImages;
import pl.dernovyi.osrazure.service.StoreageService;


import java.net.URI;
import java.net.URISyntaxException;


@RestController
@RequestMapping("/drop")
@CrossOrigin(origins = "https://osr-angular.herokuapp.com")
//@CrossOrigin(origins = "http://localhost:4200")
public class AzureBlobController {

    private final StoreageService service;
    private final ServiceImages serviceImages;

    @Autowired
    public AzureBlobController(StoreageService service, ServiceImages serviceImages) {
        this.service = service;
        this.serviceImages = serviceImages;
    }

    @PostMapping
    public ResponseEntity<ImageDto> get(@RequestParam("file") MultipartFile multipartFile) throws URISyntaxException, StorageException {

        URI uri = service.saveToStorage(multipartFile);
        RequestPicture picture = new RequestPicture();
        String u = uri.toString();
        picture.setUrl(u);


        ImageDto imageDto = serviceImages.saveNewImage(picture);
        if(imageDto!=null){
            return new ResponseEntity<>(imageDto, HttpStatus.OK);
        }

        return new ResponseEntity<>(HttpStatus.CONFLICT);
    }

    //    @RestController
//    public class FileDownload {
//        @GetMapping(path="/download-file")
//        public ResponseEntity<Resource> getFile(){
//            String fileName = "Can not find symbol.docx";
//            //azure credentials
//            String connection_string = "Connection String of storage account on azure";
//
//            BlobServiceClient blobServiceClient = new BlobServiceClientBuilder().connectionString(connection_string).buildClient();
//
//            BlobContainerClient containerClient= blobServiceClient.getBlobContainerClient("Container name");
//            System.out.println(containerClient.getBlobContainerName());
//
//            BlobClient blob = containerClient.getBlobClient(fileName);
//
//            //creating an object of output stream to recieve the file's content from azure blob.
//            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
//            blob.download(outputStream);
//
//            //converting it to the inputStream to return
//            final byte[] bytes = outputStream.toByteArray();
//            ByteArrayInputStream inputStream = new ByteArrayInputStream(bytes);
//            ByteArrayResource resource = new ByteArrayResource(bytes);
//            HttpHeaders headers = new HttpHeaders();
//            headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + fileName + "\"");
//
//            return ResponseEntity.ok().contentType(MediaType.APPLICATION_OCTET_STREAM)
//                    .headers(headers)
//                    .body(resource);
//        }
//
//    }

//    @PostMapping
//    public ResponseEntity uploadFile(@RequestParam MultipartFile multipartFile) throws URISyntaxException, InvalidKeyException, StorageException, IOException {
//        FileInfo fileInfo = new FileInfo();
//        if (!multipartFile.isEmpty()) {
//
//            CloudStorageAccount storageAccount = CloudStorageAccount.parse(myInfo.getDefaultEndpointsProtocol());
//            CloudBlobClient client = storageAccount.createCloudBlobClient();
//            CloudBlobContainer image = client.getContainerReference("image");
//            CloudBlockBlob blockBlobReference = image.getBlockBlobReference(Objects.requireNonNull(multipartFile.getOriginalFilename()));
//            blockBlobReference.upload(multipartFile.getInputStream(), -1);
//            URI uri = blockBlobReference.getUri();
//            return ResponseEntity.ok(uri);
//        }
//        return new ResponseEntity(HttpStatus.NO_CONTENT);
//    }

}
