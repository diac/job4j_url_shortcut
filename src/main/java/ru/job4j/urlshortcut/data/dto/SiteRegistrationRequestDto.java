package ru.job4j.urlshortcut.data.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * DTO для обработки входящих запросов на регистрацию сайта
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SiteRegistrationRequestDto {

    /**
     * Доменное имя сайта
     */
    @NotNull(message = "Site cannot be null")
    @NotBlank(message = "Site cannot be blank")
    private String site;
}