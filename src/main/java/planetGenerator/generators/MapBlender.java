package planetGenerator.generators;

public class MapBlender {
	private int[][] map;
	
	public MapBlender(int[][] map) {
		this.map = map.clone();
	}
	
	public int[][] blend(int[][] overlap) {
		boolean xIsPrimary = xAxisIsShorterThanYAxis(overlap);
		int overlapAmount = getShorterLength(overlap);
		int fullAmount = getLongerLength(overlap);
		
		for (int primary=0; primary<overlapAmount; primary++){
			
			float originalPercent = (primary+1) / (float) overlapAmount;
			float overlapPercent = 1 - originalPercent;
			
			if (originalPercent == 1){
				continue;
			}
			
			for (int secondary=0; secondary<fullAmount; secondary++){
				
				int x = xIsPrimary ? primary : secondary;
				int y = xIsPrimary ? secondary : primary;
				
				int original = map[x][y];
				int overlapped = overlap[x][y];
				int blended = (int) (original*originalPercent + overlapped*overlapPercent);
				
				map[x][y] = blended;
			}
		}
		return map;
	}

	private int getShorterLength(int[][] overlap) {
		return Math.min(overlap.length, overlap[0].length);
	}
	
	private int getLongerLength(int[][] overlap) {
		return Math.max(overlap.length, overlap[0].length);
	}

	private boolean xAxisIsShorterThanYAxis(int[][] overlap) {
		return overlap.length < overlap[0].length;
	}

}
