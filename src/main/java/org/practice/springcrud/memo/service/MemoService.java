package org.practice.springcrud.memo.service;

import lombok.RequiredArgsConstructor;
import org.practice.springcrud.memo.dto.*;
import org.practice.springcrud.memo.entity.Memo;
import org.practice.springcrud.memo.repository.MemoRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MemoService {
    private final MemoRepository memoRepository;

    @Transactional
    public MemoCreateResponse save(MemoCreateRequest request) {
        Memo memo = new Memo(request.getContent());
        memoRepository.save(memo);

        return new MemoCreateResponse(
                memo.getId(), memo.getContent()
        );
    }

    @Transactional(readOnly = true)
    public List<MemoGetResponse> getAll() {
        List<Memo> memos = memoRepository.findAll();

        return memos.stream()
                .map(memo -> new MemoGetResponse(
                        memo.getId(), memo.getContent()
                )).toList();
    }

    @Transactional(readOnly = true)
    public MemoGetResponse getOne(Long memoId) {
        Memo memo = memoRepository.findById(memoId)
                .orElseThrow(() -> new IllegalStateException(""));

        return new MemoGetResponse(
                memo.getId(), memo.getContent()
        );
    }

    @Transactional
    public MemoUpdateResponse update(Long memoId, MemoUpdateRequest request) {
        Memo memo = memoRepository.findById(memoId)
                .orElseThrow(() -> new IllegalStateException(""));

        memo.changeContent(request.getContent());

        return new MemoUpdateResponse(
                memo.getId(), memo.getContent()
        );
    }

    @Transactional
    public void delete(Long memoId) {
        boolean exists = memoRepository.existsById(memoId);

        if (!exists) {
            throw new IllegalStateException("");
        }

        memoRepository.deleteById(memoId);
    }
}