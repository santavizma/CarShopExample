import java.util.HashSet;
import java.util.TreeSet;

public class TreeSetExample {

    public void firstExampleTreeSet() {
        TreeSet<String> students = new TreeSet<>();
        students.add("Eve");
        students.add("Baiba");
        students.add("Zlata");

        for (String student : students) {
            System.out.println(student);
        }

        HashSet<StudentResult> studentResults = new HashSet<>(); //TreeSet could not read
        studentResults.add(new StudentResult("zino", 123));
        studentResults.add(new StudentResult("vino", 1234));
        studentResults.add(new StudentResult("bino", 456));
        studentResults.add(new StudentResult("lino", 3));

        for (StudentResult studentResult : studentResults) {
            System.out.println(studentResult);
            processResult(studentResult);
        }
//        TreeSet<StudentResultComparable> studentResults = new TreeSet<>(); //TreeSet could not read
//        studentResults.add(new StudentResultComparable("zino", 123));
//        studentResults.add(new StudentResultComparable("vino", 1234));
//        studentResults.add(new StudentResultComparable("bino", 456));
//        studentResults.add(new StudentResultComparable("lino", 3));

    }

    private void processResult(StudentResult studentResult) {
        if (studentResult.getScore() >= 100) {
            System.out.println("Your score is A");
        } else if (studentResult.getScore() < 100){
            System.out.println("Your score is B");
        }else{
        System.out.println("Have no score");
    }

    }
}
//        switch (studentResult) {
//            case A:
//                System.out.println("Excellent Result");
//                break;
//            case B:
//                System.out.println("Good Result");
//                break;
//            case C:
//                System.out.println("Average Result");
//                break;
//            case D:
//                System.out.println("Low Result");
//                break;
//            default:
//                System.out.println("No Result");
//
//        }

