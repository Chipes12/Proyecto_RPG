package rpg.characters;

public interface Combat {
	
	public abstract void attack();
	public abstract void defend();
	public abstract void heal();
	public abstract void setTarget();

}
