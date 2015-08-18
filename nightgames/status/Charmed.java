package nightgames.status;

import java.util.HashSet;

import nightgames.characters.Attribute;
import nightgames.characters.Character;
import nightgames.characters.Emotion;
import nightgames.characters.Trait;
import nightgames.combat.Combat;

public class Charmed extends Status {
	private int duration;
	public Charmed(Character affected) {
		super("Charmed", affected);
		if(affected.has(Trait.PersonalInertia)){
			duration = 8;
		}else{
			duration = 5;
		}
		flag(Stsflag.charmed);
	}

	@Override
	public String describe() {
		if(affected.human()){
			return "You feel an irresistable attraction to her and can't imagine harming her.";
		}
		else{
			return affected.name()+" is looking at you like a lovestruck teenager.";
		}
	}

	@Override
	public boolean mindgames(){
		return true;
	}
	
	@Override
	public float fitnessModifier () {
		return - (2 + duration / 2.0f);
	}

	@Override
	public int mod(Attribute a) {
		return 0;
	}

	@Override
	public int regen(Combat c) {
		duration--;
		if(duration<=0){
			affected.removelist.add(this);
			affected.addlist.add(new Cynical(affected));
		}
		affected.emote(Emotion.horny,15);
		affected.loseWillpower(c, 1);
		return 0;
	}

	@Override
	public int damage(Combat c, int x) {
		return 0;
	}

	@Override
	public double pleasure(Combat c, double x) {
		return 0;
	}

	@Override
	public String initialMessage(Combat c, boolean replaced) {
		return String.format("%s now charmed.\n", affected.subjectAction("are", "is"));
	}

	@Override
	public int weakened(int x) {
		return 0;
	}

	@Override
	public int tempted(int x) {
		return 3;
	}

	@Override
	public int evade() {
		return 0;
	}

	@Override
	public int escape() {
		return -10;
	}

	@Override
	public int gainmojo(int x) {
		return 0;
	}

	@Override
	public int spendmojo(int x) {
		return 0;
	}
	@Override
	public int counter() {
		return -10;
	}

	@Override
	public int value() {
		return 0;
	}
	@Override
	public Status instance(Character newAffected, Character newOther) {
		return new Charmed(newAffected);
	}
}
