package planetGenerator.generators;

public class MapTiler {
	private float percent;
	
	public MapTiler(float percentOverlap){
		this.percent = percentOverlap;
	}
	
	
	public void makeImageTilable(int[][] map){
		int size = map.length;
		int overlapAmount = (int) (size * percent);
		
		if (overlapAmount >= size){
			return;
		}
		
		int[][] verticalOverlap = getVerticalOverlap(map, overlapAmount);
		blend(map, verticalOverlap);
		
		int[][] horizontalOverlap = getHorizontalOverlap(map, overlapAmount);
		blend(map, horizontalOverlap);
		
	}

	private int[][] getVerticalOverlap(int[][] map, int overlapAmount) {
		int size = map.length;
		int[][] verticalOverlap = new int[overlapAmount][size];
		int start = size-overlapAmount;
		
		for (int x=start, i=1; x<map.length; x++, i++){
			int[] column = map[x].clone();
			//invert the overlap
			verticalOverlap[overlapAmount-i] = column;
		}
		
		return verticalOverlap;
	}

	private int[][] getHorizontalOverlap(int[][] map, int overlapAmount) {
		int size = map.length;
		int[][] horizontalOverlap = new int[size][overlapAmount];
		int start = size-overlapAmount;
		
		for (int y=start, i=1; y<map.length; y++, i++){
			for (int x=0; x<map.length; x++){
				//invert the overlap
				horizontalOverlap[x][overlapAmount-i] = map[x][y];
			}
		}
		
		return horizontalOverlap;
	}
	
	private void blend(int[][] map, int[][] overlap) {
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
