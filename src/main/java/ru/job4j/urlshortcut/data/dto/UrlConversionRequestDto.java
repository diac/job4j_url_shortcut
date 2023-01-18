package ru.job4j.urlshortcut.data.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * DTO для обработки входящих запросов на конвертацию URL
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UrlConversionRequestDto {

    /**
     * URL
     */
    @NotNull(message = "URL cannot be null")
    @NotBlank(message = "URL cannot be blank")
    private String url;
}