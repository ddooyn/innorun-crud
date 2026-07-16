package org.practice.springcrud.memo.controller;

import lombok.RequiredArgsConstructor;
import org.practice.springcrud.memo.dto.*;
import org.practice.springcrud.memo.service.MemoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class MemoController {
    private final MemoService memoService;

    @PostMapping("/memos")
    public ResponseEntity<MemoCreateResponse> create(
            @RequestBody MemoCreateRequest request
    ) {
        MemoCreateResponse result = memoService.save(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(result);
    }

    @GetMapping("/memos")
    public ResponseEntity<List<MemoGetResponse>> getAll() {
        List<MemoGetResponse> result = memoService.getAll();
        return ResponseEntity.ok(result);
    }

    @GetMapping("/memos/{memoId}")
    public ResponseEntity<MemoGetResponse> getOne(
            @PathVariable Long memoId
    ) {
        MemoGetResponse result = memoService.getOne(memoId);
        return ResponseEntity.ok(result);
    }

    @PutMapping("/memos/{memoId}")
    public ResponseEntity<MemoUpdateResponse> update(
            @PathVariable Long memoId,
            @RequestBody MemoUpdateRequest request
    ) {
        MemoUpdateResponse result = memoService.update(memoId, request);
        return ResponseEntity.ok(result);
    }

    @DeleteMapping("/memos/{memoId}")
    public ResponseEntity<Void> delete(
            @PathVariable Long memoId
    ) {
        memoService.delete(memoId);
        return ResponseEntity.noContent().build();
    }
}