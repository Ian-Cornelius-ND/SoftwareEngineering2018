package edu.nd.se2018.homework.hwk3;

import org.junit.jupiter.api.Test;

class MainTest {

	@Test
	public void test() {
		Race race2 = new Race(1);
		race2.enrollHorse("Ralph", 0, 0, new EarlySprintStrategy());
		race2.enrollHorse("Bob", 1, 1, new SlowStartStrategy());
		race2.enrollHorse("Shai", 1, 1, new SteadyRunStrategy());
		assert (race2.race()).equals("Shai wins!");

		Race race3 = new Race(-1);
		race3.enrollHorse("Alpha", 0, 0, new EarlySprintStrategy());
		race3.enrollHorse("Beta", 1, 0, new SlowStartStrategy());
		assert (race3.race()).equals("Alpha wins!");
		
		Race race = new Race(5);
		race.enrollHorse("", 0, 20, new EarlySprintStrategy());
		race.enrollHorse("", 0, 20, new SlowStartStrategy());
		race.enrollHorse("", 0, 20, new SteadyRunStrategy());
		assert (race.race()).equals(" wins!");
	}

}
