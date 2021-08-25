public class StudentResultComparable extends StudentResult implements Comparable<StudentResultComparable> {

    //for example purpose to see how to override so the treeSet would work with reading all inputs

    StudentResultComparable(String name, int score){
        super(name, score);
    }

    @Override
    public int compareTo(StudentResultComparable otherClass){ //compareTo method helps to compare and use TreeSet
        return otherClass.getScore() < getScore() ? 0: 1;
    }
}
