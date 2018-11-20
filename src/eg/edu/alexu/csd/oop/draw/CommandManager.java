package eg.edu.alexu.csd.oop.draw;

import java.util.ArrayList;
import java.util.Stack;

public class CommandManager {

	private Stack<Command> undos = new Stack<Command>();
	private ArrayList<Command> undoLimit = new ArrayList<Command>();
    private Stack<Command> redos = new Stack<Command>();

    public CommandManager() {}

    public void executeCommand(Command c) {
    	if (undos.size() == 20) {
    		for (int i = 0; i < 20; i ++) {
    			undoLimit.add(undos.pop());

    			if (i == 19) {
    				undoLimit.remove(undoLimit.size() - 1);
    			}
    		}

    		for (int i = 0; i < 19; i ++) {
    			undos.push(undoLimit.get(undoLimit.size()-1-i));
    		}

    	}

    	c.execute();
    	undos.push(c);
        redos.clear();
    }

    public boolean isUndoAvailable() {
        return !undos.empty();
    }

    public void undo() {
    	assert(!undos.empty());
        Command command = undos.pop();
        command.undo();
        redos.push(command);
    }

    public boolean isRedoAvailable() {
        return !redos.empty();
    }

    public void redo() {
    	assert(!redos.empty());
        Command command = redos.pop();
        command.execute();
        undos.push(command);
    }

}
