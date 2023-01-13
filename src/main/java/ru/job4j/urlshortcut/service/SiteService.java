package ru.job4j.urlshortcut.service;

import ru.job4j.urlshortcut.data.dto.SiteDto;

public interface SiteService {

    SiteDto register(String domainName);
}