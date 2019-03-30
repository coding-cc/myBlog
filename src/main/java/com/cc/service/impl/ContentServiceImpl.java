package com.cc.service.impl;

import com.cc.exception.BlogException;
import com.cc.model.entity.Contents;
import com.cc.repository.ContentRepository;
import com.cc.service.ContentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

/**
 * @author : cc
 * @date : 2018-09-18  13:37
 */
@Service
public class ContentServiceImpl implements ContentService {

    @Autowired
    private ContentRepository repository;


    @Override
    public Contents findById(Integer cid) {
        Optional<Contents> optional = repository.findById(cid);
        if(!optional.isPresent()){
            throw new BlogException("博客不存在");
        }
        return optional.get();
    }

    @Override
    public Contents addOne(Contents contents) {
        return repository.save(contents);
    }

    @Override
    public Contents save(Contents content) {
        return repository.save(content);
    }

    @Override
    @Transactional
    public void deleteOne(Integer cid) {
        repository.deleteByCid(cid);
    }

    @Override
    public Page<Contents> findByKeyword(String keyword, Pageable pageable) {
        return repository.findByTitleLike(keyword, pageable);
    }

    @Override
    public int countByTag(String tag) {
        return repository.countByTags(tag);
    }

    @Override
    public int countByCategory(String category) {
        return repository.countByCategories(category);
    }

    @Override
    public Page<Contents> pageByCategory(String category, Pageable pageable) {
        return repository.findByCategories(category, pageable);
    }

    @Override
    public Page<Contents> pageByMetaId(Integer mid, Pageable pageable) {
        return repository.pageByMetaId(mid, pageable);
    }

    @Override
    public List<Contents> findAllByAuthorId(Integer id) {
        return repository.findByAuthorId(id);
    }

    @Override
    public Page<Contents> findAllByAuthorId(Integer id, Pageable pageable) {
        return repository.findByAuthorId(id, pageable);
    }

    @Override
    public Page<Contents> findAllByAuthorIdAndType(Integer id, String type, Pageable pageable) {
        return repository.findByAuthorIdAndTypeEquals(id, type, pageable);
    }

    @Override
    public List<Contents> findAllByAuthorIdAndType(Integer id, String type) {
        return repository.findByAuthorIdAndTypeEquals(id, type);
    }
}
