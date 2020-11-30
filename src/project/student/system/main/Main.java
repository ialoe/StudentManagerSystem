package project.student.system.main;

import project.student.system.bean.Student;
import project.student.system.controller.Controller;
import project.student.system.myexception.IllegalCapacityException;
import project.student.system.myexception.OverflowArrayCapacityException;
import project.student.system.tools.StudentFilter;
import project.student.system.tools.impl.AcceptStudentAge;
import project.student.system.tools.impl.AcceptStudentScore;

/**
 */
public class Main {
    public static void main(String[] args) throws IllegalCapacityException, OverflowArrayCapacityException {
        Controller controller = new Controller(2);

        controller.add(new Student("彭于晏", 45, '男', 16.0F));
        controller.add(new Student("吴彦祖", 36, '女', 36.0F));
        controller.add(new Student("陈冠希", 85, 'X', 95.0F));
        controller.add(new Student("胡歌", 68, '男', 68.0F));
        controller.add(new Student("张一山", 15, 'X', 45.0F));
        controller.add(new Student("陈赫", 25, '女', 38.0F));
        controller.add(new Student("鹿晗", 15, '男', 75.0F));
        controller.add(new Student("易烊千玺", 37, 'X', 18.0F));
        controller.add(new Student("吴亦凡", 52, '女', 38.0F));

        controller.show();

        System.out.println("------------");

        controller.remove(3);

        controller.show();

        System.out.println(controller.size());

        System.out.println("------------");

//        controller.modify(6);

        System.out.println("------------");

        controller.accept(new AcceptStudentAge());

        System.out.println("------------");

        controller.accept(new AcceptStudentScore());

        System.out.println("------------");

        controller.sortOfTools();

        System.out.println("------------");

        controller.show();
    }
}








