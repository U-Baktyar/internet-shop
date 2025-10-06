package shop.service;

import org.springframework.stereotype.Service;

import java.util.Set;

public interface CategoryService {


    Set<String> findAllNames();
}
