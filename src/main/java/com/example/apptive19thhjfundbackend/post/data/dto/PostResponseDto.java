package com.example.apptive19thhjfundbackend.post.data.dto;

import com.example.apptive19thhjfundbackend.file.entity.ContentFile;
import com.example.apptive19thhjfundbackend.post.data.entity.Post;
import lombok.Getter;
import org.springframework.data.domain.Sort;
import org.springframework.web.multipart.MultipartFile;

import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Getter
public class PostResponseDto {
    private Long id;
    private String title;
    private String ticker;
    private String content;
    private String thumb;
    private String author;
    private List<String> files;
    private int view;

    public PostResponseDto(Post entity) {
        this.id = entity.getId();
        this.title = entity.getTitle();
        this.ticker = entity.getTicker();
        this.content = entity.getContent();
        this.thumb = entity.getThumb();
        this.author = entity.getAuthor().getNickName();
        if(entity.getContentFiles() != null) {
            List<ContentFile> contentFiles = entity.getContentFiles();
            Collections.sort(contentFiles);
            this.files =  contentFileToUrl(contentFiles);
        }
        this.view = entity.getViews();
    }

    private List<String> contentFileToUrl(List<ContentFile> contentFiles) {
        return contentFiles.stream()
                    .map(c -> c.getUrl())
                    .collect(Collectors.toList());
    }

}