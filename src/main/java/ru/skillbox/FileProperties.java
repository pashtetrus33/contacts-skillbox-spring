package ru.skillbox;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Getter
@Setter
@Component
public class FileProperties {
    @Value("${contact.file.load-path}")
    private String loadPath;

    @Value("${contact.file.save-path}")
    private String savePath;
}
