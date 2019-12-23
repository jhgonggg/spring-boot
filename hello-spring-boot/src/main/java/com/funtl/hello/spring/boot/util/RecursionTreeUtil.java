package com.funtl.hello.spring.boot.util;


import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.apache.commons.collections4.CollectionUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class RecursionTreeUtil {
    /**
     * Gets child tree nodes.
     *
     * @param list     the list
     * @param parentId the parent id
     * @return the child tree nodes
     */
    public static List<TreeNode> getChildTreeNodes(List<TreeNode> list, Long parentId) {
        List<TreeNode> returnList = new ArrayList<>();

        for (TreeNode treeNode : list) {
            if (treeNode.getPid() == null) {
                continue;
            }

            if (Objects.equals(treeNode.getPid(), parentId)) {
                recursionFn(list, treeNode);
                returnList.add(treeNode);
            }
        }
        return returnList;
    }

    /**
     * 递归列表
     */
    private static void recursionFn(List<TreeNode> list, TreeNode node) {
        List<TreeNode> childList = getChildList(list, node);
        if (CollectionUtils.isEmpty(childList)) {
            return;
        }
        node.setChildren(childList);
        for (TreeNode tChild : childList) {
            recursionFn(list, tChild);
        }
    }

    /**
     * 得到子节点列表
     */
    private static List<TreeNode> getChildList(List<TreeNode> list, TreeNode t) {
        List<TreeNode> tList = new ArrayList<>();

        for (TreeNode treeNode : list) {
            if (Objects.isNull(treeNode.getPid())) {
                continue;
            }
            if (Objects.equals(treeNode.getPid(), t.getId())) {
                tList.add(treeNode);
            }
        }
        return tList;
    }
}
