package tool;

import java.util.List;


/**
 * 树结点
 */
public class TreeNode {
    /**
     * id
     */
    private String id;
    /**
     * 父级id
     */
    private String pid;
    /**
     * code
     */
    private String code;
    /**
     * 层级
     */
    private int level;
    /**
     * order code
     */
    private int orderCode;
    private List<TreeNode> children;
    /**
     * 是否为叶子节点
     */
    private boolean leaf;
    /**
     * 是否为当前层级首节点
     */
    private boolean first;
    /**
     * 是否为当前层级尾节点
     */
    private boolean end;
    /**
     * 层级code
     */
    private String hierarchyCode;

    public TreeNode(){}

    public TreeNode(String id, String pid, String code, int orderCode) {
        this.id = id;
        this.pid = pid;
        this.code = code;
        this.orderCode = orderCode;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public int getOrderCode() {
        return orderCode;
    }

    public void setOrderCode(int orderCode) {
        this.orderCode = orderCode;
    }

    public List<TreeNode> getChildren() {
        return children;
    }

    public void setChildren(List<TreeNode> children) {
        this.children = children;
    }

    public boolean isLeaf() {
        return leaf;
    }

    public void setLeaf(boolean leaf) {
        this.leaf = leaf;
    }

    public boolean isFirst() {
        return first;
    }

    public void setFirst(boolean first) {
        this.first = first;
    }

    public boolean isEnd() {
        return end;
    }

    public void setEnd(boolean end) {
        this.end = end;
    }

    public String getHierarchyCode() {
        return hierarchyCode;
    }

    public void setHierarchyCode(String hierarchyCode) {
        this.hierarchyCode = hierarchyCode;
    }
}
