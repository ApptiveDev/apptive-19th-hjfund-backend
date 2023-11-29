package com.example.apptive19thhjfundbackend.S3;

import com.amazonaws.AmazonServiceException;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.*;
import com.example.apptive19thhjfundbackend.file.entity.ContentFile;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.util.Objects;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ImageS3Service{
    private final AmazonS3Client amazonS3Client;

    @Value("${cloud.aws.s3.bucketName}")
    private String bucketName; //버킷 이름

    @Value("${cloud.aws.region.static}")
    private String region;

    @Value("cloud.aws.credentials.accessKey")
    private String accessKey;

    @Value("cloud.aws.credentials.secretKey")
    private String secretKey;

    private String changedImageName(String originName) { //이미지 이름 중복 방지를 위해 랜덤으로 생성
        String random = UUID.randomUUID().toString();
        return random+originName;
    }

    public String uploadImageToS3(MultipartFile image) throws Exception { //이미지를 S3에 업로드하고 이미지의 url을 반환
        String originName = image.getOriginalFilename(); //원본 이미지 이름
        String contentType = image.getContentType(); //확장자
        String fileExtension;

        //jpeg, jpg, png만 허용
        if (Objects.isNull(contentType)) {
            throw new Exception("정상적인 파일이 아닙니다.");
        }
        else if (contentType.contains("image/jpeg") ||
                contentType.contains("image/jpg"))
            fileExtension = "jpg";
        else if (contentType.contains("image/png"))
            fileExtension = "png";
        else throw new Exception("dd");

        String changedName = changedImageName(originName); //새로 생성된 이미지 이름
        ObjectMetadata metadata = new ObjectMetadata(); //메타데이터
        metadata.setContentType("image/"+fileExtension);
        String url = null;
        try {
            metadata.setContentLength(image.getBytes().length);
            PutObjectResult putObjectResult = amazonS3Client.putObject(new PutObjectRequest(
                    bucketName, "images/" + changedName, image.getInputStream(), metadata
            ).withCannedAcl(CannedAccessControlList.PublicRead));
            /*
            File file = new File(image.getOriginalFilename());
            image.transferTo(file);
            PutObjectResult putObjectResult = amazonS3.putObject(bucketName, "images/" + changedName, file);
             */
            url = amazonS3Client.getUrl(bucketName, "images/" + changedName).toString();
        } catch (IOException | AmazonS3Exception e) {
            throw new Exception("이미지 저장 중 오류가 발생했습니다.");
        }
        return url; //데이터베이스에 저장할 이미지가 저장된 주소

    }


    public ContentFile uploadImage(MultipartFile image, int order) throws Exception {
        String originName = image.getOriginalFilename();
        String contentType = image.getContentType(); //확장자
        String storedImagePath = uploadImageToS3(image);

        ContentFile fileEntity = ContentFile.builder()
                    .type(contentType)
                    .order(order)
                    .name(originName)
                    .url(storedImagePath)
                    .build();
        return fileEntity;
    }

    public void deleteImageFromS3(String fileUrl) throws Exception{
        if (!fileUrl.contains("tripko-be6.s3.ap-northeast-2.amazonaws.com")) {
            return;
        }
        try{
            String fileKey = fileUrl.substring(51); // 폴더/파일.확장자
            String key = URLDecoder.decode(fileKey, StandardCharsets.UTF_8);

            boolean isObjectExist = amazonS3Client.doesObjectExist(bucketName, key);

            if (isObjectExist) {
                try {
                    amazonS3Client.deleteObject(new DeleteObjectRequest(bucketName, key));
                } catch (AmazonServiceException e) {
                    throw e;
                }
            } else {
                throw new Exception("dd");
            }

            System.out.println(String.format("[%s] deletion complete", key));

        } catch (Exception e) {
            throw e;
        }
    }

}