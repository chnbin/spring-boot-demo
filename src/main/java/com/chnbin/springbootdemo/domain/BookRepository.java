package com.chnbin.springbootdemo.domain;

import org.springframework.data.jpa.repository.JpaRepository;

// 定義一個操作Book的數據層
public interface BookRepository extends JpaRepository<Book, Long>{

}
