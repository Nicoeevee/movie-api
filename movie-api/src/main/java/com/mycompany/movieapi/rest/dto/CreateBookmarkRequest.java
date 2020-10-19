package com.mycompany.movieapi.rest.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class CreateBookmarkRequest {

    @Schema(example = "tt0117998")
    @NotBlank
    private String imdb;

    @Schema(example = "iamaname")
    @NotBlank
    private String name;

    @Schema(example = "description")
    private String description;
}
