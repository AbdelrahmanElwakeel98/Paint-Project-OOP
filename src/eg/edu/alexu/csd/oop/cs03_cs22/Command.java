package eg.edu.alexu.csd.oop.cs03_cs22;

public interface Command {
	
	public void execute();
    public void undo();
    public void redo();

}
