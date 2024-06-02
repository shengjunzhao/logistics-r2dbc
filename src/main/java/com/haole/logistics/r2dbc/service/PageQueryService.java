package com.haole.logistics.r2dbc.service;

import com.haole.logistics.r2dbc.common.PagedResultVO;
import io.r2dbc.spi.Row;
import io.r2dbc.spi.RowMetadata;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.r2dbc.core.R2dbcEntityTemplate;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.function.BiFunction;

/**
 * ClassName: PageQueryService
 *
 * @description:
 * @author: shengjunzhao
 * @since: 2024/6/1 17:34
 */
@Service
public class PageQueryService {

    private static final Logger logger = LoggerFactory.getLogger(PageQueryService.class);
    private R2dbcEntityTemplate template;

    public <T> Mono<PagedResultVO<T>> pageQuery(String baseSql, Pageable pageable,Class<T> targetClass) {
        String countSql = "select count(*) from (" + baseSql + ") a";
        StringBuilder pageSb = new StringBuilder();
        pageSb.append(baseSql);
        if (null != pageable.getSort() && pageable.getSort().stream().findAny().isPresent()) {
            pageSb.append("order by");
            for (Sort.Order order : pageable.getSort().toList()) {
                pageSb.append(order.getProperty()).append(" ").append(order.getDirection().name()).append(",");
            }
        }
        String pageSql = pageSb.toString();
        if (",".equals(pageSql.substring(pageSql.length() - 1))) {
            pageSql = pageSql.substring(0, pageSql.length() - 1);
        }
        pageSql = pageSql + " limit " + pageable.getOffset() + "," + pageable.getPageSize();
        String finalPageSql = pageSql;
        return template.getDatabaseClient().sql(countSql)
                .map((r, rd) -> r.get(0, Long.class))
                .first()
                .defaultIfEmpty(0L)
                .flatMap(total -> {
                    return template.getDatabaseClient().sql(finalPageSql)
                            .map(new BiFunction<Row, RowMetadata, T>() {
                                @Override
                                public T apply(Row row, RowMetadata meta) {
                                    return template.getConverter().read(targetClass, row, meta);
                                }
                            })
                            .all()
                            .collectList()
                            .map(list -> {
                                PagedResultVO<T> pagedResult = new PagedResultVO(list, pageable.getPageNumber() + 1, pageable.getPageSize(), total);
                                return pagedResult;
                            });
                });
    }

    @Autowired
    public void setTemplate(R2dbcEntityTemplate template) {
        this.template = template;
    }
}
