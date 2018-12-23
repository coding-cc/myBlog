package com.cc.service.impl;

import com.cc.model.entity.Comments;
import com.cc.repository.CommentsRepository;
import com.cc.service.CommentsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

/**
 * @author : cc
 * @date : 2018-09-25  19:46
 */
@Service
public class CommentsServiceImpl implements CommentsService {

    @Autowired
    private CommentsRepository commentsRepository;

    @Override
    public Page<Comments> findAllByAuId(Integer id, PageRequest pageRequest) {
        return commentsRepository.findByAuthorId(id, pageRequest);
    }

    @Override
    public Comments add(Comments comments) {
        return commentsRepository.save(comments);
    }

    @Override
    public List<Comments> findAllByArticleId(Integer id) {
        return commentsRepository.findByCid(id);
    }

    @Override
    public Page<Comments> findAllByArticleId(Integer id, Pageable pageable) {

        return commentsRepository.findByCid(id, pageable);
    }

    @Override
    @Transactional
    public void deleteOne(Integer coid) {
        commentsRepository.deleteByCoid(coid);
    }

    @Override
    public void save(Comments comments) {
        commentsRepository.save(comments);
    }

    @Override
    public Optional<Comments> findById(Integer coid) {
        return commentsRepository.findById(coid);
    }
}
