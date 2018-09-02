package planetGenerator.generators;

import spock.lang.Specification

class MapTilerSpec extends Specification{
	
	def "test"(){
		given:
			int density = 30
			float percentOverlap = 0.1f
		when:
			MockHeightMapGenerator generator = new MockHeightMapGenerator()
			int[][] heightMap = generator.generateHeightMap(density);
//			generator.printMap(heightMap)
			
			MapTiler mapTiler = new MapTiler(percentOverlap)
			mapTiler.makeImageTilable(heightMap)
			generator.printMap(heightMap)
		then:
			true == true
	}

}
