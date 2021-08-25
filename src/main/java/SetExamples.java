import java.util.HashSet;
import java.util.Set;

public class SetExamples {
    public void exampleHashSet(){
        Set<String> students = new HashSet<>();
        System.out.println(students.isEmpty());
        if (students.isEmpty()){
            System.out.println("No students yet");
        }

        students.add("Santa");
        students.add("Zlata");
        students.add("Jelena");

        if(students.contains("Zino")){
            System.out.println("Zino not found");
        }
        if (!students.contains("Santa")){
            System.out.println("Student did not do test");
        }

        for (String student : students){
            System.out.println(student);
        }
    }

    public void anotherExampleType(){
        HashSet<StudentResult>  results = new HashSet<>();

        results.add(new StudentResult("zino", 123));
        results.add(new StudentResult("vino", 1234));
        results.add(new StudentResult("bino", 456));
        results.add(new StudentResult("lido", 3));

        for (StudentResult studentResult : results){
            System.out.println(studentResult);
            processResult(studentResult);
            try {
                doSomethingImportant(studentResult);
            }catch (Exception ex){

            }
        }
    }

    private void doSomethingImportant(StudentResult studentResult) throws Exception {
    }

    private void processResult(StudentResult studentResult) {
    }
}
