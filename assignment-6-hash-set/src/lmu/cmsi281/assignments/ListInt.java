package lmu.cmsi281.assignments;

public interface ListInt {
    // Functionality of List object of ints
    int	 	size();
    int  	get(int index);
    void 	set(int index, int element);
    void 	add(int element);
    void 	insert(int index, int element);
    void 	remove(int index);
    boolean	contains(int element);
    String 	toString();
}
