    package com.seesun.andand.garden.dto.request;


    import lombok.AllArgsConstructor;
    import lombok.Getter;
    import lombok.NoArgsConstructor;
    import org.springframework.web.multipart.MultipartFile;

    @Getter
    @AllArgsConstructor
    @NoArgsConstructor
    public class GardenUpdateRequest {

        private String content;

        private MultipartFile picture;


    }
