package com.example.uploadfile.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Item {
    private Long id;
    private String itemName;
    private LocalDateTime createDate;
    private LocalDateTime changeDate;
    private String type;
}
