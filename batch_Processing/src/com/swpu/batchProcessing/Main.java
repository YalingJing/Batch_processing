package com.swpu.batchProcessing;

import java.io.*;
import java.text.Collator;
import java.util.*;

public class Main {
    public static void main(String args[]) throws IOException {

        //按照批处理的风格，四个过程顺序执行，执行完一个后再执行下一个，如果一个过程出错，后面的将不能进行，数据以整体的方式进行传递

        //输入
        String []a;
        a=input();

        //移位
        List<String> s;
        s = shifter(a);

        //排序
        List<String> s1;
        s1 =sort(s);

        //输出
        ouput(s1);

    }


    //输入
    public static String[] input() throws IOException {
        System.out.println("输入：");
        int i=0,j=0;
        FileInputStream inputStream = new FileInputStream("D:\\大三上学习文档\\软件体系结构\\软件体系结构实验\\input.txt");
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
        while((bufferedReader.readLine()) != null){
            i++;
        }
        String[] result = new String[i];
        String str = null;
        FileInputStream inputStream1 = new FileInputStream("D:\\大三上学习文档\\软件体系结构\\软件体系结构实验\\input.txt");
        BufferedReader bufferedReader1 = new BufferedReader(new InputStreamReader(inputStream1));
        while((str = bufferedReader1.readLine()) != null) {
            result[j]=str;
            System.out.println(str);
            j++;
        }
        return result;
    }

    //对每行的数据进行循环移位
    public static List<String> shifter(String[] a) {
        System.out.println("循环移位后的结果：");
        int i;
        List<String> l = new ArrayList<>();
        List<String> l1 = new ArrayList<>();
        for(i=0;i<a.length;i++){
            //分词
            String string = a[i];
            String word = "";
            int j = 0;
            while(j < string.length()){
                if(string.charAt(j) != ' '){
                    word += string.charAt(j);
                }
                else{
                    l.add(word);
                    word = "";
                }
                j++;
            }
            if (word.length() > 0) {
                l.add(word);
            }
            //移位
            String templine = "",str = "";
            int len = l.size();
            int k = l.size();
            int p;
            while(len > 0){
                for(p = 0;p < k;p++){
                    templine = templine + l.get(p)+" ";
                }
                for(p = 0;p < k; p++){
                    if(p == 0){
                        str = l.get(p);
                    }
                    if(p!=0){
                        l.set(p-1,l.get(p));
                    }
                }
                l.set(k-1,str);
                l1.add(templine);
                System.out.println(templine);
                templine="";
                len--;
            }
            l.clear();
        }
        return l1;
    }



    //根据字母顺序进行排序
    public static List<String> sort(List<String> a) {
        class AlphaabetizerComparator implements Comparator<String> {
            private Collator collator;
            AlphaabetizerComparator(){
                this.collator = Collator.getInstance(Locale.ENGLISH);
            }
            @Override
            public int compare(String o1, String o2) {
                return this.collator.compare(o1, o2);
            }
        }
        //按字母表排序
        Collections.sort(a, new AlphaabetizerComparator());
        //对排序后的数据进行输出
        return a;
    }

    //输出
    public static void ouput(List<String> a) throws IOException {
        File outfile = new File("D:\\大三上学习文档\\软件体系结构\\软件体系结构实验\\output.txt");
        PrintWriter pw = new PrintWriter(outfile);
        System.out.print("输出结果为："+"\n");
        for (int i = 0; i < a.size(); i++) {
            String temp = a.get(i);
            System.out.print(a.get(i)+"\n");
            pw.write(temp);
            pw.write("\n");
        }
        System.out.println();
    }
}
