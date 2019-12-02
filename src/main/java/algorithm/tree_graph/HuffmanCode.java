package main.java.algorithm.tree_graph;

import java.io.*;
import java.util.*;

/**
 * @Author: M˚Haonan
 * @Date: 2019-04-27 15:30
 * @Description: 哈夫曼编码的实现代码
 */
class Node implements Comparable<Node>{
    //权(表示一个字符出现的次数)
    int weight;
    //所存字符的asc编码
    Byte data;
    Node left;
    Node right;
    public Node(int weight, Byte data){
        this.weight = weight;
        this.data = data;
    }

    @Override
    public int compareTo(Node o) {
        return this.weight - o.weight;
    }

    @Override
    public String toString() {
        return "Node{" +
                "weight=" + weight +
                ", data=" + data +
                '}';
    }
}

public class HuffmanCode {

    public static void main(String[] args) {
//        String str = "我爱你";
//        byte[] bytes = str.getBytes();
//        //进行哈夫曼编码
//        byte[] b = huffmanZip(bytes);
//        //进行哈夫曼解码
//        byte[] newB = huffmanDecode(b, codeMap);
//        String str2 = new String(newB);
//        System.out.println(str2);
        String src = "1.pdf";
        String dst = "2.zip";
        fileZip(src, dst);
//        unzip(src, dst);
    }

    /**
     * 解压文件
     * @param src
     * @param dst
     */
    private static void unzip(String src, String dst){
        InputStream in = null;
        try {
            in = new FileInputStream(src);
            ObjectInputStream ois = new ObjectInputStream(in);
            byte[] data = (byte[]) ois.readObject();
            Map<Byte, String> map = (Map<Byte, String>) ois.readObject();
            ois.close();
            in.close();
            //解码
            byte[] bytes = huffmanDecode(data, map);
            OutputStream os = new FileOutputStream(dst);
            os.write(bytes);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }


    /**
     * 压缩指定文件到指定目录下
     * @param src
     * @param dst
     */
    private static void fileZip(String src, String dst){
        try {
            InputStream in = new FileInputStream(src);
            byte[] data = new byte[in.available()];
            in.read(data);
            in.close();
            byte[] zip = huffmanZip(data);
            OutputStream os = new FileOutputStream(dst);
            ObjectOutputStream obj = new ObjectOutputStream(os);
            obj.writeObject(zip);
            obj.writeObject(codeMap);
            obj.close();
            os.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    /**
     * 对字节数组进行哈夫曼解码
     * @param bytes
     * @param codeMap
     * @return
     */
    private static byte[] huffmanDecode(byte[] bytes, Map<Byte, String> codeMap) {
        //将字节数组转换为二进制字符串
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < bytes.length; i ++) {
            boolean flag = true;
            if (i == bytes.length - 1){
                flag = false;
            }
            sb.append(byteToBinaryStr(bytes[i], flag));
        }
        //反转codeMap中的k，v
        Map<String, Byte> map = new HashMap<>();
        codeMap.forEach((k, v)-> map.put(v, k));
        List<Byte> list = new ArrayList<>();
        //根据反转后的表寻找对应的字符asc码
        for (int i = 0; i < sb.length();){
            int count = 1;
            Byte b;
            while (true){
                b = map.get(sb.substring(i, i + count));
                if (b != null){
                    break;
                }
                count ++;
            }
            i = i + count;
            list.add(b);
        }
        byte [] b = new byte[list.size()];
        for (int i = 0; i < list.size(); i++) {
            b[i] = list.get(i);
        }
        return b;
    }

    /**
     * 将字节转换为8位二进制字符串
     * @param b
     * @return
     */
    private static String byteToBinaryStr(byte b, boolean flag){
        //如果为完整的8位字节
        if (flag){
            //和256或运算，可以将正数不够8位的补0
            String str = Integer.toBinaryString(b | 256);
            return str.substring(str.length() - 8);
        }else{
            return Integer.toBinaryString(b);
        }
    }



    /**
     * 进行哈夫曼编码
     * @param bytes
     * @return
     */
    private static byte[] huffmanZip(byte[] bytes) {
        //1. 先统计每个byte出现的次数，并放入一个集合中
        List<Node> nodes = getNodes(bytes);
        //2. 创建一颗哈夫曼树
        Node tree = createHuffmanTree(nodes);
        //3. 创建一个哈夫曼编码表
        Map<Byte, String> huffCodes = getCodes(tree);
        //4. 编码
        byte[] b = encode(bytes, huffCodes);
        return b;
    }

    /**
     * 对字节数组进行编码
     * @param bytes
     * @param huffCodes
     * @return
     */
    private static byte[] encode(byte[] bytes, Map<Byte, String> huffCodes) {
        StringBuilder sb = new StringBuilder();
        for (byte b : bytes) {
            sb.append(huffCodes.get(b));
        }
        //字节数组的长度
        int len = (sb.length() + 7) / 8;
        byte[] b = new byte[len];
        int index = 0;
        for (int i = 0; i < sb.length(); i+=8) {
            String byteStr = (i + 8) < sb.length() ? sb.substring(i, i + 8):sb.substring(i);
            //二进制字符串转化为10进制byte
            byte by = (byte) Integer.parseInt(byteStr, 2);
            b[index ++] = by;
        }
        return b;
    }

    /**
     * 根据哈夫曼树创建哈夫曼编码表
     * @param tree
     * @return
     */
    static StringBuilder sb = new StringBuilder();
    static Map<Byte, String> codeMap = new HashMap<>();
    private static Map<Byte, String> getCodes(Node tree) {
        if (tree == null) return null;
        getCodes(tree.left, "0", sb);
        getCodes(tree.right, "1", sb);
        return codeMap;
    }

    private static void getCodes(Node node, String s, StringBuilder sb) {
        if (node == null) return;
        //创建新的sb2来保存前面的字符串结果
        StringBuilder sb2 = new StringBuilder(sb);
        //将0或者1添加到字符串中
        sb2.append(s);
        //如果不是叶子节点(叶子结点必定有data)
        if (node.data == null){
            getCodes(node.left, "0", sb2);
            getCodes(node.right, "1", sb2);
        }else{
            //如果为叶子节点
            codeMap.put(node.data, sb2.toString());
        }
    }

    private static Node createHuffmanTree(List<Node> nodes) {
        //先升序排序
        Collections.sort(nodes);
        //进行迭代创建哈夫曼树
        while (nodes.size() > 1){
            Node left = nodes.get(0);
            Node right = nodes.get(1);
            Node parent = new Node(left.weight + right.weight, null);
            parent.left = left;
            parent.right = right;
            nodes.remove(left);
            nodes.remove(right);
            nodes.add(parent);
        }
        return nodes.get(0);
    }

    /**
     * 统计每个byte出现的次数，然后将结果放入Node的集合中
     * @param bytes
     * @return
     */
    private static List<Node> getNodes(byte[] bytes) {
        List<Node> nodes = new ArrayList<>();
        Map<Byte, Integer> map = new HashMap<>();
        for (byte b : bytes) {
            map.merge(b, 1, (a, b1) -> a + b1);
        }
        map.forEach((k ,v) ->{
            Node node = new Node(v, k);
            nodes.add(node);
        });
        return nodes;
    }

}
