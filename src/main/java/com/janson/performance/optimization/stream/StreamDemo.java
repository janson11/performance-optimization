package com.janson.performance.optimization.stream;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @Description: stream示例
 * @Author: Janson
 * @Date: 2020/9/28 19:43
 **/
public class StreamDemo {

    public static void main(String[] args) {

        List<Student> studentList = new ArrayList<>();
        studentList.add(new Student(161, "男"));
        studentList.add(new Student(159, "男"));
        studentList.add(new Student(162, "男"));
        studentList.add(new Student(161, "男"));
        studentList.add(new Student(161, "男"));
        studentList.add(new Student(163, "女"));
        studentList.add(new Student(161, "男"));
        studentList.add(new Student(161, "男"));
        studentList.add(new Student(164, "男"));
        studentList.add(new Student(161, "男"));
        studentList.add(new Student(161, "男"));
        studentList.add(new Student(165, "男"));
        studentList.add(new Student(161, "女"));
        System.out.println("排序前：" + studentList);
//        originalFilter(studentList);
//        streamFilter(studentList);
        parallelStreamFilter(studentList);
    }


    public static void originalFilter(List<Student> studentList) {
        Map<String, List<Student>> stuMap = new HashMap<String, List<Student>>();
        for (Student stu : studentList) {
            if (stu.getHeight() > 160) {
                if (stuMap.get(stu.getSex()) == null) {
                    List<Student> list = new ArrayList<>();
                    list.add(stu);
                    stuMap.put(stu.getSex(), list);
                } else {
                    stuMap.get(stu.getSex()).add(stu);
                }
            }
        }
        System.out.println("排序后：" + stuMap);
    }

    public static void streamFilter(List<Student> studentList) {
        Map<String, List<Student>> collect = studentList.stream().filter((Student s) -> s.getHeight() > 160).collect(Collectors.groupingBy(Student::getSex));
        System.out.println("排序后：" + collect);
    }

    public static void parallelStreamFilter(List<Student> studentList) {
        Map<String, List<Student>> collect = studentList.parallelStream().filter((Student s) -> s.getHeight() > 160).collect(Collectors.groupingBy(Student::getSex));
        System.out.println("排序后：" + collect);
    }


}
