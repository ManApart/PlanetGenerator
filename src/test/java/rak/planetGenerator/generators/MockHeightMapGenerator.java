package rak.planetGenerator.generators;

public class MockHeightMapGenerator extends HeightMapGenerator {
	
	public int[][] generateHeightMap(int density) {
		int[][] heightMap = new int[density][density];
		
		for (int x=0; x<density; x++){
			for (int y=0; y<density; y++){
				int amount = x*x + y*y;
				heightMap[x][y] = amount;
			}
		}
		return heightMap;
	}
	
	public void printMap(int[][] map){
		for (int y=0; y<map[0].length; y++){
			String out = "";
			for (int x=0; x<map.length; x++){
				out += map[x][y] + ", ";
			}
			System.out.println(out);
		}
		System.out.println("--------------------------------------------------\n\n");
	}

}
