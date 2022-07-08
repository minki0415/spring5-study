package com.example.sp5chapb;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.OffsetDateTime;

public class Now {

    @JsonFormat(pattern="yyyy-MM-dd'T'HH:mmssZ")
    private OffsetDateTime time;

    public Now() {
        time = OffsetDateTime.now();
    }

    public OffsetDateTime getTime() {
        return time;
    }

}
