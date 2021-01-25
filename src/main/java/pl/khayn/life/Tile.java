package pl.khayn.life;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class Tile {

	private final int x;
	private final int y;

}


