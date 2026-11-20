package com.lipton.auth.models;

public record RegisterBody(String username, String password, String confirmPassword) {
}
