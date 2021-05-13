package rpg.characters;
import rpg.specialities.Skill;

public interface Combat {
	
	public abstract boolean attack(Skill skill);
	public abstract boolean defend(Skill skill);
	public abstract boolean heal(Skill skill);

}
