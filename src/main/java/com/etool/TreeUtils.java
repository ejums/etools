package com.etool;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.function.BiConsumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 * 树工具类
 * @author <a href="mailto:hjm0928s@gmail.com">ejums</a>
 * @see tool.TreeUtilExampleTest
 */
@SuppressWarnings("JavadocReference")
public class TreeUtils {
    /**
     * 列表转换为树(非递归)
     *
     * @param list      数据列表
     * @param idColumn  顶层字段
     * @param top       顶层数据
     * @param pidColumn 父级引用字段
     * @param sortBy    排序方式
     * @param generator 父节点添加子节点的逻辑
     * @param <T>       T 范型
     * @return 顶级数据
     * @date 2019-11-01
     * @since 1.0
     */
    public static <T> List<T> buildTree(List<T> list, Predicate<T> top, Function<T, String> idColumn,
                                        Function<T, String> pidColumn, Comparator<T> sortBy,
                                        BiConsumer<T, List<T>> generator) {
        List<T> topList = list.parallelStream().filter(top).collect(Collectors.toList());
        if (sortBy != null) {
            topList.sort(sortBy);
        }
        Map<String, List<T>> childMap = list.stream().filter(v -> !top.test(v)).parallel()
                .collect(Collectors.groupingBy(pidColumn));
        List<T> children = new ArrayList<>();
        List<T> temp = new ArrayList<>(topList);
        while (!temp.isEmpty()) {
            temp.forEach(v -> {
                String apply = idColumn.apply(v);
                if (apply != null) {
                    List<T> child = childMap.get(apply);
                    if (child != null) {
                        if (sortBy != null) {
                            child.sort(sortBy);
                        }
                        generator.accept(v, child);
                        children.addAll(child);
                    }
                }
            });
            temp.clear();
            temp.addAll(children);
            children.clear();
        }
        return topList;
    }
}
