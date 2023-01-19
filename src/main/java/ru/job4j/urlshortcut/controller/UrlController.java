package ru.job4j.urlshortcut.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.job4j.urlshortcut.dto.UrlConversionRequestDto;
import ru.job4j.urlshortcut.dto.UrlConversionResponseDto;
import ru.job4j.urlshortcut.model.Site;
import ru.job4j.urlshortcut.model.Url;
import ru.job4j.urlshortcut.service.RedirectsLogService;
import ru.job4j.urlshortcut.service.SiteService;
import ru.job4j.urlshortcut.service.UrlService;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.security.Principal;

/**
 * Контроллер, реализующий доступ к объектам модели Url через REST
 */
@RestController
@AllArgsConstructor
public class UrlController {

    /**
     * Сервис, реализующий логику работы с объектами модели Url
     */
    private final UrlService urlService;

    /**
     * Сервис, реализующий логику работы с объектами модели Site
     */
    private final SiteService siteService;

    /**
     * Сервис, реализующий логику, связанную с объектами модели RedirectsLog
     */
    private final RedirectsLogService redirectsLogService;

    /**
     * Сконвертировать передаваемый URL в сокращенный формат
     *
     * @return DTO, содержащий данные сокращенного URL
     */
    @PostMapping("/convert")
    public ResponseEntity<UrlConversionResponseDto> convert(
            @RequestBody @Valid UrlConversionRequestDto requestDto,
            Principal principal
    ) {
        Site site = siteService.findByLogin(principal.getName());
        Url url = urlService.convert(requestDto.getUrl(), site);
        UrlConversionResponseDto responseDto = new UrlConversionResponseDto(url.getShortUrl());
        return new ResponseEntity<>(
                responseDto,
                HttpStatus.CREATED
        );
    }

    @GetMapping("/redirect/{shortUrl}")
    public ResponseEntity<String> redirect(@PathVariable String shortUrl, HttpServletRequest request) {
        Url url = urlService.getByShortUrl(shortUrl);
        redirectsLogService.register(url, request.getRemoteAddr());
        return new ResponseEntity<>(
                url.getFullUrl(),
                HttpStatus.FOUND
        );
    }
}