package com.educoach.chat;

import java.util.List;

public record ChatGptResponse(List<ChatGptChoice> choices) { }