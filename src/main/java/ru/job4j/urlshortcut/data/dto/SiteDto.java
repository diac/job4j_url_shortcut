package ru.job4j.urlshortcut.data.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SiteDto {

    private String domainName;
    private String login;
    private char[] password;
}