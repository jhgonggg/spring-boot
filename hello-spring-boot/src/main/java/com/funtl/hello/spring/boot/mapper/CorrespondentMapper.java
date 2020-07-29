package com.funtl.hello.spring.boot.mapper;

import com.funtl.hello.spring.boot.entity.Correspondent;
import tk.mybatis.mapper.additional.insert.InsertListMapper;
import tk.mybatis.mapper.common.Mapper;

public interface CorrespondentMapper extends Mapper<Correspondent>, InsertListMapper<Correspondent> {
}