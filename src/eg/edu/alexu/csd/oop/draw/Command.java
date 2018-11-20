package eg.edu.alexu.csd.oop.draw;

public interface Command {
	
	public void execute();
    public void undo();
    public void redo();

}
