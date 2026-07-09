package org.practice.springcrud.memo.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class MemoCreateResponse {
    private final Long id;
    private final String content;
}