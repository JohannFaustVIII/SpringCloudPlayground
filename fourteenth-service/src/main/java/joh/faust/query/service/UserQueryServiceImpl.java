package joh.faust.query.service;

import joh.faust.model.User;
import joh.faust.query.repository.UserReadRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@RequiredArgsConstructor
@Service
public class UserQueryServiceImpl implements UserQueryService {

    private final UserReadRepository repository;

    @Override
    public List<User> findAll() {
        return StreamSupport
                .stream(repository.findAll().spliterator(), false)
                .collect(Collectors.toList());
    }
}
