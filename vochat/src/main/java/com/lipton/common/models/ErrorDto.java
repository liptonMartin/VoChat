package com.lipton.common.models;

import org.springframework.http.HttpStatusCode;

public record ErrorDto(boolean success, String reason, HttpStatusCode status) {
}
