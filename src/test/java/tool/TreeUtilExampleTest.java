package tool;


import com.etool.TreeUtils;
import org.junit.Test;

import java.util.*;

public class TreeUtilExampleTest {
    public List<TreeNode> initNormalDate(){
        List<TreeNode> treeNodeList = new ArrayList<>();
        treeNodeList.add(new TreeNode("S1", null, "001", 1));
        treeNodeList.add(new TreeNode("S12", "S1", "0012", 2));
        treeNodeList.add(new TreeNode("S11", "S1", "0011", 1));
        treeNodeList.add(new TreeNode("S13", "S1", "0013", 3));
        treeNodeList.add(new TreeNode("S131", "S13", "00131", 1));
        treeNodeList.add(new TreeNode("S132", "S13", "00132", 2));
        treeNodeList.add(new TreeNode("S121", "S12", "00121", 1));
        treeNodeList.add(new TreeNode("S122", "S12", "00122", 2));
        treeNodeList.add(new TreeNode("S1221", "S122", "001221", 1));
        treeNodeList.add(new TreeNode("S111", "S11", "00111", 1));
        treeNodeList.add(new TreeNode("S112", "S11", "00112", 2));
        return treeNodeList;
    }
     private Map<String, Object> parseMap(String id, String pid, String code, int orderCode){
        HashMap<String, Object> map = new HashMap<>();
        map.put("id", id);
        map.put("pid", pid);
        map.put("code", code);
        map.put("orderCode", orderCode);
        return map;
    }

    public List<Map<String, Object>> initMapDate(){
        List<Map<String, Object>> treeNodeList = new ArrayList<>();
        treeNodeList.add(parseMap("S1", null, "001", 1));
        treeNodeList.add(parseMap("S12", "S1", "0012", 2));
        treeNodeList.add(parseMap("S11", "S1", "0011", 1));
        treeNodeList.add(parseMap("S13", "S1", "0013", 3));
        treeNodeList.add(parseMap("S131", "S13", "00131", 1));
        treeNodeList.add(parseMap("S132", "S13", "00132", 2));
        treeNodeList.add(parseMap("S121", "S12", "00121", 1));
        treeNodeList.add(parseMap("S122", "S12", "00122", 2));
        treeNodeList.add(parseMap("S1221", "S122", "001221", 1));
        treeNodeList.add(parseMap("S111", "S11", "00111", 1));
        treeNodeList.add(parseMap("S112", "S11", "00112", 2));
        return treeNodeList;
    }

    @Test
    public void simpleToNormalTreeTest(){
        List<TreeNode> list = initNormalDate();
        List<TreeNode> treeList = TreeUtils.buildTree(
                list,
                v -> v.getPid() == null,
                TreeNode::getId,
                TreeNode::getPid,
                Comparator.comparing(TreeNode::getCode),
                TreeNode::setChildren);
        System.out.println(treeList);
    }

    @Test
    public void superNormalTreeTest(){
        List<TreeNode> list = initNormalDate();
        final String splitChar = ">";
        List<TreeNode> treeList = TreeUtils.buildTree(
                list,
                v -> {
                    boolean isTop = v.getPid() == null;
                    if(isTop){
                        v.setLevel(1);
                        v.setHierarchyCode(v.getCode());
                    }
                    return isTop;
                },
                TreeNode::getId,
                TreeNode::getPid,
                Comparator.comparing(TreeNode::getCode),
                (item, children) -> {
                    children.forEach(v->{
                        v.setLevel(item.getLevel() + 1);
                        v.setLeaf(true);
                        v.setHierarchyCode(item.getHierarchyCode() + splitChar + v.getCode());
                    });
                    if(!children.isEmpty()){
                        children.get(0).setFirst(true);
                        children.get(children.size() - 1).setEnd(true);
                    }
                    item.setLeaf(false);
                    item.setChildren(children);
                });
        System.out.println(treeList);
    }

    @Test
    public void superMapTreeTest(){
        List<Map<String, Object>> mapList = initMapDate();
        final String splitChar = ">";
        List<Map<String, Object>> treeList = TreeUtils.buildTree(
                mapList,
                v -> {
                    boolean isTop = v.get("pid") == null;
                    if(isTop){
                        v.put("level", 1);
                        v.put("hierarchyCode", v.get("code"));
                    }
                    return isTop;
                },
                v->(String)v.get("id"),
                v->(String)v.get("pid"),
                Comparator.comparing(v->(int)v.get("orderCode")),
                (item, children) -> {
                    children.forEach(v->{

                        v.put("level", (int)item.get("level")+1);
                        v.put("leaf", true);
                        v.put("hierarchyCode", item.get("hierarchyCode") + splitChar + v.get("code"));
                    });
                    if(!children.isEmpty()){
                        children.get(0).put("first", true);
                        children.get(children.size() - 1).put("end", true);
                    }
                    item.put("leaf", false);
                    item.put("children", children);
                });
        System.out.println(treeList);
    }
}
