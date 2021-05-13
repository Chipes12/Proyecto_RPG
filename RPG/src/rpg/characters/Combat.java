package rpg.characters;
import rpg.specialities.Skill;

public interface Combat {
	
	public abstract void attack(Skill skill);
	public abstract void defend(Skill skill);
	public abstract void heal(Skill skill);
	public abstract void setTarget();

}
